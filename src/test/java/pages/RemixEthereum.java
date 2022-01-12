package pages;

import driver.TabManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.NetworkCreator;
import util.PageUtils;

import javax.print.DocFlavor;
import java.time.Duration;

public class RemixEthereum extends AbstractPage {
    private final String BASE_URL = "https://remix.ethereum.org";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//button[text() = 'GitHub']")
    private WebElement gitHubButton;

    @FindBy(xpath = "//input[@id = 'prompt_text']")
    private WebElement urlInput;

    @FindBy(xpath = "//select[@value = '0x089DfDf1D316ec11B24F65BFD3B223f455c4aFCD']")
    private WebElement activeOption;

    @FindBy(xpath = "//span[text() = 'github']/parent::div/parent::span/parent::div/parent::li")
    private WebElement githubFolder;

    @FindBy(xpath = "//span[text() = 'EveMonte']/parent::div/parent::span/parent::div/parent::li")
    private WebElement eveMonteFolder;

    @FindBy(xpath = "//span[text() = 'Contract']/parent::div/parent::span/parent::div/parent::li")
    private WebElement contractFolder;

    @FindBy(xpath = "//span[@id = 'modal-footer-ok']")
    private WebElement modalButtonOk;

    @FindBy(xpath = "//div[@id = 'verticalIconsKindsolidity']")
    private WebElement openSolidityCompilerButton;

    @FindBy(xpath = "//option[contains(string(), '5.16')]")
    private WebElement comboBoxOption;

    @FindBy(xpath = "//span[text() = 'Contract.sol']/parent::div/parent::span")
    private WebElement chooseFile;

    @FindBy(xpath = "//option[text() = 'Ether']")
    private WebElement etherOption;

    @FindBy(xpath = "//*[@class = 'contractNames_3rUxUe custom-select']")
    private WebElement contractComboBox;

    @FindBy(xpath = "//option[contains(string(), 'ERC20Token - ')]")
    private WebElement erc20Option;

    @FindBy(xpath = "//button[contains(string(), 'Deploy')]")
    private WebElement deployButton;

    @FindBy(id = "versionSelector")
    private WebElement versionsComboBox;

    @FindBy(id = "compileBtn")
    private WebElement compileButton;

    @FindBy(id = "verticalIconsKindudapp")
    private WebElement deployAndRunButton;

    @FindBy(id = "selectExEnvOptions")
    private WebElement environmentComboBox;

    @FindBy(id = "web3-mode")
    private WebElement web3ProviderOption;

    @FindBy(id = "unit")
    private WebElement unitComboBox;

    @FindBy(id = "value")
    private WebElement transactionValue;

    @FindBy(id = "remixTourSkipbtn")
    private WebElement skipButton;

    @FindBy(className = "modal-ok")
    private WebElement closeButton;

    @FindBy(id = "prompt_text")
    private WebElement endPoint;

    @FindBy(xpath = "//i/parent::button[@class = 'btn p-1 btn-secondary']")
    private WebElement copyToClipBoardButton;

    @FindBy(xpath = "//button[contains(string(), 'Sure')]")
    private WebElement sureButton;

    public RemixEthereum openPage() {
        TabManager.createNewTabAndSwitchToIt(BASE_URL);
        wait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        return this;
    }

    public RemixEthereum(WebDriver driver) {
        super(driver);
    }

    public RemixEthereum closePopUps(){
        logger.info("Close Pop Ups");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(string(), 'Sure')]")));
        sureButton.click();
        skipButton.click();
        return this;
    }

    public RemixEthereum getFileFromGitHub(String repoURL) {
        logger.info("Get File From GitHub");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text() = 'GitHub']")));

        gitHubButton.click();
        urlInput.sendKeys(repoURL);
        fluentWait.until(ExpectedConditions.elementToBeClickable(modalButtonOk));
        modalButtonOk.click();

        PageUtils.refreshPage(driver);
        PageUtils.acceptAlert(driver);

        fluentWait.until(ExpectedConditions.elementToBeClickable(githubFolder));
        githubFolder.click();
        eveMonteFolder.click();
        contractFolder.click();
        chooseFile.click();
        return this;
    }

    public RemixEthereum compileScript() {
        logger.info("Compile Script");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("mtk9")));
        openSolidityCompilerButton.click();
        versionsComboBox.click();
        comboBoxOption.click();
        compileButton.click();
        return this;
    }

    public MetamaskHomePage deployContract() {
        logger.info("Deploy Contract");
        deployAndRunButton.click();
        environmentComboBox.click();
        web3ProviderOption.click();

        fluentWait.until(ExpectedConditions.elementToBeClickable(By.id("modal-footer-ok")));
        endPoint.sendKeys(Keys.CONTROL + "A");

        endPoint.sendKeys(Keys.DELETE);
        endPoint.sendKeys(NetworkCreator.newURLRPC);
        driver.findElement(By.id("modal-footer-ok")).click();
        unitComboBox.click();
        etherOption.click();
        contractComboBox.click();
        erc20Option.click();

        fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@value = '0x089DfDf1D316ec11B24F65BFD3B223f455c4aFCD']")));
        deployButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i/parent::button[@class = 'btn p-1 btn-secondary']")));
        copyToClipBoardButton.click();
        TabManager.switchToTabDefinedByIndex(0);

        return new MetamaskHomePage(driver);
    }
}
