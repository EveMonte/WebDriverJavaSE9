package test;

import driver.TabManager;
import model.BillInfo;
import model.Contact;
import org.assertj.core.data.Offset;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.metamask.*;
import pages.ropsten.RopstenEthereum;
import service.NetworkCreator;
import service.UserCreator;
import transaction.TransactionSingleton;
import util.StringUtils;
import static org.assertj.core.api.Assertions.assertThat;
public class TestOfMetamask extends CommonConditions {

    private final String REPOSITORY_URL = "https://github.com/EveMonte/Contract/blob/main/Contract.sol";

    public MetamaskHomePage preconditionForTestsWithTokenManipulation() throws InterruptedException {
        return new MetamaskSignInPage(driver).openSignInWindow()
                .signInToMetamask(createdUser).closePopUps()
                .openNetworksPage()
                .removeUserNetwork()
                .createNewNetwork()
                .importAccount()
                .openPage()
                .closePopUps()
                .getFileFromGitHub(REPOSITORY_URL)
                .compileScript()
                .deployContract().closePopUps();
    }

    public MetamaskTransactionPage preconditionForTransactions() throws InterruptedException {
        return (new MetamaskSignInPage(driver)).openSignInWindow()
                .signInToMetamask(UserCreator.withCredentialsFromProperty())
                .closePopUps()
                .openAccountsPage()
                .createNewAccount()
                .swapToTheMainAccount()
                .changeNetworkToRopsten()
                .getCurrentBills()
                .openTransactionWindow()
                .chooseSecondAccountAsReceiver()
                .createTransactionBetweenMyAccounts()
                .openListOfTransactions();
    }
    @Test(description = "Transfer of Ethereum to another user's account")
    public void transferOfEthereum() throws InterruptedException {
        preconditionForTransactions().waitForNewBillValue();

            double expectedValueOfMainAccountBill = TransactionSingleton.getTransaction()
                    .getSenderAccountBill().getBillValueBeforeTransaction() -
                    TransactionSingleton.getTransaction().getTotalSumToWriteOff();
            double expectedValueOfSecondAccountBill = TransactionSingleton.getTransaction().getReceiverAccountBill().getBillValueBeforeTransaction() +
                    TransactionSingleton.getTransaction().getAmountToTransfer();
            assertThat(TransactionSingleton.getTransaction()
                    .getSenderAccountBill().getBillValueAfterTransaction()).isCloseTo(expectedValueOfMainAccountBill, Offset.offset(0.000002));

            assertThat(TransactionSingleton.getTransaction()
                    .getReceiverAccountBill().getBillValueAfterTransaction()).isCloseTo(expectedValueOfSecondAccountBill, Offset.offset(0.000002));
        }

    @Test
    public void getEthereumFromRopsten() {
        MetamaskHomePage homePage = new MetamaskHomePage(driver);
        MetamaskSignInPage signInPage = new MetamaskSignInPage(driver);

        String accountAddress = signInPage
                .openSignInWindow()
                .signInToMetamask(UserCreator.withCredentialsFromProperty())
                .closePopUps()
                .getAccountAddressToPasteIntoRopsten();
        homePage.closeAccountOptions()
                .changeNetworkToRopsten();
        (new RopstenEthereum(driver)).openPage()
                .pasteAccountAddressAndGetEthereum(accountAddress);
        TabManager.switchToTabDefinedByIndex(0);
        BillInfo bill = homePage.waitUntilTheBillChanges();
        assertThat(bill.getBillValueAfterTransaction()).isGreaterThanOrEqualTo(0.3);
    }

    @Test
    public void createNetwork() {
        MetamaskSignInPage signInPage = new MetamaskSignInPage(driver);
        NetworkCreator.withDataFromProperties();
        signInPage
                .openSignInWindow()
                .signInToMetamask(UserCreator.withCredentialsFromProperty())
                .closePopUps()
                .openNetworksPage()
                .removeUserNetwork()
                .createNewNetwork()
                .importAccount();
        assertThat(TransactionSingleton.getTransaction().getReceiverAccountBill().getBillValueAfterTransaction()).isGreaterThan(0);
    }

