package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import java.util.ArrayList;

public class TabManager {
    private static ArrayList<String> allTabs;

    public static void createNewTabAndSwitchToIt(String url){
        WebDriver newTab = DriverSingleton.getDriver(null).switchTo().newWindow(WindowType.TAB);
        newTab.get(url);
        allTabs = new ArrayList<String> (DriverSingleton.getDriver(null).getWindowHandles());
        System.out.println("Amount of: " + allTabs.size());
    }

    public static void setAllTabs(){
        allTabs = new ArrayList<String> (DriverSingleton.getDriver(null).getWindowHandles());
    }

    public static void switchToTabDefinedByIndex(int index){
        DriverSingleton.getDriver(null).switchTo().window(allTabs.get(index));
    }
}
