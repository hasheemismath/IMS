package UI;

import Class.ConnectionProvider;
import Class.Product;
import Class.Product_Supplier;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class ProductSupplier_Window extends javax.swing.JFrame 
{

    public ProductSupplier_Window() 
    {
        initComponents();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        //showOnTable();
        //loadProducts();
        //loadSupplier();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    
    public void loadProducts(){
    
        try {
            Connection con= ConnectionProvider.getInstance().getDBConnection();
            
            String query="SELECT * FROM product";
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                String ProductName=rs.getString("productname");
                cb_products.addItem(ProductName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadSupplier(){
    
        try {
            Connection con= ConnectionProvider.getInstance().getDBConnection();;
            
            String query="SELECT * FROM supplier";
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next())
            {
                String SupplierName=rs.getString("companyname");
                cb_supplier.addItem(SupplierName);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Check input Fields
    public boolean checkInputs()
    {
    
        if(!txt_ProductId.getText().isEmpty())
        {
            
            if(!txt_SupplierId.getText().isEmpty())
            {
                
                if(!txt_price.getText().isEmpty())
                {
                    
                    if(!txt_warranty.getText().isEmpty())
                    {
                        
                        if(!txt_qty.getText().isEmpty())
                        {
                            
                            try
                            {
                                Double.parseDouble(txt_price.getText());
                        
                                try
                                {
                                    Integer.parseInt(txt_qty.getText());
                                    return true;
                            
                                }catch(Exception e)
                                {
                                    JOptionPane.showMessageDialog(null, "Invalid Quantity Input!!!");
                                    return false;
                                }
                        
                            }catch(Exception e)
                            {
                                JOptionPane.showMessageDialog(null, "Invalid Price Input!!!");
                                return false;
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Quantity Field cannot be Empty");
                            return false;
                        }
                        
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Warranty Field cannot be Empty");
                        return false;
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Price Field cannot be Empty");
                    return false;
                }
            }    
            else
            {
                 JOptionPane.showMessageDialog(null, "Supplier Field cannot be Empty");   
                 return false;
            }  
        }    
        else
        {
                 JOptionPane.showMessageDialog(null, "Product Field cannot be Empty");  
                 return false;
        }              
    }
    
    //Display Date in JTable
    // 1 - Fetch data from Database and store it in ArrayList
    public ArrayList<Product_Supplier> getProducts_Supplier()
    {
        ArrayList<Product_Supplier> ProSupList=new ArrayList<>();
        Product_Supplier proSup;
        Connection con= ConnectionProvider.getInstance().getDBConnection();
        String Query="SELECT * FROM supplierinventory";
            
        try {
            Statement stm=con.createStatement();
            ResultSet rs=stm.executeQuery(Query);
            
            while(rs.next()){
                String proName="";
                String supName="";
                
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
        return ProSupList;    
    }
    
    // 2 - Populate JTable
    public void showOnTable(){
    
        ArrayList<Product_Supplier> ProSup = getProducts_Supplier();
        DefaultTableModel model= (DefaultTableModel)jTable_Products.getModel();
       
        //To clear table before inserting, to avoid duplicate
        model.setRowCount(0);
        
        Object[] row=new Object[5];
        
        for(int i=0;i<ProSup.size();i++){
            row[0]=ProSup.get(i).getProductName();
            row[1]=ProSup.get(i).getSupplierName();
            row[2]=ProSup.get(i).getPrice();
            row[3]=ProSup.get(i).getWarranty();
            row[4]=ProSup.get(i).getQty();
            
            
            model.addRow(row);
        }
        
    }
    
    public void showItems(int index){
        
        cb_products.setSelectedItem(getProducts_Supplier().get(index).getProductName());
        txt_ProductId.setText(Integer.toString(getProducts_Supplier().get(index).getProductId()));
        cb_supplier.setSelectedItem(getProducts_Supplier().get(index).getSupplierName());
        txt_SupplierId.setText(Integer.toString(getProducts_Supplier().get(index).getSupplierId()));
        txt_price.setText(Double.toString(getProducts_Supplier().get(index).getPrice()));
        txt_warranty.setText(Integer.toString(getProducts_Supplier().get(index).getWarranty()));
        txt_qty.setText(Integer.toString(getProducts_Supplier().get(index).getQty()));
        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem3 = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Products = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblstockid = new javax.swing.JLabel();
        txt_ProductId = new javax.swing.JTextField();
        lblcategoryname = new javax.swing.JLabel();
        cb_products = new javax.swing.JComboBox<>();
        txt_SupplierId = new javax.swing.JTextField();
        lblstockname = new javax.swing.JLabel();
        btn_newProduct = new javax.swing.JButton();
        btn_newSupplier = new javax.swing.JButton();
        cb_supplier = new javax.swing.JComboBox<>();
        lblstockname1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblprice = new javax.swing.JLabel();
        txt_qty = new javax.swing.JTextField();
        lblquantity = new javax.swing.JLabel();
        txt_price = new javax.swing.JTextField();
        lblquantity1 = new javax.swing.JLabel();
        txt_warranty = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btn_previous = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        btn_first = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnadd = new javax.swing.JButton();
        btnsearch = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        cbbasedon = new javax.swing.JComboBox<>();
        lblbasedon = new javax.swing.JLabel();
        btnRefresh1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuStripNew = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuStripHome = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuStripQuit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("New Airtel | Product Supplier");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));

        jPanel3.setBackground(new java.awt.Color(245, 245, 245));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(0, 31, 63));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 20, 60), 1, true));

        label1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        label1.setForeground(new java.awt.Color(245, 245, 245));
        label1.setText("Product Supplier");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 90, Short.MAX_VALUE)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTable_Products.setBackground(new java.awt.Color(245, 245, 245));
        jTable_Products.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTable_Products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Supplier Name", "Unit Price", "Warranty", "Min Qty/Order"
            }
        ));
        jTable_Products.setToolTipText("Sales Display View");
        jTable_Products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Products);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        lblstockid.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblstockid.setText("Product Name");

        txt_ProductId.setEditable(false);
        txt_ProductId.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        lblcategoryname.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblcategoryname.setText("Product ID");

        cb_products.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cb_products.setEnabled(false);
        cb_products.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_productsActionPerformed(evt);
            }
        });

        txt_SupplierId.setEditable(false);
        txt_SupplierId.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        lblstockname.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblstockname.setText("Supplier Name");

        btn_newProduct.setText("New Product");
        btn_newProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newProductActionPerformed(evt);
            }
        });

        btn_newSupplier.setText("New Supplier");
        btn_newSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newSupplierActionPerformed(evt);
            }
        });

        cb_supplier.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cb_supplier.setEnabled(false);
        cb_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_supplierActionPerformed(evt);
            }
        });

        lblstockname1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblstockname1.setText("Supplier ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblstockid)
                    .addComponent(lblstockname)
                    .addComponent(lblcategoryname)
                    .addComponent(lblstockname1))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_ProductId)
                            .addComponent(cb_products, 0, 227, Short.MAX_VALUE)
                            .addComponent(cb_supplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_newSupplier)
                            .addComponent(btn_newProduct)))
                    .addComponent(txt_SupplierId, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblstockid)
                    .addComponent(cb_products, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_newProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblcategoryname))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cb_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_newSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblstockname))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SupplierId, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblstockname1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(245, 245, 245));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 594, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );

        lblprice.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblprice.setText("Quantity");

        txt_qty.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_qtyKeyReleased(evt);
            }
        });

        lblquantity.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblquantity.setText("Unit Price");

        txt_price.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_priceActionPerformed(evt);
            }
        });
        txt_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_priceKeyReleased(evt);
            }
        });

        lblquantity1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblquantity1.setText("Warranty Period");

        txt_warranty.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_warranty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_warrantyKeyReleased(evt);
            }
        });

        btnRefresh.setBackground(new java.awt.Color(204, 204, 255));
        btnRefresh.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(102, 102, 102));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/002-update.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnRefresh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btndelete.setBackground(new java.awt.Color(220, 20, 60));
        btndelete.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btndelete.setForeground(new java.awt.Color(245, 244, 245));
        btndelete.setText("Delete");
        btndelete.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btndelete.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btn_previous.setBackground(new java.awt.Color(255, 255, 255));
        btn_previous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/previous.png"))); // NOI18N
        btn_previous.setText("Previous");
        btn_previous.setIconTextGap(15);
        btn_previous.setPreferredSize(new java.awt.Dimension(100, 30));
        btn_previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_previousActionPerformed(evt);
            }
        });

        btn_last.setBackground(new java.awt.Color(255, 255, 255));
        btn_last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/last.png"))); // NOI18N
        btn_last.setText("Last");
        btn_last.setIconTextGap(15);
        btn_last.setPreferredSize(new java.awt.Dimension(100, 30));
        btn_last.setRolloverEnabled(false);
        btn_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lastActionPerformed(evt);
            }
        });

        btn_first.setBackground(new java.awt.Color(255, 255, 255));
        btn_first.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/first.png"))); // NOI18N
        btn_first.setText("First");
        btn_first.setIconTextGap(15);
        btn_first.setPreferredSize(new java.awt.Dimension(100, 30));
        btn_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_firstActionPerformed(evt);
            }
        });

        btn_next.setBackground(new java.awt.Color(255, 255, 255));
        btn_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/next.png"))); // NOI18N
        btn_next.setText("Next");
        btn_next.setIconTextGap(15);
        btn_next.setPreferredSize(new java.awt.Dimension(100, 30));
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });

        btnupdate.setBackground(new java.awt.Color(238, 118, 60));
        btnupdate.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnupdate.setForeground(new java.awt.Color(245, 244, 245));
        btnupdate.setText("Update");
        btnupdate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnupdate.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btnadd.setBackground(new java.awt.Color(0, 158, 113));
        btnadd.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnadd.setForeground(new java.awt.Color(245, 245, 245));
        btnadd.setText("Add");
        btnadd.setBorderPainted(false);
        btnadd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnadd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnsearch.setBackground(new java.awt.Color(220, 20, 60));
        btnsearch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnsearch.setForeground(new java.awt.Color(245, 245, 245));
        btnsearch.setText("Search");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        txtsearch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        cbbasedon.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbbasedon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Please Choose --", "Stock ID", "Stock Name", "Price", "Quantity" }));

        lblbasedon.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lblbasedon.setText("Based On");

        btnRefresh1.setBackground(new java.awt.Color(255, 255, 153));
        btnRefresh1.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnRefresh1.setForeground(new java.awt.Color(0, 0, 51));
        btnRefresh1.setText("Report");
        btnRefresh1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnRefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblbasedon, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbasedon, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsearch))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblquantity1)
                                    .addComponent(lblprice)
                                    .addComponent(lblquantity))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_price)
                                    .addComponent(txt_qty)
                                    .addComponent(txt_warranty, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btn_first, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_next, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(btn_previous, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnRefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnsearch)
                            .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbasedon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblbasedon))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btn_previous, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btn_first, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblquantity))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblprice))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_warranty, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblquantity1))))
                        .addContainerGap(26, Short.MAX_VALUE))))
        );

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        menuStripNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuStripNew.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        menuStripNew.setText("New");
        menuStripNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStripNewActionPerformed(evt);
            }
        });
        jMenu1.add(menuStripNew);
        jMenu1.add(jSeparator1);

        menuStripHome.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        menuStripHome.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        menuStripHome.setText("Home");
        menuStripHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuStripHomeMouseClicked(evt);
            }
        });
        menuStripHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStripHomeActionPerformed(evt);
            }
        });
        jMenu1.add(menuStripHome);

        jMenuItem1.setText("Inventry Dashboard");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        menuStripQuit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        menuStripQuit.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        menuStripQuit.setText("Quit");
        menuStripQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStripQuitActionPerformed(evt);
            }
        });
        jMenu1.add(menuStripQuit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Report");
        jMenu2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1326, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuStripNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStripNewActionPerformed
        txt_ProductId.setText("");
    }//GEN-LAST:event_menuStripNewActionPerformed

    private void menuStripQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStripQuitActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Are you sure to QUIT?", "Exit", JOptionPane.INFORMATION_MESSAGE);
        
        if(JOptionPane.YES_OPTION == i)
        {
            this.dispose();
        }
    }//GEN-LAST:event_menuStripQuitActionPerformed

    private void menuStripHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuStripHomeMouseClicked
        //new Dashboard().setVisible(true);
        //new Sales().setVisible(false);
    }//GEN-LAST:event_menuStripHomeMouseClicked

    private void menuStripHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStripHomeActionPerformed
        //new Dashboard().setVisible(true);
        //this.setVisible(false);
    }//GEN-LAST:event_menuStripHomeActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        
        SupplierProductAdd obj= new SupplierProductAdd();
        obj.setVisible(true);
        obj.setLocationRelativeTo(null);
        //obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }//GEN-LAST:event_btnaddActionPerformed

    private void cb_productsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_productsActionPerformed
        try {
            String pName=cb_products.getSelectedItem().toString();
            
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query="SELECT * FROM product WHERE productname='"+pName+"'";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Integer pNum=rs.getInt("productid");
                txt_ProductId.setText(pNum.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(InventryAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cb_productsActionPerformed

    private void jTable_ProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ProductsMouseClicked
        int index=jTable_Products.getSelectedRow();
        showItems(index);
    }//GEN-LAST:event_jTable_ProductsMouseClicked

    int pos;
    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed

        this.setBackground(Color.WHITE);
        pos = 0;
        showItems(pos);
    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_previousActionPerformed

        this.setBackground(Color.WHITE);
        pos--;

        if(pos<0){
            pos =0;
        }

        showItems(pos);
    }//GEN-LAST:event_btn_previousActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed

        this.setBackground(Color.WHITE);
        pos++;

        if(pos>=getProducts_Supplier().size()){
            pos=getProducts_Supplier().size()-1;
        }

        showItems(pos);
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        
        this.setBackground(Color.WHITE);
        pos = getProducts_Supplier().size()-1;
        showItems(pos);
    }//GEN-LAST:event_btn_lastActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        if(checkInputs()) {

            String UpdateQuery=null;
            PreparedStatement ps= null;

            Connection con= ConnectionProvider.getInstance().getDBConnection();

           
                try {

                    UpdateQuery="UPDATE supplierinventory SET price=?,warranty=?,minquantity=? WHERE productid=? and supplierid=?";
                    ps=con.prepareStatement(UpdateQuery);
                    ps.setDouble(1, Double.parseDouble(txt_price.getText()));
                    ps.setInt(2, Integer.parseInt(txt_warranty.getText()));
                    ps.setInt(3, Integer.parseInt(txt_qty.getText()));
                    ps.setInt(4, Integer.parseInt(txt_ProductId.getText()));
                    ps.setInt(5, Integer.parseInt(txt_SupplierId.getText()));

                    ps.executeUpdate();
                    showOnTable();
                    JOptionPane.showMessageDialog(null, "Successfully Updated");

                } catch (SQLException ex) {
                    //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            

        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        if(!txt_ProductId.getText().equals("") && !txt_SupplierId.getText().equals("")){

        
            try {
                Connection con= ConnectionProvider.getInstance().getDBConnection();

                String DeleteQuery="DELETE FROM supplierinventory WHERE productid=? and supplierid=?";
                PreparedStatement ps=con.prepareStatement(DeleteQuery);
                ps.setInt(1, Integer.parseInt(txt_ProductId.getText()));
                ps.setInt(2, Integer.parseInt(txt_SupplierId.getText()));
                ps.executeUpdate();
                showOnTable();
                showItems(pos);
                JOptionPane.showMessageDialog(null, "Successfully Deleted!!!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else{
            JOptionPane.showMessageDialog(null, "Delete unsuccessful : Product ID cannot be Empty");
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsearchActionPerformed

    private void btn_newProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newProductActionPerformed
        InventryAdd obj= new InventryAdd();
        obj.setVisible(true);
        obj.setLocationRelativeTo(null);
    }//GEN-LAST:event_btn_newProductActionPerformed

    private void btn_newSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_newSupplierActionPerformed

    private void cb_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_supplierActionPerformed
        try {
            String sName=cb_supplier.getSelectedItem().toString();
            
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query="SELECT * FROM supplier WHERE companyname='"+sName+"'";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Integer sNum=rs.getInt("supplierid");
                txt_SupplierId.setText(sNum.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(InventryAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cb_supplierActionPerformed

    private void txt_priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_priceActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        showOnTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        //setEnabled(false);
        //setAutoRequestFocus(true);
    }//GEN-LAST:event_formWindowDeactivated

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new InventryManagementDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        showOnTable();
        loadProducts();
        loadSupplier();
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        cb_products.removeAllItems();
        cb_supplier.removeAllItems();
    }//GEN-LAST:event_formWindowLostFocus

    private void txt_priceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_priceKeyReleased
        String priceMin = txt_price.getText();
        String PATTERN = "^[0-9.]+$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(priceMin);
        
        if (!match.matches()) {
            JOptionPane.showMessageDialog(null, "Price amount is Invalid!!!");
            showItems(pos);
            
        } else {
            //txt_validate.setText("");
            
        }
        
        if(priceMin.isEmpty()){
            //txt_validate.setText("");
        }    
    }//GEN-LAST:event_txt_priceKeyReleased

    private void txt_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qtyKeyReleased
        String qty = txt_qty.getText();
        String PATTERN = "^[0-9]+$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(qty);
        
        if (!match.matches()) {
            JOptionPane.showMessageDialog(null, "Quantity should be Numeric!!!");
            showItems(pos);
            
        } else {
            //txt_validate1.setText("");
        }
        
        if(qty.isEmpty()){
            //txt_validate1.setText("");
        }    
    }//GEN-LAST:event_txt_qtyKeyReleased

    private void txt_warrantyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_warrantyKeyReleased
        String qty = txt_warranty.getText();
        String PATTERN = "^[0-9]+$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(qty);
        
        if (!match.matches()) {
            JOptionPane.showMessageDialog(null, "Warranty should be Numeric!!!");
            showItems(pos);
            
        } else {
            //txt_validate2.setText("");
        }
        
        if(qty.isEmpty()){
            //txt_validate2.setText("");
        }    
    }//GEN-LAST:event_txt_warrantyKeyReleased

    private void btnRefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefresh1ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        new Dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenu1ActionPerformed

    public static void main(String args[]) 
    {
        /*Important Content*/
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
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new ProductSupplier_Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRefresh1;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_newProduct;
    private javax.swing.JButton btn_newSupplier;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_previous;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> cb_products;
    private javax.swing.JComboBox<String> cb_supplier;
    private javax.swing.JComboBox<String> cbbasedon;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable_Products;
    private java.awt.Label label1;
    private javax.swing.JLabel lblbasedon;
    private javax.swing.JLabel lblcategoryname;
    private javax.swing.JLabel lblprice;
    private javax.swing.JLabel lblquantity;
    private javax.swing.JLabel lblquantity1;
    private javax.swing.JLabel lblstockid;
    private javax.swing.JLabel lblstockname;
    private javax.swing.JLabel lblstockname1;
    private javax.swing.JMenuItem menuStripHome;
    private javax.swing.JMenuItem menuStripNew;
    private javax.swing.JMenuItem menuStripQuit;
    private javax.swing.JTextField txt_ProductId;
    private javax.swing.JTextField txt_SupplierId;
    private javax.swing.JTextField txt_price;
    private javax.swing.JTextField txt_qty;
    private javax.swing.JTextField txt_warranty;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables
}
