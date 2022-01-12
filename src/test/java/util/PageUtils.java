package util;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class PageUtils {
    public static void acceptAlert(WebDriver driver){
        driver.switchTo().alert().accept();
    }

    public static void refreshPage(WebDriver driver){
        driver.navigate().refresh();
    }
}
