package de.bdh.ks;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class Commander implements CommandExecutor {
	
	Main plugin;
	public Commander(Main plugin)
	{
		this.plugin = plugin;
	}
	
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[])
    {
		if(sender instanceof Player)
        {
        	if(command.getName().equals("auction"))
        	{
        		if(args.length == 0)
        		{
        			sender.sendMessage("USAGE: /auction SELL/BUY/LIST");
        		} else
        		{
        			//Min 1 Parameter
        			if(args[0].equalsIgnoreCase("sell"))
        			{
        				//VERKAUFE
        				if(args.length == 1)
                		{
        					sender.sendMessage("USAGE: /auction sell BLOCK AMOUNT PRICEPERBLOCK");
                		} else
                		{
                			
                		}
        			} else if(args[0].equalsIgnoreCase("buy"))
        			{
        				//KAUFE
        				if(args.length == 1)
                		{
        					sender.sendMessage("USAGE: /auction BUY BLOCK AMOUNT MAXPRICE");
                		} else
                		{
                			
                		}
        			} else if(args[0].equalsIgnoreCase("list"))
        			{
        				//ZEIGE
        				if(args.length == 1)
                		{
        					sender.sendMessage("USAGE: /auction list BLOCK");
                		} else
                		{
                			
                		}
        			}
        		}
        	}
        }
		
		return true;
    }
}
