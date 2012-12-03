package de.bdh.ks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class Commander implements CommandExecutor {
	
	Main plugin;
	public Commander(Main plugin)
	{
		this.plugin = plugin;
	}

	public boolean enderChestClose(CommandSender s)
	{
		if(configManager.ender == 0)
			return true;
		
		int rad = 5;
		Block temp;
		if(s instanceof Player)
		{
			Player p = (Player)s;
			Block b = p.getLocation().getBlock();
			for(int i$ = (rad * -1); i$ < rad; i$++)
	        {
	        	for(int j$ = (rad * -1); j$ < rad; j$++)
	            {
	        		for(int k$ = (rad * -1); k$ < rad; k$++)
	        		{
	        			temp = b.getRelative(i$, j$, k$);
	        			if(temp.getType() == Material.ENDER_CHEST)
	        				return true;
	        		} 
	            }
	        }
		}
		return false;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[])
    {
		//Admin Commands
		if(command.getName().equals("ks"))
    	{
			if(((sender instanceof Player) && sender.hasPermission("ks.admin")) || !(sender instanceof Player))
			{
				if(args.length == 0)
        		{
					sender.sendMessage("USAGE: /ks PLAYER/ADD/REMOVE");	
        		} else
        		{
        			if(args[0].equalsIgnoreCase("player"))
        			{
        				//Liste von allen Transaktionen des Spielers
        			} else if(args[0].equalsIgnoreCase("add"))
        			{
        				//Füge unendliches Angebot ein (admin flag + amount = 10000)
        			} else if(args[0].equalsIgnoreCase("remove"))
        			{
        				//Entferne unendliches Angebot
        			}
        		}
			}
			
    	} else
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
