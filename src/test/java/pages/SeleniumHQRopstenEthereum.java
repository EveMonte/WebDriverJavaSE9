package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumHQRopstenEthereum extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//input[contains(@class, 'is-primary')]")
    private WebElement addressTextBox;

    @FindBy(xpath = "//button[contains(@class, 'is-link')]")
    private WebElement sendMeEthereum;

    public SeleniumHQRopstenEthereum(WebDriver driver) {
        super(driver);
    }

    public SeleniumHQRopstenEthereum  pasteAccountAddressAndGetEthereum(String accountAddress){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@class, 'is-primary')]")));
        addressTextBox.sendKeys(accountAddress);
        sendMeEthereum.click();

        return this;
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}
