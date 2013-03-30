package de.bdh.ks;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;


public class configManager {
	
	protected static Main plugin;
    protected static YamlConfiguration conf;
    
    // features
    public static String DatabaseType = "mySQL";
    public static String SQLHostname = "localhost";
    public static String SQLPort = "3306";
    public static String SQLUsername = "root";
    public static String SQLPassword = "";
    public static String SQLDatabase = "minecraft";
    public static String SQLTable = "minecraft";
    public static String worlds = "world";
    public static Integer useMaps = 0;
    public static Integer ender = 1;
    public static Integer brautec = 0;
    public static Integer fee = 0;
    public static String lang = "de";
    public static Integer enderForTransaction = 1;
    public static Integer interactBlock = 130, interactBlockSub=0;
    public static HashMap<String, String> names = new HashMap<String,String>();
    public static HashMap<String,Integer> werte = new HashMap<String,Integer>();
    private static File confFile;
    
	
    public configManager(Main main) {
    	plugin = main;
    	File theDir = new File(plugin.getDataFolder(),"");
		if (!theDir.exists())
		{
			theDir.mkdir();
		}
    	setupconf();
    	load();
    }
    
	private static void load() 
	{
		try {
			conf.load(confFile);
		} catch (Exception e) {
			e.printStackTrace();		
		}
		
    	DatabaseType = conf.getString("System.Database.Type", DatabaseType);
        SQLDatabase = conf.getString("System.Database.Settings.Name", SQLDatabase);
        SQLTable = conf.getString("System.Database.Settings.Table", SQLTable);
        SQLHostname = conf.getString("System.Database.Settings.MySQL.Hostname", SQLHostname);
        SQLPort = conf.getString("System.Database.Settings.MySQL.Port", SQLPort);
        SQLUsername = conf.getString("System.Database.Settings.MySQL.Username", SQLUsername);
        SQLPassword = conf.getString("System.Database.Settings.MySQL.Password", SQLPassword);
        useMaps = conf.getInt("System.useMaps", useMaps);
        fee = conf.getInt("System.fee", fee);
        worlds = conf.getString("System.worlds", worlds);
        lang = conf.getString("System.lang", lang);
        interactBlock = conf.getInt("System.interactBlock", interactBlock);
        interactBlockSub = conf.getInt("System.interactBlockSub", interactBlockSub);
        ender = conf.getInt("System.useEnderChests", ender);
        brautec = conf.getInt("System.bukkitforge", brautec);
        enderForTransaction = conf.getInt("System.useEnderChestsForTransaction", enderForTransaction);
        
        ConfigurationSection nsel = conf.getConfigurationSection("Names");
        for(String s : nsel.getKeys(false))
        {
        	names.put(s,nsel.getString(s));
        }
        
        nsel = conf.getConfigurationSection("Values");
        for(String s : nsel.getKeys(false))
        {
        	werte.put(s,nsel.getInt(s));
        }
        
        try {
        	if (!confFile.exists())
        		confFile.createNewFile();
			conf.save(confFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public static void reload() {
		load();
	}
	
    private static void setupconf() {
        confFile = new File(plugin.getDataFolder(), "config.yml");
        conf = null;
        
        if (confFile.exists())
        {
            conf = new YamlConfiguration();
            try {
				conf.load(confFile);
			} catch (Exception e) {
				e.printStackTrace();
			}       
        }
        else {
            File confFile;
            confFile = new File(plugin.getDataFolder(), "config.yml");
            conf = new YamlConfiguration();
            
            HashMap<String, String> n = new HashMap<String,String>();
            n.put("stone", "1");
            
            HashMap<String, Integer> v = new HashMap<String,Integer>();
            v.put("264", 8096);
            
            conf.set("System.Database.Type", DatabaseType);
            conf.set("System.Database.Settings.Name", SQLDatabase);
            conf.set("System.Database.Settings.Table", SQLTable);
            conf.set("System.Database.Settings.MySQL.Hostname", SQLHostname);
            conf.set("System.Database.Settings.MySQL.Port", SQLPort);
            conf.set("System.Database.Settings.MySQL.Username", SQLUsername);
            conf.set("System.Database.Settings.MySQL.Password", SQLPassword);
            conf.set("System.useMaps",useMaps);
            conf.set("System.fee",fee);
            conf.set("System.worlds",worlds);
            conf.set("System.lang",lang);
            conf.set("System.interactBlock",interactBlock);
            conf.set("System.interactBlockSub",interactBlockSub);
            conf.set("System.useEnderChests", ender);
            conf.set("System.bukkitforge", brautec);
            conf.set("System.useEnderChestsForTransaction", enderForTransaction);
            conf.set("Names", n);
            conf.set("Values", v);
            
            try {
                confFile.createNewFile();
				conf.save(confFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
}
