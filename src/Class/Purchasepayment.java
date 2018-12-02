
package Class;

public class Purchasepayment {
    
    public int paymentid;
    public int purchaseid;
    public String paymenttype;
    public String date;
    public String cheque;
    public double totalamount;

    public int getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(int paymentid) {
        this.paymentid = paymentid;
    }

    public int getPurchaseid() {
        return purchaseid;
    }

    public void setPurchaseid(int purchaseid) {
        this.purchaseid = purchaseid;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(double totalamount) {
        this.totalamount = totalamount;
    }

    public double getAmountpaid() {
        return amountpaid;
    }

    public void setAmountpaid(double amountpaid) {
        this.amountpaid = amountpaid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Purchasepayment(int paymentid, int purchaseid, String paymenttype, String date, String cheque, double totalamount, double amountpaid, double balance) {
        this.paymentid = paymentid;
        this.purchaseid = purchaseid;
        this.paymenttype = paymenttype;
        this.date = date;
        this.cheque = cheque;
        this.totalamount = totalamount;
        this.amountpaid = amountpaid;
        this.balance = balance;
    }
    public double amountpaid;
    public double balance;
}
