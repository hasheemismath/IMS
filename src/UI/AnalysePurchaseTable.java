/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Class.Analyse;
import Class.ConnectionProvider;
import Class.Product_Supplier;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class AnalysePurchaseTable extends javax.swing.JFrame {

    /**
     * Creates new form InventryAdd
     */
    Analyse analyse;
    
    public AnalysePurchaseTable() {
        initComponents();
        setVisible(true);
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        
    }
    
    public AnalysePurchaseTable(Analyse analyse){
        initComponents();
        this.analyse=analyse;
        showOnTable();
        showSummary();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    }
    
    String proName="";
    String supName="";
    
    //Display Date in JTable
    // 1 - Fetch data from Database and store it in ArrayList
    public ArrayList<Product_Supplier> getProducts_Supplier(){
        
        ArrayList<Product_Supplier> ProSupList=new ArrayList<>();
        Product_Supplier proSup;
        Connection con= ConnectionProvider.getInstance().getDBConnection();
        String Query;
        
        if(analyse.getPriceMax()==0 && analyse.getPriceMin()==0 &&  analyse.getWarranty()==0 && analyse.getQty()==0){
            
            Query="SELECT * FROM supplierinventory WHERE productid="+analyse.getProductId();
            
            try {
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(Query);
            
                while(rs.next()){
                    
                
                    //Getting Product Name
                    int productId=rs.getInt("productid");
                    String pNameQuery="SELECT * FROM product WHERE productid="+productId;
                    PreparedStatement psPro=con.prepareStatement(pNameQuery);
                    ResultSet rsPro=psPro.executeQuery();
                    while(rsPro.next()){
                       proName=rsPro.getString("productname");
                    }
                
                    //Getting Supplier Name
                    int supId=rs.getInt("supplierid");
                    String sNameQuery="SELECT * FROM supplier WHERE supplierid="+supId;
                    PreparedStatement psSup=con.prepareStatement(sNameQuery);
                    ResultSet rsSup=psSup.executeQuery();
                    while(rsSup.next()){
                        supName=rsSup.getString("companyname");
                    }
                
                    proSup = new Product_Supplier(rs.getInt("productid"),proName,rs.getInt("supplierid"),supName,rs.getDouble("price"),rs.getInt("warranty"),rs.getInt("minquantity"));
                    ProSupList.add(proSup);
                }
           
            } catch (SQLException ex) {
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //Only Max Price is given
        else if(analyse.getPriceMax()!=0 && analyse.getPriceMin()==0 && analyse.getWarranty()==0 && analyse.getQty()==0){
            
            Query="SELECT * FROM supplierinventory WHERE productid="+analyse.getProductId()+" AND price <= "+analyse.getPriceMax();
            
            try {
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(Query);
            
                while(rs.next()){
                    //String proName="";
                    //String supName="";
                
                    //Getting Product Name
                    int productId=rs.getInt("productid");
                    String pNameQuery="SELECT * FROM product WHERE productid="+productId;
                    PreparedStatement psPro=con.prepareStatement(pNameQuery);
                    ResultSet rsPro=psPro.executeQuery();
                    while(rsPro.next()){
                       proName=rsPro.getString("productname");
                    }
                
                    //Getting Supplier Name
                    int supId=rs.getInt("supplierid");
                    String sNameQuery="SELECT * FROM supplier WHERE supplierid="+supId;
                    PreparedStatement psSup=con.prepareStatement(sNameQuery);
                    ResultSet rsSup=psSup.executeQuery();
                    while(rsSup.next()){
                        supName=rsSup.getString("companyname");
                    }
                
                    proSup = new Product_Supplier(rs.getInt("productid"),proName,rs.getInt("supplierid"),supName,rs.getDouble("price"),rs.getInt("warranty"),rs.getInt("minquantity"));
                    ProSupList.add(proSup);
                }
           
            } catch (SQLException ex) {
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //Only Min Price is given
        else if(analyse.getPriceMin()!=0 && analyse.getPriceMax()==0 && analyse.getWarranty()==0 && analyse.getQty()==0){
            
            Query="SELECT * FROM supplierinventory WHERE productid="+analyse.getProductId()+" AND price >= "+analyse.getPriceMin();
            
            try {
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(Query);
            
                while(rs.next()){
                    //String proName="";
                    //String supName="";
                
                    //Getting Product Name
                    int productId=rs.getInt("productid");
                    String pNameQuery="SELECT * FROM product WHERE productid="+productId;
                    PreparedStatement psPro=con.prepareStatement(pNameQuery);
                    ResultSet rsPro=psPro.executeQuery();
                    while(rsPro.next()){
                       proName=rsPro.getString("productname");
                    }
                
                    //Getting Supplier Name
                    int supId=rs.getInt("supplierid");
                    String sNameQuery="SELECT * FROM supplier WHERE supplierid="+supId;
                    PreparedStatement psSup=con.prepareStatement(sNameQuery);
                    ResultSet rsSup=psSup.executeQuery();
                    while(rsSup.next()){
                        supName=rsSup.getString("companyname");
                    }
                
                    proSup = new Product_Supplier(rs.getInt("productid"),proName,rs.getInt("supplierid"),supName,rs.getDouble("price"),rs.getInt("warranty"),rs.getInt("minquantity"));
                    ProSupList.add(proSup);
                }
           
            } catch (SQLException ex) {
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //Only Warranty is given
        else if(analyse.getWarranty()!=0 && analyse.getPriceMax()==0 && analyse.getPriceMin()==0 && analyse.getQty()==0){
            
            Query="SELECT * FROM supplierinventory WHERE productid="+analyse.getProductId()+" AND warranty >= "+analyse.getWarranty();
            
            try {
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(Query);
            
                while(rs.next()){
                    //String proName="";
                    //String supName="";
                
                    //Getting Product Name
                    int productId=rs.getInt("productid");
                    String pNameQuery="SELECT * FROM product WHERE productid="+productId;
                    PreparedStatement psPro=con.prepareStatement(pNameQuery);
                    ResultSet rsPro=psPro.executeQuery();
                    while(rsPro.next()){
                       proName=rsPro.getString("productname");
                    }
                
                    //Getting Supplier Name
                    int supId=rs.getInt("supplierid");
                    String sNameQuery="SELECT * FROM supplier WHERE supplierid="+supId;
                    PreparedStatement psSup=con.prepareStatement(sNameQuery);
                    ResultSet rsSup=psSup.executeQuery();
                    while(rsSup.next()){
                        supName=rsSup.getString("companyname");
                    }
                
                    proSup = new Product_Supplier(rs.getInt("productid"),proName,rs.getInt("supplierid"),supName,rs.getDouble("price"),rs.getInt("warranty"),rs.getInt("minquantity"));
                    ProSupList.add(proSup);
                }
           
            } catch (SQLException ex) {
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //Only Quantity is given
        else if(analyse.getQty()!=0 && analyse.getPriceMax()==0 && analyse.getPriceMin()==0 && analyse.getWarranty()==0){
            
            Query="SELECT * FROM supplierinventory WHERE productid="+analyse.getProductId()+" AND minquantity <= "+analyse.getQty();
            
            try {
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(Query);
            
                while(rs.next()){
                    //String proName="";
                    //String supName="";
                
                    //Getting Product Name
                    int productId=rs.getInt("productid");
                    String pNameQuery="SELECT * FROM product WHERE productid="+productId;
                    PreparedStatement psPro=con.prepareStatement(pNameQuery);
                    ResultSet rsPro=psPro.executeQuery();
                    while(rsPro.next()){
                       proName=rsPro.getString("productname");
                    }
                
                    //Getting Supplier Name
                    int supId=rs.getInt("supplierid");
                    String sNameQuery="SELECT * FROM supplier WHERE supplierid="+supId;
                    PreparedStatement psSup=con.prepareStatement(sNameQuery);
                    ResultSet rsSup=psSup.executeQuery();
                    while(rsSup.next()){
                        supName=rsSup.getString("companyname");
                    }
                
                    proSup = new Product_Supplier(rs.getInt("productid"),proName,rs.getInt("supplierid"),supName,rs.getDouble("price"),rs.getInt("warranty"),rs.getInt("minquantity"));
                    ProSupList.add(proSup);
                }
           
            } catch (SQLException ex) {
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //Only Max and Min Prices are given
        else if( analyse.getPriceMax()!=0 && analyse.getPriceMin()!=0 && analyse.getWarranty()==0 && analyse.getQty()==0){
            
            Query="SELECT * FROM supplierinventory WHERE productid="+analyse.getProductId()+" AND price >= "+analyse.getPriceMin()+" AND price <= "+analyse.getPriceMax();
            
            try {
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(Query);
            
                while(rs.next()){
                    //String proName="";
                    //String supName="";
                
                    //Getting Product Name
                    int productId=rs.getInt("productid");
                    String pNameQuery="SELECT * FROM product WHERE productid="+productId;
                    PreparedStatement psPro=con.prepareStatement(pNameQuery);
                    ResultSet rsPro=psPro.executeQuery();
                    while(rsPro.next()){
                       proName=rsPro.getString("productname");
                    }
                
                    //Getting Supplier Name
                    int supId=rs.getInt("supplierid");
                    String sNameQuery="SELECT * FROM supplier WHERE supplierid="+supId;
                    PreparedStatement psSup=con.prepareStatement(sNameQuery);
                    ResultSet rsSup=psSup.executeQuery();
                    while(rsSup.next()){
                        supName=rsSup.getString("companyname");
                    }
                
                    proSup = new Product_Supplier(rs.getInt("productid"),proName,rs.getInt("supplierid"),supName,rs.getDouble("price"),rs.getInt("warranty"),rs.getInt("minquantity"));
                    ProSupList.add(proSup);
                }
           
            } catch (SQLException ex) {
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //Only Max, Min Prices and Warranty are given
        else if( analyse.getPriceMax()!=0 && analyse.getPriceMin()!=0 && analyse.getWarranty()!=0 && analyse.getQty()==0){
            
            Query="SELECT * FROM supplierinventory WHERE productid="+analyse.getProductId()+" AND price >= "+analyse.getPriceMin()+" AND price <= "+analyse.getPriceMax()+" AND warranty >= "+analyse.getWarranty();
            
            try {
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(Query);
            
                while(rs.next()){
                    //String proName="";
                    //String supName="";
                
                    //Getting Product Name
                    int productId=rs.getInt("productid");
                    String pNameQuery="SELECT * FROM product WHERE productid="+productId;
                    PreparedStatement psPro=con.prepareStatement(pNameQuery);
                    ResultSet rsPro=psPro.executeQuery();
                    while(rsPro.next()){
                       proName=rsPro.getString("productname");
                    }
                
                    //Getting Supplier Name
                    int supId=rs.getInt("supplierid");
                    String sNameQuery="SELECT * FROM supplier WHERE supplierid="+supId;
                    PreparedStatement psSup=con.prepareStatement(sNameQuery);
                    ResultSet rsSup=psSup.executeQuery();
                    while(rsSup.next()){
                        supName=rsSup.getString("companyname");
                    }
                
                    proSup = new Product_Supplier(rs.getInt("productid"),proName,rs.getInt("supplierid"),supName,rs.getDouble("price"),rs.getInt("warranty"),rs.getInt("minquantity"));
                    ProSupList.add(proSup);
                }
           
            } catch (SQLException ex) {
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //Only Max, Min Prices and Quantity are given
        else if( analyse.getPriceMax()!=0 && analyse.getPriceMin()!=0 && analyse.getWarranty()==0 && analyse.getQty()!=0){
            
            Query="SELECT * FROM supplierinventory WHERE productid="+analyse.getProductId()+" AND price >= "+analyse.getPriceMin()+" AND price <= "+analyse.getPriceMax()+" AND minquantity <= "+analyse.getQty();
            
            try {
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(Query);
            
                while(rs.next()){
                    //String proName="";
                    //String supName="";
                
                    //Getting Product Name
                    int productId=rs.getInt("productid");
                    String pNameQuery="SELECT * FROM product WHERE productid="+productId;
                    PreparedStatement psPro=con.prepareStatement(pNameQuery);
                    ResultSet rsPro=psPro.executeQuery();
                    while(rsPro.next()){
                       proName=rsPro.getString("productname");
                    }
                
                    //Getting Supplier Name
                    int supId=rs.getInt("supplierid");
                    String sNameQuery="SELECT * FROM supplier WHERE supplierid="+supId;
                    PreparedStatement psSup=con.prepareStatement(sNameQuery);
                    ResultSet rsSup=psSup.executeQuery();
                    while(rsSup.next()){
                        supName=rsSup.getString("companyname");
                    }
                
                    proSup = new Product_Supplier(rs.getInt("productid"),proName,rs.getInt("supplierid"),supName,rs.getDouble("price"),rs.getInt("warranty"),rs.getInt("minquantity"));
                    ProSupList.add(proSup);
                }
           
            } catch (SQLException ex) {
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //Only Max Price and Quantity are given
        else if( analyse.getPriceMax()!=0 && analyse.getPriceMin()==0 && analyse.getWarranty()==0 && analyse.getQty()!=0){
            
            Query="SELECT * FROM supplierinventory WHERE productid="+analyse.getProductId()+" AND price <= "+analyse.getPriceMax()+" AND minquantity <= "+analyse.getQty();
            
            try {
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(Query);
            
                while(rs.next()){
                    //String proName="";
                    //String supName="";
                
                    //Getting Product Name
                    int productId=rs.getInt("productid");
                    String pNameQuery="SELECT * FROM product WHERE productid="+productId;
                    PreparedStatement psPro=con.prepareStatement(pNameQuery);
                    ResultSet rsPro=psPro.executeQuery();
                    while(rsPro.next()){
                       proName=rsPro.getString("productname");
                    }
                
                    //Getting Supplier Name
                    int supId=rs.getInt("supplierid");
                    String sNameQuery="SELECT * FROM supplier WHERE supplierid="+supId;
                    PreparedStatement psSup=con.prepareStatement(sNameQuery);
                    ResultSet rsSup=psSup.executeQuery();
                    while(rsSup.next()){
                        supName=rsSup.getString("companyname");
                    }
                
                    proSup = new Product_Supplier(rs.getInt("productid"),proName,rs.getInt("supplierid"),supName,rs.getDouble("price"),rs.getInt("warranty"),rs.getInt("minquantity"));
                    ProSupList.add(proSup);
                }
           
            } catch (SQLException ex) {
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //Only Max Price and Warranty are given
        else if( analyse.getPriceMax()!=0 && analyse.getPriceMin()==0 && analyse.getWarranty()!=0 && analyse.getQty()==0){
            
            Query="SELECT * FROM supplierinventory WHERE productid="+analyse.getProductId()+" AND price <= "+analyse.getPriceMax()+" AND warranty >= "+analyse.getWarranty();
            
            try {
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(Query);
            
                while(rs.next()){
                    //String proName="";
                    //String supName="";
                
                    //Getting Product Name
                    int productId=rs.getInt("productid");
                    String pNameQuery="SELECT * FROM product WHERE productid="+productId;
                    PreparedStatement psPro=con.prepareStatement(pNameQuery);
                    ResultSet rsPro=psPro.executeQuery();
                    while(rsPro.next()){
                       proName=rsPro.getString("productname");
                    }
                
                    //Getting Supplier Name
                    int supId=rs.getInt("supplierid");
                    String sNameQuery="SELECT * FROM supplier WHERE supplierid="+supId;
                    PreparedStatement psSup=con.prepareStatement(sNameQuery);
                    ResultSet rsSup=psSup.executeQuery();
                    while(rsSup.next()){
                        supName=rsSup.getString("companyname");
                    }
                
                    proSup = new Product_Supplier(rs.getInt("productid"),proName,rs.getInt("supplierid"),supName,rs.getDouble("price"),rs.getInt("warranty"),rs.getInt("minquantity"));
                    ProSupList.add(proSup);
                }
           
            } catch (SQLException ex) {
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //Only Max Price, Warranty and Quantity are given
        else if( analyse.getPriceMax()!=0 && analyse.getPriceMin()==0 && analyse.getWarranty()!=0 && analyse.getQty()!=0){
            
            Query="SELECT * FROM supplierinventory WHERE productid="+analyse.getProductId()+" AND price <= "+analyse.getPriceMax()+" AND warranty >= "+analyse.getWarranty()+" AND minquantity <= "+analyse.getQty();
            
            try {
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(Query);
            
                while(rs.next()){
                    //String proName="";
                    //String supName="";
                
                    //Getting Product Name
                    int productId=rs.getInt("productid");
                    String pNameQuery="SELECT * FROM product WHERE productid="+productId;
                    PreparedStatement psPro=con.prepareStatement(pNameQuery);
                    ResultSet rsPro=psPro.executeQuery();
                    while(rsPro.next()){
                       proName=rsPro.getString("productname");
                    }
                
                    //Getting Supplier Name
                    int supId=rs.getInt("supplierid");
                    String sNameQuery="SELECT * FROM supplier WHERE supplierid="+supId;
                    PreparedStatement psSup=con.prepareStatement(sNameQuery);
                    ResultSet rsSup=psSup.executeQuery();
                    while(rsSup.next()){
                        supName=rsSup.getString("companyname");
                    }
                
                    proSup = new Product_Supplier(rs.getInt("productid"),proName,rs.getInt("supplierid"),supName,rs.getDouble("price"),rs.getInt("warranty"),rs.getInt("minquantity"));
                    ProSupList.add(proSup);
                }
           
            } catch (SQLException ex) {
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //Only Min Price and Quantity are given
        else if( analyse.getPriceMax()==0 && analyse.getPriceMin()!=0 && analyse.getWarranty()==0 && analyse.getQty()!=0){
            
            Query="SELECT * FROM supplierinventory WHERE productid="+analyse.getProductId()+" AND price >= "+analyse.getPriceMin()+" AND minquantity <= "+analyse.getQty();
            
            try {
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(Query);
            
                while(rs.next()){
                    //String proName="";
                    //String supName="";
                
                    //Getting Product Name
                    int productId=rs.getInt("productid");
                    String pNameQuery="SELECT * FROM product WHERE productid="+productId;
                    PreparedStatement psPro=con.prepareStatement(pNameQuery);
                    ResultSet rsPro=psPro.executeQuery();
                    while(rsPro.next()){
                       proName=rsPro.getString("productname");
                    }
                
                    //Getting Supplier Name
                    int supId=rs.getInt("supplierid");
                    String sNameQuery="SELECT * FROM supplier WHERE supplierid="+supId;
                    PreparedStatement psSup=con.prepareStatement(sNameQuery);
                    ResultSet rsSup=psSup.executeQuery();
                    while(rsSup.next()){
                        supName=rsSup.getString("companyname");
                    }
                
                    proSup = new Product_Supplier(rs.getInt("productid"),proName,rs.getInt("supplierid"),supName,rs.getDouble("price"),rs.getInt("warranty"),rs.getInt("minquantity"));
                    ProSupList.add(proSup);
                }
           
            } catch (SQLException ex) {
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //Only Min Price and Warranty are given
        else if( analyse.getPriceMax()==0 && analyse.getPriceMin()!=0 && analyse.getWarranty()!=0 && analyse.getQty()==0){
            
            Query="SELECT * FROM supplierinventory WHERE productid="+analyse.getProductId()+" AND price >= "+analyse.getPriceMin()+" AND warranty >= "+analyse.getWarranty();
            
            try {
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(Query);
            
                while(rs.next()){
                    //String proName="";
                    //String supName="";
                
                    //Getting Product Name
                    int productId=rs.getInt("productid");
                    String pNameQuery="SELECT * FROM product WHERE productid="+productId;
                    PreparedStatement psPro=con.prepareStatement(pNameQuery);
                    ResultSet rsPro=psPro.executeQuery();
                    while(rsPro.next()){
                       proName=rsPro.getString("productname");
                    }
                
                    //Getting Supplier Name
                    int supId=rs.getInt("supplierid");
                    String sNameQuery="SELECT * FROM supplier WHERE supplierid="+supId;
                    PreparedStatement psSup=con.prepareStatement(sNameQuery);
                    ResultSet rsSup=psSup.executeQuery();
                    while(rsSup.next()){
                        supName=rsSup.getString("companyname");
                    }
                
                    proSup = new Product_Supplier(rs.getInt("productid"),proName,rs.getInt("supplierid"),supName,rs.getDouble("price"),rs.getInt("warranty"),rs.getInt("minquantity"));
                    ProSupList.add(proSup);
                }
           
            } catch (SQLException ex) {
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //Only Min Price, Warranty and Quantity are given
        else if( analyse.getPriceMax()==0 && analyse.getPriceMin()!=0 && analyse.getWarranty()!=0 && analyse.getQty()!=0){
            
            Query="SELECT * FROM supplierinventory WHERE productid="+analyse.getProductId()+" AND price >= "+analyse.getPriceMin()+" AND warranty >= "+analyse.getWarranty()+" AND minquantity <= "+analyse.getQty();
            
            try {
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(Query);
            
                while(rs.next()){
                    //String proName="";
                    //String supName="";
                
                    //Getting Product Name
                    int productId=rs.getInt("productid");
                    String pNameQuery="SELECT * FROM product WHERE productid="+productId;
                    PreparedStatement psPro=con.prepareStatement(pNameQuery);
                    ResultSet rsPro=psPro.executeQuery();
                    while(rsPro.next()){
                       proName=rsPro.getString("productname");
                    }
                
                    //Getting Supplier Name
                    int supId=rs.getInt("supplierid");
                    String sNameQuery="SELECT * FROM supplier WHERE supplierid="+supId;
                    PreparedStatement psSup=con.prepareStatement(sNameQuery);
                    ResultSet rsSup=psSup.executeQuery();
                    while(rsSup.next()){
                        supName=rsSup.getString("companyname");
                    }
                
                    proSup = new Product_Supplier(rs.getInt("productid"),proName,rs.getInt("supplierid"),supName,rs.getDouble("price"),rs.getInt("warranty"),rs.getInt("minquantity"));
                    ProSupList.add(proSup);
                }
           
            } catch (SQLException ex) {
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //Only Warranty and Quantity are given
        else if( analyse.getPriceMax()==0 && analyse.getPriceMin()==0 && analyse.getWarranty()!=0 && analyse.getQty()!=0){
            
            Query="SELECT * FROM supplierinventory WHERE productid="+analyse.getProductId()+" AND warranty >= "+analyse.getWarranty()+" AND minquantity <= "+analyse.getQty();
            
            try {
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(Query);
            
                while(rs.next()){
                    //String proName="";
                    //String supName="";
                
                    //Getting Product Name
                    int productId=rs.getInt("productid");
                    String pNameQuery="SELECT * FROM product WHERE productid="+productId;
                    PreparedStatement psPro=con.prepareStatement(pNameQuery);
                    ResultSet rsPro=psPro.executeQuery();
                    while(rsPro.next()){
                       proName=rsPro.getString("productname");
                    }
                
                    //Getting Supplier Name
                    int supId=rs.getInt("supplierid");
                    String sNameQuery="SELECT * FROM supplier WHERE supplierid="+supId;
                    PreparedStatement psSup=con.prepareStatement(sNameQuery);
                    ResultSet rsSup=psSup.executeQuery();
                    while(rsSup.next()){
                        supName=rsSup.getString("companyname");
                    }
                
                    proSup = new Product_Supplier(rs.getInt("productid"),proName,rs.getInt("supplierid"),supName,rs.getDouble("price"),rs.getInt("warranty"),rs.getInt("minquantity"));
                    ProSupList.add(proSup);
                }
           
            } catch (SQLException ex) {
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //Only Warranty and Quantity are given
        else{
            
            Query="SELECT * FROM supplierinventory WHERE productid="+analyse.getProductId()+" AND warranty >= "+analyse.getWarranty()+" AND minquantity <= "+analyse.getQty()+" AND price BETWEEN "+analyse.getPriceMin()+" AND "+analyse.getPriceMax();
            
            try {
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(Query);
            
                while(rs.next()){
                    //String proName="";
                    //String supName="";
                
                    //Getting Product Name
                    int productId=rs.getInt("productid");
                    String pNameQuery="SELECT * FROM product WHERE productid="+productId;
                    PreparedStatement psPro=con.prepareStatement(pNameQuery);
                    ResultSet rsPro=psPro.executeQuery();
                    while(rsPro.next()){
                       proName=rsPro.getString("productname");
                    }
                
                    //Getting Supplier Name
                    int supId=rs.getInt("supplierid");
                    String sNameQuery="SELECT * FROM supplier WHERE supplierid="+supId;
                    PreparedStatement psSup=con.prepareStatement(sNameQuery);
                    ResultSet rsSup=psSup.executeQuery();
                    while(rsSup.next()){
                        supName=rsSup.getString("companyname");
                    }
                
                    proSup = new Product_Supplier(rs.getInt("productid"),proName,rs.getInt("supplierid"),supName,rs.getDouble("price"),rs.getInt("warranty"),rs.getInt("minquantity"));
                    ProSupList.add(proSup);
                }
           
            } catch (SQLException ex) {
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return ProSupList;
        
    }
    
    // 2 - Populate JTable
    public void showOnTable(){
    
        ArrayList<Product_Supplier> ProSup = getProducts_Supplier();
        
        if(ProSup.size()==0){
            JOptionPane.showMessageDialog(null, "No Match Found!!!");
            return;
        }
            
        DefaultTableModel model= (DefaultTableModel)tbl_analyse.getModel();
        
        double Price=0;
        Connection con =ConnectionProvider.getInstance().getDBConnection();
        String query="SELECT * FROM product WHERE productid="+analyse.getProductId();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Price=rs.getDouble("price");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnalysePurchaseTable.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        //To clear table before inserting, to avoid duplicate
        model.setRowCount(0);
        
        Object[] row=new Object[6];
        
        for(int i=0;i<ProSup.size();i++){
            row[0]=ProSup.get(i).getProductName();
            row[1]=ProSup.get(i).getSupplierName();
            row[2]=ProSup.get(i).getPrice();
            row[3]=ProSup.get(i).getWarranty();
            row[4]=ProSup.get(i).getQty();
            row[5]=(Price-ProSup.get(i).getPrice())*100/Price;
            
            
            
            model.addRow(row);
        }
        
    }
    
    public void showSummary(){
    
        ArrayList<Product_Supplier> ProSup = getProducts_Supplier();
        
        if(ProSup.size()==0){
            //JOptionPane.showMessageDialog(null, "No Match Found!!!");
            //AnalysePurchase obj = new AnalysePurchase();
            //obj.setVisible(true);
            //obj.setLocationRelativeTo(null);
            dispose();
        }
        
        //Margin
        double Price=0;
        Connection con =ConnectionProvider.getInstance().getDBConnection();
        String query="SELECT * FROM product WHERE productid="+analyse.getProductId();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Price=rs.getDouble("price");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnalysePurchaseTable.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        double margin=0;
        String Supplier="";
        
        margin=(Price-ProSup.get(0).getPrice())*100/Price;
        for(int i=0;i<ProSup.size();i++){
            if(margin<=((Price-ProSup.get(i).getPrice())*100/Price)){
                margin=(Price-ProSup.get(i).getPrice())*100/Price;
                Supplier=ProSup.get(i).getSupplierName();
            }
        }
        lbl_maxMar.setText(Supplier);
        txt_MaxMargin.setText(Double.toString(margin));
        
        margin=(Price-ProSup.get(0).getPrice())*100/Price;
        for(int i=0;i<ProSup.size();i++){
            if(margin>=((Price-ProSup.get(i).getPrice())*100/Price)){
                margin=(Price-ProSup.get(i).getPrice())*100/Price;
                Supplier=ProSup.get(i).getSupplierName();
            }
        }
        lbl_minMar.setText(Supplier);
        txt_MinMargin.setText("  "+Double.toString(margin)+" ");
        
        
        //Supplier's Price
        double SupplierPrice=0;
        
        SupplierPrice=ProSup.get(0).getPrice();
        for(int i=0;i<ProSup.size();i++){
            if(SupplierPrice<=ProSup.get(i).getPrice()){
                SupplierPrice=ProSup.get(i).getPrice();
                Supplier=ProSup.get(i).getSupplierName();
            }
        }
        lbl_maxPrice.setText(Supplier);
        txt_MaxPrice.setText("  "+Double.toString(SupplierPrice)+" ");
        
        SupplierPrice=ProSup.get(0).getPrice();
        for(int i=0;i<ProSup.size();i++){
            if(SupplierPrice>=ProSup.get(i).getPrice()){
                SupplierPrice=ProSup.get(i).getPrice();
                Supplier=ProSup.get(i).getSupplierName();
            }
        }
        lbl_minPrice.setText(Supplier);
        txt_MinPrice.setText("  "+Double.toString(SupplierPrice)+" ");
        
        //Warranty
        
        int warranty=0;
        
        warranty=ProSup.get(0).getWarranty();
        for(int i=0;i<ProSup.size();i++){
            if(warranty<=ProSup.get(i).getWarranty()){
                warranty=ProSup.get(i).getWarranty();
                Supplier=ProSup.get(i).getSupplierName();
            }
        }
        lbl_maxWarranty.setText(Supplier);
        txt_MaxWarranty.setText("   "+Integer.toString(warranty)+" ");
        
        warranty=ProSup.get(0).getWarranty();
        for(int i=0;i<ProSup.size();i++){
            if(warranty>=ProSup.get(i).getWarranty()){
                warranty=ProSup.get(i).getWarranty();
                Supplier=ProSup.get(i).getSupplierName();
            }
        }
        lbl_minWarranty.setText(Supplier);
        txt_MinWarranty.setText("   "+Integer.toString(warranty)+" ");
        
        
        //Quantity
        
        int qty=0;
        
        qty=ProSup.get(0).getQty();
        for(int i=0;i<ProSup.size();i++){
            if(qty<=ProSup.get(i).getQty()){
                qty=ProSup.get(i).getQty();
                Supplier=ProSup.get(i).getSupplierName();
            }
        }
        lbl_maxQty.setText(Supplier);
        txt_MaxQty.setText("   "+Integer.toString(qty)+" ");
        
        qty=ProSup.get(0).getQty();
        for(int i=0;i<ProSup.size();i++){
            if(qty>=ProSup.get(i).getQty()){
                qty=ProSup.get(i).getQty();
                Supplier=ProSup.get(i).getSupplierName();
            }
        }
        lbl_minQty.setText(Supplier);
        txt_MinQty.setText("   "+Integer.toString(qty)+" ");
        
        
        
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        btnadd2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_analyse = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_MaxMargin = new javax.swing.JTextField();
        txt_MinMargin = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lbl_minMar = new javax.swing.JLabel();
        lbl_maxMar = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_MaxPrice = new javax.swing.JTextField();
        txt_MinPrice = new javax.swing.JTextField();
        lbl_minPrice = new javax.swing.JLabel();
        lbl_maxPrice = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txt_MaxWarranty = new javax.swing.JTextField();
        txt_MinWarranty = new javax.swing.JTextField();
        lbl_maxWarranty = new javax.swing.JLabel();
        lbl_minWarranty = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txt_MaxQty = new javax.swing.JTextField();
        txt_MinQty = new javax.swing.JTextField();
        lbl_minQty = new javax.swing.JLabel();
        lbl_maxQty = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NA | Analyse Purchase Table");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 31, 63));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/smart devices.jpg"))); // NOI18N
        jLabel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        btnadd2.setBackground(new java.awt.Color(204, 204, 255));
        btnadd2.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnadd2.setForeground(new java.awt.Color(102, 102, 102));
        btnadd2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/profit.png"))); // NOI18N
        btnadd2.setText("Generate Report");
        btnadd2.setBorderPainted(false);
        btnadd2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnadd2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnadd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnadd2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnadd2, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(btn_back))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(jLabel7))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btn_back)
                .addGap(85, 85, 85)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(btnadd2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        tbl_analyse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Supplier Name", "Price", "Warranty(Months)", "Min.Quantity/Order", "Margin(%)"
            }
        ));
        jScrollPane1.setViewportView(tbl_analyse);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel5.setText("Margin");

        jLabel13.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel13.setText("Maximum");

        txt_MaxMargin.setEditable(false);
        txt_MaxMargin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MaxMarginActionPerformed(evt);
            }
        });

        txt_MinMargin.setEditable(false);

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel12.setText("Minimum");

        lbl_minMar.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbl_minMar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_minMar.setText("jLabel1");

        lbl_maxMar.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbl_maxMar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(630, Short.MAX_VALUE)
                .addComponent(lbl_minMar, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lbl_maxMar, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(txt_MaxMargin, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_MinMargin, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(169, 169, 169))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_minMar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_maxMar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_MaxMargin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MinMargin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel6.setText("Price");

        txt_MaxPrice.setEditable(false);
        txt_MaxPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MaxPriceActionPerformed(evt);
            }
        });

        txt_MinPrice.setEditable(false);

        lbl_minPrice.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbl_minPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_minPrice.setText("jLabel1");

        lbl_maxPrice.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbl_maxPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_maxPrice.setText("jLabel1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(lbl_maxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_minPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(txt_MaxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_MinPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_minPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_maxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_MinPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(txt_MaxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        jLabel17.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel17.setText("Warranty");

        txt_MaxWarranty.setEditable(false);
        txt_MaxWarranty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MaxWarrantyActionPerformed(evt);
            }
        });

        txt_MinWarranty.setEditable(false);

        lbl_maxWarranty.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbl_maxWarranty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_maxWarranty.setText("jLabel1");

        lbl_minWarranty.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbl_minWarranty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_minWarranty.setText("jLabel1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_maxWarranty, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_minWarranty, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(txt_MaxWarranty, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_MinWarranty, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_maxWarranty, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_minWarranty, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_MinWarranty, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_MaxWarranty, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));

        jLabel20.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel20.setText("Quantity/Order");

        txt_MaxQty.setEditable(false);
        txt_MaxQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MaxQtyActionPerformed(evt);
            }
        });

        txt_MinQty.setEditable(false);
        txt_MinQty.setBackground(new java.awt.Color(255, 255, 255));

        lbl_minQty.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbl_minQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_minQty.setText("jLabel1");

        lbl_maxQty.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbl_maxQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_maxQty.setText("jLabel1");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbl_maxQty, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_minQty, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(txt_MaxQty, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_MinQty, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_maxQty, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_minQty, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_MinQty, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MaxQty, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(127, 127, 127))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        AnalysePurchase obj = new AnalysePurchase();
        obj.setVisible(true);
        obj.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void txt_MaxPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MaxPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MaxPriceActionPerformed

    private void txt_MaxWarrantyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MaxWarrantyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MaxWarrantyActionPerformed

    private void txt_MaxQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MaxQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MaxQtyActionPerformed

    private void txt_MaxMarginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MaxMarginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MaxMarginActionPerformed

    private void btnadd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnadd2ActionPerformed
        try{
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Report.pdf"));

            //Open the Document
            document.open();

            //Add an Image to Report
            //com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("appa.jpg");
            //document.add(image);

            //Things writen herewill be included in Documnet
            document.add(new Paragraph("Purchase Analysis",FontFactory.getFont(FontFactory.TIMES_BOLD,18,Font.BOLD,BaseColor.BLUE)));
            document.add(new Paragraph(new Date().toString()));
            document.add(new Paragraph("----------------------------------------------------------------------------------------------------------"));

            //Adding List to Document
            com.itextpdf.text.List list=new com.itextpdf.text.List(true);
            list.add("Product ID : "+analyse.getProductId());
            list.add("Product Name : "+ proName);
            list.add("Minimum Price requested : "+analyse.getPriceMin());
            list.add("Maximum Price requested : "+analyse.getPriceMin());
            list.add("Warranty Peroid expected (Months): "+analyse.getPriceMin());
            list.add("Quantity to be Ordered : "+analyse.getPriceMin()+"\n\n");
            

            document.add(list);

            
            //Adding a Table
            PdfPTable table = new PdfPTable(3); //Argument is the No.of columns in table

            //Table Title
            PdfPCell cell = new PdfPCell(new Paragraph("Results"));
            cell.setColspan(3);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);

            table.addCell("");
            table.addCell("Maximum");
            table.addCell("Minimum");

            table.addCell("Margin");
            table.addCell(txt_MaxMargin.getText());
            table.addCell(txt_MinMargin.getText());

            table.addCell("Price");
            table.addCell(txt_MaxPrice.getText());
            table.addCell(txt_MinPrice.getText());

            table.addCell("Warranty");
            table.addCell(txt_MaxWarranty.getText());
            table.addCell(txt_MinWarranty.getText());

            table.addCell("Quantity to be Ordered");
            table.addCell(txt_MaxQty.getText());
            table.addCell(txt_MinQty.getText());
            document.add(table);

            //Close the Document
            document.close();
            JOptionPane.showMessageDialog(null, "Report Generated");

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnadd2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AnalysePurchaseTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AnalysePurchaseTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AnalysePurchaseTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnalysePurchaseTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AnalysePurchaseTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btnadd2;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_maxMar;
    private javax.swing.JLabel lbl_maxPrice;
    private javax.swing.JLabel lbl_maxQty;
    private javax.swing.JLabel lbl_maxWarranty;
    private javax.swing.JLabel lbl_minMar;
    private javax.swing.JLabel lbl_minPrice;
    private javax.swing.JLabel lbl_minQty;
    private javax.swing.JLabel lbl_minWarranty;
    private javax.swing.JTable tbl_analyse;
    private javax.swing.JTextField txt_MaxMargin;
    private javax.swing.JTextField txt_MaxPrice;
    private javax.swing.JTextField txt_MaxQty;
    private javax.swing.JTextField txt_MaxWarranty;
    private javax.swing.JTextField txt_MinMargin;
    private javax.swing.JTextField txt_MinPrice;
    private javax.swing.JTextField txt_MinQty;
    private javax.swing.JTextField txt_MinWarranty;
    // End of variables declaration//GEN-END:variables
}
