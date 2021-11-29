package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumHQHomePage {

    private final WebDriver driver;
    private String billOfTheMainAccountBeforeTransaction;
    private String billValueAfterTheTransaction;
    private String totalSumToWriteOff;
    private String amountToTransfer = "0.000005";
    private String billOfTheSecondAccountBeforeTransaction;
    private String billOfTheSecondAccountAfterTransaction;
    private String accountAddress;
    private String billBeforeGettingEthereum;
    private String billAfterGettingEthereum;

    public String getBillAfterGettingEthereum() {
        return billAfterGettingEthereum;
    }

    public void setBillAfterGettingEthereum(String billAfterGettingEthereum) {
        this.billAfterGettingEthereum = billAfterGettingEthereum;
    }

    public String getBillBeforeGettingEthereum() {
        return billBeforeGettingEthereum;
    }

    public void setBillBeforeGettingEthereum(String billBeforeGettingEthereum) {
        this.billBeforeGettingEthereum = billBeforeGettingEthereum;
    }

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    private final String accountDropDownMenuClassName = "account-menu__icon";

    public String getBillOfTheMainAccountBeforeTransaction() {
        return billOfTheMainAccountBeforeTransaction;
    }

    private void setBillOfTheMainAccountBeforeTransaction(String billOfTheMainAccountBeforeTransaction) {
        this.billOfTheMainAccountBeforeTransaction = billOfTheMainAccountBeforeTransaction;
    }

    public String getBillValueAfterTheTransaction() {
        return billValueAfterTheTransaction;
    }

    private void setBillValueAfterTheTransaction(String billValueAfterTheTransaction) {
        this.billValueAfterTheTransaction = billValueAfterTheTransaction;
    }

    public String getTotalSumToWriteOff() {
        return totalSumToWriteOff;
    }

    private void setTotalSumToWriteOff(String totalSumToWriteOff) {
        this.totalSumToWriteOff = totalSumToWriteOff;
    }

    public String getAmountToTransfer() {
        return amountToTransfer;
    }

    private void setAmountToTransfer(String amountToTransfer) {
        this.amountToTransfer = amountToTransfer;
    }

    public String getBillOfTheSecondAccountBeforeTransaction() {
        return billOfTheSecondAccountBeforeTransaction;
    }

    private void setBillOfTheSecondAccountBeforeTransaction(String billOfTheSecondAccountBeforeTransaction) {
        this.billOfTheSecondAccountBeforeTransaction = billOfTheSecondAccountBeforeTransaction;
    }

    public String getBillOfTheSecondAccountAfterTransaction() {
        return billOfTheSecondAccountAfterTransaction;
    }

    private void setBillOfTheSecondAccountAfterTransaction(String billOfTheSecondAccountAfterTransaction) {
        this.billOfTheSecondAccountAfterTransaction = billOfTheSecondAccountAfterTransaction;
    }

    @FindBy(className = "currency-display-component__text")
    private WebElement billBeforeTransaction;

    @FindBy(xpath = "//div[contains(@class, 'network-display--clickable')]")
    private WebElement networksDropDownMenu;

    @FindBy(xpath = "//span[contains(string(), 'Ropsten')]/parent::li")
    //@FindBy(xpath = "//*[@id='app-content']/div/div[2]/div/li[2]")
    private WebElement testNetworkRopstenItem;

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

    @FindBy(xpath = "//h6[contains(string(), 'Итого')]/following::span")
    private WebElement amountPlusGasFee;

    @FindBy(xpath = "//button[contains(string(), 'Подтвердить')]")
    private WebElement confirmTransactionButton;

    @FindBy(xpath = "//div[contains(string(), 'Account 1')]/following-sibling::div/span[@class='currency-display-component__text']")
    private WebElement mainAccountBillValue;

    @FindBy(xpath = "//div[contains(string(), 'Счет 2')]/following-sibling::div/span[@class='currency-display-component__text']")
    private WebElement secondAccountBillValue;

    @FindBy(className = "list-item--single-content-row")
    private WebElement openListOfTransactions;

    @FindBy(xpath = "//button[contains(string(), 'Далее')]")
    private WebElement nextButton;

    @FindBy(xpath = "//div[@class='menu-bar']/button")
    private WebElement accountOptionsButton;

    @FindBy(xpath = "//i[contains(@class, 'fa-qrcode')]/parent::button")
    private WebElement accountDetailsItem;

    @FindBy(className = "qr-code__address")
    private WebElement accountAddressText;

    @FindBy(className = "account-modal__close")
    private WebElement closeAccountOptions;

    public SeleniumHQHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public SeleniumHQHomePage createNewAccountAndSwapToMainAccount() {
        closeNewsPopupWindow.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(accountDropDownMenu));

        accountDropDownMenu.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(createNewAccountItem));

        createNewAccountItem.click();
        createNewAccountButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className(accountDropDownMenuClassName)));
        accountDropDownMenu.click();
        swapToTheAnotherAccount.click();

        return this;
    }

    public SeleniumHQHomePage changeNetwork() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(360));
        wait.until(ExpectedConditions.elementToBeClickable(networksDropDownMenu));
        networksDropDownMenu.click();
        testNetworkRopstenItem.click();

        return this;
    }

    public SeleniumHQHomePage getCurrentBillsAndTransactionBetweenMyAccounts() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(180));
        Thread.sleep(3000);

        accountDropDownMenu.click();
        setBillOfTheMainAccountBeforeTransaction(mainAccountBillValue.getText());
        setBillOfTheSecondAccountBeforeTransaction(secondAccountBillValue.getText());

        swapToTheAnotherAccount.click();
        sendButton.click();
        transactionBetweenMyAccountsButton.click();
        secondBillAsReceiverButton.click();
        transactionValueInput.sendKeys(amountToTransfer);

        wait.until(ExpectedConditions.elementToBeClickable(nextButton));

        nextButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(
                amountPlusGasFee));

        setTotalSumToWriteOff(amountPlusGasFee.getText());
        wait.until(ExpectedConditions.elementToBeClickable(confirmTransactionButton));

        confirmTransactionButton.click();

        return this;
    }

    public SeleniumHQHomePage waitForNewBillValue(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(180));

        openListOfTransactions.click();
        wait.until(ExpectedConditions.invisibilityOfElementWithText(
                By.className("transaction-status--pending"),
                "В ожидании"));

        accountDropDownMenu.click();
        setBillValueAfterTheTransaction(mainAccountBillValue.getText());
        setBillOfTheSecondAccountAfterTransaction(secondAccountBillValue.getText());

        return this;
    }

    public SeleniumHQHomePage getAccountAddressToPasteIntoRopsten(){
        closeNewsPopupWindow.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.elementToBeClickable(accountDropDownMenu));

        accountDropDownMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(createNewAccountItem));

        createNewAccountItem.click();
        createNewAccountButton.click();
        accountOptionsButton.click();
        accountDetailsItem.click();
        setAccountAddress(accountAddressText.getText());
        closeAccountOptions.click();

        return this;
    }

    public SeleniumHQHomePage waitUntilTheBillChanges() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.elementToBeClickable(accountDropDownMenu));

        accountDropDownMenu.click();

        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(
                        "//div[contains(string(), 'Счет 2')]/following-sibling::div/span[@class='currency-display-component__text']"),
                "0"));
        setBillAfterGettingEthereum(secondAccountBillValue.getText());
        return this;
    }
}
