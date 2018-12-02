/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Class.AdminData;
import Class.ConnectionProvider;
import Class.Model;
import Class.User;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author PraveenKumar
 */
public class UserView extends javax.swing.JFrame {

    /**
     * Creates new form UserView
     */
    private String userEmail;

    public UserView() {
        initComponents();
        loadUser();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    }

    public ArrayList<User> UserList() {
        ArrayList<User> ilist = new ArrayList<>();

        try {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM user WHERE position = '" + String.valueOf(userType.getSelectedItem()) + "'";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            User i;

            while (rs.next()) {
                i = new User(rs.getString("email"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("contactNo"), rs.getString("address"), rs.getString("profilepic"));
                ilist.add(i);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ilist;
    }

    public void loadUser() {
        btndelete.setEnabled(false);
        ArrayList<User> list = UserList();
        String[] colname = {"Picture", "Email", "Firstname", "Lastname", "ContactNo", "Address"};
        Object[][] rows = new Object[list.size()][6];

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getImgPath() != null) {
                ImageIcon myImg = new ImageIcon(list.get(i).getImgPath());
                Image img = myImg.getImage().getScaledInstance(170, 140, Image.SCALE_SMOOTH);
                ImageIcon myPicture = new ImageIcon(img);
                rows[i][0] = myPicture;
            } else {
                rows[i][0] = null;
            }

            rows[i][1] = list.get(i).getEmail();
            rows[i][2] = list.get(i).getFirstname();
            rows[i][3] = list.get(i).getLastname();
            rows[i][4] = list.get(i).getContactNo();
            rows[i][5] = list.get(i).getAddress();

        }

        Model m = new Model(rows, colname);
        UserTable.setModel(m);
        UserTable.setRowHeight(150);
        UserTable.getColumnModel().getColumn(0).setPreferredWidth(150);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        UserTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        dashboardLabel = new javax.swing.JLabel();
        GoBack = new javax.swing.JLabel();
        userType = new javax.swing.JComboBox<>();
        btndelete = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        searchbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        userSearchType = new javax.swing.JComboBox<>();
        clear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NA | View User");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        UserTable.setBackground(new java.awt.Color(245, 245, 245));
        UserTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        UserTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Picture", "Email", "First Name", "Last Name", "Contact No", "Address"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Byte.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        UserTable.setToolTipText("");
        UserTable.setGridColor(new java.awt.Color(0, 0, 0));
        UserTable.setPreferredSize(new java.awt.Dimension(1280, 920));
        UserTable.setRowHeight(18);
        UserTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(UserTable);

        jPanel1.setBackground(new java.awt.Color(0, 31, 63));

        dashboardLabel.setFont(new java.awt.Font("Calibri", 0, 50)); // NOI18N
        dashboardLabel.setForeground(new java.awt.Color(245, 245, 245));
        dashboardLabel.setText("User Details");

        GoBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/goBack.png"))); // NOI18N
        GoBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GoBackMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(GoBack))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(376, 376, 376)
                        .addComponent(dashboardLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GoBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashboardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        userType.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        userType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrator", "Staff", "Technician" }));
        userType.setToolTipText("");
        userType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                userTypeItemStateChanged(evt);
            }
        });

        btndelete.setBackground(new java.awt.Color(220, 20, 60));
        btndelete.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btndelete.setForeground(new java.awt.Color(245, 244, 245));
        btndelete.setText("Delete User");
        btndelete.setEnabled(false);
        btndelete.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        txtsearch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        searchbtn.setBackground(new java.awt.Color(0, 31, 63));
        searchbtn.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        searchbtn.setForeground(new java.awt.Color(245, 245, 245));
        searchbtn.setText("Search");
        searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtnActionPerformed(evt);
            }
        });

        jLabel1.setText("User Type");

        userSearchType.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        userSearchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Search Type --", "Email", "Firstname", "Lastname" }));
        userSearchType.setToolTipText("");

        clear.setBackground(new java.awt.Color(0, 31, 63));
        clear.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        clear.setForeground(new java.awt.Color(245, 245, 245));
        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userType, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(clear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(userSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(searchbtn))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchbtn)
                        .addComponent(userType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(userSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(clear))
                    .addComponent(btndelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GoBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GoBackMouseClicked
        Settings userDBoard = new Settings();
        userDBoard.setVisible(true);
        userDBoard.pack();
        userDBoard.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        userDBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_GoBackMouseClicked


    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed

        try {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "DELETE FROM user WHERE email='" + userEmail + "'";
            PreparedStatement ps = con.prepareStatement(query);

            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "User Deleted Successfully");
                loadUser();
            } else {
                JOptionPane.showMessageDialog(null, "Something Wrong");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void UserTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserTableMouseClicked
        btndelete.setEnabled(true);
        int i = UserTable.getSelectedRow();
        TableModel m = UserTable.getModel();
        userEmail = m.getValueAt(i, 1).toString();

        if (userEmail.equalsIgnoreCase(AdminData.AdminEmail) || SignIn.sessionEmail.isEmpty() == true || !SignIn.sessionEmail.equalsIgnoreCase(AdminData.AdminEmail)) {
            btndelete.setEnabled(false);
        }
    }//GEN-LAST:event_UserTableMouseClicked

    private void userTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_userTypeItemStateChanged
        loadUser();
    }//GEN-LAST:event_userTypeItemStateChanged

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed
        search();
    }//GEN-LAST:event_searchbtnActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        loadUser();
    }//GEN-LAST:event_clearActionPerformed
    public ArrayList<User> searchQuery() {
        ArrayList<User> listu = new ArrayList<>();
        String query;
        String value = String.valueOf(userSearchType.getSelectedItem());
        String search = txtsearch.getText().toString();

        if (value.equalsIgnoreCase("-- Search Type --")) {
            JOptionPane.showMessageDialog(null, "Please Select Search Type");
        }

        try {
            Connection con = ConnectionProvider.getInstance().getDBConnection();

            if (value.equalsIgnoreCase("firstname")) {
                query = "SELECT * FROM user WHERE firstname LIKE '" + search + "%' AND position = '" + String.valueOf(userType.getSelectedItem()) + "'";
            } else if (value.equalsIgnoreCase("lastname")) {
                query = "SELECT * FROM user WHERE lastname LIKE '" + search + "%' AND position = '" + String.valueOf(userType.getSelectedItem()) + "'";
            } else if (value.equalsIgnoreCase("email")) {
                query = "SELECT * FROM user WHERE email LIKE '" + search + "%' AND position = '" + String.valueOf(userType.getSelectedItem()) + "'";
            } else {
                query = "SELECT * FROM user WHERE position = '" + String.valueOf(userType.getSelectedItem()) + "'";
            }

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            User i;

            while (rs.next()) {
                i = new User(rs.getString("email"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("contactNo"), rs.getString("address"), rs.getString("profilepic"));
                listu.add(i);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listu;
    }

    public void search() {
        ArrayList<User> listx = searchQuery();
        String[] colname = {"Picture", "Email", "Firstname", "Lastname", "ContactNo", "Address"};
        Object[][] rows = new Object[listx.size()][6];

        for (int j = 0; j < listx.size(); j++) {

            if (listx.get(j).getImgPath() != null) {
                ImageIcon myImg = new ImageIcon(listx.get(j).getImgPath());
                Image img = myImg.getImage().getScaledInstance(170, 140, Image.SCALE_SMOOTH);
                ImageIcon myPicture = new ImageIcon(img);
                rows[j][0] = myPicture;
            } else {
                rows[j][0] = null;
            }

            rows[j][1] = listx.get(j).getEmail();
            rows[j][2] = listx.get(j).getFirstname();
            rows[j][3] = listx.get(j).getLastname();
            rows[j][4] = listx.get(j).getContactNo();
            rows[j][5] = listx.get(j).getAddress();

        }

        Model m = new Model(rows, colname);
        UserTable.setModel(m);
        UserTable.setRowHeight(150);
        UserTable.getColumnModel().getColumn(0).setPreferredWidth(150);
    }

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
            java.util.logging.Logger.getLogger(UserView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserView.class
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel GoBack;
    private javax.swing.JTable UserTable;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton clear;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton searchbtn;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JComboBox<String> userSearchType;
    private javax.swing.JComboBox<String> userType;
    // End of variables declaration//GEN-END:variables
}
