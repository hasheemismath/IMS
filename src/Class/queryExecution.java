package Class;

import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
        
public class queryExecution 
{
    public boolean executeQuery(String query)
    {
        Connection con = ConnectionProvider.getInstance().getDBConnection();
        
        Statement st;
        
        try
        {
            st = con.createStatement();
            if(st.executeUpdate(query) == 1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(HeadlessException | SQLException ex)
        {
            return false;
        }
    }
    
    public boolean updateQuery(String query)
    {
        Connection con = ConnectionProvider.getInstance().getDBConnection();
        
        Statement st;
        
        try
        {
            st = con.createStatement();
            if(st.executeUpdate(query) == 1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(HeadlessException | SQLException ex)
        {
            return false;
        }
    }
    
    public boolean deleteQuery(String query)
    {
        Connection con = ConnectionProvider.getInstance().getDBConnection();
        
        Statement st;
        
        try
        {
            st = con.createStatement();
            if(st.executeUpdate(query) == 1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(HeadlessException | SQLException ex)
        {
            return false;
        }
    }
    
    public static long generateID(String query,String rowName)
    {
        long id=1;
        
        try
        {
            Connection con = ConnectionProvider.getInstance().getDBConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next())
            {
                id = rs.getLong(rowName);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error occured, "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return id;
    } 
}