package de.bdh.ks;

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
	public static KSLang lng;
	public static Economy econ;	 
 	public Main()
    {
 		new KrimBlockName(); //INIT BlockNameLister
    }
 	
 	public void Prune()
 	{
 		helper.pruneAuctions();
 		helper.pruneDelivery();
 		helper.pruneRequests();
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
    	
    	//Lade Language System
    	lng = new KSLang();
    	
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
    		System.out.println((new StringBuilder()).append("[KSALE] !!! Database initialization failed: ").append(e).toString());
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
        
        getCommand("auction").setExecutor(new Commander(this)); 
        getCommand("ks").setExecutor(new AdminCommander(this)); 
        Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new KSTimer(this), 1, 20*60);
    }
    
    public static PluginDescriptionFile pdf;
    public static String name;
    public static String cmdName;
    public static String version;
    public static String author;
    public static Configuration config;
}
