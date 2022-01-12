package pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage
{
    protected WebDriver driver;

    protected abstract AbstractPage openPage();
    protected final int WAIT_TIMEOUT_SECONDS = 10;
    protected final int WAIT_FOR_TRANSACTION_TIMEOUT_SECONDS = 300;
    protected WebDriverWait wait;
    protected WebDriverWait billWaiter;
    protected FluentWait<WebDriver> fluentWait;

    protected AbstractPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, WAIT_TIMEOUT_SECONDS), this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        billWaiter = new WebDriverWait(driver, Duration.ofSeconds(WAIT_FOR_TRANSACTION_TIMEOUT_SECONDS));
        fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .ignoring(ElementClickInterceptedException.class);


    }
}
