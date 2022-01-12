package model;

import java.util.Objects;

public class Transaction {
    private BillInfo senderAccountBill;
    private BillInfo receiverAccountBill;
    private double amountToTransfer;
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

    public double getAmountToTransfer() {
        return amountToTransfer;
    }

    public void setAmountToTransfer(double amountToTransfer) {
        this.amountToTransfer = amountToTransfer;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "senderAccountBill=" + senderAccountBill.toString() +
                ", receiverAccountBill=" + receiverAccountBill.toString() +
                ", amountToTransfer=" + amountToTransfer +
                ", totalSumToWriteOff=" + totalSumToWriteOff +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amountToTransfer, amountToTransfer) == 0
                && Double.compare(that.totalSumToWriteOff, totalSumToWriteOff) == 0
                && senderAccountBill.equals(that.senderAccountBill)
                && receiverAccountBill.equals(that.receiverAccountBill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderAccountBill.hashCode(), receiverAccountBill.hashCode(), amountToTransfer, totalSumToWriteOff);
    }
}
