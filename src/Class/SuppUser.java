/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;
/**
 *
 * @author Hasheem
 */
public class SuppUser {
    
    int suppID;
    String suppName,address,contactNo,email; 
    
    public SuppUser(int suppID, String suppName, String address, String contactNo, String email) {
        this.suppID = suppID;
        this.suppName = suppName;
        this.address = address;
        this.contactNo = contactNo;
        this.email = email;
    }
    
        

   
    public int getId() {
        return suppID;
    }
     
    public String getName() {
        return suppName;
    }
     
    public String getAddress() {
        return address;
    }
     
    public String getcontact() {
        return contactNo;
    }
     
    public String getEmail() {
        return email;
    }
     
    
}
