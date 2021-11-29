package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SeleniumHQHomePage;
import pages.SeleniumHQRopstenEthereum;
import pages.SeleniumHQSignInPage;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class TestOfMetamask {
    private ChromeDriver driver;
    private ChromeOptions options;
    private DesiredCapabilities capabilities;
    private SeleniumHQHomePage homePage;
    private SeleniumHQSignInPage signInPage;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        options = new ChromeOptions();
        options.addExtensions(new File("D:\\extensions\\MetaMask.crx"));
        options.addArguments("start-maximized");
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        System.setProperty("webdriver.chrome.driver", "D:\\webdrivers\\chromedriver.exe");
        driver.get("chrome-extension://nkbihfbeogaeaoehlefnkodbefgpgknn/home.html");
        homePage = new SeleniumHQHomePage(driver);
        signInPage = new SeleniumHQSignInPage(driver);
    }

    @Test(description = "Transfer of Ethereum to another user's account")
    public void transferOfEthereum() throws InterruptedException {

        DecimalFormatSymbols symbol = DecimalFormatSymbols.getInstance();
        symbol.setDecimalSeparator('.');
        signInPage.openSignInWindow().signInToMetamask();
        homePage.createNewAccountAndSwapToMainAccount().changeNetwork().getCurrentBillsAndTransactionBetweenMyAccounts()
                .waitForNewBillValue();

        double expectedValueOfMainAccountBill = Double.parseDouble(homePage.getBillOfTheMainAccountBeforeTransaction()) -
                Double.parseDouble(homePage.getTotalSumToWriteOff());
        double expectedValueOfSecondAccountBill = Double.parseDouble(homePage.getBillOfTheSecondAccountBeforeTransaction()) +
                Double.parseDouble(homePage.getAmountToTransfer());
        double actualValueOfMainAccountBill = Double.parseDouble(homePage.getBillValueAfterTheTransaction());
        double actualValueOfSecondAccountBill = Double.parseDouble(homePage.getBillOfTheSecondAccountAfterTransaction());

        actualValueOfSecondAccountBill = Double.parseDouble(new DecimalFormat("#0.00000", symbol)
                .format(actualValueOfSecondAccountBill));
        actualValueOfMainAccountBill = Double.parseDouble(new DecimalFormat("#0.00000", symbol)
                .format(actualValueOfMainAccountBill));
        expectedValueOfMainAccountBill = Double.parseDouble(new DecimalFormat("#0.00000", symbol)
                .format(expectedValueOfMainAccountBill));
        expectedValueOfSecondAccountBill = Double.parseDouble(new DecimalFormat("#0.00000", symbol)
                .format(expectedValueOfSecondAccountBill));
        Assert.assertTrue(expectedValueOfMainAccountBill == actualValueOfMainAccountBill
                && expectedValueOfSecondAccountBill == actualValueOfSecondAccountBill);
    }

    @Test
    public void getEthereumFromRopsten() throws InterruptedException {
        signInPage.openSignInWindow().signInToMetamask();
        homePage.getAccountAddressToPasteIntoRopsten().changeNetwork();

        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
        newTab.get("https://faucet.ropsten.be/");
        ArrayList<String> allTabs = new ArrayList<String> (driver.getWindowHandles());

        (new SeleniumHQRopstenEthereum(driver)).pasteAccountAddressAndGetEthereum(homePage.getAccountAddress());

        driver.switchTo().window(allTabs.get(1));
        homePage.waitUntilTheBillChanges();

        Assert.assertTrue(Double.parseDouble(homePage.getBillAfterGettingEthereum()) > 0.3);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}
