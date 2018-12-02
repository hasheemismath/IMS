package UI;

import Class.Product;
import Class.ConnectionProvider;
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

public class Inventory extends javax.swing.JFrame 
{
    public Inventory() 
    {
        initComponents();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        //showOnTable();
        //loadCategory();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    
    public void loadCategory(){
    
        try {
            Connection con= ConnectionProvider.getInstance().getDBConnection();
            
            String query="SELECT * FROM category";
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                String CategoryName=rs.getString("categoryname");
                cb_category.addItem(CategoryName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Check input Fields
    public boolean checkInputs(){
    
        if(!txt_ProductName.getText().isEmpty()){
            
            if(!txt_category.getText().isEmpty()){
                
                if(!txt_description.getText().isEmpty()){
                    
                    
                    try{
                        Float.parseFloat(txt_price.getText());
                        
                        try{
                            Integer.parseInt(txt_Quantity.getText());
                            return true;
                            
                        }catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Invalid Quantity Input!!!");
                        return false;
                        }
                        
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Invalid Price Input!!!");
                        return false;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Description Field cannot be Empty");
                    return false;
                }
            }    
            else{
                 JOptionPane.showMessageDialog(null, "Category Field cannot be Empty");   
                 return false;
            }  
        }    
        else{
                 JOptionPane.showMessageDialog(null, "Name Field cannot be Empty");  
                 return false;
        }            
            
         
        
    }
    
    //Resize Image
    String ImgPath=null;
    public ImageIcon resizeImage(String imagePath,byte[] image){
        ImageIcon pic = null;
        
        if(imagePath!=null){
            pic = new ImageIcon(imagePath);
        }
        else{
            pic= new ImageIcon(image);
        }
        
        Image img1=pic.getImage().getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon myImg = new ImageIcon(img1);
        return myImg;
        
    }

    
    //Display Date in JTable
    // 1 - Fetch data from Database and store it in ArrayList
    public ArrayList<Product> getProducts(){
        
        ArrayList<Product> ProductList=new ArrayList<>();
        Product product;
        Connection con= ConnectionProvider.getInstance().getDBConnection();
        String Query="SELECT * FROM product";
            
        try {
            Statement stm=con.createStatement();
            ResultSet rs=stm.executeQuery(Query);
            
            while(rs.next()){
                product = new Product(rs.getInt("productid"), rs.getString("productname"),rs.getString("categoryname"),rs.getString("description"),rs.getInt("quantity"), rs.getDouble("price"), rs.getBytes("productimage"));
                ProductList.add(product);
            }
           
        } catch (SQLException ex) {
            //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ProductList;
        
    }
    
    // 2 - Populate JTable
    public void showOnTable(){
    
        ArrayList<Product> products = getProducts();
        DefaultTableModel model= (DefaultTableModel)jTable_Products.getModel();
       
        //To clear table before inserting, to avoid duplicate
        model.setRowCount(0);
        
        Object[] row=new Object[5];
        
        for(int i=0;i<products.size();i++){
            row[0]=products.get(i).getProductId();
            row[1]=products.get(i).getProductName();
            row[2]=products.get(i).getCategory();
            row[3]=products.get(i).getQuantity();
            row[4]=products.get(i).getPrice();
            
            
            model.addRow(row);
        }
        
    }
    
    public void showItems(int index){
        
        
            txt_ProductId.setText(Integer.toString(getProducts().get(index).getProductId()));
            txt_ProductName.setText(getProducts().get(index).getProductName());
            txt_category.setText(getProducts().get(index).getCategory());
        try {    
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM category WHERE categoryid="+getProducts().get(index).getCategory();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String cName=rs.getString("categoryname");
                cb_category.setSelectedItem(cName);
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            txt_description.setText(getProducts().get(index).getDescription());
            txt_Quantity.setText(Integer.toString(getProducts().get(index).getQuantity()));
            txt_price.setText(Double.toString(getProducts().get(index).getPrice()));
            
            lbl_image.setIcon(resizeImage(null, getProducts().get(index).getImage()));
        
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
        lblprice = new javax.swing.JLabel();
        txt_Quantity = new javax.swing.JTextField();
        lblquantity = new javax.swing.JLabel();
        txt_price = new javax.swing.JTextField();
        lblquantity1 = new javax.swing.JLabel();
        lbl_image = new javax.swing.JLabel();
        btn_addImage = new javax.swing.JButton();
        btn_first = new javax.swing.JButton();
        btn_previous = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        lblstockid = new javax.swing.JLabel();
        txt_ProductId = new javax.swing.JTextField();
        lblcategoryname = new javax.swing.JLabel();
        cb_category = new javax.swing.JComboBox<>();
        btn_newCategory = new javax.swing.JButton();
        txt_category = new javax.swing.JTextField();
        txt_ProductName = new javax.swing.JTextField();
        lbldescription = new javax.swing.JLabel();
        lblstockname = new javax.swing.JLabel();
        btnupdate = new javax.swing.JButton();
        btnadd = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        cbbasedon = new javax.swing.JComboBox<>();
        btnsearch = new javax.swing.JButton();
        lblbasedon = new javax.swing.JLabel();
        btnRefresh1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_description = new javax.swing.JTextArea();
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
        setTitle("New Airtel | Inventory");
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
            .addGap(0, 605, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(0, 31, 63));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 20, 60), 1, true));

        label1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        label1.setForeground(new java.awt.Color(245, 245, 245));
        label1.setText("Inventory");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 75, Short.MAX_VALUE)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                "Stock ID", "Stock Name", "Category Name", "Quantity", "Price"
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 462, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblprice.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblprice.setText("Quantity");

        txt_Quantity.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_Quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_QuantityKeyReleased(evt);
            }
        });

