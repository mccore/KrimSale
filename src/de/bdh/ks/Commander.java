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
        				if(!sender.hasPermission("ks.sell"))
        				{
        					sender.sendMessage("You're not allowed to sell stuff");
        					return true;
        				}
        				
        				//VERKAUFE
        				if(args.length == 1)
                		{
        					sender.sendMessage("USAGE: /auction sell BLOCK AMOUNT PRICEPERBLOCK");
                		} else
                		{
                			
                		}
        			} else if(args[0].equalsIgnoreCase("buy"))
        			{
        				if(!sender.hasPermission("ks.buy"))
        				{
        					sender.sendMessage("You're not allowed to buy stuff");
        					return true;
        				}
        				
        				//KAUFE
        				if(args.length == 1)
                		{
        					sender.sendMessage("USAGE: /auction BUY BLOCK AMOUNT MAXPRICE");
                		} else
                		{
                			
                		}
        			} else if(args[0].equalsIgnoreCase("list"))
        			{
        				if(!sender.hasPermission("ks.list"))
        				{
        					sender.sendMessage("You're not allowed to list stuff");
        					return true;
        				}
        				
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
