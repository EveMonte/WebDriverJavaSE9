package pages;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumHQSignInPage extends AbstractPage {
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

    public SeleniumHQSignInPage(WebDriver driver){
        super(driver);
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

    public SeleniumHQHomePage signInToMetamask(User user) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath(keyPhraseInputLocator)));

        keyPhraseInput.sendKeys(user.getKeyPhrase());
        firstPasswordInput.sendKeys(user.getPassword());
        secondPasswordInput.sendKeys(user.getPassword());
        termsOfUseCheckBox.click();
        importWalletButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(openWalletButtonLocator)));

        openWalletButton.click();
        return new SeleniumHQHomePage(driver);
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}
