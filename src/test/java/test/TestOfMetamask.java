package test;

import driver.TabManager;
import model.BillInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SeleniumHQHomePage;
import pages.SeleniumHQRopstenEthereum;
import pages.SeleniumHQSignInPage;
import service.UserCreator;
import transaction.TransactionSingleton;
import util.DoubleUtils;

import java.util.ArrayList;

public class TestOfMetamask extends CommonConditions {

    @Test(description = "Transfer of Ethereum to another user's account")
    public void transferOfEthereum() throws InterruptedException {
        SeleniumHQSignInPage signInPage = new SeleniumHQSignInPage(driver);

        signInPage.openSignInWindow()
                .signInToMetamask(UserCreator.withCredentialsFromProperty())
                .createNewAccountAndSwapToMainAccount()
                .changeNetwork()
                .getCurrentBillsAndTransactionBetweenMyAccounts()
                .waitForNewBillValue();

        double expectedValueOfMainAccountBill = TransactionSingleton.getTransaction()
                .getSenderAccountBill().getBillValueBeforeTransaction() -
                TransactionSingleton.getTransaction().getTotalSumToWriteOff();
        double expectedValueOfSecondAccountBill = TransactionSingleton.getTransaction().getReceiverAccountBill().getBillValueBeforeTransaction() +
                0.000005;

        Assert.assertEquals(DoubleUtils.roundDouble(TransactionSingleton.getTransaction()
                .getSenderAccountBill().getBillValueAfterTransaction()), DoubleUtils.roundDouble(expectedValueOfMainAccountBill));

        Assert.assertEquals(DoubleUtils.roundDouble(TransactionSingleton.getTransaction()
                .getReceiverAccountBill().getBillValueAfterTransaction()), DoubleUtils.roundDouble(expectedValueOfSecondAccountBill));
    }

    @Test
    public void getEthereumFromRopsten() {
        SeleniumHQHomePage homePage = new SeleniumHQHomePage(driver);
        SeleniumHQSignInPage signInPage = new SeleniumHQSignInPage(driver);

        String accountAddress = signInPage
                .openSignInWindow()
                .signInToMetamask(UserCreator.withCredentialsFromProperty())
                .getAccountAddressToPasteIntoRopsten()
                .changeNetwork()
                .getAccountAddress();

        TabManager.createNewTabAndSwitchToIt("https://faucet.ropsten.be/");
        (new SeleniumHQRopstenEthereum(driver)).pasteAccountAddressAndGetEthereum(accountAddress);
        TabManager.switchToTabDefinedByIndex(1);
        BillInfo bill = homePage.waitUntilTheBillChanges();

        Assert.assertTrue(bill.getBillValueAfterTransaction() > 0.3);
    }
}
