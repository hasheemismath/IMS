package Class;

public class SupplierClass 
{
    private int supplierid;
    private String companyname;
    private String address;
    private String email;
    private int contactno;
    
    public SupplierClass()
    {
        
    }
    
    public SupplierClass(int supplierid, String companyname, String address, String email, int contactno)
    {
        this.supplierid = supplierid;
        this.companyname = companyname;
        this.address = address;
        this.email = email;
        this.contactno = contactno;
    }
    
    public void setSupplierid(int supplierid)
    {
        this.supplierid = supplierid;
    }
    
    public int getSupplierid()
    {
        return this.supplierid;
    }
    
    public void setCompanyname(String companyname)
    {
        this.companyname = companyname;
    }
    
    public String getCompanyname()
    {
        return this.companyname;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getAddress()
    {
        return this.address;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getEmail()
    {
        return this.email;
    }
    
    public void setContactno(int contactno)
    {
        this.contactno = contactno;
    }
    
    public int getContactno()
    {
        return this.contactno;
    }
}