        lblquantity.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblquantity.setText("Unit Price");

        txt_price.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_priceKeyReleased(evt);
            }
        });

        lblquantity1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblquantity1.setText("Image");

        lbl_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        btn_addImage.setText("SELECT IMAGE");
        btn_addImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addImageActionPerformed(evt);
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

        lblstockid.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblstockid.setText("Product ID");

        txt_ProductId.setEditable(false);
        txt_ProductId.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        lblcategoryname.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblcategoryname.setText("Category Name");

        cb_category.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cb_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_categoryActionPerformed(evt);
            }
        });

        btn_newCategory.setText("New Category");
        btn_newCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newCategoryActionPerformed(evt);
            }
        });

        txt_category.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_category.setEnabled(false);

        txt_ProductName.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        lbldescription.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lbldescription.setText("Description");

        lblstockname.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblstockname.setText("Product Name");

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

        btnRefresh.setBackground(new java.awt.Color(204, 255, 204));
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

        btnDelete.setBackground(new java.awt.Color(220, 20, 60));
        btnDelete.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(245, 244, 245));
        btnDelete.setText("Delete");
        btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        txtsearch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        cbbasedon.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbbasedon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Please Choose --", "Stock ID", "Stock Name", "Price", "Quantity" }));

        btnsearch.setBackground(new java.awt.Color(220, 20, 60));
        btnsearch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnsearch.setForeground(new java.awt.Color(245, 245, 245));
        btnsearch.setText("Search");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

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

        txt_description.setColumns(20);
        txt_description.setRows(5);
        jScrollPane2.setViewportView(txt_description);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
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
                .addComponent(btnsearch)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(lbldescription)
                                    .addGap(42, 42, 42))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(lblcategoryname)
                                    .addGap(18, 18, 18)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblstockname)
                                    .addComponent(lblstockid))
                                .addGap(25, 25, 25)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(172, 172, 172)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_category)
                                    .addComponent(txt_ProductId)
                                    .addComponent(txt_ProductName)
                                    .addComponent(cb_category, 0, 227, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2))
                                .addGap(18, 18, 18)
                                .addComponent(btn_newCategory)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblquantity1)
                                        .addGap(60, 60, 60)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btn_addImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblprice)
                                            .addComponent(lblquantity))
                                        .addGap(40, 40, 40)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_price))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_previous, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(btn_first, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnRefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                            .addComponent(txt_ProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblstockid))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblcategoryname)
                            .addComponent(cb_category, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_newCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_category, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblstockname)
                                .addGap(96, 96, 96))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_ProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbldescription)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGap(34, 34, 34)))))
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblprice)
                                    .addComponent(txt_Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblquantity)
                                    .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblquantity1)
                                    .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_last, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn_first, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_previous, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_addImage, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)))
                .addContainerGap())
        );

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

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

        jMenuItem1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jMenuItem1.setText("Inventory Dashboard");
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuStripNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStripNewActionPerformed
        txt_ProductId.setText("");
    }//GEN-LAST:event_menuStripNewActionPerformed

    private void menuStripQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStripQuitActionPerformed
        int i = JOptionPane.showConfirmDialog(null,"Are you sure to QUIT?", "Exit", JOptionPane.INFORMATION_MESSAGE);
        
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
        new Dashboard().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_menuStripHomeActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        
        InventryAdd obj= new InventryAdd();
        obj.setVisible(true);
        obj.setLocationRelativeTo(null);
        //obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }//GEN-LAST:event_btnaddActionPerformed

    private void cb_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_categoryActionPerformed
        try {
            String cName=cb_category.getSelectedItem().toString();
            
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query="SELECT * FROM category WHERE categoryname='"+cName+"'";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Integer cNum=rs.getInt("categoryid");
                txt_category.setText(cNum.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(InventryAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cb_categoryActionPerformed

    private void btn_addImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addImageActionPerformed

        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg","png");
        file.addChoosableFileFilter(filter);

        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            ImgPath=selectedFile.getAbsolutePath();
            lbl_image.setIcon(resizeImage(ImgPath, null));

        }else{
            JOptionPane.showMessageDialog(null, "No Image Selected!!!");
            System.out.println("No File Selected");
        }
    }//GEN-LAST:event_btn_addImageActionPerformed

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

        if(pos>=getProducts().size()){
            pos=getProducts().size()-1;
        }

        showItems(pos);
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        
        this.setBackground(Color.WHITE);
        pos = getProducts().size()-1;
        showItems(pos);
    }//GEN-LAST:event_btn_lastActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        if(checkInputs() && txt_ProductId.getText()!=null){

            String UpdateQuery=null;
            PreparedStatement ps= null;

            Connection con= ConnectionProvider.getInstance().getDBConnection();

            //Update without Image
            if(ImgPath==null){
                try {

                    UpdateQuery="UPDATE product SET productname=?,categoryname=?,description=?,quantity=?,price=? WHERE productid=?";
                    ps=con.prepareStatement(UpdateQuery);
                    ps.setString(1, txt_ProductName.getText());
                    ps.setString(2, txt_category.getText());
                    ps.setString(3, txt_description.getText());
                    ps.setInt(4, Integer.parseInt(txt_Quantity.getText()));
                    ps.setDouble(5, Double.parseDouble(txt_price.getText()));
                    ps.setInt(6, Integer.parseInt(txt_ProductId.getText()));

                    ps.executeUpdate();
                    showOnTable();
                    JOptionPane.showMessageDialog(null, "Successfully Updated");

                } catch (SQLException ex) {
                    //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Update with Image
            else{
                try {
                    UpdateQuery="UPDATE product SET productname=?,categoryname=?,description=?,quantity=?,price=?,productimage=? WHERE productid=?";

                    ps=con.prepareStatement(UpdateQuery);
                    
                    ps.setString(1, txt_ProductName.getText());
                    ps.setInt(2, Integer.parseInt(txt_category.getText()));
                    ps.setString(3, txt_description.getText());
                    ps.setInt(4, Integer.parseInt(txt_Quantity.getText()));
                    ps.setDouble(5, Double.parseDouble(txt_price.getText()));

                    InputStream img=new FileInputStream(new File(ImgPath));
                    ps.setBlob(6, img);

                    ps.setInt(7, Integer.parseInt(txt_ProductId.getText()));
                    ps.executeUpdate();
                    showOnTable();
                    JOptionPane.showMessageDialog(null, "Successfully Updated");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if(!txt_ProductId.getText().equals("")){

            try{
                Integer.parseInt(txt_ProductId.getText());
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Invalid Product ID!!!");
            }

            try {
                Connection con= ConnectionProvider.getInstance().getDBConnection();

                String DeleteQuery="DELETE FROM product WHERE productid=?";
                PreparedStatement ps=con.prepareStatement(DeleteQuery);
                ps.setInt(1, Integer.parseInt(txt_ProductId.getText()));
                ps.executeUpdate();
                showOnTable();
                showItems(pos);
                JOptionPane.showMessageDialog(null, "Successfully Deleted!!!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Poduct being used by other Tables!!!");
                //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else{
            JOptionPane.showMessageDialog(null, "Delete unsuccessful : Product ID cannot be Empty");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsearchActionPerformed

    private void btn_newCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newCategoryActionPerformed
        Category_Window obj= new Category_Window();
        obj.setVisible(true);
        obj.setLocationRelativeTo(null);
    }//GEN-LAST:event_btn_newCategoryActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        showOnTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        //setEnabled(false);
    }//GEN-LAST:event_formWindowDeactivated

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new InventryManagementDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        showOnTable();
        loadCategory();
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        cb_category.removeAllItems();
    }//GEN-LAST:event_formWindowLostFocus

    private void txt_QuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_QuantityKeyReleased
        String qty = txt_Quantity.getText();
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
    }//GEN-LAST:event_txt_QuantityKeyReleased

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

    private void btnRefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefresh1ActionPerformed

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
                new Inventory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRefresh1;
    private javax.swing.JButton btn_addImage;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_newCategory;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_previous;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> cb_category;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable_Products;
    private java.awt.Label label1;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JLabel lblbasedon;
    private javax.swing.JLabel lblcategoryname;
    private javax.swing.JLabel lbldescription;
    private javax.swing.JLabel lblprice;
    private javax.swing.JLabel lblquantity;
    private javax.swing.JLabel lblquantity1;
    private javax.swing.JLabel lblstockid;
    private javax.swing.JLabel lblstockname;
    private javax.swing.JMenuItem menuStripHome;
    private javax.swing.JMenuItem menuStripNew;
    private javax.swing.JMenuItem menuStripQuit;
    private javax.swing.JTextField txt_ProductId;
    private javax.swing.JTextField txt_ProductName;
    private javax.swing.JTextField txt_Quantity;
    private javax.swing.JTextField txt_category;
    private javax.swing.JTextArea txt_description;
    private javax.swing.JTextField txt_price;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables
}
