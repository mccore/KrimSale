package de.bdh.ks;

import java.sql.*;

public class Database
{
	private Connection con = null;
    public Database()
    {
        if(configManager.DatabaseType.equalsIgnoreCase("mySQL"))
        {
            driver = "com.mysql.jdbc.Driver";
            dsn = (new StringBuilder()).append("jdbc:mysql://").append(configManager.SQLHostname).append(":").append(configManager.SQLPort).append("/").append(configManager.SQLDatabase).toString();
            username = configManager.SQLUsername;
            password = configManager.SQLPassword;
        }
        try
        {
            Class.forName(driver).newInstance();
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder()).append("[KREG] Driver error: ").append(e).toString());
        }
	}

    public Connection getConnection()
    {
    	try
    	{
    		if(this.con == null || this.con.isClosed())
	    	{
	    		this.con = this.makeConnection();
	    	}
	    } catch(SQLException e)
        {
            System.out.println((new StringBuilder()).append("[KREG] Could not create connection: ").append(e).toString());
        }
    	
    	return this.con;
    }
    
    public Connection makeConnection()
    {
        if(username.equalsIgnoreCase("") && password.equalsIgnoreCase(""))
			try {
				return DriverManager.getConnection(dsn);
			} catch (SQLException e1) { }
        
        try
        {
            return DriverManager.getConnection(dsn, username, password);
        }
        catch(SQLException e)
        {
            System.out.println((new StringBuilder()).append("[KREG] Could not create connection: ").append(e).toString());
        }
        return null;
    }
    

    public void close(Connection connection)
    {
        if(connection != null)
        try
        {
            connection.close();
        }
        catch(SQLException ex) { }
    }

    public void setupTable() throws Exception
    {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        DatabaseMetaData dbm = conn.getMetaData();
        rs = dbm.getTables(null, null, (new StringBuilder()).append(configManager.SQLTable).append("_krimreg").toString(), null);
        if(!rs.next())
        {
            System.out.println((new StringBuilder()).append("[KREG] Creating table: ").append(configManager.SQLTable).append("_krimreg").toString());
            ps = conn.prepareStatement((new StringBuilder()).append("CREATE TABLE ").append(configManager.SQLTable).append("_krimreg(").append("`id` int(12) NOT NULL AUTO_INCREMENT,").append("`username` varchar(128) NULL DEFAULT  '0',").append("`passwort` varchar(128) NULL DEFAULT  '0',").append("PRIMARY KEY (`id`)").append(")").toString());
            ps.executeUpdate();
            System.out.println("[KREG] Table registration Created.");
        }
        
        if(ps != null)
            try
            {
                ps.close();
            }
            catch(SQLException ex) { }
        if(rs != null)
            try
            {
                rs.close();
            }
            catch(SQLException ex) { }
        if(conn != null)
            try
            {
                conn.close();
            }
            catch(SQLException ex) { }
    }
    
    public void setupTableMutex() throws Exception
    {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        DatabaseMetaData dbm = conn.getMetaData();
        rs = dbm.getTables(null, null, (new StringBuilder()).append("mutex").toString(), null);
        if(!rs.next())
        {
            System.out.println((new StringBuilder()).append("[KREG] Creating table: ").append("mutex").toString());
            ps = conn.prepareStatement((new StringBuilder()).append("CREATE TABLE `mutex` (`i` int(11) NOT NULL,PRIMARY KEY (`i`))").toString());
            ps.executeUpdate();
            
            if(ps != null)
                try
                {
                    ps.close();
                }
                catch(SQLException ex) { }
            
            ps = conn.prepareStatement((new StringBuilder()).append("INSERT INTO `mutex` (`i`) VALUES (0),(1)").toString());
            ps.executeUpdate();
            
            System.out.println("[KREG] Table mutex Created.");
        }
        
        if(ps != null)
            try
            {
                ps.close();
            }
            catch(SQLException ex) { }
        if(rs != null)
            try
            {
                rs.close();
            }
            catch(SQLException ex) { }
    }
    
    private String driver;
    private String dsn;
    private String username;
    private String password;
}
