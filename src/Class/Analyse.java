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
public class Analyse {
    
    private String ProductId;
    private double priceMin;
    private double priceMax;
    private int warranty;
    private int qty;

    public Analyse(String ProductId, double priceMin, double priceMax, int warranty, int qty) {
        this.ProductId = ProductId;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.warranty = warranty;
        this.qty = qty;
    }

    public String getProductId() {
        return ProductId;
    }

    public double getPriceMin() {
        return priceMin;
    }

    public double getPriceMax() {
        return priceMax;
    }

    public int getWarranty() {
        return warranty;
    }

    public int getQty() {
        return qty;
    }

    
}
