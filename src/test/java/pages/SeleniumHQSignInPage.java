package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumHQSignInPage {
    private ChromeDriver driver;
    private String keyPhrase = "question until unfold mystery predict surge whale crumble dress arrange hello wife";
    private String randomPassword = "rm.dthnb";
    private final String keyPhraseInputLocator = "//div[contains(@class, 'first-time-flow__seedphrase')]/div/input";
    private final String openWalletButtonLocator = "//div[@class='end-of-flow']/button";
    private final String startButtonLocator = "//div[@class='welcome-page']/button";
    private final String importButtonLocator = "//div[@class='select-action__select-button']/button";
    private final String confirmButtonLocator = "//footer/button[2]";

    @FindBy(xpath = startButtonLocator)
    private WebElement startButton;

    @FindBy(xpath = "//div[@class='select-action__select-button']/button")
    private WebElement importButton;

    @FindBy(xpath = confirmButtonLocator)
    private WebElement confirmButton;

    @FindBy(xpath = keyPhraseInputLocator)
    private WebElement keyPhraseInput;

    @FindBy(id = "password")
    private WebElement firstPasswordInput;

    @FindBy(id = "confirm-password")
    private WebElement secondPasswordInput;

    @FindBy(xpath = "//div[contains(@class, 'first-time-flow__terms')]")
    private WebElement termsOfUseCheckBox;

    @FindBy(xpath = "//form[@class='first-time-flow__form']/button")
    private WebElement importWalletButton;

    @FindBy(xpath = openWalletButtonLocator)
    private WebElement openWalletButton;

    public SeleniumHQSignInPage(ChromeDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    public SeleniumHQSignInPage openSignInWindow(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath(startButtonLocator)));

        startButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath(importButtonLocator)));

        importButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath(confirmButtonLocator)));

        confirmButton.click();

        return this;
    }

    public SeleniumHQSignInPage signInToMetamask() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath(keyPhraseInputLocator)));

        keyPhraseInput.sendKeys(keyPhrase);
        firstPasswordInput.sendKeys(randomPassword);
        secondPasswordInput.sendKeys(randomPassword);
        termsOfUseCheckBox.click();
        importWalletButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(openWalletButtonLocator)));

        openWalletButton.click();
        return this;
    }
}
