/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Class.ConnectionProvider;
import Class.Customer;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.regex.*;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author PraveenKumar
 */
public class CustomerMgt extends javax.swing.JFrame {

    /**
     * Creates new form UserMgt
     */
    boolean address_result;
    boolean fname_result;
    boolean lname_result;
    boolean email_result;
    boolean contact_result;
    boolean empty_result;

    int id, cid;
    int pos = 0;
    Object[] col = new Object[6];

    public CustomerMgt() {
        initComponents();
        loadCustomer();
        genCusID();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    }

    public ArrayList<Customer> CustomerList() {
        ArrayList<Customer> ilist = new ArrayList<>();

        try {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM customer";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            Customer i;

            while (rs.next()) {
                i = new Customer(rs.getInt("customerid"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("contactno"), rs.getString("address"), rs.getString("email"));
                ilist.add(i);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ilist;
    }

    public void loadCustomer() {
        ArrayList<Customer> list = CustomerList();
        DefaultTableModel dtm = (DefaultTableModel) CustomerTable.getModel();

        for (int i = 0; i < list.size(); i++) {
            col[0] = list.get(i).getCustomerid();
            col[1] = list.get(i).getFirstname();
            col[2] = list.get(i).getLastname();
            col[3] = list.get(i).getEmail();
            col[4] = list.get(i).getContactNo();
            col[5] = list.get(i).getAddress();

            dtm.addRow(col);
        }
    }

    public int genCusID() {
        try {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            Statement st = con.createStatement();
            String query = "SELECT CustomerID FROM Customer";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                id = rs.getInt("CustomerID");
            }

            cid = id + 1;
            CustomerID.setText(String.valueOf(cid));

        } catch (Exception e) {
            System.out.println(e);
        }

        return cid;
    }

    public void search() {
        DefaultTableModel dtm = (DefaultTableModel) CustomerTable.getModel();
        dtm.setRowCount(0);
        String query;

        String search = txtsearch.getText().toString();

        String value = String.valueOf(customerSearchType.getSelectedItem());

        if (value.equalsIgnoreCase("-- Please Choose --")) {
            JOptionPane.showMessageDialog(null, "Please Select Search Type");
        }

        ArrayList<Customer> list1 = new ArrayList<>();
        Customer i;

        try {
            Connection con = ConnectionProvider.getInstance().getDBConnection();

            if (value.equalsIgnoreCase("firstname")) {
                query = "SELECT * FROM customer WHERE firstname LIKE '" + search + "%'";
            } else if (value.equalsIgnoreCase("lastname")) {
                query = "SELECT * FROM customer WHERE lastname LIKE '" + search + "%'";
            } else if (value.equalsIgnoreCase("email")) {
                query = "SELECT * FROM customer WHERE email LIKE '" + search + "%'";
            } else {
                query = "SELECT * FROM customer";
            }

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                i = new Customer(rs.getInt("customerid"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("contactno"), rs.getString("address"), rs.getString("email"));
                list1.add(i);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // ArrayList<Customer> list = list1;
        for (int j = 0; j < list1.size(); j++) {
            col[0] = list1.get(j).getCustomerid();
            col[1] = list1.get(j).getFirstname();
            col[2] = list1.get(j).getLastname();
            col[3] = list1.get(j).getEmail();
            col[4] = list1.get(j).getContactNo();
            col[5] = list1.get(j).getAddress();
            dtm.addRow(col);
        }
    }

    public void showItems(int index) {
        btnadd.setEnabled(false);
        btnupdate.setEnabled(true);
        btndelete.setEnabled(true);
        CustomerID.setEditable(false);

        CustomerID.setText(Integer.toString(CustomerList().get(index).getCustomerid()));
        FirstName.setText(CustomerList().get(index).getFirstname());
        Email.setText(CustomerList().get(index).getEmail());
        Address.setText(CustomerList().get(index).getAddress());
        ContactNo.setText(CustomerList().get(index).getContactNo());
        LastName.setText(CustomerList().get(index).getLastname());

    }

    public void reloadCustomer() {
        clear();
        btnadd.setEnabled(true);
        btnupdate.setEnabled(false);
        btndelete.setEnabled(false);
    }

    private void clear() {
        DefaultTableModel dtm = (DefaultTableModel) CustomerTable.getModel();
        dtm.setRowCount(0);
        loadCustomer();
        genCusID();
        customerSearchType.setSelectedItem("-- Please Choose --");
        FirstName.setText("");
        Email.setText("");
        Address.setText("");
        ContactNo.setText("");
        LastName.setText("");
        txtsearch.setText("");
        Commonlbl.setText("");

    }

    private void quit() {
        int i = JOptionPane.showConfirmDialog(null, "Are you sure to QUIT?", "Exit", JOptionPane.YES_NO_OPTION);

        if (JOptionPane.YES_OPTION == i) {
            this.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CustomerTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        CustomerID = new javax.swing.JTextField();
        FirstName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        LastName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ContactNo = new javax.swing.JTextField();
        Address = new javax.swing.JTextField();
        Email = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btn_previous = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        btn_first = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btnadd = new javax.swing.JButton();
        btnarrear = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();
        Commonlbl = new javax.swing.JLabel();
        searchbtn = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        customerSearchType = new javax.swing.JComboBox<>();
        lblbasedon = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblsales1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuStripNew = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuStripHome = new javax.swing.JMenuItem();
        menuStripQuit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NA | Customer");

        jPanel2.setBackground(new java.awt.Color(231, 231, 231));
        jPanel2.setPreferredSize(new java.awt.Dimension(1315, 615));

        CustomerTable.setBackground(new java.awt.Color(245, 245, 245));
        CustomerTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        CustomerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "First Name", "Last Name", "Email", "Contact No", "Address"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        CustomerTable.setToolTipText("");
        CustomerTable.setGridColor(new java.awt.Color(0, 0, 0));
        CustomerTable.setRowHeight(18);
        CustomerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CustomerTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(CustomerTable);

        jPanel1.setBackground(new java.awt.Color(231, 231, 231));

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Customer ID");

        CustomerID.setEditable(false);
        CustomerID.setBackground(new java.awt.Color(217, 217, 217));
        CustomerID.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        CustomerID.setForeground(new java.awt.Color(51, 51, 51));
        CustomerID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CustomerID.setAlignmentX(1.0F);
        CustomerID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        CustomerID.setMaximumSize(new java.awt.Dimension(6, 12));
        CustomerID.setMinimumSize(new java.awt.Dimension(6, 23));
        CustomerID.setName(""); // NOI18N
        CustomerID.setPreferredSize(new java.awt.Dimension(3, 25));

        FirstName.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        FirstName.setForeground(new java.awt.Color(51, 51, 51));
        FirstName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FirstName.setAlignmentX(1.0F);
        FirstName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        FirstName.setMaximumSize(new java.awt.Dimension(6, 12));
        FirstName.setMinimumSize(new java.awt.Dimension(6, 12));
        FirstName.setName(""); // NOI18N
        FirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FirstNameKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("First Name");

        LastName.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        LastName.setForeground(new java.awt.Color(51, 51, 51));
        LastName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        LastName.setAlignmentX(1.0F);
        LastName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        LastName.setMaximumSize(new java.awt.Dimension(6, 12));
        LastName.setMinimumSize(new java.awt.Dimension(6, 12));
        LastName.setName(""); // NOI18N
        LastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                LastNameKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("last Name");

        ContactNo.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        ContactNo.setForeground(new java.awt.Color(51, 51, 51));
        ContactNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ContactNo.setAlignmentX(1.0F);
        ContactNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        ContactNo.setMaximumSize(new java.awt.Dimension(6, 12));
        ContactNo.setMinimumSize(new java.awt.Dimension(6, 12));
        ContactNo.setName(""); // NOI18N
        ContactNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ContactNoKeyReleased(evt);
            }
        });

        Address.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        Address.setForeground(new java.awt.Color(51, 51, 51));
        Address.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Address.setAlignmentX(1.0F);
        Address.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        Address.setMaximumSize(new java.awt.Dimension(6, 12));
        Address.setMinimumSize(new java.awt.Dimension(6, 12));
        Address.setName(""); // NOI18N
        Address.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AddressKeyReleased(evt);
            }
        });

