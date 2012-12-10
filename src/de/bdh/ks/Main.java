package de.bdh.ks;

import java.util.HashMap;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


public class Main  extends JavaPlugin
{
	static Database Database;
	public KSListener listener;
	public static KSHelper helper;
	public Economy econ;
	public HashMap<Integer,KSMap> maps = new HashMap<Integer,KSMap>();
	 
 	public Main()
    {
 		//INIT
 		new KrimBlockName();
    }

 	//Für spätere Funktionen eingeplant
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
 	
 	public void Prune()
 	{
 		helper.pruneAuctions();
 		helper.pruneDelivery();
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
    		System.out.println((new StringBuilder()).append("[KSALE] Database initialization failed: ").append(e).toString());
            getServer().getPluginManager().disablePlugin(this);
            return;
    	}
    	
    	RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
        	System.out.println((new StringBuilder()).append("[KSALE] !!! NO MONEY SYSTEM FOUND! DISABLING!").toString()); 
        	Bukkit.getServer().getPluginManager().disablePlugin(this);
        } else
        	econ = rsp.getProvider();
        
    	helper = new KSHelper(this);
        pdf = getDescription();
        name = pdf.getName();
        cmdName = (new StringBuilder("[")).append(name).append("] ").toString();
        version = pdf.getVersion();
        author = "Krim";
        
        this.listener = new KSListener(this);
        Bukkit.getServer().getPluginManager().registerEvents(this.listener, this);
        
        Commander c = new Commander(this);
        getCommand("auction").setExecutor(c); 
        getCommand("ks").setExecutor(c); 
        Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new KSTimer(this), 1, 20*60);
    }
    
    public static PluginDescriptionFile pdf;
    public static String name;
    public static String cmdName;
    public static String version;
    public static String author;
    public static Configuration config;
}
