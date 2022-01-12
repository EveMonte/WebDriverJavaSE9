package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import transaction.TransactionSingleton;
import util.DoubleUtils;

import java.time.Duration;

public class MetamaskTransactionPage extends AbstractPage {

    private final String accountDropDownMenuClassName = "account-menu__icon";

    @FindBy(xpath = "//a[contains(@class, 'send__select-recipient-wrapper__list__link')]")
    private WebElement transactionBetweenMyAccountsButton;

    @FindBy(xpath = "//input[@value = 'high']")
    private WebElement highValueRadioButton;

    @FindBy(xpath = "//button[contains(string(), 'Ускорить')]")
    private WebElement speedUpButton;

    @FindBy(xpath = "//div[contains(string(), 'Счет 2')]/parent::div[@class='send__select-recipient-wrapper__group-item__content']/parent::div")
    private WebElement secondBillAsReceiverButton;

    @FindBy(xpath = "//div[contains(string(), 'Account 1')]/parent::div[@class='send__select-recipient-wrapper__group-item__content']/parent::div")
    private WebElement firstBillAsReceiverButton;

    @FindBy(xpath = "//span[contains(string(), 'Отправить')]/parent::button")
    private WebElement sendButton;

    @FindBy(className = "unit-input__input")
    private WebElement transactionValueInput;

    @FindBy(xpath = "//i[@class = 'fa fa-sm fa-angle-down']/parent::div")
    private WebElement arrowDown;

    @FindBy(xpath = "//button[contains(string(), 'Далее')]")
    private WebElement nextButton;

    @FindBy(xpath = "//button[contains(string(), 'Сохранить')]")
    private WebElement saveButton;

    @FindBy(xpath = "//h1[contains(string(), 'ETH')]")
    private WebElement newGasFee;

    @FindBy(xpath = "//button[contains(string(), 'Подтвердить')]")
    private WebElement confirmTransactionButton;

    @FindBy(className = "list-item--single-content-row")
    private WebElement openListOfTransactions;

    @FindBy(xpath = "//h6[contains(string(), 'Итого')]/following::span")
    private WebElement amountPlusGasFee;

    @FindBy(className = "token-cell")
    private WebElement tokenCell;

    @FindBy(xpath = "//div[contains(string(), 'Account 1')]/following-sibling::div/span[@class='currency-display-component__text']")
    private WebElement mainAccountBillValue;

    @FindBy(xpath = "//div[contains(string(), 'Счет 2')]/following-sibling::div/span[@class='currency-display-component__text']")
    private WebElement secondAccountBillValue;

    @FindBy(className = accountDropDownMenuClassName)
    private WebElement accountDropDownMenu;

    @FindBy(className = "currency-display-component__text")
    private WebElement billBeforeTransaction;

    @FindBy(className = "send__select-recipient-wrapper__group-item")
    private WebElement contactAsReceiver;

    public MetamaskTransactionPage(WebDriver driver) {
        super(driver);
    }
    public MetamaskTransactionPage openTransactionWindow() {
        logger.info("Open Transaction Window");
        sendButton.click();
        transactionBetweenMyAccountsButton.click();
        return this;
    }

    public MetamaskTransactionPage speedUp() {
        logger.info("Speed Up");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(string(), 'Ускорить')]")));
        wait.until(ExpectedConditions.elementToBeClickable(speedUpButton));
        speedUpButton.click();
        highValueRadioButton.click();
        TransactionSingleton.getTransaction().setTotalSumToWriteOff(Double.parseDouble(newGasFee.getText().split(" ")[0]));
        saveButton.click();
        return this;
    }

    public MetamaskTransactionPage chooseSecondAccountAsReceiver() {
        logger.info("Choose Second Account As Receiver");
        secondBillAsReceiverButton.click();
        return this;
    }

    public MetamaskTransactionPage chooseContactAsReceiver() {
        logger.info("Choose Contact As Receiver");
        contactAsReceiver.click();
        return this;
    }

    public MetamaskTransactionPage transferOfToken() throws InterruptedException {
        logger.info("Transfer Of Token");
        transactionValueInput.sendKeys(String.valueOf(TransactionSingleton.getTransaction().getAmountToTransfer()));
        arrowDown.click();
        Thread.sleep(2000);
        TransactionSingleton.getTransaction().getSenderAccountBill()
                .setBillValueBeforeTransaction(Double.parseDouble(
                        driver.findElement(By.className("currency-display-component__text")).getText()));
        nextButton.click();
        confirmTransactionButton.click();
        return this;
    }

    public MetamaskTransactionPage chooseMainAccountAsReceiver() {
        logger.info("Choose Main Account As Receiver");
        firstBillAsReceiverButton.click();
        return this;
    }

    public MetamaskTransactionPage createTransactionBetweenMyAccounts() {
        logger.info("Create Transaction Between My Accounts");
        transactionValueInput.sendKeys(DoubleUtils
                .roundDouble(TransactionSingleton.getTransaction().getAmountToTransfer()));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(string(), 'Далее')]")));
        nextButton.click();

        TransactionSingleton.getTransaction().setTotalSumToWriteOff(Double.parseDouble(amountPlusGasFee.getText()));
        wait.until(ExpectedConditions.elementToBeClickable(confirmTransactionButton));

        confirmTransactionButton.click();
        return this;
    }

    public MetamaskAccountsPage waitForTokenTransaction() {
        logger.info("Wait For Token Transaction");
        tokenCell.click();
        billWaiter.until(ExpectedConditions
                .invisibilityOfElementWithText(By.className("transaction-status--pending"), "В ожидании"));
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions
                .invisibilityOfElementWithText(By.className("currency-display-component__text"),
                        billBeforeTransaction.getText()));

        TransactionSingleton.getTransaction().getSenderAccountBill()
                .setBillValueAfterTransaction(Double.parseDouble(billBeforeTransaction.getText()));
        return new MetamaskAccountsPage(driver);
    }

    public MetamaskTransactionPage openListOfTransactions() {
        logger.info("Open List Of Transactions");
        openListOfTransactions.click();
        return this;
    }

    public MetamaskTransactionPage waitForNewBillValue() {
        logger.info("Wait For New Bill Value");
        billWaiter.until(ExpectedConditions
                .invisibilityOfElementWithText(By.className("transaction-status--pending"), "В ожидании"));

        accountDropDownMenu.click();
        TransactionSingleton.getTransaction().getSenderAccountBill().setBillValueAfterTransaction(Double.parseDouble(mainAccountBillValue.getText()));
        TransactionSingleton.getTransaction().getReceiverAccountBill().setBillValueAfterTransaction(Double.parseDouble(secondAccountBillValue.getText()));

        return this;
    }

    public MetamaskTransactionPage setBillOfTheMainAccount() {
        logger.info("Set Bill Of The Main Account");
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions
                .invisibilityOfElementWithText(By.className("currency-display-component__text"),
                        billBeforeTransaction.getText()));
        TransactionSingleton.getTransaction().getReceiverAccountBill().setBillValueAfterTransaction(Double.parseDouble(billBeforeTransaction.getText()));
        return this;
    }

}
