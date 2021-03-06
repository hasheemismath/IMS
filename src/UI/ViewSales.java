/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Class.ConnectionProvider;
import Class.SalesClass;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Roshan Nizar
 */
public class ViewSales extends javax.swing.JFrame {

    /**
     * Creates new form Payment
     */
    public ViewSales() {
        initComponents();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        loadSales();
    }
    
    private ArrayList<SalesClass> salesList()
    {
        ArrayList<SalesClass> slist = new ArrayList<>();
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM sales";
        
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
    
    private void loadSales()
    {
        ArrayList <SalesClass> list = salesList();
        DefaultTableModel dtm = (DefaultTableModel)dgvsales.getModel();
        Object []row = new Object[8];
        
        for(int i=0;i<list.size();i++)
        {
            row[0] = list.get(i).getSalesid();
            row[1] = list.get(i).getSalesPerson();
            row[2] = list.get(i).getProductID();
            row[3] = list.get(i).getCustomerid();
            row[4] = list.get(i).getDate();
            row[5] = list.get(i).getQuantity();
            row[6] = list.get(i).getPrice();
            row[7] = list.get(i).getPrice() * list.get(i).getQuantity();
            dtm.addRow(row);
        }
    }
    
    private void loadSalesSearch(String type)
    {
        ArrayList <SalesClass> list = salesListSearch(type);
        DefaultTableModel dtm = (DefaultTableModel)dgvsales.getModel();
        Object []row = new Object[8];
        
        for(int i=0;i<list.size();i++)
        {
            row[0] = list.get(i).getSalesid();
            row[1] = list.get(i).getSalesPerson();
            row[2] = list.get(i).getProductID();
            row[3] = list.get(i).getCustomerid();
            row[4] = list.get(i).getDate();
            row[5] = list.get(i).getQuantity();
            row[6] = list.get(i).getPrice();
            row[7] = list.get(i).getPrice() * list.get(i).getQuantity();
            dtm.addRow(row);
        }
    }

    private void quit()
    {
        int i = JOptionPane.showConfirmDialog(null, "Are you sure to QUIT?", "Exit", JOptionPane.INFORMATION_MESSAGE);
        
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
        lblbasedon = new javax.swing.JLabel();
        cbbasedon = new javax.swing.JComboBox<>();
        txtsearch = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblsales = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dgvsales = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NA | Sales View");
        setBackground(new java.awt.Color(0, 153, 153));

        lblbasedon.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lblbasedon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblbasedon.setText("Based On");

        cbbasedon.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        cbbasedon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Please Choose --", "Sales ID", "Sales Person", "Stock ID", "Customer ID", "Quantity" }));

        txtsearch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });

        btnsearch.setBackground(new java.awt.Color(0, 31, 63));
        btnsearch.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnsearch.setForeground(new java.awt.Color(245, 245, 245));
        btnsearch.setText("Search");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(0, 31, 63));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 31, 63), 1, true));

        lblsales.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lblsales.setForeground(new java.awt.Color(245, 245, 245));
        lblsales.setText("Sales View");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addComponent(lblsales)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblsales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dgvsales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sales ID", "Sales Person", "Stock ID", "Customer ID", "Date", "Quantity", "Price", "Total Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(dgvsales);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 550, Short.MAX_VALUE)
                        .addComponent(lblbasedon, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbasedon, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsearch)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbasedon)
                        .addComponent(lblbasedon))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtsearch)
                        .addComponent(btnsearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jMenuItem1.setText("New");
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator1);

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
        jMenuItem3.setText("Dashboard");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new Dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new SalesDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        quit();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        if(txtsearch.getText().equals(""))
        {
            DefaultTableModel model = (DefaultTableModel)dgvsales.getModel();
            model.setRowCount(0);
            loadSales();
        }
        else if(cbbasedon.getSelectedItem().equals("Sales ID"))
        {
            String query = "SELECT * FROM sales WHERE salesid='"+txtsearch.getText()+"'";
            DefaultTableModel model = (DefaultTableModel)dgvsales.getModel();
            model.setRowCount(0);
            loadSalesSearch(query);
        }
        else if(cbbasedon.getSelectedItem().equals("Product ID"))
        {
            String query = "SELECT * FROM sales WHERE productid='"+txtsearch.getText()+"'";
            DefaultTableModel model = (DefaultTableModel)dgvsales.getModel();
            model.setRowCount(0);
            loadSalesSearch(query);
        }
        else if(cbbasedon.getSelectedItem().equals("Sales Person"))
        {
            String query = "SELECT * FROM sales WHERE salesperson like '%"+txtsearch.getText()+"%'";
            DefaultTableModel model = (DefaultTableModel)dgvsales.getModel();
            model.setRowCount(0);
            loadSalesSearch(query);
        }
        else if(cbbasedon.getSelectedItem().equals("Customer ID"))
        {
            String query = "SELECT * FROM sales WHERE customerid='"+txtsearch.getText()+"'";
            DefaultTableModel model = (DefaultTableModel)dgvsales.getModel();
            model.setRowCount(0);
            loadSalesSearch(query);
        }
        else if(cbbasedon.getSelectedItem().equals("Quantity"))
        {
            String query = "SELECT * FROM sales WHERE quantity='"+txtsearch.getText()+"'";
            DefaultTableModel model = (DefaultTableModel)dgvsales.getModel();
            model.setRowCount(0);
            loadSalesSearch(query);
        }
        else 
        {
            JOptionPane.showMessageDialog(null, "Please choose the search type", "Search type INVALID", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnsearchActionPerformed

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
        if(txtsearch.getText().equals(""))
        {
            DefaultTableModel model = (DefaultTableModel)dgvsales.getModel();
            model.setRowCount(0);
            loadSales();
        }
    }//GEN-LAST:event_txtsearchKeyReleased

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
            java.util.logging.Logger.getLogger(ViewSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewSales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsearch;
    private javax.swing.JComboBox<String> cbbasedon;
    private javax.swing.JTable dgvsales;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblbasedon;
    private javax.swing.JLabel lblsales;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables
}
