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
public class Product {
    
    private int ProductId;
    private String ProductName;
    private String Category;
    private String Description;
    private int Quantity;
    private double price;
    private byte[] Image;

    public Product(int ProductId, String ProductName, String Category, String Description, int Quantity, double price, byte[] Image) {
        this.ProductId = ProductId;
        this.ProductName = ProductName;
        this.Category = Category;
        this.Description = Description;
        this.Quantity = Quantity;
        this.price = price;
        this.Image = Image;
    }

    public int getProductId() {
        return ProductId;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getCategory() {
        return Category;
    }

    public String getDescription() {
        return Description;
    }

    public int getQuantity() {
        return Quantity;
    }

    public double getPrice() {
        return price;
    }

    public byte[] getImage() {
        return Image;
    }
    
    

    
    
}
