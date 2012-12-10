package de.bdh.ks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
	
	public ItemStack parseName(String nam)
	{
		ItemStack i = new ItemStack(Material.AIR);
		int n = 0;
		try
		{
			n = Integer.parseInt(nam);
			i.setTypeId(n);
		} catch(NumberFormatException e)
		{
			
		}
		
		if(n == 0)
		{
			try
			{
				if(nam.split(":").length > 1)
				{
					String[] sp = nam.split(":");
					//Block mit ID
					i.setTypeId(Integer.parseInt(sp[0]));
					i.setDurability((short) Integer.parseInt(sp[1]));
				} else
				{
					nam = KrimBlockName.getIdByName(nam);
					if(nam.split(":").length > 1)
					{
						String[] sp = nam.split(":");
						//Block mit ID
						i.setTypeId(Integer.parseInt(sp[0]));
						i.setDurability((short) Integer.parseInt(sp[1]));
					} else
					{
						i.setTypeId(Integer.parseInt(nam));
					}
				}
			} catch(Exception e)
			{
				return null;
			}
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
					sender.sendMessage("USAGE: /ks PLAYER/ADDOFFER/REMOVEOFFER/ADDBUY/REMOVEBUY");	
        		} else
        		{
        			if(args[0].equalsIgnoreCase("player"))
        			{
        				//Liste von allen Transaktionen des Spielers
        				//TODO
        			} else if(args[0].equalsIgnoreCase("addoffer"))
        			{
        				//Füge unendliches Angebot ein (admin flag + amount = 10000)
        				//TODO
        			} else if(args[0].equalsIgnoreCase("removeoffer"))
        			{
        				//Entferne unendliches Angebot
        				//TODO
        			} else if(args[0].equalsIgnoreCase("addbuy"))
        			{
        				//füge automatisches kaufen ein
        				//TODO
        			}
        			else if(args[0].equalsIgnoreCase("removebuy"))
        			{
        				//Entferne automatisches kaufen
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
        				
        				if(this.enderChestClose(sender) == false)
    					{
        					sender.sendMessage("You've to go to an auction house to sell items");	
    					}
        				
        				//VERKAUFE
        				if(args.length < 2)
                		{
        					sender.sendMessage("USAGE: /auction sell BLOCK AMOUNT PRICEPERBLOCK OR /auction sell PRICE for Item in Hand");
                		} else
                		{
                			if(args.length == 2)
                			{
                				//KBOffer of;
                				int price = 0;
                				try
                				{
                					price = Integer.parseInt(args[1]);
                				}
                				catch(Exception e)
                				{
                					sender.sendMessage("Price must be Numeric");
                					return true;
                				}
                				
                				ItemStack i = ((Player) sender).getItemInHand();
                				int am = Main.helper.takeItemsFromPlayer((Player) sender, i, i.getAmount());
                				System.out.println(am);
                				//Block == IteminHand
                				//TODO
                			} else if(args.length == 4)
                			{
                				int maxAm = 0;
                				int price = 0;
                				
                				try
                				{
                					maxAm = Integer.parseInt(args[2]);
                				}
                				catch(Exception e)
                				{
                					sender.sendMessage("Amount must be Numeric");
                					return true;
                				}
                				try
                				{
                					price = Integer.parseInt(args[3]);
                				}
                				catch(Exception e)
                				{
                					sender.sendMessage("Price must be Numeric");
                					return true;
                				}
                				ItemStack i = this.parseName(args[1]);
                				if(i == null)
                				{
                					sender.sendMessage("Block with Name/ID '"+args[1]+"' not found");
                					return true;
                				}
                				
                				int am = Main.helper.takeItemsFromPlayer((Player) sender, i, maxAm);
                				System.out.println(am);
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
                			ItemStack i = this.parseName(args[1]);
                			if(i != null)
                				Main.helper.sendInfos((Player)sender, i);
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
