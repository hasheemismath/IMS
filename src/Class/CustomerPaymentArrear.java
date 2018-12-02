/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author PraveenKumar
 */
public class CustomerPaymentArrear {

    private int customerid;
    private String customername;
    private String email;
    private double amountpaid;
    private double totalamount;
    private double arrear;

    public CustomerPaymentArrear(int customerid, String customername, String email, double amountpaid, double totalamount, double arrear) {
        this.customerid = customerid;
        this.customername = customername;
        this.email = email;
        this.amountpaid = amountpaid;
        this.totalamount = totalamount;
        this.arrear = arrear;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAmountpaid() {
        return amountpaid;
    }

    public void setAmountpaid(double amountpaid) {
        this.amountpaid = amountpaid;
    }

    public double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(double totalamount) {
        this.totalamount = totalamount;
    }

    public double getArrear() {
        return arrear;
    }

    public void setArrear(double arrear) {
        this.arrear = arrear;
    }

   }
