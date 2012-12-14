package de.bdh.ks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommander implements CommandExecutor
{
	Main m;
	public AdminCommander(Main main) 
	{
		this.m = main;
	}

	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[])
	{
		if(command.getName().equals("ks"))
    	{
			if(((sender instanceof Player) && sender.hasPermission("ks.admin")) || !(sender instanceof Player))
			{
				if(args.length == 0)
        		{
					sender.sendMessage("USAGE: /ks PLAYER/ADDOFFER/REMOVEOFFER/LISTOFFER/ADDBUY/REMOVEBUY/LISTBUY/ABORT/CLEARDELIVERY");	
        		} else
        		{
        			if(args[0].equalsIgnoreCase("player"))
        			{
        				//Liste von allen Transaktionen des Spielers oder alle Verkäufe oder alle Deposits
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
        			else if(args[0].equalsIgnoreCase("listoffer"))
        			{
        				//Zeige alle Adminoffers
        				//TODO
        			}
        			else if(args[0].equalsIgnoreCase("listbuy"))
        			{
        				//Zeige alle Adminkäufe
        				//TODO
        			}
        			else if(args[0].equalsIgnoreCase("cleardelivery"))
        			{
        				//Entferne alle deliveries von einem user
        				if(args.length < 2)
                		{
        					sender.sendMessage("USAGE: /ks cleardelivery PLAYER");
                		} 
        				else
        				{
	        				try
	        				{
	        		    		Connection conn = Main.Database.getConnection();
	        		        	PreparedStatement ps;
	        		        	StringBuilder b = (new StringBuilder()).append("DELETE FROM ").append(configManager.SQLTable).append("_deliver WHERE player = ?");
	        		    		ps = conn.prepareStatement(b.toString());
	        		    		ps.setString(1, args[1]);
	        		    		int am = ps.executeUpdate();
	        		    		
	        		    		if(ps != null)
	        						ps.close();
	        					
	        		    		sender.sendMessage("Cleared "+am+" deliveries");
	        				} catch (SQLException e)
	        				{
	        					System.out.println((new StringBuilder()).append("[KS] unable to clear delivery: ").append(e).toString());
	        				}
        				}
        			}
        			else if(args[0].equalsIgnoreCase("abort"))
        			{
        				//Entferne auktion
        				if(args.length < 2)
                		{
        					sender.sendMessage("USAGE: /ks abort ID");
                		} else
                		{
                			int id = 0;
                			try
            				{
            					id = Integer.parseInt(args[1]);
            				}
            				catch(Exception e) 
            				{ 
            					sender.sendMessage("ID must be numeric");
            					return true;
            				}
                			if(Main.helper.removeAuction(id))
                			{
                				sender.sendMessage("This auction has been cancelled");
                			} else 
                				sender.sendMessage("This ID was invalid");

                		}
        			}
        		}
			} else
			{
				sender.sendMessage("You don't have permissions for that");
			}
			return true;
    	} 
		return true;
	}

}
