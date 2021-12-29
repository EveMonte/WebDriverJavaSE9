package model;

import java.util.Objects;

public class BillInfo {
    private double billValueBeforeTransaction;
    private double billValueAfterTransaction;

    public BillInfo() {
    }

    public BillInfo(double billValueBeforeTransaction, double billValueAfterTransaction) {
        this.billValueBeforeTransaction = billValueBeforeTransaction;
        this.billValueAfterTransaction = billValueAfterTransaction;
    }
    public double getBillValueBeforeTransaction() {
        return billValueBeforeTransaction;
    }

    public double getBillValueAfterTransaction() {
        return billValueAfterTransaction;
    }

    public void setBillValueAfterTransaction(double billValueAfterTransaction) {
        this.billValueAfterTransaction = billValueAfterTransaction;
    }

    public void setBillValueBeforeTransaction(double billValueBeforeTransaction) {
        this.billValueBeforeTransaction = billValueBeforeTransaction;
    }

    @Override
    public String toString() {
        return "BillInfo{" +
                "billValueBeforeTransaction=" + billValueBeforeTransaction +
                ", billValueAfterTransaction=" + billValueAfterTransaction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillInfo billInfo = (BillInfo) o;
        return Double.compare(billInfo.billValueBeforeTransaction, billValueBeforeTransaction) == 0 && Double.compare(billInfo.billValueAfterTransaction, billValueAfterTransaction) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(billValueBeforeTransaction, billValueAfterTransaction);
    }
}
