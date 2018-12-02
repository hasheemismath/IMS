/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author User
 */
public class Product_Supplier {
    
    private int ProductId;
    private String ProductName;
    private int SupplierId;
    private String SupplierName;
    private double price;
    private int warranty;
    private int qty;

    public Product_Supplier(int ProductId, String ProductName, int SupplierId, String SupplierName, double price, int warranty, int qty) {
        this.ProductId = ProductId;
        this.ProductName = ProductName;
        this.SupplierId = SupplierId;
        this.SupplierName = SupplierName;
        this.price = price;
        this.warranty = warranty;
        this.qty = qty;
    }

    public int getProductId() {
        return ProductId;
    }

    public String getProductName() {
        return ProductName;
    }

    public int getSupplierId() {
        return SupplierId;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public double getPrice() {
        return price;
    }

    public int getWarranty() {
        return warranty;
    }

    public int getQty() {
        return qty;
    }

    
    
}