        Email.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        Email.setForeground(new java.awt.Color(51, 51, 51));
        Email.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Email.setAlignmentX(1.0F);
        Email.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        Email.setMaximumSize(new java.awt.Dimension(6, 12));
        Email.setMinimumSize(new java.awt.Dimension(6, 12));
        Email.setName(""); // NOI18N
        Email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EmailKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Contact #");

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Address");

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Email");

        jPanel8.setBackground(new java.awt.Color(231, 231, 231));

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
        btn_first.setText("      First");
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

        btnadd.setBackground(new java.awt.Color(0, 158, 113));
        btnadd.setFont(new java.awt.Font("Calibri", 0, 30)); // NOI18N
        btnadd.setForeground(new java.awt.Color(245, 245, 245));
        btnadd.setText("Add");
        btnadd.setBorderPainted(false);
        btnadd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnarrear.setBackground(new java.awt.Color(0, 51, 51));
        btnarrear.setFont(new java.awt.Font("Calibri", 0, 30)); // NOI18N
        btnarrear.setForeground(new java.awt.Color(245, 244, 245));
        btnarrear.setText("Arrears");
        btnarrear.setActionCommand("");
        btnarrear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnarrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnarrearActionPerformed(evt);
            }
        });

        btndelete.setBackground(new java.awt.Color(220, 20, 60));
        btndelete.setFont(new java.awt.Font("Calibri", 0, 30)); // NOI18N
        btndelete.setForeground(new java.awt.Color(245, 244, 245));
        btndelete.setText("Remove");
        btndelete.setEnabled(false);
        btndelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnupdate.setBackground(new java.awt.Color(238, 118, 60));
        btnupdate.setFont(new java.awt.Font("Calibri", 0, 30)); // NOI18N
        btnupdate.setForeground(new java.awt.Color(245, 244, 245));
        btnupdate.setText("Update");
        btnupdate.setEnabled(false);
        btnupdate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btnclear.setBackground(new java.awt.Color(0, 31, 63));
        btnclear.setFont(new java.awt.Font("Calibri", 0, 30)); // NOI18N
        btnclear.setForeground(new java.awt.Color(245, 245, 245));
        btnclear.setText("Clear");
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_first, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_previous, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnarrear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnadd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_first, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnarrear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_previous, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LastName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FirstName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(CustomerID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ContactNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Address, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Email, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(LastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(ContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        Commonlbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Commonlbl.setForeground(new java.awt.Color(204, 0, 0));
        Commonlbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        searchbtn.setBackground(new java.awt.Color(0, 31, 63));
        searchbtn.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        searchbtn.setForeground(new java.awt.Color(245, 245, 245));
        searchbtn.setText("Search");
        searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtnActionPerformed(evt);
            }
        });

        txtsearch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        customerSearchType.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        customerSearchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Please Choose --", "Firstname", "Lastname", "Email" }));
        customerSearchType.setToolTipText("");

        lblbasedon.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lblbasedon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblbasedon.setText("Based On");

        jPanel5.setBackground(new java.awt.Color(0, 31, 63));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 31, 63), 1, true));

        lblsales1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lblsales1.setForeground(new java.awt.Color(245, 245, 245));
        lblsales1.setText("Customer");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addComponent(lblsales1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblsales1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblbasedon, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(customerSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchbtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(Commonlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 55, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(searchbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtsearch, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(customerSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblbasedon)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Commonlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        menuStripNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuStripNew.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        menuStripNew.setText("New");
        menuStripNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStripNewActionPerformed(evt);
            }
        });
        jMenu1.add(menuStripNew);
        jMenu1.add(jSeparator1);

        menuStripHome.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        menuStripHome.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
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

        menuStripQuit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        menuStripQuit.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        menuStripQuit.setText("Quit");
        menuStripQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStripQuitActionPerformed(evt);
            }
        });
        jMenu1.add(menuStripQuit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1321, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed
        search();
    }//GEN-LAST:event_searchbtnActionPerformed

    private void CustomerTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerTableMouseClicked
        btnadd.setEnabled(false);
        btnupdate.setEnabled(true);
        btndelete.setEnabled(true);
        CustomerID.setEditable(false);

        int i = CustomerTable.getSelectedRow();
        showItems(i);
    }//GEN-LAST:event_CustomerTableMouseClicked

    private void LastNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LastNameKeyReleased
        String lname = LastName.getText();
        String PATTERN = "^[a-zA-z]+$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(lname);

        lname_result = !match.matches();

        if (lname_result) {
            Commonlbl.setForeground(Color.red);
            Commonlbl.setText("Last Name is not Correct");
        } else {
            Commonlbl.setText(null);
        }
    }//GEN-LAST:event_LastNameKeyReleased

    private void FirstNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FirstNameKeyReleased
        String fname = FirstName.getText();
        String PATTERN = "^[a-zA-z]+$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(fname);

        fname_result = !match.matches();

        if (fname_result) {
            Commonlbl.setForeground(Color.red);
            Commonlbl.setText("First Name is not Correct");
        } else {
            Commonlbl.setText(null);
        }
    }//GEN-LAST:event_FirstNameKeyReleased

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        clear();
        btnadd.setEnabled(true);
        btnupdate.setEnabled(false);
        btndelete.setEnabled(false);
    }//GEN-LAST:event_btnclearActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        String customerID = CustomerID.getText();
        try {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            //  String query1 = "DELETE FROM paymentc WHERE customerid='" + customerID + "'";
            String query2 = "DELETE FROM customer WHERE customerid='" + customerID + "'";
            //PreparedStatement ps1 = con.prepareStatement(query1);

            PreparedStatement ps2 = con.prepareStatement(query2);
            // if (ps1.executeUpdate() != 0) {
            int i = JOptionPane.showConfirmDialog(null, "Are you sure to Delete?", "Delete Customer", JOptionPane.YES_NO_OPTION);

            if (JOptionPane.YES_OPTION == i) {
                if (ps2.executeUpdate() != 0) {
                    JOptionPane.showMessageDialog(null, "Customer Deleted Successfully");
                    reloadCustomer();
                } else {
                    JOptionPane.showMessageDialog(null, "Error : DataBase Connectivity Problem");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        String customerID = CustomerID.getText();
        String fname = FirstName.getText();
        String lname = LastName.getText();
        String email = Email.getText();
        String contactno = ContactNo.getText();
        String address = Address.getText();

        if (fname_result) {
            JOptionPane.showMessageDialog(null, "First Name must have Alphabet Characters Only");
        }

        if (lname_result) {
            JOptionPane.showMessageDialog(null, "Last Name must have Alphabet Characters Only");
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

        if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || address.isEmpty() || contactno.isEmpty()) {
            empty_result = true;
            JOptionPane.showMessageDialog(null, "Empty Input Field");
        } else {
            empty_result = false;
        }

        if (address_result == false && lname_result == false && fname_result == false && contact_result == false && email_result == false && empty_result == false) {
            try {

                Connection con = ConnectionProvider.getInstance().getDBConnection();
                String query1 = "INSERT INTO customer(customerid,firstname,lastname,email,address,contactno) VALUES(?,?,?,?,?,?)";
                PreparedStatement ps1 = con.prepareStatement(query1);
                ps1.setString(1, customerID);
                ps1.setString(2, fname);
                ps1.setString(3, lname);
                ps1.setString(4, email);
                ps1.setString(5, address);
                ps1.setString(6, contactno);

                if (ps1.executeUpdate() != 0) {
                    JOptionPane.showMessageDialog(null, "Customer Added Successfully");
                    reloadCustomer();
                } else {
                    JOptionPane.showMessageDialog(null, "Error : DataBase Connectivity Problem");
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        String customerID = CustomerID.getText();
        String fname = FirstName.getText();
        String lname = LastName.getText();
        String email = Email.getText();
        String contactno = ContactNo.getText();
        String address = Address.getText();

        if (fname_result) {
            JOptionPane.showMessageDialog(null, "First Name must have Alphabet Characters Only");
        }

        if (lname_result) {
            JOptionPane.showMessageDialog(null, "Last Name must have Alphabet Characters Only");
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

        if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || address.isEmpty() || contactno.isEmpty()) {
            empty_result = true;
            JOptionPane.showMessageDialog(null, "Empty Input Field");
        } else {
            empty_result = false;
        }

        if (address_result == false && lname_result == false && fname_result == false && contact_result == false && email_result == false) {
            try {

                Connection con = ConnectionProvider.getInstance().getDBConnection();
                String query = "UPDATE customer SET firstname=?,lastname=?,email=?,contactno=?,address=? WHERE customerid='" + customerID + "'";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, fname);
                ps.setString(2, lname);
                ps.setString(3, email);
                ps.setString(4, contactno);
                ps.setString(5, address);

                int i = JOptionPane.showConfirmDialog(null, "Are you sure to Update?", "Update Customer", JOptionPane.YES_NO_OPTION);

                if (JOptionPane.YES_OPTION == i) {
                    if (ps.executeUpdate() != 0) {
                        JOptionPane.showMessageDialog(null, "Customer Updated Successfully");
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

    private void btnarrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnarrearActionPerformed
        CustomerCheckArrears cca = new CustomerCheckArrears();
        cca.setVisible(true);
        cca.pack();
    }//GEN-LAST:event_btnarrearActionPerformed

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
        pos = 0;
        showItems(pos);
        CustomerTable.setRowSelectionInterval(pos, pos);

    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_previousActionPerformed
        pos--;

        if (pos < 0) {
            pos = 0;
        }

        showItems(pos);
        CustomerTable.setRowSelectionInterval(pos, pos);

    }//GEN-LAST:event_btn_previousActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        pos++;

        if (pos >= CustomerList().size()) {
            pos = CustomerList().size() - 1;
        }
        CustomerTable.setRowSelectionInterval(pos, pos);
        showItems(pos);
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        pos = CustomerList().size() - 1;
        showItems(pos);
        CustomerTable.setRowSelectionInterval(pos, pos);

    }//GEN-LAST:event_btn_lastActionPerformed

    private void ContactNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ContactNoKeyReleased
        String contactno = ContactNo.getText();
        String PATTERN = "^[0-9]{10}+$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(contactno);

        contact_result = !match.matches();

        if (contact_result) {
            Commonlbl.setForeground(Color.red);
            Commonlbl.setText("Contact Number is Invalid (Maximum Length = 10)");
        } else {
            Commonlbl.setText(null);
        }
    }//GEN-LAST:event_ContactNoKeyReleased

    private void EmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EmailKeyReleased
        String position = Email.getText();
        String PATTERN = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(position);

        email_result = !match.matches();

        if (email_result) {
            Commonlbl.setForeground(Color.red);
            Commonlbl.setText("Email Field is not Correct");
        } else {
            Commonlbl.setText(null);
        }
    }//GEN-LAST:event_EmailKeyReleased

    private void AddressKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddressKeyReleased
        String address = Address.getText();
        String PATTERN = "^[a-zA-Z0-9/\\s,.'-]*$";
        Pattern pat = Pattern.compile(PATTERN);
        Matcher match = pat.matcher(address);

        address_result = !match.matches();

        if (address_result) {
            Commonlbl.setForeground(Color.red);
            Commonlbl.setText("Address Field is not Correct");
        } else {
            Commonlbl.setText(null);
        }
    }//GEN-LAST:event_AddressKeyReleased

    private void menuStripNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStripNewActionPerformed
        clear();
    }//GEN-LAST:event_menuStripNewActionPerformed

    private void menuStripHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuStripHomeMouseClicked
        new More().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuStripHomeMouseClicked

    private void menuStripHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStripHomeActionPerformed
        new More().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuStripHomeActionPerformed

    private void menuStripQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStripQuitActionPerformed
        quit();
    }//GEN-LAST:event_menuStripQuitActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerMgt.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerMgt.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerMgt.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerMgt.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new CustomerMgt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Address;
    private javax.swing.JLabel Commonlbl;
    private javax.swing.JTextField ContactNo;
    private javax.swing.JTextField CustomerID;
    private javax.swing.JTable CustomerTable;
    private javax.swing.JTextField Email;
    private javax.swing.JTextField FirstName;
    private javax.swing.JTextField LastName;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_previous;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnarrear;
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> customerSearchType;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblbasedon;
    private javax.swing.JLabel lblsales1;
    private javax.swing.JMenuItem menuStripHome;
    private javax.swing.JMenuItem menuStripNew;
    private javax.swing.JMenuItem menuStripQuit;
    private javax.swing.JButton searchbtn;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables

}
