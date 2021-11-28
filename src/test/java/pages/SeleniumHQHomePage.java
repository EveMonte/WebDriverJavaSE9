package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumHQHomePage {

    private WebDriver driver;
    private String billOfTheMainAccountBeforeTransaction;
    private String billValueAfterTheTransaction;
    private String totalSumToWriteOff;
    private String amountToTransfer = "0.000005";
    private String billOfTheSecondAccountBeforeTransaction;
    private String billOfTheSecondAccountAfterTransaction;
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

    @FindBy(className = "selected-account__clickable")
    private WebElement copyToClipboardAccountAddressButton;

    public SeleniumHQHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    public SeleniumHQHomePage createNewAccountAndSwapToMainAccount() throws InterruptedException {
        closeNewsPopupWindow.click();//account-menu__icon
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(accountDropDownMenu));

        accountDropDownMenu.click();
        createNewAccountItem.click();
        createNewAccountButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className(accountDropDownMenuClassName)));
        accountDropDownMenu.click();
        swapToTheAnotherAccount.click();

        return this;
    }

    public SeleniumHQHomePage changeNetworkAndGetCurrentBill() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(180));

        networksDropDownMenu.click();
        testNetworkRopstenItem.click();
        accountDropDownMenu.click();

        Thread.sleep(3000);

        setBillOfTheMainAccountBeforeTransaction(mainAccountBillValue.getText());
        setBillOfTheSecondAccountBeforeTransaction(secondAccountBillValue.getText());

        return this;
    }

    public SeleniumHQHomePage transactionBetweenMyAccounts() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(180));

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
        openListOfTransactions.click();//class="transaction-status transaction-status--pending"
        wait.until(ExpectedConditions.invisibilityOfElementWithText(
                By.className("transaction-status--pending"),
                "В ожидании"));

        accountDropDownMenu.click();
        setBillValueAfterTheTransaction(mainAccountBillValue.getText());
        setBillOfTheSecondAccountAfterTransaction(secondAccountBillValue.getText());

        return this;
    }

    public SeleniumHQHomePage copyToClipBoardAccountAddress(){
        copyToClipboardAccountAddressButton.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "v")).build().perform();

        return this;
    }
}
