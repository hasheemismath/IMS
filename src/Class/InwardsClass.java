/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author Roshan Nizar
 */
public class InwardsClass 
{
    private int inwardid;
    private long productid;
    private int customerid;
    private String description;
    private String date;
    private int quantity;
    private double price;
    
    public InwardsClass()
    {
        this.inwardid = 0;
        this.productid = 0;
        this.customerid = 0;
        this.description = "";
        this.date = "";
        this.quantity = 0;
        this.price = 0.0;
    }
    
    public InwardsClass(int inwardid, long productid, int customerid, String description, String date, int quantity, double price)
    {
        this.inwardid = inwardid;
        this.productid = productid;
        this.customerid = customerid;
        this.description = description;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
    }
    
    public void setInwardID(int inwardid)
    {
        this.inwardid = inwardid;
    }
    
    public int getInwardID()
    {
        return this.inwardid;
    }
    
    public void setProductID(int productid)
    {
        this.productid = productid;
    }
    
    public long getProductID()
    {
        return this.productid;
    }
    
    public void setCustomerID(int customerid)
    {
        this.customerid = customerid;
    }
    
    public int getCustomerID()
    {
        return this.customerid;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getDescription()
    {
        return this.description;
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public String getDate()
    {
        return this.date;
    }
    
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    public int getQuantity()
    {
        return this.quantity;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public double getPrice()
    {
        return this.price;
    }
}
