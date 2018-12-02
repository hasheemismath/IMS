package UI;

import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.*;
import Class.Purchase;

public class Purchase_interface extends javax.swing.JFrame {

    public static int supid = 0;
    public Purchase_interface() {
        initComponents();
        
        
        generatepurchaseid();
        setdate();
        
        suppliername.setEditable(false);
        purchasedate.setEditable(false);
        productname.setEditable(false);
        totalamount.setEditable(false);
        availableqty.setEditable(false);
        unitprice.setEditable(false);
        pidfunction();
       
          }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        availablequantity = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        searchcombo = new javax.swing.JComboBox<>();
        searchtext = new javax.swing.JTextField();
        search = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        purchasetable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        availableqty = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        totalamount = new javax.swing.JTextField();
        productid = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        productname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        unitprice = new javax.swing.JTextField();
        quantity = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        purchasername = new javax.swing.JTextField();
        purchaseid = new javax.swing.JTextField();
        purchasedate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        supplierid = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        suppliername = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        clear = new javax.swing.JButton();
        insert = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        cleartable = new javax.swing.JButton();
        viewall = new javax.swing.JButton();
        purcb = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1268, 681));

        jLabel9.setText("jLabel9");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setForeground(new java.awt.Color(0, 31, 63));
        jPanel5.setPreferredSize(new java.awt.Dimension(1315, 681));

        jLabel17.setBackground(new java.awt.Color(0, 31, 63));
        jLabel17.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Purchase Handler");
        jLabel17.setOpaque(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        searchcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select One--", "Purchase ID", "Purchaser Name", "Product ID", "Supplier ID", " " }));
        searchcombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchcomboActionPerformed(evt);
            }
        });

        searchtext.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchtextKeyReleased(evt);
            }
        });

        search.setBackground(new java.awt.Color(255, 255, 255));
        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(searchcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchtext, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(searchcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(searchtext, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        purchasetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Purchase ID", "Purchaser Name", "Purchase Date", "Supplier ID", "Product ID", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        purchasetable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                purchasetableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(purchasetable);
        if (purchasetable.getColumnModel().getColumnCount() > 0) {
            purchasetable.getColumnModel().getColumn(0).setResizable(false);
            purchasetable.getColumnModel().getColumn(1).setResizable(false);
            purchasetable.getColumnModel().getColumn(2).setResizable(false);
            purchasetable.getColumnModel().getColumn(3).setResizable(false);
            purchasetable.getColumnModel().getColumn(4).setResizable(false);
            purchasetable.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel3.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel3.setText("Quantity");

        availableqty.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel16.setText("Available Quantity");

        jLabel11.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel11.setText("Unti Price");

        totalamount.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        totalamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalamountActionPerformed(evt);
            }
        });
        totalamount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                totalamountKeyReleased(evt);
            }
        });

        productid.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        productid.setToolTipText("");
        productid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                productidKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel1.setText("Product ID");

        productname.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel2.setText("Product Name");

        jLabel7.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel7.setText("Total Amount");

        unitprice.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N

        quantity.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quantityKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(productid, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(availableqty)
                    .addComponent(totalamount)
                    .addComponent(productname)
                    .addComponent(unitprice)
                    .addComponent(quantity))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unitprice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(availableqty, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalamount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel15.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel15.setText("Purchase ID");
        jLabel15.setToolTipText("");
        jLabel15.setAlignmentX(0.5F);

        jLabel10.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel10.setText("Purchaser Name");
        jLabel10.setAlignmentX(0.5F);

        purchasername.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        purchasername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                purchasernameKeyReleased(evt);
            }
        });

        purchaseid.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        purchaseid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseidActionPerformed(evt);
            }
        });

        purchasedate.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel5.setText("Purchase Date");
        jLabel5.setAlignmentX(0.5F);

        supplierid.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        supplierid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                supplieridKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel4.setText("Supplier ID");
        jLabel4.setAlignmentX(0.5F);

        suppliername.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel6.setText("Supplier Name");
        jLabel6.setAlignmentX(0.5F);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(supplierid, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(purchasedate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(suppliername, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(purchasername, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(purchaseid, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(purchaseid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(purchasername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(purchasedate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplierid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suppliername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        clear.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        clear.setText("Clear All");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        insert.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        insert.setForeground(new java.awt.Color(0, 31, 63));
        insert.setText("Insert");
        insert.setRolloverEnabled(false);
        insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 54, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clear, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(insert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(insert, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)))
                .addGap(25, 25, 25))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        update.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        update.setForeground(new java.awt.Color(0, 31, 63));
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        delete.setForeground(new java.awt.Color(0, 31, 63));
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        cleartable.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        cleartable.setForeground(new java.awt.Color(0, 31, 63));
        cleartable.setText("Clear Table");
        cleartable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleartableActionPerformed(evt);
            }
        });

        viewall.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        viewall.setForeground(new java.awt.Color(0, 31, 63));
        viewall.setText("View All");
        viewall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewallActionPerformed(evt);
            }
        });

        purcb.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        purcb.setForeground(new java.awt.Color(0, 31, 63));
        purcb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select one --" }));
        purcb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purcbActionPerformed(evt);
            }
        });
        purcb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                purcbKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cleartable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(purcb, 0, 253, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(purcb, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewall, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cleartable, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setPreferredSize(new java.awt.Dimension(5, 100));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 368, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Home");

        jMenu3.setText("Return to Dashboard");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenu1.add(jMenu3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Report");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(availablequantity, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(availablequantity))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    //generate purchase id
    public void generatepurchaseid(){
      Connection connectiongpd = getConnection();
        try {
          Statement st1 = connectiongpd.createStatement();
           ResultSet rs1 = st1.executeQuery("select purchaseid from purchase");
           while(rs1.next())
           {
              int pid = rs1.getInt("purchaseid") + 1;
              purchaseid.setText(String.valueOf(pid));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }   
    

    //generate current date in purchasetime
    public void setdate(){
        Calendar cal = Calendar.getInstance();
        int d = cal.get(Calendar.DATE);
        int m = cal.get(Calendar.MONTH);
        int y = cal.get(Calendar.YEAR);
        purchasedate.setText(String.valueOf(d+"/"+m+"/"+y)); 
    }
    
    
    
    
    
    //update
    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
       
        int ppid = Integer.parseInt(purchaseid.getText());
        
        String query = "UPDATE purchase SET purchaser='"+purchasername.getText()+"',quantity='"+Integer.parseInt(quantity.getText())+"' WHERE purchaseid = '"+ppid+"'";
        executequery(query, "Updated");
        clear();
        setdate();
        pidfunction();
    }//GEN-LAST:event_updateActionPerformed

    
    
    //get suppliername
    public void suppliername(){
    int sid = Integer.parseInt(supplierid.getText());
        
       Connection connectionsn= getConnection();
        try {
           Statement stsn = connectionsn.createStatement();
           ResultSet rssn = stsn.executeQuery("select suppliername from supplier where supplierid = '"+sid+"' ");
           
           while(rssn.next())
           {
             suppliername.setText(rssn.getString("suppliername"));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
    
     //get productname
    public void productname(){
    int pid = Integer.parseInt(productid.getText());
        
       Connection connectionpn= getConnection();
        try {
           Statement stpn = connectionpn.createStatement();
           ResultSet rspn = stpn.executeQuery("select productname,price,quantity from product where productid ='"+pid+"'");
           
           while(rspn.next())
           {
             productname.setText(rspn.getString("productname"));
             unitprice.setText(String.valueOf(rspn.getDouble("price")));
             availableqty.setText(String.valueOf(rspn.getInt("quantity")));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
    
    
   
    private void purchaseidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseidActionPerformed
    }//GEN-LAST:event_purchaseidActionPerformed
    
    
    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        searchtext.setText("");
        clear_jtable();
        purchaselist_search();
        purchaselist_search_table();
    }//GEN-LAST:event_searchActionPerformed

    

    private void purchasetableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchasetableMouseClicked
        int i = purchasetable.getSelectedRow();
        TableModel model = (TableModel)purchasetable.getModel();    
        
        purchaseid.setText(model.getValueAt(i,0).toString());     
        purchasername.setText(model.getValueAt(i,1).toString());
        purchasedate.setText(model.getValueAt(i,2).toString());
        supplierid.setText(model.getValueAt(i,3).toString());
        productid.setText(model.getValueAt(i,4).toString());
        quantity.setText(model.getValueAt(i,5).toString());
        suppliername();
        productname();
        totalfunction();
    }//GEN-LAST:event_purchasetableMouseClicked

    private void totalamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalamountActionPerformed
    }//GEN-LAST:event_totalamountActionPerformed

    private void totalamountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalamountKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_totalamountKeyReleased
    
    //generate purchasername
    private void supplieridKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_supplieridKeyReleased
        boolean sid_val;
        
        String sid = supplierid.getText();
        String PATTERN = "^[0-9]+$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(sid);

        sid_val = !match.matches();

        if (sid_val) {
            JOptionPane.showMessageDialog(null, "Insert Only Numbers" );
        } 
        suppliername();
    }//GEN-LAST:event_supplieridKeyReleased

    //generate productname available quantity price and set total
    private void productidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productidKeyReleased
       boolean pid_val;
        
        String pid = productid.getText();
        String PATTERN = "^[0-9]+$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(pid);

        pid_val = !match.matches();

        if (pid_val) {
            JOptionPane.showMessageDialog(null, "Insert Only Numbers" );
        } 
        productname();
    }//GEN-LAST:event_productidKeyReleased
    

    
    private void searchcomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchcomboActionPerformed
     
    }//GEN-LAST:event_searchcomboActionPerformed

    
   
    
    //function to retreive purchaseid on combo box
    public void pidfunction(){
     Connection connection = getConnection();
        try {
          Statement st = connection.createStatement();
           ResultSet rs = st.executeQuery("select purchaseid from purchase");
           while(rs.next())
           {
              purcb.addItem(String.valueOf(rs.getString("purchaseid")));              
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
 
    
    
    
    //purchaseid combobox retreive
    private void purcbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purcbActionPerformed
       clear_jtable();
       purchaselist_pid();
       purchaselist_pid_table();
       
    }//GEN-LAST:event_purcbActionPerformed
     
    //display purchase on table for one selected item from combo
    
     public ArrayList<Purchase> purchaselist_pid(){
         
       String pidcb = String.valueOf(purcb.getSelectedItem());
         
       ArrayList<Purchase> supplyList = new ArrayList<Purchase>();
       Connection connection = getConnection();
       
       String query = "SELECT * FROM  purchase where purchaseid = "+pidcb+" ";
        
       try {
          Statement st = connection.createStatement();
           ResultSet rs = st.executeQuery(query);
           Purchase purchase;
           while(rs.next())
           {
               purchase= new Purchase(rs.getInt("purchaseid"),rs.getString("purchaser"),rs.getString("purchasedate"),rs.getInt("supplierid"),rs.getInt("productid"),rs.getInt("quantity"));
               supplyList.add(purchase);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return supplyList;
   }
     
     public void purchaselist_pid_table(){
       ArrayList<Purchase> list = purchaselist_pid();
       DefaultTableModel model = (DefaultTableModel)purchasetable.getModel();
       Object[] row = new Object[6];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getPurchaseid();
           row[1] = list.get(i).getPurchasername();
           row[2] = list.get(i).getPurchasedate();
           row[3] = list.get(i).getSupplierid();
           row[4] = list.get(i).getProductid();
           row[5] = list.get(i).getQuantity();    
           
           model.addRow(row);
       }
    }
     
     
    //clear all textfeilds
    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        clear();
    }//GEN-LAST:event_clearActionPerformed

    //clear function
    public void clear(){
        purchasername.setText("");
        supplierid.setText("");
        suppliername.setText("");
        productid.setText("");
        productname.setText("");
        availableqty.setText("");
        quantity.setText("");
        unitprice.setText("");
        totalamount.setText("");
    }
    
    //view all details in jtable
    private void viewallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewallActionPerformed
        clear_jtable();
        purchaselist_View_All();
        purchaselist_View_All_table();
    }//GEN-LAST:event_viewallActionPerformed

    //clear jtable
    private void cleartableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleartableActionPerformed
        clear_jtable();
    }//GEN-LAST:event_cleartableActionPerformed

    //delete
    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed

        int dpid = Integer.parseInt(purchaseid.getText());
                
        String query = "Delete FROM purchase WHERE purchaseid = '"+dpid+"'";
        executequery(query, "Deleted");
        clear();
        setdate();
        pidfunction();
    }//GEN-LAST:event_deleteActionPerformed

    
    //insert  
    private void insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertActionPerformed
        String query = "INSERT INTO purchase(purchaseid,purchaser,purchasedate,supplierid,productid,quantity) VALUES ('"+Integer.parseInt(purchaseid.getText())+"','"+purchasername.getText()+"','"+purchasedate.getText()+"','"+Integer.parseInt(supplierid.getText())+"','"+Integer.parseInt(productid.getText())+"','"+Integer.parseInt(quantity.getText())+"')";
        executequery(query, "Inserted");
        clear();
        pidfunction();
        setdate();
    }//GEN-LAST:event_insertActionPerformed

    
    
    
    
    //delete this
    private void purcbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_purcbKeyPressed
    }//GEN-LAST:event_purcbKeyPressed

    
    private void quantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityKeyReleased
        boolean qua_val;
        
        String qu = quantity.getText();
        String PATTERN = "^[0-9]+$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(qu);

        qua_val = !match.matches();

        if (qua_val) {
            JOptionPane.showMessageDialog(null, "Insert Only Numbers" );
        }   
        totalfunction();
    }//GEN-LAST:event_quantityKeyReleased

    public void totalfunction(){
    int quan = Integer.parseInt(quantity.getText());
        double prc = Double.parseDouble(unitprice.getText());
        
        double t = quan * prc;
     
         totalamount.setText(String.valueOf(t));
    }
    
    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        clear_jtable();
        purchaselist_search();
        purchaselist_search_table();
        searchtext.setText("");
    }//GEN-LAST:event_searchKeyReleased

    private void searchtextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtextKeyReleased
  
    }//GEN-LAST:event_searchtextKeyReleased

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        this.setVisible(false);
        new Purchase_dashboard().setVisible(true);
    }//GEN-LAST:event_jMenu3MouseClicked

    private void purchasernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_purchasernameKeyReleased
        boolean pn_val;
        
        String pn = purchasername.getText();
        String PATTERN = "^[0-9]+$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(pn);

        pn_val = match.matches();

        if (pn_val) {
            JOptionPane.showMessageDialog(null, "Insert Only Letters" );
        } 
    }//GEN-LAST:event_purchasernameKeyReleased

   
    //clear table
    public void clear_jtable(){
        DefaultTableModel model = (DefaultTableModel)purchasetable.getModel();
               model.setRowCount(0);
       }
    

   
    //Connection
    public Connection getConnection()
   {
         try {
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/purchase", "root", "");
           return con;
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }
    
    
    
   
    //view all purchase in table
     public ArrayList<Purchase> purchaselist_View_All()
   {
       ArrayList<Purchase> purchaseall = new ArrayList<Purchase>();
       Connection connectionall = getConnection();
      
       String query = "SELECT * FROM  purchase" ;
        
       try {
           Statement stal = connectionall.createStatement();
           ResultSet rsal = stal.executeQuery(query);
           Purchase spurchase;
           while(rsal.next())
           {
              spurchase= new Purchase(rsal.getInt("purchaseid"),rsal.getString("purchaser"),rsal.getString("purchasedate"),rsal.getInt("supplierid"),rsal.getInt("productid"),rsal.getInt("quantity"));
              purchaseall.add(spurchase);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return purchaseall;
   }

    public void purchaselist_View_All_table(){
       ArrayList<Purchase> list = purchaselist_View_All();
       DefaultTableModel model = (DefaultTableModel)purchasetable.getModel();
       Object[] row = new Object[6];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getPurchaseid();
           row[1] = list.get(i).getPurchasername();
           row[2] = list.get(i).getPurchasedate();
           row[3] = list.get(i).getSupplierid();
           row[4] = list.get(i).getProductid();
           row[5] = list.get(i).getQuantity();
           
           model.addRow(row);
       }
    }

    
   //view all purchase on table for search
     public ArrayList<Purchase> purchaselist_search()
   {
       int nit = Integer.parseInt(searchtext.getText());
       String snit = searchtext.getText();
       String ict = String.valueOf(searchcombo.getSelectedItem());
       
       ArrayList<Purchase> purchasesearch = new ArrayList<Purchase>();
       Connection connectionsr = getConnection();

       String query ;
 
       if(ict.equals("Purchase ID")){
       query = "SELECT * FROM  purchase where purchaseid ='"+nit+"'";
       }else if(ict.equals("Purchaser Name")){
       query = "SELECT * FROM  purchase where purchaser ='"+snit+"'";
       }else if(ict.equals("Product ID")){
       query = "SELECT * FROM  purchases where productid ='"+nit+"'";
       }else if(ict.equals("Supplier ID")){
       query = "SELECT * FROM  purchase where supplierid ='"+nit+"'";
       }else  query =  "SELECT * FROM  purchase"; 
      
        try {
           Statement stsr = connectionsr.createStatement();
           ResultSet rssr = stsr.executeQuery(query);
           Purchase searchpurchase;
           while(rssr.next())
           {
              searchpurchase= new Purchase(rssr.getInt("purchaseid"),rssr.getString("purchaser"),rssr.getString("purchasedate"),rssr.getInt("supplierid"),rssr.getInt("productid"),rssr.getInt("quantity"));
              purchasesearch.add(searchpurchase);
           }
 
       } catch (Exception e) {
           e.printStackTrace();
       }
        
       return purchasesearch;
 
   }

    public void purchaselist_search_table(){
       ArrayList<Purchase> list = purchaselist_search();
       DefaultTableModel model = (DefaultTableModel)purchasetable.getModel();
       Object[] row = new Object[6];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getPurchaseid();
           row[1] = list.get(i).getPurchasername();
           row[2] = list.get(i).getPurchasedate();
           row[3] = list.get(i).getSupplierid();
           row[4] = list.get(i).getProductid(); 
           row[5] = list.get(i).getQuantity();  
           
           model.addRow(row);
       }
    }
 

    
    
    //execute query function
     public void executequery(String query, String message){
       Connection con = getConnection();
       Statement st;
       try{
           st = con.createStatement();
           if((st.executeUpdate(query)) == 1)
           {
               DefaultTableModel model = (DefaultTableModel)purchasetable.getModel();
               model.setRowCount(0);
               purchaselist_View_All_table(); 
           }else{
               JOptionPane.showMessageDialog(null, "Data Not" + message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
     
     
     
     
     
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Purchase_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Purchase_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Purchase_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Purchase_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Purchase_interface().setVisible(true);
            }
        });
    }

    
    
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField availableqty;
    private javax.swing.JLabel availablequantity;
    private javax.swing.JButton clear;
    private javax.swing.JButton cleartable;
    private javax.swing.JButton delete;
    private javax.swing.JButton insert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField productid;
    private javax.swing.JTextField productname;
    private javax.swing.JComboBox<String> purcb;
    private javax.swing.JTextField purchasedate;
    private javax.swing.JTextField purchaseid;
    private javax.swing.JTextField purchasername;
    private javax.swing.JTable purchasetable;
    private javax.swing.JTextField quantity;
    private javax.swing.JButton search;
    private javax.swing.JComboBox<String> searchcombo;
    private javax.swing.JTextField searchtext;
    private javax.swing.JTextField supplierid;
    private javax.swing.JTextField suppliername;
    private javax.swing.JTextField totalamount;
    private javax.swing.JTextField unitprice;
    private javax.swing.JButton update;
    private javax.swing.JButton viewall;
    // End of variables declaration//GEN-END:variables
}

