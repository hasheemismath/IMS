
package Class;


public class Outwards {

    public Outwards(int outwardsid, int supplierid, int productid, int quantity, String date, String description) {
        this.outwardsid = outwardsid;
        this.supplierid = supplierid;
        this.productid = productid;
        this.quantity = quantity;
        this.date = date;
        this.description = description;
    }

    public int getOutwardsid() {
        return outwardsid;
    }

    public void setOutwardsid(int outwardsid) {
        this.outwardsid = outwardsid;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public int outwardsid;
    public int supplierid;
    public int productid;
    public int quantity;
    public String date;
    public String description;
}
