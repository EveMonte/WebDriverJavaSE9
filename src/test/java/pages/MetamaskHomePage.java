package pages;
import model.BillInfo;
import network.NetworkSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import transaction.TransactionSingleton;
import util.StringUtils;

import java.time.Duration;

public class MetamaskHomePage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private final String accountDropDownMenuClassName = "account-menu__icon";

    private String accountAddress;

    @FindBy(className = "currency-display-component__text")
    private WebElement billBeforeTransaction;

    @FindBy(className = "currency-display-component__suffix")
    private WebElement tokenSuffix;

    @FindBy(id = "network-name")
    private WebElement networkName;

    @FindBy(id = "custom-address")
    private WebElement customAddress;

    @FindBy(xpath = "//div[text()='Localhost 8545']/parent::div")
    private WebElement localhost;

    @FindBy(xpath = "//button[contains(string(), 'Add')]")
    private WebElement addCustomTokenButton;

    @FindBy(xpath = "//button[contains(string(), 'Import')]")
    private WebElement importTokenButton;

    @FindBy(xpath = "//div[text()='Импортировать счет']/parent::div")
    private WebElement importBill;

    @FindBy(xpath = "//a[contains(string(), 'Import')]")
    private WebElement importToken;

    @FindBy(xpath = "//button[text()='Удалить']")
    private WebElement removeNetwork;

    @FindBy(xpath = "//button[text()='Удалить']")
    private WebElement confirmRemoving;

    @FindBy(id = "rpc-url")
    private WebElement newURLRPC;

    @FindBy(id = "private-key-box")
    private WebElement privateKeyBox;

    @FindBy(id = "chainId")
    private WebElement chainIdentifier;

    @FindBy(id = "custom-symbol")
    private WebElement customSymbol;

    @FindBy(xpath = "//div[contains(@class, 'network-display--clickable')]")
    private WebElement networksDropDownMenu;

    @FindBy(xpath = "//span[contains(string(), 'Ropsten')]/parent::li")
    private WebElement testNetworkRopstenItem;

    @FindBy(xpath = "//span[contains(string(), 'RPC')]/parent::li")
    private WebElement createNewNetwork;

    @FindBy(xpath = "//button[contains(string(), 'Добавить сеть')]")
    private WebElement addNetwork;

    @FindBy(className = accountDropDownMenuClassName)
    private WebElement accountDropDownMenu;

    @FindBy(xpath = "//div[contains(string(), 'Создать счет')]/parent::div[contains(@class, 'account-menu__item--clickable')]")
    private WebElement createNewAccountItem;

    @FindBy(xpath = "//div[@class='new-account-create-form']/div//button[contains(@class, 'btn-primary')]")
    private WebElement createNewAccountButton;

    @FindBy(xpath = "//div[@class='account-menu__accounts']/div[contains(@class, 'account-menu__item--clickable')]")
    private WebElement swapToTheAnotherAccount;

    @FindBy(xpath = "//a[contains(@class, 'send__select-recipient-wrapper__list__link')]")
    private WebElement transactionBetweenMyAccountsButton;

    @FindBy(xpath = "//div[contains(string(), 'Счет 2')]/parent::div[@class='send__select-recipient-wrapper__group-item__content']/parent::div")
    private WebElement secondBillAsReceiverButton;

    @FindBy(xpath = "//div[@class='popover-header__title']/button")
    private WebElement closeNewsPopupWindow;

    @FindBy(xpath = "//span[contains(string(), 'Отправить')]/parent::button")
    private WebElement sendButton;

    @FindBy(className = "unit-input__input")
    private WebElement transactionValueInput;

    @FindBy(className = "currency-display-component__text")
    private WebElement currentBillValueShort;

    @FindBy(xpath = "//h6[contains(string(), 'Итого')]/following::span")
    private WebElement amountPlusGasFee;

    @FindBy(xpath = "//button[contains(string(), 'Подтвердить')]")
    private WebElement confirmTransactionButton;

    @FindBy(xpath = "//button[contains(string(), 'Сохранить')]")
    private WebElement saveNetworkButton;

    @FindBy(xpath = "//div[contains(string(), 'Account 1')]/following-sibling::div/span[@class='currency-display-component__text']")
    private WebElement mainAccountBillValue;

    @FindBy(xpath = "//div[contains(string(), 'Счет 2')]/following-sibling::div/span[@class='currency-display-component__text']")
    private WebElement secondAccountBillValue;

    @FindBy(className = "list-item--single-content-row")
    private WebElement openListOfTransactions;

    @FindBy(className = "app-header__logo-container--clickable")
    private WebElement logo;

    @FindBy(xpath = "//button[contains(string(), 'Далее')]")
    private WebElement nextButton;

    @FindBy(xpath = "//button[contains(string(), 'Импорт')]")
    private WebElement saveImportBill;

    @FindBy(xpath = "//div[@class='menu-bar']/button")
    private WebElement accountOptionsButton;

    @FindBy(xpath = "//i[contains(@class, 'fa-qrcode')]/parent::button")
    private WebElement accountDetailsItem;

    @FindBy(className = "qr-code__address")
    private WebElement accountAddressText;

    @FindBy(className = "account-modal__close")
    private WebElement closeAccountOptions;

    public MetamaskHomePage(WebDriver driver) {
        super(driver);
        closePopUps();
    }

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }

    public MetamaskHomePage closePopUps(){
        wait.until(ExpectedConditions.elementToBeClickable(closeNewsPopupWindow));
        closeNewsPopupWindow.click();
        return this;
    }

    public MetamaskHomePage createNewAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(accountDropDownMenu));
        accountDropDownMenu.click();
        createNewAccountItem.click();
        createNewAccountButton.click();
        return this;
    }
    
    public MetamaskHomePage swapToTheMainAccount(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className(accountDropDownMenuClassName)));
        accountDropDownMenu.click();
        swapToTheAnotherAccount.click();

        return this;
    }

    public MetamaskHomePage changeNetworkToRopsten() {
        new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(3)).ignoring(ElementClickInterceptedException.class);
        networksDropDownMenu.click();
        testNetworkRopstenItem.click();
        return this;
    }

    public MetamaskHomePage getCurrentBills() throws InterruptedException {
        Thread.sleep(3000);

        accountDropDownMenu.click();
        TransactionSingleton.getTransaction().getSenderAccountBill()
                .setBillValueBeforeTransaction(Double.parseDouble(mainAccountBillValue.getText()));
        TransactionSingleton.getTransaction().getReceiverAccountBill()
                .setBillValueBeforeTransaction(Double.parseDouble(secondAccountBillValue.getText()));

        swapToTheAnotherAccount.click();

        return this;
    }
    
    public MetamaskHomePage createTransactionBetweenMyAccounts() {
        sendButton.click();
        transactionBetweenMyAccountsButton.click();
        secondBillAsReceiverButton.click();
        transactionValueInput.sendKeys("0.000005");

        wait.until(ExpectedConditions.elementToBeClickable(nextButton));

        nextButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(amountPlusGasFee));
        TransactionSingleton.getTransaction().setTotalSumToWriteOff(Double.parseDouble(amountPlusGasFee.getText()));
        System.out.println("Pupupu: " + amountPlusGasFee.getText() + " " + TransactionSingleton.getTransaction().getTotalSumToWriteOff());
        wait.until(ExpectedConditions.elementToBeClickable(confirmTransactionButton));

        confirmTransactionButton.click();
        return this;
    }

    public MetamaskHomePage waitForNewBillValue(){
        openListOfTransactions.click();
        billWaiter.until(ExpectedConditions
                .invisibilityOfElementWithText(By.className("transaction-status--pending"),"В ожидании"));

        accountDropDownMenu.click();
        TransactionSingleton.getTransaction().getSenderAccountBill().setBillValueAfterTransaction(Double.parseDouble(mainAccountBillValue.getText()));
        TransactionSingleton.getTransaction().getReceiverAccountBill().setBillValueAfterTransaction(Double.parseDouble(secondAccountBillValue.getText()));

        return this;
    }

    public MetamaskHomePage getAccountAddressToPasteIntoRopsten(){
        createNewAccount();
        accountOptionsButton.click();
        accountDetailsItem.click();
        setAccountAddress(accountAddressText.getText());
        closeAccountOptions.click();

        return this;
    }

    public BillInfo waitUntilTheBillChanges() {
        billWaiter.until(ExpectedConditions.elementToBeClickable(accountDropDownMenu));
        accountDropDownMenu.click();

        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(
                        "//div[contains(string(), 'Счет 2')]/following-sibling::div/span[@class='currency-display-component__text']"),
                "0.0"));
        BillInfo billInfo = new BillInfo();
        billInfo.setBillValueAfterTransaction(Double.parseDouble(secondAccountBillValue.getText()));
        return billInfo;
    }

    public MetamaskHomePage removeUserNetwork() {
        wait.until(ExpectedConditions.elementToBeClickable(networksDropDownMenu));
        networksDropDownMenu.click();
        createNewNetwork.click();
        localhost.click();
        removeNetwork.click();
        confirmRemoving.click();

        return this;
    }

    public MetamaskHomePage createNewNetwork(){
        new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(3)).ignoring(ElementClickInterceptedException.class);
        addNetwork.click();
        networkName.sendKeys(NetworkSingleton.getNetwork().getNetworkName());
        chainIdentifier.sendKeys(NetworkSingleton.getNetwork().getChainIdentifier());
        newURLRPC.sendKeys(NetworkSingleton.getNetwork().getNewURLRPC());
        saveNetworkButton.click();
        return this;
    }

    public RemixEthereum importAccount(){
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

    public String importToken(String symbol) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(string(), 'Import')]")));
        wait.until(ExpectedConditions.elementToBeClickable(importToken));
        Thread.sleep(2000);
        importToken.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("custom-address")));
        customAddress.sendKeys(Keys.CONTROL + "V");
        customSymbol.sendKeys(symbol);
        addCustomTokenButton.click();
        importTokenButton.click();
        return tokenSuffix.getText();
    }


}
