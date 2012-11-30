package de.bdh.ks;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main  extends JavaPlugin
{
	Database Database;
 	public Main()
    {
 		
    }

    public void onDisable()
    {
        getServer().getScheduler().cancelTasks(this);
        System.out.println((new StringBuilder(String.valueOf(cmdName))).append("by ").append(author).append(" version ").append(version).append(" disabled.").toString());
    }
    
    public void onEnable()
    {	
    	
    	//Lade Config Datei
    	new configManager(this);
    	
    	//Lade mySQL Lib - wenn nicht existent
    	//if (!(new File("lib/", "mysql-connector-java-bin.jar")).exists()) Downloader.install(configManager.MySQL_Jar_Location, "mysql-connector-java-bin.jar");
    	
    	//Erstelle Tabellen
    	try
    	{
    		Database = new Database();
    		Database.setupTable();
    		Database.setupTableMutex();
    		
    	} catch(Exception e)
    	{
    		System.out.println((new StringBuilder()).append("[KS] Database initialization failed: ").append(e).toString());
            getServer().getPluginManager().disablePlugin(this);
            return;
    	}
    	
        pdf = getDescription();
        name = pdf.getName();
        cmdName = (new StringBuilder("[")).append(name).append("] ").toString();
        version = pdf.getVersion();
        author = "Krim";
    }
    public static PluginDescriptionFile pdf;
    public static String name;
    public static String cmdName;
    public static String version;
    public static String author;
    public static Configuration config;
}
