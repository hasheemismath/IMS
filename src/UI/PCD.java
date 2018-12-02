/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Class.ConnectionProvider;
import Class.Vlis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vishwa
 */
public class PCD extends javax.swing.JFrame {

    /**
     * Creates new form PCD
     */
    public PCD() {
        initComponents();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        Expansetable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dashboardLabel = new javax.swing.JLabel();
        BtnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Extable = new javax.swing.JTable();
        IDLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();
        nameLabel2 = new javax.swing.JLabel();
        Amount = new javax.swing.JLabel();
        exName = new javax.swing.JTextField();
        dateText = new javax.swing.JTextField();
        amountText = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        BtnUpdate = new javax.swing.JButton();
        BtnCreate = new javax.swing.JButton();
        BtnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1528, 800));
        setResizable(false);

        dashboardLabel.setFont(new java.awt.Font("Calibri", 0, 48)); // NOI18N
        dashboardLabel.setForeground(new java.awt.Color(0, 31, 63));
        dashboardLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashboardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/sales.png"))); // NOI18N
        dashboardLabel.setText("Expense Management");

        BtnBack.setBackground(new java.awt.Color(153, 153, 153));
        BtnBack.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        BtnBack.setText("BACK");
        BtnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBackActionPerformed(evt);
            }
        });

        Extable.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Extable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Expense ID", "Expense name", "Expense type", "Date", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Extable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExtableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Extable);

        IDLabel.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        IDLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        nameLabel.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        nameLabel.setText("Expense name");
        nameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        typeLabel.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        typeLabel.setText("Expense type");
        typeLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        nameLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        nameLabel2.setText("Date");
        nameLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        Amount.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Amount.setText("Amount");
        Amount.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        exName.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        dateText.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        amountText.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Distribution", "Administration", "Other" }));

        BtnUpdate.setBackground(new java.awt.Color(153, 153, 153));
        BtnUpdate.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        BtnUpdate.setText("UPDATE SELECTED");
        BtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdateActionPerformed(evt);
            }
        });

        BtnCreate.setBackground(new java.awt.Color(153, 153, 153));
        BtnCreate.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        BtnCreate.setText("CREATE NEW");
        BtnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCreateActionPerformed(evt);
            }
        });

        BtnDelete.setBackground(new java.awt.Color(153, 153, 153));
        BtnDelete.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        BtnDelete.setText("DELETE SELECTED");
        BtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dashboardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(IDLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Amount)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(amountText, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(typeLabel)
                                        .addComponent(nameLabel))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(exName)
                                        .addComponent(jComboBox1, 0, 200, Short.MAX_VALUE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(nameLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(95, 95, 95)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BtnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 292, Short.MAX_VALUE)
                        .addComponent(BtnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dashboardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(IDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(exName)
                            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(typeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(amountText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Amount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(BtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBackActionPerformed
        new ExDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnBackActionPerformed

    private void Expansetable(){
        DefaultTableModel model = (DefaultTableModel) Extable.getModel();
        //String date = java.time.LocalDate.now().toString();
        try {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String querry = "select * from petty";
            Statement myStat = con.createStatement();
            ResultSet rSet = myStat.executeQuery(querry);
            
            while(rSet.next()){
                String id = rSet.getString("expenseid");
                String name = rSet.getString("expensename");
                String type = rSet.getString("expensetype");
                String date = rSet.getString("date");
                String amount = rSet.getString("amount");
                
                model.addRow(new Object[]{id, name, type, date, amount});
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    private void BtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdateActionPerformed
        String id = Extable.getModel().getValueAt(Extable.getSelectedRow(), 0).toString();
        String name = exName.getText();
        String type = jComboBox1.getSelectedItem().toString();
        String date = dateText.getText();
        String amount = amountText.getText();

        try {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "update petty set expensename = ?, expensetype = ?, date = ?, amount = ? where expenseid = ?";
            PreparedStatement myStat = con.prepareStatement(query);
            myStat.setString(1, name);
            myStat.setString(2, type);
            myStat.setString(3, date);
            myStat.setString(4, amount);
            myStat.setString(5, id);

            int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this entry?");
            if(a == JOptionPane.YES_OPTION){
                Vlis vli = new Vlis(name, date, amount);
                if(vli.ExUDval()){
                    myStat.executeUpdate();
                    DefaultTableModel model = (DefaultTableModel) Extable.getModel();
                    model.setRowCount(0);
                    JOptionPane.showMessageDialog(null, "Successfully Updated!");
                    Expansetable();
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_BtnUpdateActionPerformed

    private void BtnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCreateActionPerformed
        new NewExpanse().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnCreateActionPerformed

    private void BtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDeleteActionPerformed
        String id = Extable.getModel().getValueAt(Extable.getSelectedRow(), 0).toString();

        try {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "delete from petty where expenseid = ?";
            PreparedStatement myStat = con.prepareStatement(query);
            myStat.setString(1, id);

            int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this entry?");
            if(a == JOptionPane.YES_OPTION){
                myStat.executeUpdate();
                DefaultTableModel model = (DefaultTableModel) Extable.getModel();
                model.setRowCount(0);
                JOptionPane.showMessageDialog(null, "Successfully Deleted!");
                Expansetable();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_BtnDeleteActionPerformed

    private void ExtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExtableMouseClicked
        IDLabel.setText("Expanse ID: " + Extable.getModel().getValueAt(Extable.getSelectedRow(), 0).toString());
        exName.setText(Extable.getModel().getValueAt(Extable.getSelectedRow(), 1).toString());
        jComboBox1.setSelectedItem(Extable.getModel().getValueAt(Extable.getSelectedRow(), 2).toString());
        dateText.setText(Extable.getModel().getValueAt(Extable.getSelectedRow(), 3).toString());
        amountText.setText(Extable.getModel().getValueAt(Extable.getSelectedRow(), 4).toString());
    }//GEN-LAST:event_ExtableMouseClicked

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
            java.util.logging.Logger.getLogger(PCD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PCD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PCD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PCD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PCD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Amount;
    private javax.swing.JButton BtnBack;
    private javax.swing.JButton BtnCreate;
    private javax.swing.JButton BtnDelete;
    private javax.swing.JButton BtnUpdate;
    private javax.swing.JTable Extable;
    private javax.swing.JLabel IDLabel;
    private javax.swing.JTextField amountText;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JTextField dateText;
    private javax.swing.JTextField exName;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameLabel2;
    private javax.swing.JLabel typeLabel;
    // End of variables declaration//GEN-END:variables
}
