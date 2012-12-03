package de.bdh.ks;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;


public class Main  extends JavaPlugin
{
	Database Database;
	public KSListener listener;
	public static KSHelper helper;
	public HashMap<Integer,KSMap> maps = new HashMap<Integer,KSMap>();
	 
 	public Main()
    {
 		
    }

 	//Für spätere Funktionen
 	public KSMap getMap(int id)
 	{
 		KSMap m;
 		if(this.maps.get(id) == null)
 		{
 			m = new KSMap(this,id);
 			this.maps.put(id,m);
 		} else
 			m = this.maps.get(id);
 		
 		return m;
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
    	
    	helper = new KSHelper(this);
        pdf = getDescription();
        name = pdf.getName();
        cmdName = (new StringBuilder("[")).append(name).append("] ").toString();
        version = pdf.getVersion();
        author = "Krim";
        
        this.listener = new KSListener(this);
        Bukkit.getServer().getPluginManager().registerEvents(this.listener, this);
        
        Commander c = new Commander(this);
        getCommand("register").setExecutor(c); 
    }
    public static PluginDescriptionFile pdf;
    public static String name;
    public static String cmdName;
    public static String version;
    public static String author;
    public static Configuration config;
}
