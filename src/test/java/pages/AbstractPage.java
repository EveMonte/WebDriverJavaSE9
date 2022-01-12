package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    protected final int WAIT_TIMEOUT_SECONDS = 10;
    protected final int WAIT_FOR_TRANSACTION_TIMEOUT_SECONDS = 300;
    protected final String PRIVATE_KEY = "73f4a43a07881ed7684557dea7818976702126eac7dff14b8618a7082a9b8f8f";
    protected WebDriverWait wait;
    protected WebDriverWait billWaiter;
    protected FluentWait<WebDriver> fluentWait;
    protected final Logger logger = LogManager.getRootLogger();


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
