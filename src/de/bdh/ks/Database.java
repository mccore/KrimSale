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
            System.out.println((new StringBuilder()).append("[KSALE] Driver error: ").append(e).toString());
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
            System.out.println((new StringBuilder()).append("[KSALE] Could not create connection: ").append(e).toString());
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
            System.out.println((new StringBuilder()).append("[KSALE] Could not create connection: ").append(e).toString());
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
        rs = dbm.getTables(null, null, (new StringBuilder()).append(configManager.SQLTable).append("_deliver").toString(), null);
        if(!rs.next())
        {
            System.out.println((new StringBuilder()).append("[KSALE] Creating table: ").append(configManager.SQLTable).append("_deliver").toString());
            ps = conn.prepareStatement((new StringBuilder()).append("CREATE TABLE ").append(configManager.SQLTable).append("_deliver(").append("`id` int(11) unsigned NOT NULL AUTO_INCREMENT,`money` int(11) NOT NULL DEFAULT '0',`type` int(11) NOT NULL DEFAULT '0',`subtype` int(11) NOT NULL DEFAULT '0',`amount` int(11) NOT NULL DEFAULT '0',`player` varchar(50) NOT NULL,`zeit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (`id`), KEY `player` (`player`)) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1").toString());
            ps.executeUpdate();
            System.out.println("[KSALE] Table deliver Created.");
        }
        
        rs = dbm.getTables(null, null, (new StringBuilder()).append(configManager.SQLTable).append("_offer").toString(), null);
        if(!rs.next())
        {
            System.out.println((new StringBuilder()).append("[KSALE] Creating table: ").append(configManager.SQLTable).append("_offer").toString());
            ps = conn.prepareStatement((new StringBuilder()).append("CREATE TABLE ").append(configManager.SQLTable).append("_offer(").append("`id` int(11) unsigned NOT NULL AUTO_INCREMENT,`type` int(11) NOT NULL,`amount` int(11) NOT NULL,`subtype` int(11) NOT NULL,`price` int(11) NOT NULL,`player` varchar(50) NOT NULL, `admin` int(1) NOT NULL DEFAULT '0',`sworld` varchar(50) NOT NULL DEFAULT '',`signx` int(13) NOT NULL DEFAULT '0', `signy` int(13) NOT NULL DEFAULT '0', `signz` int(13) NOT NULL DEFAULT '0', `nodeliver` tinyint(4) NOT NULL DEFAULT '0', `zeit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (`id`),KEY `sign` (`sworld`,`signx`,`signy`,`signz`), KEY `player` (`player`)) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1").toString());
            ps.executeUpdate();
            System.out.println("[KSALE] Table offer Created.");
        }
        
        rs = dbm.getTables(null, null, (new StringBuilder()).append(configManager.SQLTable).append("_request").toString(), null);
        if(!rs.next())
        {
            System.out.println((new StringBuilder()).append("[KSALE] Creating table: ").append(configManager.SQLTable).append("_request").toString());
            ps = conn.prepareStatement((new StringBuilder()).append("CREATE TABLE ").append(configManager.SQLTable).append("_request(").append("`id` int(11) unsigned NOT NULL AUTO_INCREMENT,`type` int(11) NOT NULL,`amount` int(11) NOT NULL,`subtype` int(11) NOT NULL,`price` int(11) NOT NULL,`player` varchar(50) NOT NULL, `admin` int(1) NOT NULL DEFAULT '0',`zeit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, `sworld` varchar(50) NOT NULL DEFAULT '',`signx` int(13) NOT NULL DEFAULT '0', `signy` int(13) NOT NULL DEFAULT '0', `signz` int(13) NOT NULL DEFAULT '0', `nodeliver` tinyint(4) NOT NULL DEFAULT '0', PRIMARY KEY (`id`),KEY `sign` (`sworld`,`signx`,`signy`,`signz`), KEY `player` (`player`)) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1").toString());
            ps.executeUpdate();
            System.out.println("[KSALE] Table offer Created.");
        }
        
        rs = dbm.getTables(null, null, (new StringBuilder()).append(configManager.SQLTable).append("_transaction").toString(), null);
        if(!rs.next())
        {
            System.out.println((new StringBuilder()).append("[KSALE] Creating table: ").append(configManager.SQLTable).append("_transaction").toString());
            ps = conn.prepareStatement((new StringBuilder()).append("CREATE TABLE ").append(configManager.SQLTable).append("_transaction(").append("`type` int(11) unsigned NOT NULL, `subtype` int(11) NOT NULL DEFAULT '0',`fromplayer` varchar(50) NOT NULL, `toplayer` varchar(50) NOT NULL, `amount` int(11) NOT NULL, `price` double unsigned NOT NULL,`zeit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, KEY `fromplayer` (`fromplayer`), KEY `toplayer` (`toplayer`), KEY `id` (`type`)) ENGINE=MyISAM DEFAULT CHARSET=latin1;").toString());
            ps.executeUpdate();
            System.out.println("[KSALE] Table transaction Created.");
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
            System.out.println((new StringBuilder()).append("[KSALE] Creating table: ").append("mutex").toString());
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
            
            System.out.println("[KSALE] Table mutex Created.");
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
