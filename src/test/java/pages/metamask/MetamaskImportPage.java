package pages.metamask;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AbstractPage;

import java.time.Duration;

public class MetamaskImportPage extends AbstractPage {

    @FindBy(className = "currency-display-component__suffix")
    private WebElement tokenSuffix;

    @FindBy(id = "custom-address")
    private WebElement customAddress;

    @FindBy(xpath = "//button[contains(string(), 'Add')]")
    private WebElement addCustomTokenButton;

    @FindBy(xpath = "//button[contains(string(), 'Import')]")
    private WebElement importTokenButton;

    @FindBy(id = "custom-symbol")
    private WebElement customSymbol;


    public String importToken(String symbol) {
        logger.info("Import Token");
        fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.id("custom-address")));
        customAddress.sendKeys(Keys.CONTROL + "V");
        customSymbol.sendKeys(symbol);
        fluentWait.until(ExpectedConditions.elementToBeClickable(addCustomTokenButton));
        addCustomTokenButton.click();
        importTokenButton.click();
        wait.until(ExpectedConditions
                .invisibilityOfElementWithText(By.className("currency-display-component__text"), "1"));

        return tokenSuffix.getText();
    }

    public MetamaskImportPage(WebDriver driver) {
        super(driver);
    }

    public MetamaskTransactionPage setAmountToTransfer(){
        logger.info("Set Amount To Transfer");
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions
                .invisibilityOfElementWithText(By.className("currency-display-component__text"), "0"));
        return new MetamaskTransactionPage(driver);
    }
}
