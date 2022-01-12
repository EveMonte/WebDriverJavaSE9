package pages.metamask;
import model.BillInfo;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AbstractPage;
import transaction.TransactionSingleton;

public class MetamaskHomePage extends AbstractPage {

    private final String accountDropDownMenuClassName = "account-menu__icon";

    @FindBy(className = "currency-display-component__text")
    private WebElement billBeforeTransaction;

    @FindBy(className = "currency-display-component__suffix")
    private WebElement tokenSuffix;

    @FindBy(xpath = "//div[contains(@class, 'network-display--clickable')]")
    private WebElement networksDropDownMenu;

    @FindBy(xpath = "//span[contains(string(), 'Ropsten')]/parent::li")
    private WebElement testNetworkRopstenItem;

    @FindBy(className = accountDropDownMenuClassName)
    private WebElement accountDropDownMenu;

    @FindBy(xpath = "//div[@class='account-menu__accounts']/div[contains(@class, 'account-menu__item--clickable')]")
    private WebElement swapToTheAnotherAccount;

    @FindBy(xpath = "//div[@class='popover-header__title']/button")
    private WebElement closeNewsPopupWindow;

    @FindBy(xpath = "//div[contains(string(), 'Account 1')]/following-sibling::div/span[@class='currency-display-component__text']")
    private WebElement mainAccountBillValue;

    @FindBy(xpath = "//div[contains(string(), 'Счет 2')]/following-sibling::div/span[@class='currency-display-component__text']")
    private WebElement secondAccountBillValue;

    @FindBy(xpath = "//div[@class='menu-bar']/button")
    private WebElement accountOptionsButton;

    @FindBy(xpath = "//i[contains(@class, 'fa-qrcode')]/parent::button")
    private WebElement accountDetailsItem;

    @FindBy(className = "qr-code__address")
    private WebElement accountAddressText;

    @FindBy(className = "account-modal__close")
    private WebElement closeAccountOptions;

    @FindBy(xpath = "//a[contains(string(), 'Import')]")
    private WebElement importToken;

    @FindBy(className = "app-header__logo-container--clickable")
    private WebElement logo;

    public MetamaskHomePage(WebDriver driver) {
        super(driver);
    }

    public MetamaskHomePage clickOnLogo() {
        logger.info("Click On Logo");
        logo.click();
        return this;
    }

    public MetamaskHomePage closePopUps() {
        logger.info("Close Pop Ups");
        wait.until(ExpectedConditions.elementToBeClickable(closeNewsPopupWindow));
        closeNewsPopupWindow.click();
        return this;
    }

    public MetamaskHomePage closeAccountOptions(){
        logger.info("Close Account Options");
        closeAccountOptions.click();
        return this;
    }

    public MetamaskHomePage changeNetworkToRopsten() {
        logger.info("Change Network To Ropsten");

        networksDropDownMenu.click();
        testNetworkRopstenItem.click();
        return this;
    }

    public MetamaskAccountsPage openAccountsPage() {
        logger.info("Open Accounts Page");
        wait.until(ExpectedConditions.elementToBeClickable(accountDropDownMenu));
        accountDropDownMenu.click();
        return new MetamaskAccountsPage(driver);
    }

    public MetamaskTransactionPage getCurrentBills() throws InterruptedException {
        logger.info("Get Current Bills");
        fluentWait.until(ExpectedConditions.elementToBeClickable(accountDropDownMenu));
        accountDropDownMenu.click();
        Thread.sleep(3000);
        TransactionSingleton.getTransaction().getSenderAccountBill()
                .setBillValueBeforeTransaction(Double.parseDouble(mainAccountBillValue.getText()));
        TransactionSingleton.getTransaction().getReceiverAccountBill()
                .setBillValueBeforeTransaction(Double.parseDouble(secondAccountBillValue.getText()));

        swapToTheAnotherAccount.click();

        return new MetamaskTransactionPage(driver);
    }

    public String getAccountAddressToPasteIntoRopsten() {
        logger.info("Get Account Address To Paste Into Ropsten");
        openAccountsPage();
        (new MetamaskAccountsPage(driver)).createNewAccount();
        accountOptionsButton.click();
        accountDetailsItem.click();
        return accountAddressText.getText();
    }

    public MetamaskImportPage openImportPage() {
        logger.info("Open Import Page");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(string(), 'Import')]")));
        wait.until(ExpectedConditions.elementToBeClickable(importToken));
        importToken.click();
        return new MetamaskImportPage(driver);
    }

    public MetamaskNetworkPage openNetworksPage() {
        logger.info("Open Networks Page");
        wait.until(ExpectedConditions.elementToBeClickable(networksDropDownMenu));
        networksDropDownMenu.click();
        return new MetamaskNetworkPage(driver);
    }

    public BillInfo waitUntilTheBillChanges() {
        logger.info("Wait Until The Bill Changes");
        billWaiter.until(ExpectedConditions.elementToBeClickable(accountDropDownMenu));
        accountDropDownMenu.click();

        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(
                        "//div[contains(string(), 'Счет 2')]/following-sibling::div/span[@class='currency-display-component__text']"),
                "0.0"));
        BillInfo billInfo = new BillInfo();
        billInfo.setBillValueAfterTransaction(Double.parseDouble(secondAccountBillValue.getText()));
        return billInfo;
    }
}
