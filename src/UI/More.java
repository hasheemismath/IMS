package UI;

import Class.ConnectionProvider;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Roshan Nizar
 */

public class More extends javax.swing.JFrame 
{
    public More() 
    {
        initComponents();
        moreIndicator.setBackground(new Color(245,245,245));
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        activeProfileLabel.setText("Active: "+SignIn.sessionName);
        introLabel.setText("Welcome! "+SignIn.sessionName);
        load();
    }
    
    private void load()
    {
        totalCustomer();
        totalSupplier();
        totalService();
        totalRepair();
        totalInwards();
        totalOutwards();
        totalPetty();
    }
    
    private void totalCustomer()
    {
        int total = 0;
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM customer";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next())
            {
                total = total + 1;
            }
            
            txtCustomer.setText("Total: "+String.valueOf(total));
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void totalSupplier()
    {
        int total = 0;
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM supplier";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next())
            {
                total = total + 1;
            }
            
            txtSupplier.setText("Total: "+String.valueOf(total));
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void totalService()
    {
        double total = 0;
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM service";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next())
            {
                total = total + rs.getDouble("amount");
            }
            
            txtService.setText("Rs: "+String.valueOf(total)+"/=");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void totalRepair()
    {
        double total = 0;
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM repair";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next())
            {
                total = total + rs.getDouble("amount");
            }
            
            txtRepair.setText("Rs: "+String.valueOf(total)+"/=");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void totalInwards()
    {
        double total = 0;
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM inward";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next())
            {
                total = total + (rs.getDouble("quantity") * rs.getDouble("price"));
            }
            
            txtInwards.setText("Rs: "+String.valueOf(total)+"/=");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void totalOutwards()
    {
        double total = 0;
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM outward";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next())
            {
                total = total + (rs.getDouble("quantity") * rs.getDouble("price"));
            }
            
            txtOutwards.setText("Rs: "+String.valueOf(total)+"/=");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void totalPetty()
    {
        double total = 0;
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            String query = "SELECT * FROM petty";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next())
            {
                total = total + rs.getDouble("amount");
            }
            
            txtOutwards.setText("Rs: "+String.valueOf(total)+"/=");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
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

        jPanel4 = new javax.swing.JPanel();
        dashboardLabel = new javax.swing.JLabel();
        activeProfileLabel = new javax.swing.JLabel();
        logoLabel = new javax.swing.JLabel();
        introLabel = new javax.swing.JLabel();
        signoutPanel = new javax.swing.JPanel();
        signoutLabel = new javax.swing.JLabel();
        dashboardPanel = new javax.swing.JPanel();
        dashboardButton = new javax.swing.JLabel();
        dashboardIndicator = new javax.swing.JPanel();
        inventoryPanel = new javax.swing.JPanel();
        inventoryLabel = new javax.swing.JLabel();
        inventoryIndicator = new javax.swing.JPanel();
        salesPanel = new javax.swing.JPanel();
        salesLabel = new javax.swing.JLabel();
        salesIndicator = new javax.swing.JPanel();
        purchasePanel = new javax.swing.JPanel();
        purchaseLabel = new javax.swing.JLabel();
        purchaseIndicator = new javax.swing.JPanel();
        morePanel = new javax.swing.JPanel();
        moreLabel = new javax.swing.JLabel();
        moreIndicator = new javax.swing.JPanel();
        settingsPanel = new javax.swing.JPanel();
        settingsLabel = new javax.swing.JLabel();
        settingsIndicator = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dashboardDetailsPanel = new javax.swing.JPanel();
        dashboardDetailsPanel2 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtCustomer = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnCustomer = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtSupplier = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnSupplier = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtService = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnService = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtRepair = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnRepair = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtPetty = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btnPetty = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtInwards = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        btnGo = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtOutwards = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btnOutwards = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtHR = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        btnHrGo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("New Airtel | More");
        setPreferredSize(new java.awt.Dimension(1315, 666));

        jPanel4.setBackground(new java.awt.Color(0, 31, 63));

        dashboardLabel.setFont(new java.awt.Font("Calibri", 0, 48)); // NOI18N
        dashboardLabel.setForeground(new java.awt.Color(245, 245, 245));
        dashboardLabel.setText("More");

        activeProfileLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        activeProfileLabel.setForeground(new java.awt.Color(245, 245, 245));
        activeProfileLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        activeProfileLabel.setText("Active: Ameen Arafath Hussain");

        logoLabel.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        logoLabel.setForeground(new java.awt.Color(245, 245, 245));
        logoLabel.setText(" NEW AIRTEL");

        introLabel.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        introLabel.setForeground(new java.awt.Color(245, 245, 245));
        introLabel.setText(" Welcome! Ameen Arafath Hussain");

        signoutPanel.setBackground(new java.awt.Color(0, 31, 63));

        signoutLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        signoutLabel.setForeground(new java.awt.Color(245, 245, 245));
        signoutLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signoutLabel.setText("Sign Out");

        javax.swing.GroupLayout signoutPanelLayout = new javax.swing.GroupLayout(signoutPanel);
        signoutPanel.setLayout(signoutPanelLayout);
        signoutPanelLayout.setHorizontalGroup(
            signoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signoutPanelLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(signoutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        signoutPanelLayout.setVerticalGroup(
            signoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(signoutLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dashboardPanel.setBackground(new java.awt.Color(0, 31, 63));

        dashboardButton.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        dashboardButton.setForeground(new java.awt.Color(245, 245, 245));
        dashboardButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dashboardButton.setText("Dashboard");
        dashboardButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardButtonMouseClicked(evt);
            }
        });

        dashboardIndicator.setBackground(new java.awt.Color(0, 31, 63));
        dashboardIndicator.setPreferredSize(new java.awt.Dimension(0, 3));

        javax.swing.GroupLayout dashboardIndicatorLayout = new javax.swing.GroupLayout(dashboardIndicator);
        dashboardIndicator.setLayout(dashboardIndicatorLayout);
        dashboardIndicatorLayout.setHorizontalGroup(
            dashboardIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboardIndicatorLayout.setVerticalGroup(
            dashboardIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout dashboardPanelLayout = new javax.swing.GroupLayout(dashboardPanel);
        dashboardPanel.setLayout(dashboardPanelLayout);
        dashboardPanelLayout.setHorizontalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashboardIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
            .addComponent(dashboardButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
        );
        dashboardPanelLayout.setVerticalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dashboardPanelLayout.createSequentialGroup()
                .addComponent(dashboardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashboardIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        inventoryPanel.setBackground(new java.awt.Color(0, 31, 63));

        inventoryLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        inventoryLabel.setForeground(new java.awt.Color(245, 245, 245));
        inventoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        inventoryLabel.setText("Inventory");
        inventoryLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventoryLabelMouseClicked(evt);
            }
        });

        inventoryIndicator.setBackground(new java.awt.Color(0, 31, 63));
        inventoryIndicator.setPreferredSize(new java.awt.Dimension(0, 3));

        javax.swing.GroupLayout inventoryIndicatorLayout = new javax.swing.GroupLayout(inventoryIndicator);
        inventoryIndicator.setLayout(inventoryIndicatorLayout);
        inventoryIndicatorLayout.setHorizontalGroup(
            inventoryIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        inventoryIndicatorLayout.setVerticalGroup(
            inventoryIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout inventoryPanelLayout = new javax.swing.GroupLayout(inventoryPanel);
        inventoryPanel.setLayout(inventoryPanelLayout);
        inventoryPanelLayout.setHorizontalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(inventoryIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
            .addGroup(inventoryPanelLayout.createSequentialGroup()
                .addComponent(inventoryLabel)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        inventoryPanelLayout.setVerticalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inventoryPanelLayout.createSequentialGroup()
                .addComponent(inventoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inventoryIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        salesPanel.setBackground(new java.awt.Color(0, 31, 63));

        salesLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        salesLabel.setForeground(new java.awt.Color(245, 245, 245));
        salesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salesLabel.setText("Sales");
        salesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salesLabelMouseClicked(evt);
            }
        });

        salesIndicator.setBackground(new java.awt.Color(0, 31, 63));
        salesIndicator.setPreferredSize(new java.awt.Dimension(0, 3));

        javax.swing.GroupLayout salesIndicatorLayout = new javax.swing.GroupLayout(salesIndicator);
        salesIndicator.setLayout(salesIndicatorLayout);
        salesIndicatorLayout.setHorizontalGroup(
            salesIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        salesIndicatorLayout.setVerticalGroup(
            salesIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout salesPanelLayout = new javax.swing.GroupLayout(salesPanel);
        salesPanel.setLayout(salesPanelLayout);
        salesPanelLayout.setHorizontalGroup(
            salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(salesIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
            .addComponent(salesLabel)
        );
        salesPanelLayout.setVerticalGroup(
            salesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salesPanelLayout.createSequentialGroup()
                .addComponent(salesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salesIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        purchasePanel.setBackground(new java.awt.Color(0, 31, 63));

        purchaseLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        purchaseLabel.setForeground(new java.awt.Color(245, 245, 245));
        purchaseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        purchaseLabel.setText("Purchase");
        purchaseLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                purchaseLabelMouseClicked(evt);
            }
        });

        purchaseIndicator.setBackground(new java.awt.Color(0, 31, 63));
        purchaseIndicator.setPreferredSize(new java.awt.Dimension(0, 3));

        javax.swing.GroupLayout purchaseIndicatorLayout = new javax.swing.GroupLayout(purchaseIndicator);
        purchaseIndicator.setLayout(purchaseIndicatorLayout);
        purchaseIndicatorLayout.setHorizontalGroup(
            purchaseIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        purchaseIndicatorLayout.setVerticalGroup(
            purchaseIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout purchasePanelLayout = new javax.swing.GroupLayout(purchasePanel);
        purchasePanel.setLayout(purchasePanelLayout);
        purchasePanelLayout.setHorizontalGroup(
            purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(purchaseIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
            .addComponent(purchaseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
        );
        purchasePanelLayout.setVerticalGroup(
            purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, purchasePanelLayout.createSequentialGroup()
                .addComponent(purchaseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(purchaseIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        morePanel.setBackground(new java.awt.Color(0, 31, 63));

        moreLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        moreLabel.setForeground(new java.awt.Color(245, 245, 245));
        moreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moreLabel.setText("More");
        moreLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moreLabelMouseClicked(evt);
            }
        });

        moreIndicator.setBackground(new java.awt.Color(0, 31, 63));
        moreIndicator.setPreferredSize(new java.awt.Dimension(0, 3));

        javax.swing.GroupLayout moreIndicatorLayout = new javax.swing.GroupLayout(moreIndicator);
        moreIndicator.setLayout(moreIndicatorLayout);
        moreIndicatorLayout.setHorizontalGroup(
            moreIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        moreIndicatorLayout.setVerticalGroup(
            moreIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout morePanelLayout = new javax.swing.GroupLayout(morePanel);
        morePanel.setLayout(morePanelLayout);
        morePanelLayout.setHorizontalGroup(
            morePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(moreIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
            .addComponent(moreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        morePanelLayout.setVerticalGroup(
            morePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, morePanelLayout.createSequentialGroup()
                .addComponent(moreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(moreIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        settingsPanel.setBackground(new java.awt.Color(0, 31, 63));

        settingsLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        settingsLabel.setForeground(new java.awt.Color(245, 245, 245));
        settingsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settingsLabel.setText("Settings");
        settingsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsLabelMouseClicked(evt);
            }
        });

        settingsIndicator.setBackground(new java.awt.Color(0, 31, 63));
        settingsIndicator.setPreferredSize(new java.awt.Dimension(0, 3));

        javax.swing.GroupLayout settingsIndicatorLayout = new javax.swing.GroupLayout(settingsIndicator);
        settingsIndicator.setLayout(settingsIndicatorLayout);
        settingsIndicatorLayout.setHorizontalGroup(
            settingsIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        settingsIndicatorLayout.setVerticalGroup(
            settingsIndicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(settingsIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
            .addComponent(settingsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsPanelLayout.createSequentialGroup()
                .addComponent(settingsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(settingsIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(245, 245, 245));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Profit: ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dashboardLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(logoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(introLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(activeProfileLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(dashboardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inventoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(purchasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(morePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(settingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 739, Short.MAX_VALUE)
                        .addComponent(signoutPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(activeProfileLabel)
                    .addComponent(logoLabel))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dashboardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(introLabel)
                .addGap(130, 130, 130)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(signoutPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(dashboardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inventoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(purchasePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(morePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(settingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel13.setBackground(new java.awt.Color(245, 245, 245));
        jPanel13.setPreferredSize(new java.awt.Dimension(321, 130));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/customer.png"))); // NOI18N

        jLabel18.setBackground(new java.awt.Color(220, 20, 60));
        jLabel18.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 31, 63));
        jLabel18.setText("Customer");

        txtCustomer.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtCustomer.setForeground(new java.awt.Color(0, 31, 63));
        txtCustomer.setText("4");

        jPanel3.setBackground(new java.awt.Color(0, 31, 63));
        jPanel3.setPreferredSize(new java.awt.Dimension(0, 7));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(0, 31, 63));

        btnCustomer.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnCustomer.setForeground(new java.awt.Color(245, 245, 245));
        btnCustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCustomer.setText("Go");
        btnCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCustomerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel15.setBackground(new java.awt.Color(245, 245, 245));
        jPanel15.setPreferredSize(new java.awt.Dimension(321, 130));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/supplier.png"))); // NOI18N

        jLabel21.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 31, 63));
        jLabel21.setText("Supplier");

        txtSupplier.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtSupplier.setForeground(new java.awt.Color(0, 31, 63));
        txtSupplier.setText("3");

        jPanel18.setBackground(new java.awt.Color(0, 31, 63));
        jPanel18.setPreferredSize(new java.awt.Dimension(0, 7));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 31, 63));
        jPanel2.setPreferredSize(new java.awt.Dimension(64, 30));

        btnSupplier.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnSupplier.setForeground(new java.awt.Color(245, 245, 245));
        btnSupplier.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSupplier.setText("Go");
        btnSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSupplierMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSupplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSupplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(txtSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))))
        );

        jPanel16.setBackground(new java.awt.Color(245, 245, 245));
        jPanel16.setPreferredSize(new java.awt.Dimension(321, 130));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/service.png"))); // NOI18N

        jLabel24.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 31, 63));
        jLabel24.setText("Service");

        txtService.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtService.setForeground(new java.awt.Color(0, 31, 63));
        txtService.setText("127500/-");

        jPanel12.setBackground(new java.awt.Color(0, 31, 63));
        jPanel12.setPreferredSize(new java.awt.Dimension(0, 7));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(0, 31, 63));
        jPanel6.setPreferredSize(new java.awt.Dimension(64, 30));

        btnService.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnService.setForeground(new java.awt.Color(245, 245, 245));
        btnService.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnService.setText("Go");
        btnService.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnServiceMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnService, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(txtService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel24)
                        .addGap(7, 7, 7)
                        .addComponent(txtService, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jPanel17.setBackground(new java.awt.Color(245, 245, 245));
        jPanel17.setPreferredSize(new java.awt.Dimension(321, 130));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/repair.png"))); // NOI18N

        jLabel27.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 31, 63));
        jLabel27.setText("Repair");

        txtRepair.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtRepair.setForeground(new java.awt.Color(0, 31, 63));
        txtRepair.setText("85000/-");

        jPanel14.setBackground(new java.awt.Color(0, 31, 63));
        jPanel14.setPreferredSize(new java.awt.Dimension(0, 7));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(0, 31, 63));
        jPanel7.setPreferredSize(new java.awt.Dimension(64, 30));

        btnRepair.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnRepair.setForeground(new java.awt.Color(245, 245, 245));
        btnRepair.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnRepair.setText("Go");
        btnRepair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRepairMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRepair, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRepair, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(txtRepair, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRepair, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jPanel19.setBackground(new java.awt.Color(245, 245, 245));
        jPanel19.setPreferredSize(new java.awt.Dimension(321, 130));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/accounting.png"))); // NOI18N

        jLabel30.setBackground(new java.awt.Color(220, 20, 60));
        jLabel30.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 31, 63));
        jLabel30.setText("Petty Cash");

        txtPetty.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtPetty.setForeground(new java.awt.Color(0, 31, 63));
        txtPetty.setText("127500/-");

        jPanel5.setBackground(new java.awt.Color(0, 31, 63));
        jPanel5.setPreferredSize(new java.awt.Dimension(0, 7));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(0, 31, 63));
        jPanel10.setPreferredSize(new java.awt.Dimension(64, 30));

        btnPetty.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnPetty.setForeground(new java.awt.Color(245, 245, 245));
        btnPetty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnPetty.setText("Go");
        btnPetty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPettyMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPetty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPetty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPetty, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPetty, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))))
        );

        jPanel20.setBackground(new java.awt.Color(245, 245, 245));
        jPanel20.setPreferredSize(new java.awt.Dimension(321, 130));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/inward.png"))); // NOI18N

        jLabel33.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 31, 63));
        jLabel33.setText("Inwards");

        txtInwards.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtInwards.setForeground(new java.awt.Color(0, 31, 63));
        txtInwards.setText("1500/-");

        jPanel21.setBackground(new java.awt.Color(0, 31, 63));
        jPanel21.setPreferredSize(new java.awt.Dimension(0, 7));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        jPanel27.setBackground(new java.awt.Color(0, 31, 63));
        jPanel27.setPreferredSize(new java.awt.Dimension(64, 30));

        btnGo.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnGo.setForeground(new java.awt.Color(245, 245, 245));
        btnGo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGo.setText("Go");
        btnGo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnGo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnGo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtInwards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtInwards, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(245, 245, 245));
        jPanel22.setPreferredSize(new java.awt.Dimension(321, 130));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/outward.png"))); // NOI18N

        jLabel36.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 31, 63));
        jLabel36.setText("Outwards");

        txtOutwards.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtOutwards.setForeground(new java.awt.Color(0, 31, 63));
        txtOutwards.setText("17500/-");

        jPanel23.setBackground(new java.awt.Color(0, 31, 63));
        jPanel23.setPreferredSize(new java.awt.Dimension(0, 7));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(0, 31, 63));
        jPanel9.setPreferredSize(new java.awt.Dimension(64, 30));

        btnOutwards.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnOutwards.setForeground(new java.awt.Color(245, 245, 245));
        btnOutwards.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnOutwards.setText("Go");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnOutwards, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnOutwards, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(txtOutwards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(87, 87, 87))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOutwards, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel24.setBackground(new java.awt.Color(245, 245, 245));
        jPanel24.setPreferredSize(new java.awt.Dimension(321, 130));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/hr.png"))); // NOI18N

        jLabel39.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 31, 63));
        jLabel39.setText("HR");

        txtHR.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtHR.setForeground(new java.awt.Color(0, 31, 63));
        txtHR.setText("1257500/-");

        jPanel25.setBackground(new java.awt.Color(0, 31, 63));
        jPanel25.setPreferredSize(new java.awt.Dimension(0, 7));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(0, 31, 63));
        jPanel8.setPreferredSize(new java.awt.Dimension(64, 30));

        btnHrGo.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btnHrGo.setForeground(new java.awt.Color(245, 245, 245));
        btnHrGo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnHrGo.setText("Go");
        btnHrGo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHrGoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHrGo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHrGo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38)
                        .addContainerGap())
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHR, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout dashboardDetailsPanel2Layout = new javax.swing.GroupLayout(dashboardDetailsPanel2);
        dashboardDetailsPanel2.setLayout(dashboardDetailsPanel2Layout);
        dashboardDetailsPanel2Layout.setHorizontalGroup(
            dashboardDetailsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardDetailsPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(dashboardDetailsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dashboardDetailsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dashboardDetailsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dashboardDetailsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                .addContainerGap())
        );
        dashboardDetailsPanel2Layout.setVerticalGroup(
            dashboardDetailsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardDetailsPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(dashboardDetailsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 151, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dashboardDetailsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 151, Short.MAX_VALUE))
                .addGap(86, 86, 86))
        );