    @Test
    public void createContact() throws InterruptedException {
        new MetamaskSignInPage(driver).openSignInWindow()
                .signInToMetamask(UserCreator.withCredentialsFromProperty())
                .closePopUps();
        Contact newContact = new MetamaskSettingsPage(driver)
                .openPage()
                .addContact();

        Assert.assertEquals(newContact.getActualContactName(), newContact.getContactName());
    }

    @Test
    public void deployContract() throws InterruptedException {
        MetamaskHomePage homePage = preconditionForTestsWithTokenManipulation();
        String expectedSymbol = StringUtils.generateRandomTokenSymbolWithPostfixLength(3);

        String suffix = homePage.openImportPage().importToken(expectedSymbol);
        Assert.assertEquals(suffix, expectedSymbol);
    }

    @Test
    public void sendToken() throws InterruptedException {
        TransactionSingleton.getTransaction().setAmountToTransfer(1);
        MetamaskHomePage homePage = preconditionForTestsWithTokenManipulation();
        String expectedSymbol = StringUtils.generateRandomTokenSymbolWithPostfixLength(3);
        MetamaskImportPage importPage = homePage.openImportPage();
        importPage.importToken(expectedSymbol);
        importPage.setAmountToTransfer()
                .openTransactionWindow()
                .chooseMainAccountAsReceiver()
                .transferOfToken()
                .setBillOfTheMainAccount()
                .waitForTokenTransaction()
                .swapToTheMainAccount();
        homePage.openImportPage().importToken(expectedSymbol);
        (new MetamaskTransactionPage(driver)).setBillOfTheMainAccount();

        assertThat(TransactionSingleton.getTransaction().getReceiverAccountBill().getBillValueAfterTransaction())
                .isEqualTo(TransactionSingleton.getTransaction().getAmountToTransfer()/100);

        assertThat(TransactionSingleton.getTransaction().getSenderAccountBill().getBillValueAfterTransaction())
                .isEqualTo(TransactionSingleton.getTransaction().getSenderAccountBill().getBillValueBeforeTransaction()
                        - TransactionSingleton.getTransaction().getAmountToTransfer()/100);
    }

    @Test
    public void sendTokenToContact() throws InterruptedException {
        TransactionSingleton.getTransaction().setAmountToTransfer(1);
        MetamaskHomePage homePage = preconditionForTestsWithTokenManipulation();
        String expectedSymbol = StringUtils.generateRandomTokenSymbolWithPostfixLength(3);
        new MetamaskSettingsPage(driver)
                .openPage()
                .addContact();

        MetamaskImportPage importPage = homePage.clickOnLogo()
                .openImportPage();
        importPage.importToken(expectedSymbol);
        importPage.setAmountToTransfer()
                .openTransactionWindow()
                .chooseContactAsReceiver()
                .transferOfToken()
                .setBillOfTheMainAccount()
                .waitForTokenTransaction();

        assertThat(TransactionSingleton.getTransaction().getSenderAccountBill().getBillValueAfterTransaction())
                .isEqualTo(TransactionSingleton.getTransaction().getSenderAccountBill().getBillValueBeforeTransaction()
                        - TransactionSingleton.getTransaction().getAmountToTransfer()/100);
    }

    @Test(description = "Transfer of Ethereum to another user's account")
    public void speedUpTransaction() throws InterruptedException {
        preconditionForTransactions().speedUp()
                .waitForNewBillValue();

        double expectedValueOfMainAccountBill = TransactionSingleton.getTransaction()
                .getSenderAccountBill().getBillValueBeforeTransaction() -
                TransactionSingleton.getTransaction().getTotalSumToWriteOff();
        double expectedValueOfSecondAccountBill = TransactionSingleton.getTransaction().getReceiverAccountBill().getBillValueBeforeTransaction() +
                TransactionSingleton.getTransaction().getAmountToTransfer();

        assertThat(TransactionSingleton.getTransaction()
                .getSenderAccountBill().getBillValueAfterTransaction()).isCloseTo(expectedValueOfMainAccountBill, Offset.offset(0.00002));

        assertThat(TransactionSingleton.getTransaction()
                .getReceiverAccountBill().getBillValueAfterTransaction()).isCloseTo(expectedValueOfSecondAccountBill, Offset.offset(0.00002));
    }

}
