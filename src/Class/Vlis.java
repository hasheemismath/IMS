/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Vishwa
 */
public class Vlis {
    
    private String name;
    private String date;
    private String amount;
    private String bsal;
    private String bnus;
    
    public Vlis(String name, String date, String amount){
        this.name = name;
        this.date = date;
        this.amount = amount;
        System.out.println(date);
    }
    
    public Vlis(String bsal, String bnus){
        this.bnus = bnus;
        this.bsal = bsal;
    }
    
    public boolean dateChecker(){
        try{
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            df.parse(date);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean numberChecker(String val){
        try{
            double d = Double.parseDouble(val);
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }
    
    public boolean ExUDval(){
        if(name.equals("") || date.equals("") || date.equals(null) || amount.equals("")){
            JOptionPane.showMessageDialog(null, "One or more fields are empty!", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!dateChecker()){
            JOptionPane.showMessageDialog(null, "Invalid date!", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!numberChecker(amount)){
            JOptionPane.showMessageDialog(null, "Invalid amount!", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else
            return true;
    }
    
    public boolean HRVal(){
        if(bsal.equals("") || bnus.equals("")){
            JOptionPane.showMessageDialog(null, "One or more fields are empty!", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!numberChecker(bsal)){
            JOptionPane.showMessageDialog(null, "Invalid Basic salary!", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!numberChecker(bnus)){
            JOptionPane.showMessageDialog(null, "Invalid Bonus value!", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else
            return true;
    }
    
}
