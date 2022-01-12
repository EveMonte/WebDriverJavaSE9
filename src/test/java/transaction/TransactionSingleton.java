package transaction;

import model.BillInfo;
import model.Transaction;
import util.DoubleUtils;

public class TransactionSingleton {
    private static Transaction transaction;

    private TransactionSingleton(){}

    public static Transaction getTransaction() {
        if (null == transaction) {
            transaction = new Transaction();
            transaction.setSenderAccountBill(new BillInfo());
            transaction.setReceiverAccountBill(new BillInfo());
            transaction.setTotalSumToWriteOff(0);
            transaction.setAmountToTransfer(0.000005);
        }
        return transaction;
    }

    public static void clearTransaction(){
        transaction.setSenderAccountBill(null);
        transaction.setReceiverAccountBill(null);
        transaction.setTotalSumToWriteOff(0);
        transaction.setAmountToTransfer(0);
        transaction = null;
    }

}
