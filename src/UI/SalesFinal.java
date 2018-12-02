package UI;

import Class.ConnectionProvider;
import Class.InwardsClass;
import Class.SalesClass;
import Class.queryExecution;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Roshan Nizar
 */

public class SalesFinal extends javax.swing.JFrame 
{
    public static boolean value;
    private static int quan = 0;
    private double grossAmount = 0;
    private final String salesPerson;
    public static int id = 0;
    public static double total = 0;
    public static String paymenttype;
    public static double amountpaid = 0;
    public static double balance = 0;
    public static long chequeno = 0;
    private static int lid = 0;
    
    public SalesFinal() 
    {
        this.salesPerson = SignIn.sessionEmail;
        initComponents();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        lblactive.setText("Active: "+SignIn.sessionEmail);
        loadStockID();
        loadCustomerID();
        Date d = new Date();
        lblDate.setText("Today: "+d.toLocaleString());
    }

    private int generateid()
    {
        String query = "SELECT * FROM sales";
        String rowName = "salesid";
        
        return (int) queryExecution.generateID(query, rowName);
    }
    
    private ArrayList<SalesClass> salesListSearch(String type)
    {
        ArrayList<SalesClass> slist = new ArrayList<>();
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = type;
        
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            SalesClass s;
            
            while(rs.next())
            {
                s = new SalesClass(rs.getInt("salesid"),rs.getString("salesperson"),rs.getLong("productid"),rs.getInt("customerid"),rs.getString("date"),rs.getInt("quantity"),rs.getInt("price"));
                slist.add(s);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return slist;
    }
    
    private void loadSalesSearch(String type)
    {
        ArrayList <SalesClass> list = salesListSearch(type);
        DefaultTableModel dtm = (DefaultTableModel)dgvSales.getModel();
        Object []row = new Object[8];
        
        for(int i=0;i<list.size();i++)
        {
            row[0] = list.get(i).getSalesid();
            row[1] = list.get(i).getSalesPerson();
            row[2] = list.get(i).getProductID();
            row[3] = list.get(i).getCustomerid();
            row[4] = list.get(i).getQuantity();
            row[5] = list.get(i).getPrice();
            row[6] = list.get(i).getPrice() * list.get(i).getQuantity();
            dtm.addRow(row);
        }
    }
    
    private void addItem()
    {
        DefaultTableModel model = (DefaultTableModel)dgvSales.getModel();
        Collection list = new ArrayList();

        list.add(Integer.parseInt(txtSalesId.getText()));
        list.add(salesPerson);
        list.add(Long.parseLong(cbProductID.getSelectedItem().toString()));
        list.add(Long.parseLong(cbCustomerID.getSelectedItem().toString()));
        list.add(Integer.parseInt(txtSellQuan.getText()));
        list.add(Double.parseDouble(txtPrice.getText()));
        list.add(Double.parseDouble(txtPrice.getText())*Integer.parseInt(txtSellQuan.getText()));
        model.addRow(list.toArray());
    }
    
    private void loadStockID()
    {
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM product";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            
            while(rs.next())
            {
                cbProductID.addItem(String.valueOf(rs.getInt("productid")));
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadCustomerID()
    {
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM customer";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            
            while(rs.next())
            {
                cbCustomerID.addItem(String.valueOf(rs.getInt("customerid")));
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadStockDetails()
    {
        String id;
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            Statement st = con.createStatement();
            id = cbProductID.getSelectedItem().toString();
            String query = "SELECT  * FROM product WHERE productid='"+id+"'";
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next())
            {
                txtProductName.setText(rs.getString("productname"));
                txtStockQuantity.setText(rs.getString("quantity"));
                txtPrice.setText(rs.getString("price"));
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
    } 
    
    private void loadCustomerDetails()
    {
        String id;
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            Statement st = con.createStatement();
            id = cbCustomerID.getSelectedItem().toString();
            String query = "SELECT * FROM customer WHERE customerid='"+id+"'";
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next())
            {
                txtCustomerName.setText(rs.getString("firstname")+" "+rs.getString("lastname"));
                /*txtcustotal.setText(rs.getString("totalamount"));
                txtcuspaid.setText(rs.getString("amountpaid"));*/
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void checkArrears()
    {
        double total = 0,amount=0,tot=0;
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM customer c, paymentc pc WHERE c.customerid = pc.customerid AND pc.customerid = '"+cbCustomerID.getSelectedItem()+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            
            while(rs.next())
            {
                total = total + rs.getDouble("totalamount");
                amount = amount + rs.getDouble("amountpaid");
                
                tot = total- amount;
                
            }
            
            String name = txtCustomerName.getText();
            
            JOptionPane.showMessageDialog(null, name+" has "+ tot+ "/- to settle his amount.","Arrears",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void checkContact()
    {
        String contact="";
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM customer WHERE customerid='"+cbCustomerID.getSelectedItem()+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            
            while(rs.next())
            {
                contact = rs.getString("contactno");
            }
            
            String name = txtCustomerName.getText();
            
            JOptionPane.showMessageDialog(null, name +" Contact No: "+contact ,"Contact No",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private int lastID()
    {
        int id=0;
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM sales";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            
            while(rs.next())
            {
                id = rs.getInt("salesid");
            }
            
            return id;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
    
    private void amountPaid()
    {
        if("".equals(lblNettAmount.getText()) || txtPay.getText() == null)
        {
            txtBalance.setText("");
        }
        else
        {
            try
            {
                double total = Double.parseDouble(lblNettAmount.getText());
                double paid = Double.parseDouble(txtPay.getText());
                double bal = total - paid;
                txtBalance.setText(String.valueOf(bal));
                btnPay.setText("Pay "+txtPay.getText());
            }
            catch(NumberFormatException ex)
            {
            
            }
        }
    }
    
     private void remainingQuantity()
    {
        if("".equals(txtSellQuan.getText()) || txtSellQuan.getText() == null)
        {
            txtAvaiQuan.setText("");
        }
        else
        {
            try
            {
                int quantity = Integer.parseInt(txtStockQuantity.getText());
                int sellQuan = Integer.parseInt(txtSellQuan.getText());
                int avaiQuan = quantity - sellQuan;
                txtAvaiQuan.setText(String.valueOf(avaiQuan));
                
                if(Integer.parseInt(txtAvaiQuan.getText())<0)
                {
                    txtAvaiQuan.setEnabled(true);
                    txtAvaiQuan.setBackground(Color.red);
                }
                else
                {
                    txtAvaiQuan.setBackground(Color.WHITE);
                }
            }
            catch(NumberFormatException ex)
            {
            
            }
        }
    }
    
    private void quit()
    {
        int i = JOptionPane.showConfirmDialog(null,"Are you sure to QUIT?", "Exit", JOptionPane.INFORMATION_MESSAGE);
        
        if(JOptionPane.YES_OPTION == i)
        {
            this.dispose();
        }
    }
    
    private void clear()
    {
        txtSalesId.setText(String.valueOf(generateid()));
        cbProductID.setSelectedItem("-- Please Choose --");
        txtSellQuan.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem3 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        btnCart = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtPay = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtBalance = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dtpDate = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        cbPaymentType = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtChequeNo = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbCustomerID = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JLabel();
        btnCheckArrears = new javax.swing.JButton();
        btnContact = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnPay = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblsales = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dgvSales = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblProductImage = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtSalesId = new javax.swing.JTextField();
        btnGenerate = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        cbProductID = new javax.swing.JComboBox<>();
        btnRequest = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtStockQuantity = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtSellQuan = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtAvaiQuan = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lblNettAmount = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblNettAmount1 = new javax.swing.JLabel();
        lblactive = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuStripHome = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuStripQuit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("New Airtel | Sales");

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        btnCart.setBackground(new java.awt.Color(0, 31, 63));
        btnCart.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        btnCart.setForeground(new java.awt.Color(245, 245, 245));
        btnCart.setText("Add to cart");
        btnCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCartActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(220, 20, 60));
        btnUpdate.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(245, 245, 245));
        btnUpdate.setText("Update");
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRemove.setBackground(new java.awt.Color(220, 20, 60));
        btnRemove.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnRemove.setForeground(new java.awt.Color(245, 245, 245));
        btnRemove.setText("Remove");
        btnRemove.setEnabled(false);
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel2.setText("Paid Amount:");

        txtPay.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtPay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPayKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel3.setText("Balance:");

        txtBalance.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel5.setText("Due Date:");
        jLabel5.setEnabled(false);

        dtpDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        dtpDate.setEnabled(false);
        dtpDate.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel6.setText("Payment Type: ");

        cbPaymentType.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbPaymentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Please Choose --", "Cash", "Cheque" }));
        cbPaymentType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPaymentTypeActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel7.setText("Cheque No:");

        txtChequeNo.setEditable(false);
        txtChequeNo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtChequeNo.setText("0");
        txtChequeNo.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Customer's Details");

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel9.setText("ID");

        cbCustomerID.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbCustomerID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Choose --" }));
        cbCustomerID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCustomerIDActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel10.setText("Name:");

        txtCustomerName.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        btnCheckArrears.setBackground(new java.awt.Color(220, 20, 60));
        btnCheckArrears.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        btnCheckArrears.setForeground(new java.awt.Color(245, 245, 245));
        btnCheckArrears.setText("Check Arrears");
        btnCheckArrears.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckArrearsActionPerformed(evt);
            }
        });

        btnContact.setBackground(new java.awt.Color(220, 20, 60));
        btnContact.setFont(new java.awt.Font("Calibri", 0, 19)); // NOI18N
        btnContact.setForeground(new java.awt.Color(245, 245, 245));
        btnContact.setText(" Contact");
        btnContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContactActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnPay.setBackground(new java.awt.Color(255, 102, 0));
        btnPay.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        btnPay.setForeground(new java.awt.Color(245, 245, 245));
        btnPay.setText("Pay");
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPay)
                            .addComponent(txtBalance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dtpDate)
                            .addComponent(cbPaymentType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtChequeNo)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCustomerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnCheckArrears, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnContact, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCart, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dtpDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtChequeNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnContact, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCheckArrears, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 31, 63));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 31, 63), 1, true));

        lblsales.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lblsales.setForeground(new java.awt.Color(245, 245, 245));
        lblsales.setText("Sales");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addComponent(lblsales)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblsales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dgvSales.setBackground(new java.awt.Color(245, 245, 245));
        dgvSales.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        dgvSales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sales ID", "Sales Person", "Product ID", "Customer ID", "Quantity", "Price", "Total Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Long.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dgvSales.setSelectionBackground(new java.awt.Color(0, 31, 63));
        dgvSales.setSelectionForeground(new java.awt.Color(245, 245, 245));
        dgvSales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dgvSalesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dgvSales);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(245, 245, 245));

        jPanel5.setBackground(new java.awt.Color(245, 245, 245));

        lblProductImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProductImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/customer.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel14.setText("Sales ID:");

        txtSalesId.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtSalesId.setEnabled(false);

        btnGenerate.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnGenerate.setText("Generate ID");
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel15.setText("Product ID:");

        cbProductID.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbProductID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Please Choose --" }));
        cbProductID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductIDActionPerformed(evt);
            }
        });

        btnRequest.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnRequest.setText("Request");

        jLabel16.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel16.setText("Product Name:");

        txtProductName.setEditable(false);
        txtProductName.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtProductName.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel17.setText("Stock Quantity:");

        txtStockQuantity.setEditable(false);
        txtStockQuantity.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtStockQuantity.setEnabled(false);

        jLabel18.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel18.setText("Selling Quantity:");

        txtSellQuan.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtSellQuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSellQuanKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSellQuanKeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel19.setText("Available:");

        txtAvaiQuan.setEditable(false);
        txtAvaiQuan.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtAvaiQuan.setEnabled(false);

        jLabel20.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Price");

        txtPrice.setEditable(false);
        txtPrice.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblProductImage, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtSalesId, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(cbProductID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRequest))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(24, 24, 24))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSellQuan)
                            .addComponent(txtStockQuantity)
                            .addComponent(txtProductName)
                            .addComponent(txtAvaiQuan))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addComponent(txtPrice))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProductImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txtSalesId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGenerate))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnRequest)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel15)
                                        .addComponent(cbProductID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStockQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSellQuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAvaiQuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(0, 128, 128));

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(245, 245, 245));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Nett Amount");

        lblNettAmount.setFont(new java.awt.Font("Calibri", 0, 30)); // NOI18N
        lblNettAmount.setForeground(new java.awt.Color(245, 245, 245));

        lblNettAmount1.setFont(new java.awt.Font("Calibri", 0, 30)); // NOI18N
        lblNettAmount1.setForeground(new java.awt.Color(245, 245, 245));
        lblNettAmount1.setText("Rs:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblNettAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNettAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNettAmount1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNettAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblactive.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lblactive.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblactive.setText("Active:");

        lblDate.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDate.setText("Today: ");

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
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

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jMenuItem1.setText("Dashboard");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblactive, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblactive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuStripQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStripQuitActionPerformed
        quit();
    }//GEN-LAST:event_menuStripQuitActionPerformed
    
    private void menuStripHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuStripHomeMouseClicked
        if(grossAmount == 0)
        {
            new Dashboard().setVisible(true);
            this.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "You cant't go back untill you make the payment!", "Access Denied!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_menuStripHomeMouseClicked

    private void menuStripHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStripHomeActionPerformed
        if(grossAmount == 0)
        {
            new Dashboard().setVisible(true);
            this.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "You cant't go back untill you make the payment!", "Access Denied!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_menuStripHomeActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        /*if(grossAmount == 0)
        {*/
            new SalesDashboard().setVisible(true);
            this.dispose();
        /*}
        else
        {
            JOptionPane.showMessageDialog(null, "You cant't go back untill you make the payment!", "Access Denied!", JOptionPane.INFORMATION_MESSAGE);
        }*/
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        txtSalesId.setText(String.valueOf(generateid()+1));
    }//GEN-LAST:event_btnGenerateActionPerformed

    private void dgvSalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgvSalesMouseClicked
        btnGenerate.setEnabled(false);
        btnCart.setEnabled(false);
        btnUpdate.setEnabled(true);
        btnRemove.setEnabled(true);
        
        int i = dgvSales.getSelectedRow();
        TableModel m = dgvSales.getModel();
        txtSalesId.setText(m.getValueAt(i,0).toString());
        lblactive.setText(m.getValueAt(i,1).toString());
        cbProductID.setSelectedItem(m.getValueAt(i,2).toString());
        cbCustomerID.setSelectedItem(m.getValueAt(i,3).toString());
        txtSellQuan.setText(m.getValueAt(i,4).toString());
        quan = Integer.parseInt(m.getValueAt(i,4).toString());
        txtPrice.setText(m.getValueAt(i,5).toString());
    }//GEN-LAST:event_dgvSalesMouseClicked

    private void btnCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartActionPerformed
        int tot = 0;
        tot = tot+1;
        if("".equals(txtSalesId.getText()) || "".equals(salesPerson) || cbProductID.getSelectedItem() == "" || cbCustomerID.getSelectedItem() == "" || "".equals(txtSellQuan.getText()) || "".equals(txtPrice.getText()))
        {
            JOptionPane.showMessageDialog(null, "You can't keep field empty!","Empty",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            int id = generateid()+1;
            Date d = new Date();
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
            String da = DateFormat.format(d.getDate());
            String query = "INSERT INTO sales (salesid,salesperson,productid,customerid,date,quantity,price) VALUES('"+id+"','"+SignIn.sessionEmail+"','"+cbProductID.getSelectedItem()+"','"+cbCustomerID.getSelectedItem().toString()+"','"+da+"','"+txtSellQuan.getText()+"','"+txtPrice.getText()+"')";
            String query1 = "UPDATE product SET quantity='"+txtAvaiQuan.getText()+"' WHERE productid='"+cbProductID.getSelectedItem().toString()+"'";
            queryExecution iSales = new queryExecution();
            if(iSales.executeQuery(query) == true)
            {
                if(iSales.updateQuery(query1) == true)
                {
                    JOptionPane.showMessageDialog(null, "Sales details added successfully!", "Added", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Error occured while updating stock!", "Error", JOptionPane.ERROR_MESSAGE);                     
                }
            }
            else
            {
                
                JOptionPane.showMessageDialog(null, "Error occured while adding!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            DefaultTableModel model = (DefaultTableModel)dgvSales.getModel();
            if(tot == 1)
            {
                lid = Integer.parseInt(txtSalesId.getText())-1;
                if(lastID() >= lid)
                {
                    model.setRowCount(0);
                    String query2 = "SELECT * FROM sales WHERE salesid>'"+lid+"'";
                    loadSalesSearch(query2);
                }
            }
            else
            {
                model.setRowCount(0);
                String query3 = "SELECT * FROM sales WHERE salesid>'"+lid+"'";
                loadSalesSearch(query3);
            }
            
            grossAmount = grossAmount + (Double.parseDouble(txtPrice.getText())*Integer.parseInt(txtSellQuan.getText()));
            lblNettAmount.setText(String.valueOf(grossAmount));
            clear();
        }
    }//GEN-LAST:event_btnCartActionPerformed

    private void cbProductIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductIDActionPerformed
        if(cbProductID.getSelectedItem() == "-- Please Choose --")
        {
            txtProductName.setText("");
            txtStockQuantity.setText("");
            txtPrice.setText("");
        }
        else
        {
            loadStockDetails();
        }
    }//GEN-LAST:event_cbProductIDActionPerformed

    private void cbCustomerIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCustomerIDActionPerformed
        if(cbCustomerID.getSelectedItem() == "-- Choose --")
        {
            txtCustomerName.setText("");
        }
        else
        {
            id = Integer.parseInt(cbCustomerID.getSelectedItem().toString());
            loadCustomerDetails();
        }
    }//GEN-LAST:event_cbCustomerIDActionPerformed

    private void btnCheckArrearsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckArrearsActionPerformed
        if("".equals(cbCustomerID.getSelectedItem()))
        {
            JOptionPane.showMessageDialog(null, "Please select a customer", "Select", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            checkArrears();
        }
    }//GEN-LAST:event_btnCheckArrearsActionPerformed

    private void btnContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContactActionPerformed
        if("".equals(cbCustomerID.getSelectedItem()))
        {
            JOptionPane.showMessageDialog(null, "Please select a customer", "Select", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            checkContact();
        }
    }//GEN-LAST:event_btnContactActionPerformed

    private void cbPaymentTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPaymentTypeActionPerformed
        if("-- Please Choose --".equals(cbPaymentType.getSelectedItem()))
        {
            JOptionPane.showMessageDialog(null, "Please select a payment type", "Selection", JOptionPane.INFORMATION_MESSAGE);
        }
        else if("Cheque".equals(cbPaymentType.getSelectedItem()))
        {
            txtChequeNo.setEditable(true);
            txtChequeNo.setEnabled(true);
        }
        else
        {
            txtChequeNo.setEditable(false);
            txtChequeNo.setEnabled(false);
        }
    }//GEN-LAST:event_cbPaymentTypeActionPerformed

    private void txtPayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayKeyReleased
        amountPaid();
    }//GEN-LAST:event_txtPayKeyReleased

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed

        if(lblNettAmount.getText().equals("") || lblNettAmount.getText() == null)
        {
            JOptionPane.showMessageDialog(null, "You can't make payment without any sales", "Restricted", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            paymenttype = cbPaymentType.getSelectedItem().toString();
            balance = Double.parseDouble(txtBalance.getText());
            chequeno = Long.parseLong(txtChequeNo.getText());
            amountpaid = Double.parseDouble(txtPay.getText());
            new PaymentC().setVisible(true);
        }
    }//GEN-LAST:event_btnPayActionPerformed

    private void txtSellQuanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSellQuanKeyReleased
        remainingQuantity();
    }//GEN-LAST:event_txtSellQuanKeyReleased

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        new CustomerMgt().setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtSellQuanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSellQuanKeyTyped
        char id =evt.getKeyChar();
        if(!(Character.isDigit(id) || (id==KeyEvent.VK_BACK_SPACE)|| id==KeyEvent.VK_DELETE)){
             getToolkit().beep();
              JOptionPane.showMessageDialog(null,"Numeric Values Only");
             evt.consume();
        }
    }//GEN-LAST:event_txtSellQuanKeyTyped

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if("".equals(txtSalesId.getText()) || "".equals(salesPerson) || cbProductID.getSelectedItem() == "" || cbCustomerID.getSelectedItem() == "" || "".equals(txtSellQuan.getText()) || "".equals(txtPrice.getText()))
        {
            JOptionPane.showMessageDialog(null, "You can't keep field empty!","Empty",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            int id = generateid()+1;
            Date d = new Date();
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
            String da = DateFormat.format(d.getDate());
            int quan1 = Integer.parseInt(txtSellQuan.getText());
            int stableQuan=0,stable1=0;
            if(quan > quan1)
            {
                stableQuan = quan1 - quan;
                stable1 = Integer.parseInt(txtAvaiQuan.getText())+stableQuan;
            }
            else
            {
                stableQuan = quan1 - quan;
                stable1 = Integer.parseInt(txtAvaiQuan.getText())+stableQuan;
            }
            
            String query = "UPDATE sales SET salesperson = '"+SignIn.sessionEmail+"', productid = '"+cbProductID.getSelectedItem()+"', customerid = '"+cbCustomerID.getSelectedItem()+"', date = '"+da+"', quantity = '"+txtSellQuan.getText()+"', price = '"+txtPrice.getText()+"' WHERE salesid='"+txtSalesId.getText()+"'";
            String query1 = "UPDATE product SET quantity='"+stable1+"' WHERE productid='"+cbProductID.getSelectedItem().toString()+"'";
            queryExecution iSales = new queryExecution();
            if(iSales.executeQuery(query) == true)
            {
                if(iSales.updateQuery(query1) == true)
                {
                    JOptionPane.showMessageDialog(null, "Sales details updated successfully!", "Added", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Error occured while updating stock!", "Error", JOptionPane.ERROR_MESSAGE);                     
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error occured while updating sales!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            DefaultTableModel model = (DefaultTableModel)dgvSales.getModel();
            model.setRowCount(0);
            String query3 = "SELECT * FROM sales WHERE salesid>'"+lid+"'";
            loadSalesSearch(query3);
            double val = Double.parseDouble(txtSellQuan.getText()) * Double.parseDouble(txtPrice.getText());
            double val1 = grossAmount - val;
            grossAmount = grossAmount + val1;
            lblNettAmount.setText(String.valueOf(grossAmount));
            clear();
            btnCart.setEnabled(true);
            btnUpdate.setEnabled(false);
            btnRemove.setEnabled(false);
            btnGenerate.setEnabled(false);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        if("".equals(txtSalesId.getText()) || "".equals(salesPerson) || cbProductID.getSelectedItem() == "" || cbCustomerID.getSelectedItem() == "" || "".equals(txtSellQuan.getText()) || "".equals(txtPrice.getText()))
        {
            JOptionPane.showMessageDialog(null, "You can't keep field empty!","Empty",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            int quan1 = Integer.parseInt(txtSellQuan.getText());
            
            int stable1=0;
            stable1 = Integer.parseInt(txtAvaiQuan.getText())+quan;
            
            String query = "DELETE FROM sales WHERE salesid='"+txtSalesId.getText()+"'";
            String query1 = "UPDATE product SET quantity='"+stable1+"' WHERE productid='"+cbProductID.getSelectedItem().toString()+"'";
            queryExecution iSales = new queryExecution();
            if(iSales.executeQuery(query) == true)
            {
                if(iSales.updateQuery(query1) == true)
                {
                    JOptionPane.showMessageDialog(null, "Sales details deleted successfully!", "Added", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Error occured while updating stock!", "Error", JOptionPane.ERROR_MESSAGE);                     
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error occured while deleting sales!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            DefaultTableModel model = (DefaultTableModel)dgvSales.getModel();
            model.setRowCount(0);
            String query3 = "SELECT * FROM sales WHERE salesid>'"+lid+"'";
            loadSalesSearch(query3);
            
            grossAmount = grossAmount - (Double.parseDouble(txtPrice.getText())*Integer.parseInt(txtSellQuan.getText()));
            lblNettAmount.setText(String.valueOf(grossAmount));
            clear();
            btnCart.setEnabled(true);
            btnUpdate.setEnabled(false);
            btnRemove.setEnabled(false);
            btnGenerate.setEnabled(false);
        }
    }//GEN-LAST:event_btnRemoveActionPerformed
                
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>
        
        java.awt.EventQueue.invokeLater(() -> {
            new SalesFinal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCart;
    private javax.swing.JButton btnCheckArrears;
    private javax.swing.JButton btnContact;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton btnPay;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnRequest;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbCustomerID;
    private javax.swing.JComboBox<String> cbPaymentType;
    private javax.swing.JComboBox<String> cbProductID;
    private javax.swing.JTable dgvSales;
    private javax.swing.JFormattedTextField dtpDate;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblNettAmount;
    private javax.swing.JLabel lblNettAmount1;
    private javax.swing.JLabel lblProductImage;
    private javax.swing.JLabel lblactive;
    private javax.swing.JLabel lblsales;
    private javax.swing.JMenuItem menuStripHome;
    private javax.swing.JMenuItem menuStripQuit;
    private javax.swing.JTextField txtAvaiQuan;
    private javax.swing.JLabel txtBalance;
    private javax.swing.JTextField txtChequeNo;
    private javax.swing.JLabel txtCustomerName;
    private javax.swing.JTextField txtPay;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtSalesId;
    private javax.swing.JTextField txtSellQuan;
    private javax.swing.JTextField txtStockQuantity;
    // End of variables declaration//GEN-END:variables
}
