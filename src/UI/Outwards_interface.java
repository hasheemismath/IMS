package UI;

import Class.Outwards;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Outwards_interface extends javax.swing.JFrame {

   
    public Outwards_interface() {
        initComponents();
        generateoutwardid();
        setdate();
        
        outwardsid.setEditable(false);
        outwardsdate.setEditable(false);
        oproductname.setEditable(false);
        osuppliername.setEditable(false);
        ounitprice.setEditable(false);
        ototalamount.setEditable(false);
    }
    
        boolean sid_val;
        boolean pid_val;
        boolean qua_val;
        boolean des_val;

    
    //generate outwards id
    public void generateoutwardid(){
      Connection connectionot = getConnection();
        try {
          Statement stot = connectionot.createStatement();
           ResultSet rsot = stot.executeQuery("select outwardsid from outwards");
           while(rsot.next())
           {
              int oid = 0;
              oid = rsot.getInt("outwardsid") + 1;
              outwardsid.setText(String.valueOf(oid));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }                   

    
    //set outwards registered date
    public void setdate(){
        Calendar cal = Calendar.getInstance();
        int d = cal.get(Calendar.DATE);
        int m = cal.get(Calendar.MONTH);
        int y = cal.get(Calendar.YEAR);
        outwardsdate.setText(String.valueOf(d+"/"+m+"/"+y));
       
    }
    
    //get supplier name
    public void getsuppliername(){
        
        int sid = Integer.parseInt(osupplierid.getText());
        Connection connectionsn = getConnection();
        try {
           Statement stsn = connectionsn.createStatement();
           ResultSet rssn = stsn.executeQuery("select suppliername from supplier where supplierid = '"+sid+"' ");

           while(rssn.next())
           {
              String sname = rssn.getString("suppliername");
              osuppliername.setText(sname);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
    
    double price;
    //get productname
    public void getproductname(){
        
        int pid = Integer.parseInt(oproductid.getText());
        
       Connection connectionpn = getConnection();
        try {
           Statement stpn = connectionpn.createStatement();
           ResultSet rspn = stpn.executeQuery("select productname,price from product where productid = '"+pid+"' ");
           
           while(rspn.next())
           {
              String pname = rspn.getString("productname");
              price = rspn.getDouble("price");
              
              oproductname.setText(pname);
              ounitprice.setText(String.valueOf(price));
              
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
       
    
    
    //calculate total
    public void calculatetotal(){
    
        double total = 0;
        int quantity = Integer.parseInt(oquantity.getText());
        total = price * quantity ;
        
        ototalamount.setText(String.valueOf(total));

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
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu9 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        jMenuBar6 = new javax.swing.JMenuBar();
        jMenu13 = new javax.swing.JMenu();
        jMenu14 = new javax.swing.JMenu();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        oproductid = new javax.swing.JTextField();
        osupplierid = new javax.swing.JTextField();
        oquantity = new javax.swing.JTextField();
        ounitprice = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ototalamount = new javax.swing.JTextField();
        oupdate = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        outwardsdate = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        description = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        oproductname = new javax.swing.JTextField();
        osuppliername = new javax.swing.JTextField();
        outwardsid = new javax.swing.JTextField();
        oclearfeild = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        searchcomboout = new javax.swing.JComboBox<>();
        searchtextout = new javax.swing.JTextField();
        search = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        outwardstable = new javax.swing.JTable();
        oviewall = new javax.swing.JButton();
        tclearall = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu15 = new javax.swing.JMenu();
        jMenu16 = new javax.swing.JMenu();
        jMenu17 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        jMenu7.setText("File");
        jMenuBar4.add(jMenu7);

        jMenu8.setText("Edit");
        jMenuBar4.add(jMenu8);

        jMenu9.setText("File");
        jMenuBar5.add(jMenu9);

        jMenu12.setText("Edit");
        jMenuBar5.add(jMenu12);

        jMenu13.setText("File");
        jMenuBar6.add(jMenu13);

        jMenu14.setText("Edit");
        jMenuBar6.add(jMenu14);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1315, 681));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1315, 679));

        jLabel9.setBackground(new java.awt.Color(0, 31, 63));
        jLabel9.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("OUTWARD MANAGEMENT");
        jLabel9.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel2.setText("Product ID");
        jLabel2.setAlignmentX(0.5F);

        jLabel3.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel3.setText("Supplier ID");
        jLabel3.setAlignmentX(0.5F);

        jLabel5.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel5.setText("Quantity");
        jLabel5.setToolTipText("");
        jLabel5.setAlignmentX(0.5F);

        jLabel6.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel6.setText("Unit Price");
        jLabel6.setAlignmentX(0.5F);

        oproductid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                oproductidKeyReleased(evt);
            }
        });

        osupplierid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                osupplieridKeyReleased(evt);
            }
        });

        oquantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                oquantityKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel7.setText("Total Amount");

        oupdate.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        oupdate.setText("Update");
        oupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oupdateActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel10.setText("Outwards ID");
        jLabel10.setAlignmentX(0.5F);

        jLabel8.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel8.setText("Date");

        jLabel11.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel11.setText("Description");

        description.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descriptionKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel1.setText("Product Name");
        jLabel1.setAlignmentX(0.5F);

        jLabel4.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel4.setText("Supplier Name");
        jLabel4.setAlignmentX(0.5F);

        oclearfeild.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        oclearfeild.setText("Clear All");
        oclearfeild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oclearfeildActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(oproductid)
                            .addComponent(oquantity)
                            .addComponent(ounitprice)
                            .addComponent(ototalamount)
                            .addComponent(osupplierid)
                            .addComponent(oproductname)
                            .addComponent(osuppliername, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(outwardsid))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(outwardsdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(oupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(oclearfeild, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(outwardsid))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oproductid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(oproductname, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(osupplierid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(osuppliername, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ounitprice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ototalamount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outwardsdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(oupdate)
                .addGap(8, 8, 8)
                .addComponent(oclearfeild)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        searchcomboout.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select -- ", "Outwards ID", "Supplier ID", "Product ID", " " }));
        searchcomboout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchcombooutActionPerformed(evt);
            }
        });

        search.setBackground(new java.awt.Color(255, 255, 255));
        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchcomboout, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchtextout, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(searchcomboout, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(searchtextout, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        outwardstable.setModel(new javax.swing.table.DefaultTableModel(
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
                "Outwards ID", "Supplier ID", "Product ID", "Quantity", "Date", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        outwardstable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                outwardstableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(outwardstable);
        if (outwardstable.getColumnModel().getColumnCount() > 0) {
            outwardstable.getColumnModel().getColumn(0).setResizable(false);
            outwardstable.getColumnModel().getColumn(1).setResizable(false);
            outwardstable.getColumnModel().getColumn(2).setResizable(false);
            outwardstable.getColumnModel().getColumn(3).setResizable(false);
            outwardstable.getColumnModel().getColumn(4).setResizable(false);
            outwardstable.getColumnModel().getColumn(5).setResizable(false);
        }

        oviewall.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        oviewall.setText("View All");
        oviewall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oviewallActionPerformed(evt);
            }
        });

        tclearall.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        tclearall.setText("Clear All");
        tclearall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tclearallActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tclearall, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(oviewall, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oviewall, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tclearall, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jMenu15.setText("Home");

        jMenu16.setText("Return to Dashboard");
        jMenu16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu16MouseClicked(evt);
            }
        });
        jMenu15.add(jMenu16);

        jMenu17.setText("View Purchase Interface");
        jMenu17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu17MouseClicked(evt);
            }
        });
        jMenu15.add(jMenu17);

        jMenuBar1.add(jMenu15);

        jMenu2.setText("Report");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //get suppliername
    private void osupplieridKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_osupplieridKeyReleased
        String osid = osupplierid.getText();
        String PATTERN = "^[0-9]+$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(osid);

        sid_val = !match.matches();

        if (sid_val) {
            JOptionPane.showMessageDialog(null, "Insert Only Numbers " );
        } 

        getsuppliername();
    }//GEN-LAST:event_osupplieridKeyReleased

    //get productname
    private void oproductidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_oproductidKeyReleased
        String opid = oproductid.getText();
        String PATTERN = "^[0-9]+$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(opid);

        pid_val = !match.matches();

        if (pid_val) {
            JOptionPane.showMessageDialog(null, "Insert Only Numbers " );
        } 

        getproductname();  
    }//GEN-LAST:event_oproductidKeyReleased

    private void searchcombooutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchcombooutActionPerformed

    }//GEN-LAST:event_searchcombooutActionPerformed

    //outward search
    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        clear_jtable();
        outwards_search();
        outwards_search_table();
    }//GEN-LAST:event_searchActionPerformed

    //dispaly total
    private void oquantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_oquantityKeyReleased
        calculatetotal();
        
        String vvquantity = oquantity.getText();  
    }//GEN-LAST:event_oquantityKeyReleased

    //insert into database
    private void oupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oupdateActionPerformed
       
        String query = "INSERT INTO outwards(outwardsid,supplierid,productid,quantity,date,description) VALUES ('"+outwardsid.getText()+"','"+osupplierid.getText()+"','"+oproductid.getText()+"','"+oquantity.getText()+"','"+outwardsdate.getText()+"','"+description.getText()+"')";
        executequery(query, "Inserted");
        clear();
        generateoutwardid();
        
    }//GEN-LAST:event_oupdateActionPerformed

    
    
    //view all in table
    private void oviewallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oviewallActionPerformed
        clear_jtable();
        outwards_ViewAll();
        outwards_table();
    }//GEN-LAST:event_oviewallActionPerformed

    //clear table
    private void tclearallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tclearallActionPerformed
        clear_jtable();
    }//GEN-LAST:event_tclearallActionPerformed

    private void oclearfeildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oclearfeildActionPerformed
        clear();
        generateoutwardid();
        setdate();
    }//GEN-LAST:event_oclearfeildActionPerformed

    private void descriptionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descriptionKeyReleased
        String des = description.getText();
        String PATTERN = "^[0-9]+$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(des);

        des_val = match.matches();

        if (des_val) {
            JOptionPane.showMessageDialog(null, "Start with Letter " );
        } 
    }//GEN-LAST:event_descriptionKeyReleased

    private void outwardstableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_outwardstableMouseClicked
         int i = outwardstable.getSelectedRow();
        TableModel model = (TableModel)outwardstable.getModel();    
        
        outwardsid.setText(model.getValueAt(i,0).toString());     
        osupplierid.setText(model.getValueAt(i,1).toString());
        oproductid.setText(model.getValueAt(i,2).toString());
        oquantity.setText(model.getValueAt(i,3).toString());
        outwardsdate.setText(model.getValueAt(i,4).toString());
        description.setText(model.getValueAt(i,5).toString());
        
        getproductname();
        getsuppliername();
        calculatetotal();
    }//GEN-LAST:event_outwardstableMouseClicked

    private void jMenu16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu16MouseClicked
        this.setVisible(false);
        new Purchase_dashboard().setVisible(true);
    }//GEN-LAST:event_jMenu16MouseClicked

    private void jMenu17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu17MouseClicked
        this.setVisible(false);
        new Purchase_interface().setVisible(true);
    }//GEN-LAST:event_jMenu17MouseClicked


     public void clear_jtable(){
      
         DefaultTableModel model = (DefaultTableModel)outwardstable.getModel();
         model.setRowCount(0);
       }
     
     public void executequery(String query, String message){
       Connection con = getConnection();
       Statement st;
       try{
           st = con.createStatement();
           if((st.executeUpdate(query)) == 1)
           {
               DefaultTableModel model = (DefaultTableModel)outwardstable.getModel();
               model.setRowCount(0);
               outwards_table();
               JOptionPane.showMessageDialog(null, "Data Successfully " +message);
               
           }else{
               JOptionPane.showMessageDialog(null, "Data Not " +message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
     
     
     public void clear(){
        osupplierid.setText("");
        osuppliername.setText("");
        oproductid.setText("");
        oproductname.setText("");
        oquantity.setText("");
        ounitprice.setText("");
        ototalamount.setText("");
        description.setText("");
    }
     
    
     public ArrayList<Outwards> outwards_ViewAll()
   {
       ArrayList<Outwards> outwardsList = new ArrayList<Outwards>();
       Connection connectionva = getConnection();
      
       String query = "SELECT * FROM  outwards";
        
       try {
          
           Statement stva = connectionva.createStatement();
           ResultSet rsva = stva.executeQuery(query);
           Outwards outwards;
           while(rsva.next())
           {
               outwards = new Outwards(rsva.getInt("outwardsid"),rsva.getInt("supplierid"),rsva.getInt("productid"),rsva.getInt("quantity"),rsva.getString("date"),rsva.getString("description"));
               outwardsList.add(outwards);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return outwardsList;
   }
    
     public void outwards_table(){
       ArrayList<Outwards> list = outwards_ViewAll();
       DefaultTableModel model = (DefaultTableModel)outwardstable.getModel();
       Object[] row = new Object[8];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getOutwardsid();
           row[1] = list.get(i).getSupplierid();
           row[2] = list.get(i).getProductid();
           row[3] = list.get(i).getQuantity();
           row[4] = list.get(i).getDate();
           row[5] = list.get(i).getDescription();

           model.addRow(row);
       }
    }
 
     //search
     public ArrayList<Outwards> outwards_search()
   {
       String osc = String.valueOf(searchcomboout.getSelectedItem());
       int ost = Integer.parseInt(searchtextout.getText());
       String query ;
       ArrayList<Outwards> outwardsearchlist = new ArrayList<Outwards>();
       Connection connectionva = getConnection();
      
       if(osc.equals("Outwards ID")){
       query = "SELECT * FROM  outwards where outwardsid ='"+ost+"'";
       }else if(osc.equals("Supplier ID")){
       query = "SELECT * FROM  outwards where supplierid ='"+ost+"'";
       }else if(osc.equals("Product ID")){
       query = "SELECT * FROM  outwards where productid ='"+ost+"'";
       }else  query =  "SELECT * FROM  outwards"; 
       try {
          
           Statement stva = connectionva.createStatement();
           ResultSet rsva = stva.executeQuery(query);
           Outwards outwardsearch;
           while(rsva.next())
           {
               outwardsearch = new Outwards(rsva.getInt("outwardsid"),rsva.getInt("supplierid"),rsva.getInt("productid"),rsva.getInt("quantity"),rsva.getString("date"),rsva.getString("description"));
               outwardsearchlist .add(outwardsearch);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return outwardsearchlist ;
   }
    
     public void outwards_search_table(){
       ArrayList<Outwards> slist = outwards_search();
       DefaultTableModel model = (DefaultTableModel)outwardstable.getModel();
       Object[] row = new Object[8];
       for(int i = 0; i < slist.size(); i++)
       {
           row[0] = slist.get(i).getOutwardsid();
           row[1] = slist.get(i).getSupplierid();
           row[2] = slist.get(i).getProductid();
           row[3] = slist.get(i).getQuantity();
           row[4] = slist.get(i).getDate();
           row[5] = slist.get(i).getDescription();

           model.addRow(row);
       }
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Outwards_interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField description;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu17;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuBar jMenuBar6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton oclearfeild;
    private javax.swing.JTextField oproductid;
    private javax.swing.JTextField oproductname;
    private javax.swing.JTextField oquantity;
    private javax.swing.JTextField osupplierid;
    private javax.swing.JTextField osuppliername;
    private javax.swing.JTextField ototalamount;
    private javax.swing.JTextField ounitprice;
    private javax.swing.JButton oupdate;
    private javax.swing.JTextField outwardsdate;
    private javax.swing.JTextField outwardsid;
    private javax.swing.JTable outwardstable;
    private javax.swing.JButton oviewall;
    private javax.swing.JButton search;
    private javax.swing.JComboBox<String> searchcomboout;
    private javax.swing.JTextField searchtextout;
    private javax.swing.JButton tclearall;
    // End of variables declaration//GEN-END:variables
}
