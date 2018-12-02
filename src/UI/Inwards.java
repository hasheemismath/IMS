/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Class.ConnectionProvider;
import Class.InwardsClass;
import Class.queryExecution;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Roshan Nizar
 */
public class Inwards extends javax.swing.JFrame {

    /**
     * Creates new form Inwards
     */
    public Inwards() {
        initComponents();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        loadStockID();
        loadCustomerID();
        loadInward();
    }
    
    private ArrayList<InwardsClass> inwardList()
    {
        ArrayList<InwardsClass> slist = new ArrayList<>();
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM inward";
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            InwardsClass i;
            
            while(rs.next())
            {
                i = new InwardsClass(rs.getInt("inwardid"),rs.getLong("productid"),rs.getInt("customerid"),rs.getString("description"),rs.getString("date"),rs.getInt("quantity"),rs.getDouble("price"));
                slist.add(i);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return slist;
    }
    
    private ArrayList<InwardsClass> inwardSearch(String type)
    {
        ArrayList<InwardsClass> slist = new ArrayList<>();
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(type);
            
            InwardsClass i;
            
            while(rs.next())
            {
                i = new InwardsClass(rs.getInt("inwardid"),rs.getLong("productid"),rs.getInt("customerid"),rs.getString("description"),rs.getString("date"),rs.getInt("quantity"),rs.getDouble("price"));
                slist.add(i);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return slist;
    }
    
    private void searchInward(String query)
    {
        ArrayList <InwardsClass> list = inwardSearch(query);
        DefaultTableModel dtm = (DefaultTableModel)dgvInward.getModel();
        Object []row = new Object[8];
        
        for(int i=0;i<list.size();i++)
        {
            row[0] = list.get(i).getInwardID();
            row[1] = list.get(i).getProductID();
            row[2] = list.get(i).getCustomerID();
            row[3] = list.get(i).getDescription();
            row[4] = list.get(i).getDate();
            row[5] = list.get(i).getQuantity();
            dtm.addRow(row);
        }
    }
    
    private void loadInward()
    {
        ArrayList <InwardsClass> list = inwardList();
        DefaultTableModel dtm = (DefaultTableModel)dgvInward.getModel();
        Object []row = new Object[8];
        
        for(int i=0;i<list.size();i++)
        {
            row[0] = list.get(i).getInwardID();
            row[1] = list.get(i).getProductID();
            row[2] = list.get(i).getCustomerID();
            row[3] = list.get(i).getDescription();
            row[4] = list.get(i).getDate();
            row[5] = list.get(i).getQuantity();
            dtm.addRow(row);
        }
    }
    
    private int generateID()
    {
        String query = "SELECT * FROM inward";
        String rowName = "inwardid";
        
        return (int) queryExecution.generateID(query, rowName);
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
                cbproductid.addItem(String.valueOf(rs.getInt("productid")));
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
                cbcustomerid.addItem(String.valueOf(rs.getInt("customerid")));
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
            id = cbproductid.getSelectedItem().toString();
            String query = "SELECT  * FROM product WHERE productid='"+id+"'";
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next())
            {
                txtproductname.setText(rs.getString("productname"));
                txtquantity.setText(rs.getString("quantity"));
                txtprice.setText(rs.getString("price"));
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
            id = cbcustomerid.getSelectedItem().toString();
            String query = "SELECT * FROM customer WHERE customerid='"+id+"'";
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next())
            {
                txtcustomername.setText(rs.getString("firstname")+" "+rs.getString("lastname"));
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
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
    
    public void clear()
    {
        txtInwardID.setText("");
        cbproductid.setSelectedItem("-- Please Choose --");
        cbcustomerid.setSelectedItem("-- Choose --");
        txtdescription.setText("");
        txtquantity.setText("");
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
        jPanel4 = new javax.swing.JPanel();
        lblsales = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dgvInward = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblstockid = new javax.swing.JLabel();
        lblsalesid = new javax.swing.JLabel();
        txtInwardID = new javax.swing.JTextField();
        btngenerate = new javax.swing.JButton();
        cbproductid = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtproductname = new javax.swing.JTextField();
        lblcustomerid = new javax.swing.JLabel();
        cbcustomerid = new javax.swing.JComboBox<>();
        lblcustomername = new javax.swing.JLabel();
        txtcustomername = new javax.swing.JTextField();
        btncheckarrears = new javax.swing.JButton();
        lbldescription = new javax.swing.JLabel();
        txtdescription = new javax.swing.JTextField();
        dtpdate = new com.toedter.calendar.JDateChooser();
        lblquantity = new javax.swing.JLabel();
        btntoday = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnadd = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        lblbasedon = new javax.swing.JLabel();
        cbbasedon = new javax.swing.JComboBox<>();
        txtsearch = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        lblquantity1 = new javax.swing.JLabel();
        lblquantity2 = new javax.swing.JLabel();
        txtprice = new javax.swing.JTextField();
        txtquantity = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NA - Inwards");

        jPanel4.setBackground(new java.awt.Color(0, 31, 63));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 31, 63), 1, true));

        lblsales.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lblsales.setForeground(new java.awt.Color(245, 245, 245));
        lblsales.setText("Inwards");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addComponent(lblsales)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblsales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jScrollPane1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        dgvInward.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        dgvInward.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Inward ID", "Product ID", "Customer ID", "Description", "Date", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        dgvInward.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dgvInwardMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dgvInward);

        lblstockid.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblstockid.setText("Product ID");

        lblsalesid.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblsalesid.setText("Inwards ID");

        txtInwardID.setEditable(false);
        txtInwardID.setBackground(new java.awt.Color(204, 204, 204));
        txtInwardID.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        btngenerate.setBackground(new java.awt.Color(102, 102, 102));
        btngenerate.setForeground(new java.awt.Color(245, 245, 245));
        btngenerate.setText("Generate ID");
        btngenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngenerateActionPerformed(evt);
            }
        });

        cbproductid.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbproductid.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Please Choose --" }));
        cbproductid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbproductidActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setText("Product Name");

        txtproductname.setEditable(false);
        txtproductname.setBackground(new java.awt.Color(204, 204, 204));
        txtproductname.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        lblcustomerid.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblcustomerid.setText("Customer ID");

        cbcustomerid.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbcustomerid.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Choose --" }));
        cbcustomerid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcustomeridActionPerformed(evt);
            }
        });

        lblcustomername.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblcustomername.setText("Customer Name");

        txtcustomername.setEditable(false);
        txtcustomername.setBackground(new java.awt.Color(204, 204, 204));
        txtcustomername.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        btncheckarrears.setBackground(new java.awt.Color(102, 102, 102));
        btncheckarrears.setForeground(new java.awt.Color(245, 245, 245));
        btncheckarrears.setText("Check Arrears");

        lbldescription.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lbldescription.setText("Description");

        txtdescription.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        dtpdate.setDateFormatString("yyyy-MM-dd");
        dtpdate.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        lblquantity.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblquantity.setText("Date");

        btntoday.setBackground(new java.awt.Color(102, 102, 102));
        btntoday.setForeground(new java.awt.Color(245, 245, 245));
        btntoday.setText("Today");
        btntoday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntodayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblstockid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblsalesid, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                            .addComponent(jLabel1))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtInwardID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btngenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbproductid, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtproductname)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblcustomername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblcustomerid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lbldescription)
                            .addComponent(lblquantity))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(dtpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btntoday, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtdescription)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbcustomerid, 0, 146, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btncheckarrears, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtcustomername))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblsalesid)
                            .addComponent(txtInwardID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btngenerate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbproductid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblstockid, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtproductname)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbcustomerid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btncheckarrears))
                            .addComponent(lblcustomerid, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtcustomername)
                            .addComponent(lblcustomername, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbldescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtdescription))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btntoday)
                            .addComponent(lblquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dtpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

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

        btnupdate.setBackground(new java.awt.Color(238, 118, 60));
        btnupdate.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnupdate.setForeground(new java.awt.Color(245, 244, 245));
        btnupdate.setText("Update");
        btnupdate.setEnabled(false);
        btnupdate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnupdate.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btndelete.setBackground(new java.awt.Color(220, 20, 60));
        btndelete.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btndelete.setForeground(new java.awt.Color(245, 244, 245));
        btndelete.setText("Delete");
        btndelete.setEnabled(false);
        btndelete.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btndelete.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        lblbasedon.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lblbasedon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblbasedon.setText("Search Based On: ");

        cbbasedon.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbbasedon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Please Choose --", "Inward ID", "Product ID", "Customer ID", "Quantity" }));

        txtsearch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });

        lblquantity1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblquantity1.setText("Quantity");

        lblquantity2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblquantity2.setText("Price");

        txtprice.setEditable(false);
        txtprice.setBackground(new java.awt.Color(204, 204, 204));
        txtprice.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        txtquantity.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtquantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtquantityKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblquantity1)
                    .addComponent(lblquantity2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtquantity, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                    .addComponent(txtprice))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtquantity)
                    .addComponent(lblquantity1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtprice)
                    .addComponent(lblquantity2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblbasedon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbasedon, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 423, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbasedon)
                        .addComponent(lblbasedon))
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jMenuItem1.setText("New");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jMenuItem2.setText("Home");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jMenuItem3.setText("Back to Overview");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator1);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jMenuItem4.setText("Quit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Report");
        jMenu2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

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

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        if(cbproductid.getSelectedItem().equals("-- Please Choose --"))
        {
            JOptionPane.showMessageDialog(null, "Can't keep product id empty!", "Empty", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(cbcustomerid.getSelectedItem().equals("-- Choose --"))
        {
            JOptionPane.showMessageDialog(null, "Can't keep customer id empty!", "Empty", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(txtquantity.getText().equals(""))
        {
            JOptionPane.showConfirmDialog(null, "Can't keep quantity field empty!", "Empty", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(lbldescription.getText().equals(""))
        {
            JOptionPane.showConfirmDialog(null, "Can't keep description field empty!", "Empty", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(dtpdate.getDate().equals(""))
        {
            JOptionPane.showConfirmDialog(null, "Can't keep date field empty!", "Empty", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            int id = generateID()+1;
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
            String da = DateFormat.format(dtpdate.getDate());
            String query = "INSERT INTO inward (productid,customerid,description,date,quantity,price) VALUES('"+cbproductid.getSelectedItem()+"','"+cbcustomerid.getSelectedItem().toString()+"','"+txtdescription.getText()+"','"+da+"','"+txtquantity.getText()+"','"+txtprice.getText()+"')";
            
            queryExecution iStock = new queryExecution();
        
            if(iStock.executeQuery(query) == true)
            {
                JOptionPane.showMessageDialog(null, "Inwards details added successfully!", "Added", JOptionPane.INFORMATION_MESSAGE);
                clear();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error occured while adding!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            DefaultTableModel model = (DefaultTableModel)dgvInward.getModel();
            model.setRowCount(0);
            loadInward();
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        if(txtInwardID.getText().equals("-- Please Choose --"))
        {
            JOptionPane.showMessageDialog(null, "Can't keep product id empty!", "Empty", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            int id = generateID()+1;
            String query = "DELETE FROM inward WHERE inwardid = '"+txtInwardID.getText()+"'";
        
            queryExecution iStock = new queryExecution();
        
            if(iStock.executeQuery(query) == true)
            {
                JOptionPane.showMessageDialog(null, "Inwards details deleted successfully!", "Deleted", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error occured while deleting!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            DefaultTableModel model = (DefaultTableModel)dgvInward.getModel();
            model.setRowCount(0);
            loadInward();
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new Dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new InwardsDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void btngenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngenerateActionPerformed
        txtInwardID.setText(String.valueOf(generateID()));
    }//GEN-LAST:event_btngenerateActionPerformed

    private void btntodayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntodayActionPerformed
        Date d = new Date();
        dtpdate.setDate(d);
    }//GEN-LAST:event_btntodayActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        if(cbproductid.getSelectedItem().equals("-- Please Choose --"))
        {
            JOptionPane.showMessageDialog(null, "Can't keep product id empty!", "Empty", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(cbcustomerid.getSelectedItem().equals("-- Choose --"))
        {
            JOptionPane.showMessageDialog(null, "Can't keep customer id empty!", "Empty", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(txtquantity.getText().equals(""))
        {
            JOptionPane.showConfirmDialog(null, "Can't keep quantity field empty!", "Empty", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(txtdescription.getText().equals(""))
        {
            JOptionPane.showConfirmDialog(null, "Can't keep description field empty!", "Empty", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(dtpdate.getDate().equals(""))
        {
            JOptionPane.showConfirmDialog(null, "Can't keep date field empty!", "Empty", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            int id = generateID()+1;
            SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
            String da = DateFormat.format(dtpdate.getDate());
            String query = "UPDATE inward SET productid='"+cbproductid.getSelectedItem()+"', customerid = '"+cbcustomerid.getSelectedItem()+"', description = '"+txtdescription.getText()+"', date = '"+da+"', quantity = '"+txtquantity.getText()+"', price='"+txtprice.getText()+"' WHERE inwardid = '"+txtInwardID.getText()+"'";
        
            queryExecution iInwards = new queryExecution();
        
            if(iInwards.executeQuery(query) == true)
            {
                JOptionPane.showMessageDialog(null, "Inwards details updated successfully!", "Updated", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error occured while updating!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            DefaultTableModel model = (DefaultTableModel)dgvInward.getModel();
            model.setRowCount(0);
            loadInward();
        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void cbproductidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbproductidActionPerformed
        if(cbproductid.getSelectedItem() == "-- Please Choose --")
        {
            txtproductname.setText("");
            txtquantity.setText("");
            txtprice.setText("");
        }
        else
        {
            loadStockDetails();
        }
    }//GEN-LAST:event_cbproductidActionPerformed

    private void cbcustomeridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcustomeridActionPerformed
        if(cbcustomerid.getSelectedItem() == "-- Choose --")
        {
            txtcustomername.setText("");
            
        }
        else
        {
            loadCustomerDetails();
        }
    }//GEN-LAST:event_cbcustomeridActionPerformed

    private void dgvInwardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgvInwardMouseClicked
       
        btngenerate.setEnabled(false);
        btnadd.setEnabled(false);
        btnupdate.setEnabled(true);
        btndelete.setEnabled(true);
        
        int i = dgvInward.getSelectedRow();
        TableModel m = dgvInward.getModel();
        txtInwardID.setText(m.getValueAt(i,0).toString());
        cbproductid.setSelectedItem(m.getValueAt(i,1).toString());
        cbcustomerid.setSelectedItem(m.getValueAt(i,2).toString());
        txtdescription.setText(m.getValueAt(i, 3).toString());
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        try
        {
            dtpdate.setDate(DateFormat.parse(m.getValueAt(i, 4).toString()));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        txtquantity.setText(m.getValueAt(i,5).toString());
    }//GEN-LAST:event_dgvInwardMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        txtInwardID.setText("");
        cbproductid.setSelectedItem("-- Please Choose --");
        cbcustomerid.setSelectedItem("-- Choose --");
        txtdescription.setText("");
        txtquantity.setText("");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
        if(txtsearch.getText().equals(""))
        {
            DefaultTableModel model = (DefaultTableModel)dgvInward.getModel();
            model.setRowCount(0);
            loadInward();
        }
        else if(cbbasedon.getSelectedItem().equals("Inward ID"))
        {
            String query = "SELECT * FROM inward WHERE inwardid='"+txtsearch.getText()+"'";
            DefaultTableModel model = (DefaultTableModel)dgvInward.getModel();
            model.setRowCount(0);
            searchInward(query);
        }
        else if(cbbasedon.getSelectedItem().equals("Product ID"))
        {
            String query = "SELECT * FROM inward WHERE productid='"+txtsearch.getText()+"'";
            DefaultTableModel model = (DefaultTableModel)dgvInward.getModel();
            model.setRowCount(0);
            searchInward(query);
        }
        else if(cbbasedon.getSelectedItem().equals("Customer ID"))
        {
            String query = "SELECT * FROM inward WHERE customerid='"+txtsearch.getText()+"'";
            DefaultTableModel model = (DefaultTableModel)dgvInward.getModel();
            model.setRowCount(0);
            searchInward(query);
        }
        else if(cbbasedon.getSelectedItem().equals("Quantity"))
        {
            String query = "SELECT * FROM inward WHERE quantity='"+txtsearch.getText()+"'";
            DefaultTableModel model = (DefaultTableModel)dgvInward.getModel();
            model.setRowCount(0);
            searchInward(query);
        }
        else 
        {
            JOptionPane.showMessageDialog(null, "Please choose the search type", "Search type INVALID", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_txtsearchKeyReleased

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        quit();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void txtquantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtquantityKeyTyped
        char id =evt.getKeyChar();
        if(!(Character.isDigit(id) || (id==KeyEvent.VK_BACK_SPACE)|| id==KeyEvent.VK_DELETE)){
             getToolkit().beep();
              JOptionPane.showMessageDialog(null,"Numeric Values Only");
             evt.consume();
        }
    }//GEN-LAST:event_txtquantityKeyTyped

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
            java.util.logging.Logger.getLogger(Inwards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inwards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inwards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inwards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inwards().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btncheckarrears;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btngenerate;
    private javax.swing.JButton btntoday;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> cbbasedon;
    private javax.swing.JComboBox<String> cbcustomerid;
    private javax.swing.JComboBox<String> cbproductid;
    private javax.swing.JTable dgvInward;
    private com.toedter.calendar.JDateChooser dtpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblbasedon;
    private javax.swing.JLabel lblcustomerid;
    private javax.swing.JLabel lblcustomername;
    private javax.swing.JLabel lbldescription;
    private javax.swing.JLabel lblquantity;
    private javax.swing.JLabel lblquantity1;
    private javax.swing.JLabel lblquantity2;
    private javax.swing.JLabel lblsales;
    private javax.swing.JLabel lblsalesid;
    private javax.swing.JLabel lblstockid;
    private javax.swing.JTextField txtInwardID;
    private javax.swing.JTextField txtcustomername;
    private javax.swing.JTextField txtdescription;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txtproductname;
    private javax.swing.JTextField txtquantity;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables
}
