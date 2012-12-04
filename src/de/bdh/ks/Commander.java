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
	
	public int parseName(String nam)
	{
		int i = 0;
		try
		{
			i = Integer.parseInt(nam);
		} catch(Exception e)
		{
			i = KrimBlockName.getIdByName(nam);
		}
		return i;
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
        				//TODO
        			} else if(args[0].equalsIgnoreCase("add"))
        			{
        				//Füge unendliches Angebot ein (admin flag + amount = 10000)
        				//TODO
        			} else if(args[0].equalsIgnoreCase("remove"))
        			{
        				//Entferne unendliches Angebot
        				//TODO
        			}
        		}
			} else
			{
				sender.sendMessage("You don't have permissions for that");
			}
			return true;
    	} else
		if(sender instanceof Player)
        {
        	if(command.getName().equals("auction"))
        	{
        		if(args.length == 0)
        		{
        			sender.sendMessage("USAGE: /auction SELL/BUY/LIST/COLLECT");
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
        				if(args.length < 3)
                		{
        					sender.sendMessage("USAGE: /auction sell BLOCK AMOUNT PRICEPERBLOCK");
                		} else
                		{
                			if(args.length == 3)
                			{
                				//Block == IteminHand
                				//TODO
                			} else if(args.length == 4)
                			{
                				//Normale Usage
                				//TODO
                			}
                		}
        			} else if(args[0].equalsIgnoreCase("buy"))
        			{
        				if(!sender.hasPermission("ks.buy"))
        				{
        					sender.sendMessage("You're not allowed to buy stuff");
        					return true;
        				}
        				
        				//KAUFE
        				if(args.length < 3)
                		{
        					sender.sendMessage("USAGE: /auction BUY BLOCK AMOUNT MAXPRICE");
                		} else
                		{
                			if(args.length == 3)
                			{
                				//Block == IteminHand
                				//TODO
                			} else if(args.length == 4)
                			{
                				//Normale Usage
                				//TODO
                			}
                		}
        			} else if(args[0].equalsIgnoreCase("list"))
        			{
        				if(!sender.hasPermission("ks.list"))
        				{
        					sender.sendMessage("You're not allowed to list stuff");
        					return true;
        				}
        				
        				//ZEIGE
        				if(args.length < 2)
                		{
        					sender.sendMessage("USAGE: /auction list BLOCK");
                		} else
                		{
                			int id = this.parseName(args[1]);
                			if(id != -1)
                				Main.helper.sendInfos((Player)sender, id);
                			else
                				sender.sendMessage("ERROR: BlockID '"+args[1]+"' invalid");	
                		}
        			} else if(args[0].equalsIgnoreCase("collect"))
        			{
        				if(!sender.hasPermission("ks.buy"))
        				{
        					sender.sendMessage("You're not allowed to buy stuff");
        					return true;
        				} else
        				{
        					int am = Main.helper.hasDelivery((Player)sender);
        					if(am == 0)
        						sender.sendMessage("There is nothing for delivery");
        					else
        						sender.sendMessage("You've '"+am+"' items waiting for delivery");
        					
        					if(am > 0)
        					{
	        					if(this.enderChestClose(sender))
	        					{
	        						Main.helper.getDelivery((Player)sender);
	        					} else
	        					{
	        						sender.sendMessage("You've to go to the auction house to collect your items");
	        					}
        					}
        				}
        			}
        		}
        	}
        }
		
		return true;
    }
}