        javax.swing.GroupLayout dashboardDetailsPanelLayout = new javax.swing.GroupLayout(dashboardDetailsPanel);
        dashboardDetailsPanel.setLayout(dashboardDetailsPanelLayout);
        dashboardDetailsPanelLayout.setHorizontalGroup(
            dashboardDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardDetailsPanelLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(dashboardDetailsPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );
        dashboardDetailsPanelLayout.setVerticalGroup(
            dashboardDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dashboardDetailsPanelLayout.createSequentialGroup()
                .addComponent(dashboardDetailsPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dashboardDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashboardDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardButtonMouseClicked
        dashboardIndicator.setBackground(new Color(245,245,245));
        inventoryIndicator.setBackground(new Color(0,31,63));
        salesIndicator.setBackground(new Color(0,31,63));
        purchaseIndicator.setBackground(new Color(0,20,63));
        moreIndicator.setBackground(new Color(0,31,63));
        settingsIndicator.setBackground(new Color(0,31,63));
        
        new Dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_dashboardButtonMouseClicked

    private void inventoryLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryLabelMouseClicked
        dashboardIndicator.setBackground(new Color(0,31,63));
        inventoryIndicator.setBackground(new Color(245,245,245));
        salesIndicator.setBackground(new Color(0,31,63));
        purchaseIndicator.setBackground(new Color(0,31,63));
        moreIndicator.setBackground(new Color(0,31,63));
        settingsIndicator.setBackground(new Color(0,31,63));
        
        new InventryManagementDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_inventoryLabelMouseClicked

    private void salesLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salesLabelMouseClicked
        dashboardIndicator.setBackground(new Color(0,31,63));
        inventoryIndicator.setBackground(new Color(0,31,63));
        salesIndicator.setBackground(new Color(245,245,245));
        purchaseIndicator.setBackground(new Color(0,31,63));
        moreIndicator.setBackground(new Color(0,31,63));
        settingsIndicator.setBackground(new Color(0,31,63));
        
        new SalesDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_salesLabelMouseClicked

    private void purchaseLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchaseLabelMouseClicked
        dashboardIndicator.setBackground(new Color(0,31,63));
        inventoryIndicator.setBackground(new Color(0,31,63));
        salesIndicator.setBackground(new Color(0,31,63));
        purchaseIndicator.setBackground(new Color(245,245,245));
        moreIndicator.setBackground(new Color(0,31,63));
        settingsIndicator.setBackground(new Color(0,31,63));
        
        new Purchase_dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_purchaseLabelMouseClicked

    private void moreLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moreLabelMouseClicked
        dashboardIndicator.setBackground(new Color(0,31,63));
        inventoryIndicator.setBackground(new Color(0,31,63));
        salesIndicator.setBackground(new Color(0,31,63));
        purchaseIndicator.setBackground(new Color(0,31,63));
        moreIndicator.setBackground(new Color(245,245,245));
        settingsIndicator.setBackground(new Color(0,31,63));
    }//GEN-LAST:event_moreLabelMouseClicked

    private void settingsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsLabelMouseClicked
        dashboardIndicator.setBackground(new Color(0,31,63));
        inventoryIndicator.setBackground(new Color(0,31,63));
        salesIndicator.setBackground(new Color(0,31,63));
        purchaseIndicator.setBackground(new Color(0,31,63));
        moreIndicator.setBackground(new Color(0,31,63));
        settingsIndicator.setBackground(new Color(245,245,245));
        
        new Settings().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_settingsLabelMouseClicked

    private void btnHrGoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHrGoMouseClicked
        new HRDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHrGoMouseClicked

    private void btnGoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGoMouseClicked
        new InwardsDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnGoMouseClicked

    private void btnSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSupplierMouseClicked
        new supplier_dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSupplierMouseClicked

    private void btnCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCustomerMouseClicked
        new CustomerMgt().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCustomerMouseClicked

    private void btnServiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnServiceMouseClicked
        new ServiceRepair().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnServiceMouseClicked

    private void btnRepairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRepairMouseClicked
        new ServiceRepair().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRepairMouseClicked

    private void btnPettyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPettyMouseClicked
        new ExDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPettyMouseClicked

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new More().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel activeProfileLabel;
    private javax.swing.JLabel btnCustomer;
    private javax.swing.JLabel btnGo;
    private javax.swing.JLabel btnHrGo;
    private javax.swing.JLabel btnOutwards;
    private javax.swing.JLabel btnPetty;
    private javax.swing.JLabel btnRepair;
    private javax.swing.JLabel btnService;
    private javax.swing.JLabel btnSupplier;
    private javax.swing.JLabel dashboardButton;
    private javax.swing.JPanel dashboardDetailsPanel;
    private javax.swing.JPanel dashboardDetailsPanel2;
    private javax.swing.JPanel dashboardIndicator;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JLabel introLabel;
    private javax.swing.JPanel inventoryIndicator;
    private javax.swing.JLabel inventoryLabel;
    private javax.swing.JPanel inventoryPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPanel moreIndicator;
    private javax.swing.JLabel moreLabel;
    private javax.swing.JPanel morePanel;
    private javax.swing.JPanel purchaseIndicator;
    private javax.swing.JLabel purchaseLabel;
    private javax.swing.JPanel purchasePanel;
    private javax.swing.JPanel salesIndicator;
    private javax.swing.JLabel salesLabel;
    private javax.swing.JPanel salesPanel;
    private javax.swing.JPanel settingsIndicator;
    private javax.swing.JLabel settingsLabel;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JLabel signoutLabel;
    private javax.swing.JPanel signoutPanel;
    private javax.swing.JLabel txtCustomer;
    private javax.swing.JLabel txtHR;
    private javax.swing.JLabel txtInwards;
    private javax.swing.JLabel txtOutwards;
    private javax.swing.JLabel txtPetty;
    private javax.swing.JLabel txtRepair;
    private javax.swing.JLabel txtService;
    private javax.swing.JLabel txtSupplier;
    // End of variables declaration//GEN-END:variables
}
