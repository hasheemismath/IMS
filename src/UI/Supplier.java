/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Class.ConnectionProvider;
import Class.SupplierClass;
import Class.queryExecution;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Roshan Nizar
 */
public class Supplier extends javax.swing.JFrame {

    boolean contact_result;
    boolean address_result;
    boolean fname_result;
    boolean lname_result;
    boolean email_result;
    
    boolean empty_result;

    int id, cid;
    int pos = 0;
    /**
     * Creates new form Inwards
     */
    public Supplier() {
        initComponents();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        loadSupplier();
    }
    
    private ArrayList<SupplierClass> supplierList()
    {
        ArrayList<SupplierClass> slist = new ArrayList<>();
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM supplier";
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            SupplierClass i;
            
            while(rs.next())
            {
                i = new SupplierClass(rs.getInt("supplierid"), rs.getString("companyname"), rs.getString("address"), rs.getString("email"), rs.getInt("contactno"));
                slist.add(i);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return slist;
    }
    
    private ArrayList<SupplierClass> SupplierSearch(String type)
    {
        ArrayList<SupplierClass> slist = new ArrayList<>();
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(type);
            
            SupplierClass i;
            
            while(rs.next())
            {
                i = new SupplierClass(rs.getInt("supplierid"), rs.getString("companyname"), rs.getString("address"), rs.getString("email"), rs.getInt("contactno"));
                slist.add(i);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return slist;
    }
    
    private void searchSupplier(String query)
    {
        ArrayList <SupplierClass> list = SupplierSearch(query);
        DefaultTableModel dtm = (DefaultTableModel)dgvSupplier.getModel();
        Object []row = new Object[5];
        
        for(int i=0;i<list.size();i++)
        {
            row[0] = list.get(i).getSupplierid();
            row[1] = list.get(i).getCompanyname();
            row[2] = list.get(i).getAddress();
            row[3] = list.get(i).getEmail();
            row[4] = list.get(i).getContactno();
            dtm.addRow(row);
        }
    }
    
    private void loadSupplier()
    {
        ArrayList <SupplierClass> list = supplierList();
        DefaultTableModel dtm = (DefaultTableModel)dgvSupplier.getModel();
        Object []row = new Object[5];
        
        for(int i=0;i<list.size();i++)
        {
            row[0] = list.get(i).getSupplierid();
            row[1] = list.get(i).getCompanyname();
            row[2] = list.get(i).getAddress();
            row[3] = list.get(i).getEmail();
            row[4] = list.get(i).getContactno();
            dtm.addRow(row);
        }
    }
    public void showItems(int index) {

        btnadd.setEnabled(false);
        btnupdate.setEnabled(true);
        btndelete.setEnabled(true);
        txtsupplierid.setEditable(false);

        txtsupplierid.setText(Integer.toString(supplierList().get(index).getSupplierid()));
        txtcompanyname.setText(supplierList().get(index).getCompanyname());
        txtaddress.setText(supplierList().get(index).getAddress());
        txtemail.setText(supplierList().get(index).getEmail());
        txtcontactno.setText(Integer.toString(supplierList().get(index).getContactno()));
       

    }




public void reloadCustomer() {
        clear();
        btnadd.setEnabled(true);
        btnupdate.setEnabled(false);
        btndelete.setEnabled(false);
    }



private void clear() {
        DefaultTableModel dtm = (DefaultTableModel) dgvSupplier.getModel();
        dtm.setRowCount(0);
        loadSupplier();
        generateID();
        cbbasedon.setSelectedItem("-- Please Choose --");
        txtcompanyname.setText("");
        txtaddress.setText("");
        txtemail.setText("");
        txtcontactno.setText("");
        common.setText("");

    }
    
    private int generateID()
    {
        try {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            Statement st = con.createStatement();
            String query = "SELECT * FROM supplier";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                id = rs.getInt("supplierid");
            }

            cid = id + 1;
            txtsupplierid.setText(String.valueOf(cid));

        } catch (Exception e) {
            System.out.println(e);
        }

        return cid;
        
        
        
        
        
    }
    
    private void quit()
    {
        int i = JOptionPane.showConfirmDialog(null,"Are you sure to QUIT?", "Exit", JOptionPane.INFORMATION_MESSAGE);
        
        if(JOptionPane.YES_OPTION == i)
        {
            this.dispose();
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
        jPanel4 = new javax.swing.JPanel();
        lblsales = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dgvSupplier = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblstockid = new javax.swing.JLabel();
        lblsalesid = new javax.swing.JLabel();
        txtcompanyname = new javax.swing.JTextField();
        btngenerate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtaddress = new javax.swing.JTextField();
        lblcustomername = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        txtsupplierid = new javax.swing.JTextField();
        lblcustomername1 = new javax.swing.JLabel();
        txtcontactno = new javax.swing.JTextField();
        common = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnupdate = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();
        lblbasedon = new javax.swing.JLabel();
        cbbasedon = new javax.swing.JComboBox<>();
        txtsearch = new javax.swing.JTextField();
        btnadd = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_previous1 = new javax.swing.JButton();
        btn_first = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NA | Supplier");

        jPanel4.setBackground(new java.awt.Color(0, 31, 63));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 31, 63), 1, true));

