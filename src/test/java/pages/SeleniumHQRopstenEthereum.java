package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumHQRopstenEthereum {
    private ChromeDriver driver;

    @FindBy(xpath = "//input[contains(@class, 'is-primary')]")
    private WebElement addressTextBox;

    @FindBy(xpath = "//button[contains(@class, 'is-link')]")
    private WebElement sendMeEthereum;

    public SeleniumHQRopstenEthereum(ChromeDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public SeleniumHQRopstenEthereum  pasteAccountAddressAndGetEthereum(String accountAddress){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@class, 'is-primary')]")));
        addressTextBox.sendKeys(accountAddress);
        sendMeEthereum.click();

        return this;
    }
}
