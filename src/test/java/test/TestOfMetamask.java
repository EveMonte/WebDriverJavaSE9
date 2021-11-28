package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SeleniumHQHomePage;
import pages.SeleniumHQSignInPage;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class TestOfMetamask {
    private ChromeDriver driver;
    private ChromeOptions options;
    private DesiredCapabilities capabilities;
    private SeleniumHQHomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        options = new ChromeOptions();
        options.addExtensions(new File("MetaMask.crx"));
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver.get("chrome-extension://nkbihfbeogaeaoehlefnkodbefgpgknn/home.html");
        homePage = new SeleniumHQHomePage(driver);
    }

    @Test(description = "Transfer of Ethereum to another user's account")
    public void transferOfEthereum() throws InterruptedException {
        DecimalFormatSymbols symbol = DecimalFormatSymbols.getInstance();
        symbol.setDecimalSeparator('.');

        SeleniumHQSignInPage seleniumHQSignInPage = new SeleniumHQSignInPage(driver);
        seleniumHQSignInPage.openSignInWindow().signInToMetamask();


        homePage.createNewAccountAndSwapToMainAccount().changeNetworkAndGetCurrentBill().transactionBetweenMyAccounts();

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

    @Test(dependsOnMethods = { "transferOfEthereum" })
    public void GetEthereumFromRopsten(){
        homePage.copyToClipBoardAccountAddress();
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.TAB);
        newWindow.get("https://faucet.ropsten.be/");
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowser() {
        //driver.quit();
    }
}