        lblsales.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lblsales.setForeground(new java.awt.Color(245, 245, 245));
        lblsales.setText("Supplier");

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
            .addComponent(lblsales, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        jScrollPane1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        dgvSupplier.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        dgvSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SupplierID", "Company Name", "Address", "E-Mail", "Contact No"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dgvSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dgvSupplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dgvSupplier);

        lblstockid.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblstockid.setText("Company Name:");

        lblsalesid.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblsalesid.setText("Supplier ID:");

        txtcompanyname.setBackground(new java.awt.Color(245, 245, 245));
        txtcompanyname.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtcompanyname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcompanynameKeyReleased(evt);
            }
        });

        btngenerate.setBackground(new java.awt.Color(102, 102, 102));
        btngenerate.setForeground(new java.awt.Color(245, 245, 245));
        btngenerate.setText("Generate ID");
        btngenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngenerateActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setText("Address:");

        txtaddress.setBackground(new java.awt.Color(245, 245, 245));
        txtaddress.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtaddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtaddressKeyReleased(evt);
            }
        });

        lblcustomername.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblcustomername.setText("E-Mail:");

        txtemail.setBackground(new java.awt.Color(245, 245, 245));
        txtemail.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtemailKeyReleased(evt);
            }
        });

        txtsupplierid.setEditable(false);
        txtsupplierid.setBackground(new java.awt.Color(204, 204, 204));
        txtsupplierid.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        lblcustomername1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblcustomername1.setText("Contact No:");

        txtcontactno.setBackground(new java.awt.Color(245, 245, 245));
        txtcontactno.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtcontactno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcontactnoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(common, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblstockid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblsalesid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel1)
                            .addComponent(lblcustomername)
                            .addComponent(lblcustomername1))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcontactno)
                            .addComponent(txtemail)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtsupplierid, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btngenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtaddress)
                            .addComponent(txtcompanyname, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblsalesid)
                    .addComponent(btngenerate)
                    .addComponent(txtsupplierid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblstockid, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcompanyname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtemail)
                    .addComponent(lblcustomername, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtcontactno)
                    .addComponent(lblcustomername1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(common, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
        );

        btnupdate.setBackground(new java.awt.Color(0, 0, 255));
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
        btnupdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnupdateKeyReleased(evt);
            }
        });

        btnclear.setBackground(new java.awt.Color(238, 118, 60));
        btnclear.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnclear.setForeground(new java.awt.Color(245, 244, 245));
        btnclear.setText("Clear");
        btnclear.setEnabled(false);
        btnclear.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnclear.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });
        btnclear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnclearKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        lblbasedon.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lblbasedon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblbasedon.setText("Search Based On: ");

        cbbasedon.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbbasedon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Please Choose --", "Supplier ID", "Company Name", "Address", "E-Mail", "Contact No" }));

        txtsearch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
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

        btn_previous1.setBackground(new java.awt.Color(255, 255, 255));
        btn_previous1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/previous.png"))); // NOI18N
        btn_previous1.setText("Previous");
        btn_previous1.setIconTextGap(15);
        btn_previous1.setPreferredSize(new java.awt.Dimension(100, 30));
        btn_previous1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_previous1ActionPerformed(evt);
            }
        });

        btn_first.setBackground(new java.awt.Color(255, 255, 255));
        btn_first.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/first.png"))); // NOI18N
        btn_first.setText("      First");
        btn_first.setIconTextGap(15);
        btn_first.setPreferredSize(new java.awt.Dimension(100, 30));
        btn_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_firstActionPerformed(evt);
            }
        });

        btn_last.setBackground(new java.awt.Color(255, 255, 255));
        btn_last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/last.png"))); // NOI18N
        btn_last.setText("Last");
        btn_last.setIconTextGap(15);
        btn_last.setPreferredSize(new java.awt.Dimension(100, 30));
        btn_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lastActionPerformed(evt);
            }
        });

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
                                .addGap(135, 135, 135)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_previous1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_first, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_next, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnadd, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                    .addComponent(btndelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_first, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_previous1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
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

        String supplierID = txtsupplierid.getText();
       String name = txtcompanyname.getText();
        
        String email = txtemail.getText();
        String contactno = txtcontactno.getText();
        String address = txtaddress.getText();

        if (fname_result) {
            JOptionPane.showMessageDialog(null, "Name must have Alphabet Characters Only");
        }

        

        if (email_result) {
            JOptionPane.showMessageDialog(null, "Enter Valid Email Address");
        }

        if (address_result) {
            JOptionPane.showMessageDialog(null, "Enter Valid Address");
        }

        if (contact_result) {
            JOptionPane.showMessageDialog(null, "Contact Number is Invalid");
        }

        if (name.isEmpty() ||  email.isEmpty() || address.isEmpty() || contactno.isEmpty()) {
            empty_result = true;
            JOptionPane.showMessageDialog(null, "Empty Input Field");
        } else {
            empty_result = false;
        }

        if (address_result == false && lname_result == false && fname_result == false && contact_result == false && email_result == false && empty_result == false) {
            try {

                Connection con = ConnectionProvider.getInstance().getDBConnection();
                String query1 = "INSERT INTO supplier(supplierid,companyname,address,email,contactno) VALUES(?,?,?,?,?)";
                PreparedStatement ps1 = con.prepareStatement(query1);
                ps1.setString(1, supplierID);
                ps1.setString(2, name);
               ps1.setString(3, address);
                ps1.setString(4, email);
                
                ps1.setString(5, contactno);

                if (ps1.executeUpdate() != 0) {
                    JOptionPane.showMessageDialog(null, "supplier Added Successfully");
                    reloadCustomer();
                } else {
                    JOptionPane.showMessageDialog(null, "Error occured while adding!");
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

//        if(txtsupplierid.getText().equals(""))
//        {
//            JOptionPane.showMessageDialog(null, "Can't keep supplier id empty!", "Empty", JOptionPane.INFORMATION_MESSAGE);
//        }
//        else if(txtcompanyname.getText().equals(""))
//        {
//            JOptionPane.showMessageDialog(null, "Can't keep company name empty!", "Empty", JOptionPane.INFORMATION_MESSAGE);
//        }
//        else if(txtaddress.getText().equals(""))
//        {
//            JOptionPane.showConfirmDialog(null, "Can't keep address field empty!", "Empty", JOptionPane.INFORMATION_MESSAGE);
//        }
//        else if(txtemail.getText().equals(""))
//        {
//            JOptionPane.showConfirmDialog(null, "Can't keep email field empty!", "Empty", JOptionPane.INFORMATION_MESSAGE);
//        }
//        else if(txtcontactno.getText().equals(""))
//        {
//            JOptionPane.showConfirmDialog(null, "Can't keep contact no field empty!", "Empty", JOptionPane.INFORMATION_MESSAGE);
//        }
//        else
//        {
//            int id = generateID()+1;
//            String query = "INSERT INTO supplier (supplierid,companyname,address,email,contactno) VALUES('"+id+"','"+txtcompanyname.getText()+"','"+txtaddress.getText()+"','"+txtemail.getText()+"','"+txtcontactno.getText()+"')";
//        
//            queryExecution iSupplier = new queryExecution();
//        
//            if(iSupplier.executeQuery(query) == true)
//            {
//                JOptionPane.showMessageDialog(null, "Supplier details added successfully!", "Added", JOptionPane.INFORMATION_MESSAGE);
//            }
//            else
//            {
//                JOptionPane.showMessageDialog(null, "Error occured while adding!", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//            
//            DefaultTableModel model = (DefaultTableModel)dgvSupplier.getModel();
//            model.setRowCount(0);
//            reloadCustomer();
//        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        if(txtsupplierid.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Can't keep product id empty!", "Empty", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            
            int id = generateID()+1;
            
            String query = "DELETE FROM supplier WHERE supplierid = '"+txtsupplierid.getText()+"'";
        
            queryExecution iSupplier= new queryExecution();
        
            if(iSupplier.executeQuery(query) == true)
            {
                JOptionPane.showMessageDialog(null, "Supplier details deleted successfully!", "Added", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error occured while deleteing!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            DefaultTableModel model = (DefaultTableModel)dgvSupplier.getModel();
            model.setRowCount(0);
            reloadCustomer();
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new More().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new supplier_dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void btngenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngenerateActionPerformed
        txtsupplierid.setText(String.valueOf(generateID()));
    }//GEN-LAST:event_btngenerateActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed

        String supplirID = txtsupplierid.getText();
        String name = txtcompanyname.getText();
        
        String email = txtemail.getText();
        String contactno = txtcontactno.getText();
        String address = txtaddress.getText();

        if (fname_result) {
            JOptionPane.showMessageDialog(null, "please enter valid name");
        }

        

        if (email_result) {
            JOptionPane.showMessageDialog(null, "Enter Valid Email Address");
        }

        if (address_result) {
            JOptionPane.showMessageDialog(null, "Enter Valid Address");
        }

        if (contact_result) {
            JOptionPane.showMessageDialog(null, "Contact Number is Invalid");
        }

        if (name.isEmpty() ||email.isEmpty() || address.isEmpty() || contactno.isEmpty()) {
            empty_result = true;
            JOptionPane.showMessageDialog(null, "Empty Input Field");
        } else {
            empty_result = false;
        }

        if (address_result == false && fname_result == false && contact_result == false && email_result == false) {
            try {

                Connection con = ConnectionProvider.getInstance().getDBConnection();
                String query = "UPDATE supplier SET companyname=?,address=?,email=?,contactno=? WHERE supplierid='" + supplirID + "'";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, name);
                ps.setString(2, address);
                ps.setString(3, email);
                ps.setString(4, contactno);
                

                int i = JOptionPane.showConfirmDialog(null, "Are you sure to Update?", "Update Supplier", JOptionPane.YES_NO_OPTION);

                if (JOptionPane.YES_OPTION == i) {
                    if (ps.executeUpdate() != 0) {
                        JOptionPane.showMessageDialog(null, "Supplier Updated Successfully");
                       DefaultTableModel model = (DefaultTableModel)dgvSupplier.getModel();
                          model.setRowCount(0);
                          
                     reloadCustomer();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error : DataBase Connectivity Problem");
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }





    }//GEN-LAST:event_btnupdateActionPerformed

    private void dgvSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgvSupplierMouseClicked
       
        btngenerate.setEnabled(false);
        btnadd.setEnabled(false);
        btnupdate.setEnabled(true);
        btndelete.setEnabled(true);
        btnclear.setEnabled(true);
        
        int i = dgvSupplier.getSelectedRow();
        TableModel m = dgvSupplier.getModel();
        txtsupplierid.setText(m.getValueAt(i,0).toString());
        txtcompanyname.setText(m.getValueAt(i,1).toString());
        txtaddress.setText(m.getValueAt(i, 2).toString());
        txtemail.setText(m.getValueAt(i, 3).toString());
        txtcontactno.setText(m.getValueAt(i,4).toString());
    }//GEN-LAST:event_dgvSupplierMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        txtcompanyname.setText("");
        txtsupplierid.setText("");
        txtaddress.setText("");
        txtemail.setText("");
        txtcontactno.setText("");
        
        btngenerate.setEnabled(true);
        btnadd.setEnabled(true);
        btnupdate.setEnabled(false);
        btndelete.setEnabled(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
        if(txtsearch.getText().equals(""))
        {
            DefaultTableModel model = (DefaultTableModel)dgvSupplier.getModel();
            model.setRowCount(0);
            loadSupplier();
        }
        else if(cbbasedon.getSelectedItem().equals("Supplier ID"))
        {
            String query = "SELECT * FROM supplier WHERE supplierid='"+txtsearch.getText()+"'";
            DefaultTableModel model = (DefaultTableModel)dgvSupplier.getModel();
            model.setRowCount(0);
            searchSupplier(query);
        }
        else if(cbbasedon.getSelectedItem().equals("Company Name"))
        {
            String query = "SELECT * FROM supplier WHERE companyname like '%"+txtsearch.getText()+"%'";
            DefaultTableModel model = (DefaultTableModel)dgvSupplier.getModel();
            model.setRowCount(0);
            searchSupplier(query);
        }
        else if(cbbasedon.getSelectedItem().equals("Address"))
        {
            String query = "SELECT * FROM supplier WHERE address like '%"+txtsearch.getText()+"%'";
            DefaultTableModel model = (DefaultTableModel)dgvSupplier.getModel();
            model.setRowCount(0);
            searchSupplier(query);
        }
        else if(cbbasedon.getSelectedItem().equals("E-Mail"))
        {
            String query = "SELECT * FROM supplier WHERE email like '%"+txtsearch.getText()+"%'";
            DefaultTableModel model = (DefaultTableModel)dgvSupplier.getModel();
            model.setRowCount(0);
            searchSupplier(query);
        }
        else if(cbbasedon.getSelectedItem().equals("Contact No"))
        {
            String query = "SELECT * FROM supplier WHERE contactno = '"+txtsearch.getText()+"'";
            DefaultTableModel model = (DefaultTableModel)dgvSupplier.getModel();
            model.setRowCount(0);
            searchSupplier(query);
        }
        else 
        {
            JOptionPane.showMessageDialog(null, "Please choose the search type", "Search type INVALID", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_txtsearchKeyReleased

    private void txtcontactnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontactnoKeyReleased
        String contactno = txtcontactno.getText();
        String PATTERN = "^[0-9]{10}+$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(contactno);

        contact_result = !match.matches();

        if (contact_result) {
            common.setForeground(Color.red);
            common.setText("Contact Number is Invalid (Maximum Length = 10)");
        } else {
            common.setText(null);
        }
    }//GEN-LAST:event_txtcontactnoKeyReleased

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        quit();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnupdateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnupdateKeyReleased
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_btnupdateKeyReleased

    private void txtcompanynameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcompanynameKeyReleased
        // TODO add your handling code here:
        
        String fname = txtcompanyname.getText();
        String PATTERN = "^[a-zA-z]+$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(fname);

        fname_result = !match.matches();

        if (fname_result) {
            common.setForeground(Color.red);
            common.setText("company Name is not Correct");
        } else {
            common.setText(null);
        }
    }//GEN-LAST:event_txtcompanynameKeyReleased

    private void txtaddressKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaddressKeyReleased
        // TODO add your handling code here:
        
        String address = txtaddress.getText();
        String PATTERN = "^[a-zA-Z0-9/\\s,.'-]*$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(address);

        address_result = !match.matches();

        if (address_result) {
            common.setForeground(Color.red);
            common.setText("Address Field is not Correct");
        } else {
            common.setText(null);
        }
    }//GEN-LAST:event_txtaddressKeyReleased

    private void txtemailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyReleased
        // TODO add your handling code here:
        
        String position = txtemail.getText();
        String PATTERN = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(position);

        email_result = !match.matches();

        if (email_result) {
            common.setForeground(Color.red);
            common.setText("Email Field is not Correct");
        } else {
            common.setText(null);
        }
    }//GEN-LAST:event_txtemailKeyReleased

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // TODO add your handling code here:
         clear();
        btnadd.setEnabled(true);
        btnupdate.setEnabled(false);
        btndelete.setEnabled(false);
    }//GEN-LAST:event_btnclearActionPerformed

    private void btnclearKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnclearKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnclearKeyReleased

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        pos++;

        if (pos >= supplierList().size()) {
            pos = supplierList().size() - 1;
        }
        dgvSupplier.setRowSelectionInterval(pos, pos);
        showItems(pos);
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_previous1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_previous1ActionPerformed
        pos--;

        if (pos < 0) {
            pos = 0;
        }

        showItems(pos);
        dgvSupplier.setRowSelectionInterval(pos, pos);
    }//GEN-LAST:event_btn_previous1ActionPerformed

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
        pos = 0;
        showItems(pos);
        dgvSupplier.setRowSelectionInterval(pos, pos);
    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
       pos = supplierList().size() - 1;
        showItems(pos);
        dgvSupplier.setRowSelectionInterval(pos, pos);
    }//GEN-LAST:event_btn_lastActionPerformed

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
                new Supplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_previous1;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btngenerate;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> cbbasedon;
    private javax.swing.JLabel common;
    private javax.swing.JTable dgvSupplier;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblbasedon;
    private javax.swing.JLabel lblcustomername;
    private javax.swing.JLabel lblcustomername1;
    private javax.swing.JLabel lblsales;
    private javax.swing.JLabel lblsalesid;
    private javax.swing.JLabel lblstockid;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtcompanyname;
    private javax.swing.JTextField txtcontactno;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtsupplierid;
    // End of variables declaration//GEN-END:variables
}
