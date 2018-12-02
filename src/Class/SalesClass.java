package Class;

public class SalesClass
{
    private int salesid;
    private String salesperson;
    private long productid;
    private int customerid;
    private String date;
    private int quantity;
    private double price;
    
    public void SalesClass()
    {
        
    }
    
    public SalesClass(int salesid,String salesperson,long stockid,int customerid,String date,int quantity,double price)
    {
        this.salesid = salesid;
        this.salesperson = salesperson;
        this.productid = stockid;
        this.customerid = customerid;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
    }
    
    public void setSalesid(int salesid)
    {
        this.salesid = salesid;
    }
    
    public int getSalesid()
    {
        return this.salesid;
    }
    
    public void setSalesPerson(String salesperson)
    {
        this.salesperson = salesperson;
    }
    
    public String getSalesPerson()
    {
        return this.salesperson;
    }
    
    public void setProductID(long stockid)
    {
        this.productid = stockid;
    }
    
    public long getProductID()
    {
        return this.productid;
    }
    
    public void setCustomerID(int customerid)
    {
        this.customerid = customerid;
    }
    
    public int getCustomerid()
    {
        return this.customerid;
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public String getDate()
    {
        return this.date;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public double getPrice()
    {
        return this.price;
    }
    
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    public int getQuantity()
    {
        return this.quantity;
    }
} 
