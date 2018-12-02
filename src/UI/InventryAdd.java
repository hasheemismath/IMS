/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Class.ConnectionProvider;
import Class.queryExecution;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author User
 */
public class InventryAdd extends javax.swing.JFrame {

    /**
     * Creates new form InventryAdd
     */
    
    public InventryAdd() {
        initComponents();
        //setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setVisible(true);
        //loadCategory();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        
    }
    
    private long generateID()
    {
         int id=0;
        
        try{
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query="SELECT * FROM product";
            
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
               id=rs.getInt("productid");
            }   
            return (id+1);
        }
        catch(Exception e){
            //JOptionPane.showMessageDialog(null, "Product Already Exists!!!");
            return 0;
        }
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
                        
                        //try{
                            //Integer.parseInt(txt_Quantity.getText());
                            return true;
                            
                        //}catch(Exception e){
                        //JOptionPane.showMessageDialog(null, "Invalid Quantity Input!!!");
                        //return false;
                        //}
                        
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
    
    public boolean checkAvailability(){
        try {
            String product_name=txt_ProductName.getText();
            
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query="SELECT * FROM product WHERE productname='"+product_name+"'";
            
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Product Already Exists!!!");
                return false;
            }    
            
            else 
                return true;
            
        } catch (SQLException ex) {
            //Logger.getLogger(Category_Window.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_ProductName = new javax.swing.JTextField();
        txt_category = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_description = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        lbl_image = new javax.swing.JLabel();
        btn_insert = new javax.swing.JButton();
        btn_addImage = new javax.swing.JButton();
        cb_category = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btn_newCategory = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txt_validate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NA | New Inventory");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 31, 63));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/smart devices.jpg"))); // NOI18N
        jLabel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel7)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel1.setText("Product Name");

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel2.setText("Category Name");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel3.setText("Description");

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel5.setText("Price");

        txt_category.setEnabled(false);
        txt_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_categoryActionPerformed(evt);
            }
        });

        txt_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_priceKeyReleased(evt);
            }
        });

        txt_description.setColumns(20);
        txt_description.setRows(5);
        jScrollPane1.setViewportView(txt_description);

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel6.setText("Image");

        lbl_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        btn_insert.setBackground(new java.awt.Color(255, 51, 51));
        btn_insert.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        btn_insert.setForeground(new java.awt.Color(255, 255, 255));
        btn_insert.setText("ADD");
        btn_insert.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        btn_addImage.setText("SELECT IMAGE");
        btn_addImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addImageActionPerformed(evt);
            }
        });

        cb_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_categoryActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel4.setText("Category ID");

        btn_newCategory.setText("New Category");
        btn_newCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newCategoryActionPerformed(evt);
            }
        });

        jButton1.setText("Check Availability");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)))
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_price)
                        .addComponent(txt_category)
                        .addComponent(txt_ProductName)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_category, 0, 250, Short.MAX_VALUE))
                    .addComponent(btn_addImage, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btn_newCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txt_validate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(35, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_ProductName, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_newCategory, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(cb_category))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_category, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(btn_addImage))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_validate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
           //long id = generateID()+1;
        if(checkAvailability()){
            if(checkInputs() && !ImgPath.isEmpty()){
            try {
                Connection con = ConnectionProvider.getInstance().getDBConnection();
                PreparedStatement ps= con.prepareStatement("INSERT INTO product(productname,categoryname, description,price,productimage,productid) VALUES(?,?,?,?,?,?)");
                ps.setString(1, txt_ProductName.getText());
                ps.setString(2, txt_category.getText());
                ps.setString(3, txt_description.getText());
                //ps.setInt(4, Integer.parseInt(txt_Quantity.getText()));
                ps.setDouble(4, Double.parseDouble(txt_price.getText()));

                InputStream img=new FileInputStream(new File(ImgPath));
                ps.setBlob(5, img);
                ps.setLong(6, generateID());
                //Finally Insert
                ps.executeUpdate();
                //showOnTable();
                JOptionPane.showMessageDialog(null, "Successfully Data Inserted!!!");
                //Inventory obj=new Inventory();
                //obj.setVisible(true);
                this.dispose();
                
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    //Logger.getLogger(Product_Window.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(ImgPath=="" || ImgPath==null){
              JOptionPane.showMessageDialog(null, "Image should be Inserted!!!");
            }
        }
        
    }//GEN-LAST:event_btn_insertActionPerformed

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

    private void txt_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_categoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_categoryActionPerformed

    private void cb_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_categoryActionPerformed
        
        try {
            String cName=cb_category.getSelectedItem().toString();
            
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query="SELECT * FROM category WHERE categoryname='"+cName+"'";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Integer cNum=rs.getInt("Categoryid");
                txt_category.setText(cNum.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(InventryAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cb_categoryActionPerformed

    private void btn_newCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newCategoryActionPerformed
        
        Category_Window obj=new Category_Window();
        obj.setVisible(true);
        obj.setLocationRelativeTo(null);
                
    }//GEN-LAST:event_btn_newCategoryActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(checkAvailability()){
            if(txt_ProductName.getText().isEmpty())
                JOptionPane.showMessageDialog(null, "Product Name cannot be Empty!!!");
            else
                JOptionPane.showMessageDialog(null, "Product Name can be Used!!!");
                
        }
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        loadCategory();
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        cb_category.removeAllItems();
    }//GEN-LAST:event_formWindowLostFocus

    private void txt_priceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_priceKeyReleased
        String priceMin = txt_price.getText();
        String PATTERN = "^[0-9.]+$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(priceMin);
        
        if (!match.matches()) {
            txt_validate.setForeground(Color.red);
            txt_validate.setText("Price should be Numeric!!!");
            
        } else {
            txt_validate.setText("");
            
        }
        
        if(priceMin.isEmpty()){
            txt_validate.setText("");
        }    
    }//GEN-LAST:event_txt_priceKeyReleased

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
            java.util.logging.Logger.getLogger(InventryAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InventryAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InventryAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InventryAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InventryAdd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addImage;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_newCategory;
    private javax.swing.JComboBox<String> cb_category;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JTextField txt_ProductName;
    private javax.swing.JTextField txt_category;
    private javax.swing.JTextArea txt_description;
    private javax.swing.JTextField txt_price;
    private javax.swing.JLabel txt_validate;
    // End of variables declaration//GEN-END:variables
}
