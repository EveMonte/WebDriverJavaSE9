package test;

import driver.DriverSingleton;
import driver.TabManager;
import model.BillInfo;
import model.Network;
import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import service.NetworkCreator;
import service.UserCreator;
import transaction.TransactionSingleton;
import util.TestListener;

import java.io.File;

@Listeners({TestListener.class})
    public class CommonConditions {
        private ChromeOptions options;
        private DesiredCapabilities capabilities;
        protected User createdUser;
        protected WebDriver driver;

        @BeforeMethod()
        public void setUp() {
            options = new ChromeOptions();
            options.addExtensions(new File("MetaMask.crx"));

            options.addArguments("start-maximized");
            capabilities = new DesiredCapabilities();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            driver = DriverSingleton.getDriver(capabilities);
            driver.get("chrome-extension://nkbihfbeogaeaoehlefnkodbefgpgknn/home.html");
            TabManager.setAllTabs();
            TabManager.switchToTabDefinedByIndex(0);
            NetworkCreator.withDataFromProperties();
            createdUser = UserCreator.withCredentialsFromProperty();
            TransactionSingleton.getTransaction().setAmountToTransfer(0.000005);
        }

        @AfterMethod(alwaysRun = true)
        public void stopBrowser() {
            DriverSingleton.closeDriver();
        }
    }

