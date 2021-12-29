package model;

public class Transaction {
    private BillInfo senderAccountBill;
    private BillInfo receiverAccountBill;
    private double totalSumToWriteOff;

    public BillInfo getSenderAccountBill() {
        return senderAccountBill;
    }

    public void setSenderAccountBill(BillInfo senderAccountBill) {
        this.senderAccountBill = senderAccountBill;
    }

    public BillInfo getReceiverAccountBill() {
        return receiverAccountBill;
    }

    public void setReceiverAccountBill(BillInfo receiverAccountBill) {
        this.receiverAccountBill = receiverAccountBill;
    }

    public double getTotalSumToWriteOff() {
        return totalSumToWriteOff;
    }

    public void setTotalSumToWriteOff(double totalSumToWriteOff) {
        this.totalSumToWriteOff = totalSumToWriteOff;
    }
}
