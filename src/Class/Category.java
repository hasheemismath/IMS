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
public class Category {
    
    private int CategoryId;
    private String CategoryName;
    private String Description;

    public Category(int CategoryId, String CategoryName, String Description) {
        this.CategoryId = CategoryId;
        this.CategoryName = CategoryName;
        this.Description = Description;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public String getDescription() {
        return Description;
    }

    
    
}
