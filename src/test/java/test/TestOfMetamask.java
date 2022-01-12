package test;

import driver.TabManager;
import model.BillInfo;
import model.Contact;
import org.assertj.core.data.Offset;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import service.NetworkCreator;
import service.UserCreator;
import transaction.TransactionSingleton;
import util.DoubleUtils;
import util.StringUtils;
import static org.assertj.core.api.Assertions.assertThat;
public class TestOfMetamask extends CommonConditions {

    private final String REPOSITORY_URL = "https://github.com/EveMonte/Contract/blob/main/Contract.sol";

//        @Test(description = "Transfer of Ethereum to another user's account")
//    public void transferOfEthereum() throws InterruptedException {
//        MetamaskSignInPage signInPage = new MetamaskSignInPage(driver);
//
//        signInPage.openSignInWindow()
//                .signInToMetamask(UserCreator.withCredentialsFromProperty())
//                .createNewAccount()
//                .swapToTheMainAccount()
//                .changeNetworkToRopsten()
//                .getCurrentBills()
//                .createTransactionBetweenMyAccounts()
//                .waitForNewBillValue();
//
//        double expectedValueOfMainAccountBill = TransactionSingleton.getTransaction()
//                .getSenderAccountBill().getBillValueBeforeTransaction() -
//                TransactionSingleton.getTransaction().getTotalSumToWriteOff();
//        double expectedValueOfSecondAccountBill = TransactionSingleton.getTransaction().getReceiverAccountBill().getBillValueBeforeTransaction() +
//                TransactionSingleton.getTransaction().getAmountToTransfer();
//        assertThat(TransactionSingleton.getTransaction()
//                .getSenderAccountBill().getBillValueAfterTransaction()).isCloseTo(expectedValueOfMainAccountBill, Offset.offset(0.000002));
//
//        assertThat(TransactionSingleton.getTransaction()
//                .getReceiverAccountBill().getBillValueAfterTransaction()).isCloseTo(expectedValueOfSecondAccountBill, Offset.offset(0.000002));
//    }
//
    //DONE
//    @Test
//    public void getEthereumFromRopsten() {
//        MetamaskHomePage homePage;
//        MetamaskSignInPage signInPage = new MetamaskSignInPage(driver);
//
//        homePage = signInPage
//                .openSignInWindow()
//                .signInToMetamask(UserCreator.withCredentialsFromProperty())
//                .getAccountAddressToPasteIntoRopsten()
//                .changeNetworkToRopsten();
//        String accountAddress = homePage.getAccountAddress();
//        (new RopstenEthereum(driver)).openPage()
//                .pasteAccountAddressAndGetEthereum(accountAddress);
//        TabManager.switchToTabDefinedByIndex(0);
//        BillInfo bill = homePage.waitUntilTheBillChanges();
//        assertThat(bill.getBillValueAfterTransaction()).isGreaterThanOrEqualTo(0.3);
//    }
//
//    @Test
//    public void createNetwork() throws InterruptedException {
//        MetamaskSignInPage signInPage = new MetamaskSignInPage(driver);
//        NetworkCreator.withDataFromProperties();
//        signInPage
//                .openSignInWindow()
//                .signInToMetamask(UserCreator.withCredentialsFromProperty())
//                .removeUserNetwork()
//                .createNewNetwork()
//                .importAccount();
//        Assert.assertEquals(TransactionSingleton.getTransaction().getReceiverAccountBill().getBillValueAfterTransaction(), 100.0);
//    }
//      DONE
//    @Test
//    public void createContact() throws InterruptedException {
//        new MetamaskSignInPage(driver).openSignInWindow()
//                .signInToMetamask(UserCreator.withCredentialsFromProperty());
//        Contact newContact = new MetamaskSettingsPage(driver)
//                .openPage()
//                .addContact();
//
//        Assert.assertEquals(newContact.getActualContactName(), newContact.getContactName());
//    }
//    DONE
//    @Test
//    public void deployContract() throws InterruptedException {
//        MetamaskSignInPage signInPage = new MetamaskSignInPage(driver);
//
//        MetamaskHomePage homePage = signInPage.openSignInWindow()
//                .signInToMetamask(createdUser)
//                .removeUserNetwork()
//                .createNewNetwork()
//                .importAccount()
//                .openPage()
//                .closePopUps()
//                .getFileFromGitHub(REPOSITORY_URL)
//                .compileScript()
//                .deployContract();
//        String expectedSymbol = StringUtils.generateRandomTokenSymbolWithPostfixLength(3);
//
//        String suffix = homePage.importToken(expectedSymbol);
//        Assert.assertEquals(suffix, expectedSymbol);
//    }
}
