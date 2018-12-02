
package Class;

public class Purchase{
    
    private int purchaseid;
    private String purchasername;
    private String purchasedate; 
    private int supplierid;
    private int productid;
    private int quantity;
    
    public Purchase(){}

    public int getPurchaseid() {
        return purchaseid;
    }

    public void setPurchaseid(int purchaseid) {
        this.purchaseid = purchaseid;
    }

    public String getPurchasername() {
        return purchasername;
    }

    public void setPurchasername(String purchasername) {
        this.purchasername = purchasername;
    }

    public String getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(String purchasedate) {
        this.purchasedate = purchasedate;
    }

    public int getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Purchase(int purchaseid, String purchasername, String purchasedate, int supplierid, int productid, int quantity) {
        this.purchaseid = purchaseid;
        this.purchasername = purchasername;
        this.purchasedate = purchasedate;
        this.supplierid = supplierid;
        this.productid = productid;
        this.quantity = quantity;
    }
    
    
}