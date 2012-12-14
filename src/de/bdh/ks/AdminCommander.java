package de.bdh.ks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        				//Füge unendliches Angebot ein (admin flag)
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
        				int page = 0;
    					int ppg = 5;
    					if(args.length > 1)
    					{
    						try
            				{
            					page = Integer.parseInt(args[1]);
            				}
            				catch(Exception e)
            				{
            					sender.sendMessage("Page must be Numeric");
            					return true;
            				}
    					}
    					
    					sender.sendMessage("ListAdminOffer Page: "+(page+1));
        				try
        				{
        					
        		    		Connection conn = Main.Database.getConnection();
        		        	PreparedStatement ps;
        		        	StringBuilder b = (new StringBuilder()).append("SELECT type,subtype,id,price FROM ").append(configManager.SQLTable).append("_offer WHERE admin = 1 ORDER BY price ASC, admin ASC LIMIT ?,?");
        		    		ps = conn.prepareStatement(b.toString());
        		    		ps.setInt(1, page * ppg);
        		    		ps.setInt(2,ppg);
        		    		boolean found = false;
        		    		ResultSet rs = ps.executeQuery();
        		    		while(rs.next())
        		    		{
        		    			found = true;
        		    			sender.sendMessage("ID: "+rs.getInt("id")+" BLOCK: "+KrimBlockName.getNameById(rs.getInt("type"),rs.getInt("subtype"))+" PRICE:"+rs.getInt("price"));
        		    			//TODO
        		    		}
        		    		if(!found)
        		    			sender.sendMessage("Nothing found");
        		    		
        		    		if(ps != null)
        						ps.close();
        		    		if(rs != null)
        						rs.close();
        				} catch (SQLException e)
        				{
        					System.out.println((new StringBuilder()).append("[KS] unable to list admin buy: ").append(e).toString());
        				}
        			}
        			else if(args[0].equalsIgnoreCase("listbuy"))
        			{
        				//Zeige alle Adminkäufe
        				int page = 0;
    					int ppg = 5;
    					if(args.length > 1)
    					{
    						try
            				{
            					page = Integer.parseInt(args[1]);
            				}
            				catch(Exception e)
            				{
            					sender.sendMessage("Page must be Numeric");
            					return true;
            				}
    					}
    					
    					sender.sendMessage("ListAdminBuy Page: "+(page+1));
        				try
        				{
        					
        		    		Connection conn = Main.Database.getConnection();
        		        	PreparedStatement ps;
        		        	StringBuilder b = (new StringBuilder()).append("SELECT type,subtype,id,price FROM ").append(configManager.SQLTable).append("_request WHERE admin = 1 ORDER BY price ASC, admin ASC LIMIT ?,?");
        		    		ps = conn.prepareStatement(b.toString());
        		    		ps.setInt(1, page * ppg);
        		    		ps.setInt(2,ppg);
        		    		boolean found = false;
        		    		ResultSet rs = ps.executeQuery();
        		    		while(rs.next())
        		    		{
        		    			found = true;
        		    			sender.sendMessage("ID: "+rs.getInt("id")+" BLOCK: "+KrimBlockName.getNameById(rs.getInt("type"),rs.getInt("subtype"))+" PRICE:"+rs.getInt("price"));
        		    			//TODO
        		    		}
        		    		if(!found)
        		    			sender.sendMessage("Nothing found");
        		    		
        		    		if(ps != null)
        						ps.close();
        		    		if(rs != null)
        						rs.close();
        				} catch (SQLException e)
        				{
        					System.out.println((new StringBuilder()).append("[KS] unable to list admin buy: ").append(e).toString());
        				}
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
