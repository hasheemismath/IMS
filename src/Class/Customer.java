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
public class Customer {

    private int customerid;
    private String firstname;
    private String lastname;
    private String contactNo;
    private String address;
    private String email;

    public Customer(int customerid, String firstname, String lastname, String contactNo, String address, String email) {
        this.customerid = customerid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactNo = contactNo;
        this.address = address;
        this.email = email;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


   
   
}
