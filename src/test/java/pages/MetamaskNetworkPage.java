package pages;

import network.NetworkSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import transaction.TransactionSingleton;

import java.time.Duration;

public class MetamaskNetworkPage extends AbstractPage {

    private final String accountDropDownMenuClassName = "account-menu__icon";

    @FindBy(id = "network-name")
    private WebElement networkName;

    @FindBy(xpath = "//div[text()='Localhost 8545']/parent::div")
    private WebElement localhost;

    @FindBy(xpath = "//div[text()='Импортировать счет']/parent::div")
    private WebElement importBill;

    @FindBy(xpath = "//button[text()='Удалить']")
    private WebElement removeNetwork;

    @FindBy(xpath = "//button[text()='Удалить']")
    private WebElement confirmRemoving;

    @FindBy(xpath = "//span[contains(string(), 'RPC')]/parent::li")
    private WebElement createNewNetwork;

    @FindBy(xpath = "//button[contains(string(), 'Добавить сеть')]")
    private WebElement addNetwork;

    @FindBy(className = "currency-display-component__text")
    private WebElement currentBillValueShort;

    @FindBy(id = "rpc-url")
    private WebElement newURLRPC;

    @FindBy(id = "private-key-box")
    private WebElement privateKeyBox;

    @FindBy(id = "chainId")
    private WebElement chainIdentifier;

    @FindBy(xpath = "//div[contains(@class, 'network-display--clickable')]")
    private WebElement networksDropDownMenu;

    @FindBy(className = accountDropDownMenuClassName)
    private WebElement accountDropDownMenu;

    @FindBy(className = "app-header__logo-container--clickable")
    private WebElement logo;

    @FindBy(xpath = "//button[contains(string(), 'Импорт')]")
    private WebElement saveImportBill;

    @FindBy(xpath = "//button[contains(string(), 'Сохранить')]")
    private WebElement saveNetworkButton;

    public MetamaskNetworkPage(WebDriver driver) {
        super(driver);
    }

    public MetamaskNetworkPage removeUserNetwork() {
        logger.info("Remove User Network");
        createNewNetwork.click();
        localhost.click();
        removeNetwork.click();
        confirmRemoving.click();

        return this;
    }

    public MetamaskNetworkPage createNewNetwork(){
        logger.info("Create New Network");
        fluentWait.until(ExpectedConditions.elementToBeClickable(addNetwork));
        addNetwork.click();
        networkName.sendKeys(NetworkSingleton.getNetwork().getNetworkName());
        chainIdentifier.sendKeys(NetworkSingleton.getNetwork().getChainIdentifier());
        newURLRPC.sendKeys(NetworkSingleton.getNetwork().getNewURLRPC());
        saveNetworkButton.click();
        return this;
    }

    public RemixEthereum importAccount(){
        logger.info("Import Account");
        accountDropDownMenu.click();
        importBill.click();
        privateKeyBox.sendKeys("73f4a43a07881ed7684557dea7818976702126eac7dff14b8618a7082a9b8f8f");
        saveImportBill.click();
        driver.navigate().refresh();
        logo.click();
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("currency-display-component__text"),"0"));

        TransactionSingleton.getTransaction().getReceiverAccountBill()
                .setBillValueAfterTransaction(Double.parseDouble(currentBillValueShort.getText()));

        return new RemixEthereum(driver);
    }
}
