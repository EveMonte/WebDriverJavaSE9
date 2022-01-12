package pages.ropsten;

import driver.TabManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AbstractPage;

import java.time.Duration;

public class RopstenEthereum extends AbstractPage {
    private final String BASE_URL = "https://faucet.ropsten.be/";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//input[contains(@class, 'is-primary')]")
    private WebElement addressTextBox;

    @FindBy(xpath = "//button[contains(@class, 'is-link')]")
    private WebElement sendMeEthereum;

    public RopstenEthereum(WebDriver driver) {
        super(driver);
    }

    public void pasteAccountAddressAndGetEthereum(String accountAddress){
        logger.info("Paste Account Address And Get Ethereum");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@class, 'is-primary')]")));
        addressTextBox.sendKeys(accountAddress);
        sendMeEthereum.click();
    }


    public RopstenEthereum openPage() {
        logger.info("Open Ropsten Page");
        TabManager.createNewTabAndSwitchToIt(BASE_URL);
        return this;
    }
}
