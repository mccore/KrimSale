package de.bdh.ks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;


public class KSHelper 
{
	Main m;
	public KSHelper(Main main) {
		this.m = main;
	}
	
	public int giveItem(Player p, ItemStack is)
	{
		PlayerInventory inv = p.getInventory();
		int blockType = is.getTypeId();
		int subType = is.getDurability();
		int amount = is.getAmount();

		int addedAmount = 0;
		int stackSize = Material.getMaterial(blockType).getMaxStackSize();
		int freeslot = -1;
		while(amount - addedAmount > 0)
		{
			freeslot = -1;
			for (int i =  0; i < 36; i++) 
	    	{
				ItemStack tmp = inv.getItem(i);
	    		if(tmp != null)
	    		{
	    			if(tmp.getEnchantments().size() == 0 && tmp.getTypeId() == blockType && tmp.getDurability() == subType && tmp.getAmount() < tmp.getMaxStackSize())
	    			{
	    				freeslot = i;
	    			}
	    		}
	    	}	 
			
			if (freeslot == -1)
			{
				freeslot = inv.firstEmpty();
				if (freeslot == -1)
				{
					break;
				}
				else
				{
					if (amount - addedAmount >= stackSize)
					{
						inv.setItem(freeslot, new ItemStack(blockType, stackSize, (short) subType));
						addedAmount += stackSize;
					}
					else
					{
						inv.setItem(freeslot, new ItemStack(blockType, amount - addedAmount, (short) subType));
						addedAmount += amount - addedAmount;
					}
				}
			}
			else
			{
				ItemStack stack = inv.getItem(freeslot);
				int diff = Math.min(stackSize - stack.getAmount(), amount - addedAmount);
				addedAmount += diff;
				stack.setAmount(stack.getAmount() + diff);
			}
		}
		return addedAmount;
	}
	
	public int getDelivery(Player p)
	{
		int ret = 0;
		try
		{
			ItemStack i = null;
    		Connection conn = Main.Database.getConnection();
        	PreparedStatement ps,ps2 = null;
        	StringBuilder b = (new StringBuilder()).append("SELECT id,money,type,subtype,amount FROM ").append(configManager.SQLTable).append("_deliver WHERE player = ?");
    		ps = conn.prepareStatement(b.toString());
    		ps.setString(1, p.getName());
    		ResultSet rs = ps.executeQuery();
    		boolean remove = false;
    		boolean fisent = false;
    		while(rs.next())
    		{
    			remove = false;
    			if(rs.getInt("money") > 0)
    			{
    				System.out.println("[KS] Delivering to User "+p.getName()+ " Money: "+rs.getInt("money"));
    				Main.econ.withdrawPlayer(p.getName(), rs.getInt("money"));
    				++ret;
    				remove = true;
    			} else if(rs.getInt("type") > 0 && rs.getInt("amount") > 0)
    			{
    				i = new ItemStack(rs.getInt("type"));
    				if(rs.getInt("subtype") != 0)
    					i.setDurability((short) rs.getInt("subtype"));
    				i.setAmount(rs.getInt("amount"));
    				
    				int given = this.giveItem(p, i);
    				if(given == rs.getInt("amount"))
    				{
        				System.out.println("[KS] Delivering to User "+p.getName()+ " Item: "+rs.getInt("type")+":"+rs.getInt("subtype")+" Amount: "+rs.getInt("amount"));
    					remove = true;
    					++ret;
    				} else 
    				{
    					//Inventar voll - müssen die Delivery anpassen
        				if(given > 0)
        				{
        					//ein Teil wurde gegeben
            				System.out.println("[KS] Part-Delivering to User "+p.getName()+ " Item: "+rs.getInt("type")+":"+rs.getInt("subtype")+" Amount: "+given+"/"+rs.getInt("amount"));
            				b = (new StringBuilder()).append("UPDATE ").append(configManager.SQLTable).append("_deliver SET amount = ? WHERE id = ? LIMIT 1");
            				ps2 = conn.prepareStatement(b.toString());
            				ps2.setInt(1, rs.getInt("amount") - given);
            				ps2.setInt(2, rs.getInt("id"));
            				ps2.executeUpdate();
        				} 
        				
        				if(fisent == false)
    					{
    						fisent = true;
    						p.sendMessage("Your inventory is full");
    					}
    				}
    			} else remove = true;
    			
    			if(remove == true)
    			{
    				b = (new StringBuilder()).append("DELETE FROM ").append(configManager.SQLTable).append("_deliver WHERE id = ? LIMIT 1");
    				ps2 = conn.prepareStatement(b.toString());
    				ps2.setInt(1, rs.getInt("id"));
    				ps2.executeUpdate();
    				
    			}
    		}
    		
    		if(ps2 != null)
				ps2.close();
    		
    		if(ps != null)
				ps.close();
			if(rs != null)
				rs.close();
			

		} catch (SQLException e)
		{
			System.out.println((new StringBuilder()).append("[KS] unable to get prices: ").append(e).toString());
		}
		return ret;
	}
	
	//Hat er was im AH?
	public int hasDelivery(Player p)
	{
		try
		{
    		Connection conn = Main.Database.getConnection();
        	PreparedStatement ps;
        	StringBuilder b = (new StringBuilder()).append("SELECT COUNT(*) as c FROM ").append(configManager.SQLTable).append("_deliver WHERE player = ?");
    		ps = conn.prepareStatement(b.toString());
    		ps.setString(1, p.getName());
    		ResultSet rs = ps.executeQuery();
    		int am = 0;	
    		while(rs.next())
    		{
    			am = rs.getInt("c");
    		}
    		
    		if(ps != null)
				ps.close();
			if(rs != null)
				rs.close();
			
			return am;

		} catch (SQLException e)
		{
			System.out.println((new StringBuilder()).append("[KS] unable to get prices: ").append(e).toString());
		}
		return 0;
	}
	
	public Map<Integer,KSOffer> getOffersFromPlayer(Player p)
	{
		return this.getOffersFromPlayer(p, null);
	}
	
	public Map<Integer,KSOffer> getOffersFromPlayer(Player p, ItemStack i)
	{
		//TODO - gebe alle Angebote eines Spielers aus
		return new HashMap<Integer,KSOffer>();
	}
	
	public Map<Integer,KSOffer> getTransactionsFromPlayer(Player p)
	{
		return this.getTransactionsFromPlayer(p,null);
	}
	
	public Map<Integer,KSOffer> getTransactionsFromPlayer(Player p, ItemStack i)
	{
		//TODO - gebe alle Transaktionen eines Spielers aus
		return new HashMap<Integer,KSOffer>();
	}
	
	public void giveBack(KSOffer of)
	{
		try
		{
    		Player plx = Bukkit.getServer().getPlayerExact(of.ply);
    		plx.getInventory().addItem(of.getItemStack());
		} catch(Exception e)
		{	
		}
	
	}
	
	public Map<Integer,KSOffer> getPrices(ItemStack i, int rows)
	{
		if(canbeSold(i) == false)
			return null;
		
		HashMap<Integer,KSOffer> ret = new HashMap<Integer,KSOffer>();
		try
		{
			KSOffer k;
    		Connection conn = Main.Database.getConnection();
        	PreparedStatement ps;
        	StringBuilder b = (new StringBuilder()).append("SELECT SUM(amount) as m, price, player FROM ").append(configManager.SQLTable).append("_offer WHERE type = ? AND subtype = ? GROUP BY price ORDER BY price ASC limit 0,?");

        	String strg = b.toString();
    		ps = conn.prepareStatement(strg);
    		ps.setInt(1, i.getTypeId());
    		ps.setInt(2, i.getDurability());
    		ps.setInt(3, rows);
    		ResultSet rs = ps.executeQuery();
    			
    		while(rs.next())
    		{
    			k = new KSOffer(i,rs.getString("player"),rs.getInt("price"),rs.getInt("m"));
    			ret.put(rs.getInt("price"), k);
    		}
    		
    		if(ps != null)
				ps.close();
			if(rs != null)
				rs.close();

		} catch (SQLException e)
		{
			System.out.println((new StringBuilder()).append("[KS] unable to get prices: ").append(e).toString());
		}
		
		return ret;
	
	}
	
	//Hole Maximale Angebotsmenge für Material
	public int getMaxAmount(ItemStack i)
	{
		return this.getMaxAmount(i, -1);
	}
	
	public int getLowestPrice(ItemStack i)
	{
		if(canbeSold(i) == false)
			return -1;
		
		int ret = 0;
		try
		{
    		Connection conn = Main.Database.getConnection();
        	PreparedStatement ps;
        	StringBuilder b = (new StringBuilder()).append("SELECT price as pr FROM ").append(configManager.SQLTable).append("_offer WHERE type = ? AND subtype = ? ORDER BY price ASC limit 0,1");

        	String strg = b.toString();
    		ps = conn.prepareStatement(strg);
    		ps.setInt(1, i.getTypeId());
    		ps.setInt(2, i.getDurability());
    		ResultSet rs = ps.executeQuery();
    			
    		while(rs.next())
    		{
    			ret = rs.getInt("pr");
    		}
    		
    		if(ps != null)
				ps.close();
			if(rs != null)
				rs.close();

		} catch (SQLException e)
		{
			System.out.println((new StringBuilder()).append("[KS] unable to get lowest price: ").append(e).toString());
		}
		
		return ret;
	}
	
	//Hole maximale Angebotsmenge für Material mit max. Preis
	public int getMaxAmount(ItemStack i, int maxPrice)
	{
		if(canbeSold(i) == false)
			return -1;
		
		int ret = 0;
		try
		{
    		Connection conn = Main.Database.getConnection();
        	PreparedStatement ps;
        	StringBuilder b = (new StringBuilder()).append("SELECT SUM(amount) as am FROM ").append(configManager.SQLTable).append("_offer WHERE type = ? AND subtype = ?");
    		if(maxPrice != -1)
    			b.append(" AND price <= ? ");
        	String strg = b.toString();
    		ps = conn.prepareStatement(strg);
    		ps.setInt(1, i.getTypeId());
    		
    		ps.setInt(2, i.getDurability());
    		
    		if(maxPrice != -1)
    			ps.setInt(3, maxPrice);
    		
    		ResultSet rs = ps.executeQuery();
    			
    		while(rs.next())
    		{
    			ret = rs.getInt("am");
    		}
    		
    		if(ps != null)
				ps.close();
			if(rs != null)
				rs.close();

		} catch (SQLException e)
		{
			System.out.println((new StringBuilder()).append("[KS] unable to get max amount: ").append(e).toString());
		}
		
		return ret;
	}
	
	//Füge Item in das AH ein
	public boolean enlistItem(KSOffer of)
	{
		if(this.canbeSold(of.getItemStack()) == false)
			return false;
		try
		{
			
    		Connection conn = Main.Database.getConnection();
        	PreparedStatement ps;
        	StringBuilder b = (new StringBuilder()).append("INSERT INTO ").append(configManager.SQLTable).append("_offer (type,subtype,amount,price,player) VALUES (?,?,?,?,?)");
    		ps = conn.prepareStatement(b.toString());
    		ps.setInt(1, of.getItemStack().getTypeId());
    		ps.setInt(2,of.getItemStack().getDurability());
    		ps.setInt(3, of.getAmount());
    		ps.setInt(4, of.getPrice());
    		ps.setString(5, of.getPlayer());
    		ps.executeUpdate();
    		
    		if(ps != null)
				ps.close();

		} catch (SQLException e)
		{
			System.out.println((new StringBuilder()).append("[KS] unable to enlist Item: ").append(e).toString());
			return false;
		}
		
		return true;
	}
	
	public int buyItem(int id, int amount, String p)
	{
		
		try
		{
			int ret = 0;
			KSOffer ks = null;
    		Connection conn = Main.Database.getConnection();
        	PreparedStatement ps,ps2=null;
        	
        	StringBuilder b = (new StringBuilder()).append("SELECT amount,price,type,subtype,player,admin FROM ").append(configManager.SQLTable).append("_offer WHERE id = ? LIMIT 0,1");
        	ps = conn.prepareStatement(b.toString());
    		ps.setInt(1, id);
    		
    		boolean found = false;
    		ResultSet rs = ps.executeQuery();
			
    		while(rs.next())
    		{
    			found = true;
    			ItemStack i = null;
    			i = new ItemStack(rs.getInt("type"));
    			i.setDurability((short) rs.getInt("subtype"));
    			
    			
    			if(rs.getInt("amount") > amount)
    			{
    				ret = amount;
    				//Update angebot
    				b = (new StringBuilder()).append("UPDATE ").append(configManager.SQLTable).append("_offer SET amount = ? WHERE id = ? LIMIT 1");
    				ps2 = conn.prepareStatement(b.toString());
    				ps2.setInt(1, (rs.getInt("amount") - amount));
    				ps2.setInt(2,id);
    				ps2.executeUpdate();

    			} else
    			{
    				ret = rs.getInt("amount");
    				//Entferne angebot
    				b = (new StringBuilder()).append("DELETE FROM ").append(configManager.SQLTable).append("_offer WHERE id = ? LIMIT 1");
    				ps2 = conn.prepareStatement(b.toString());
    				ps2.setInt(1,id);
    				ps2.executeUpdate();
    			}

    			ks = new KSOffer(i,rs.getString("player"),rs.getInt("price"),ret);
    		}

    		
    		if(found == true && ks != null)
    		{
				b = (new StringBuilder()).append("INSERT INTO ").append(configManager.SQLTable).append("_transaction (type,subtype,fromplayer,toplayer,amount,price) VALUES (?,?,?,?,?,?)");
				ps2 = conn.prepareStatement(b.toString());
				ps2.setInt(1,ks.getItemStack().getTypeId());
				ps2.setInt(2,ks.getItemStack().getDurability());
				ps2.setString(3, ks.getPlayer());
				ps2.setString(4,p);
				ps2.setInt(5,ks.getAmount());
				ps2.setInt(6,ks.getFullPrice());
				ps2.executeUpdate();
				
				//Käufer
				this.addDelivery(p, ks.getItemStack());
				
				//Verkäufer
				this.addDelivery(ks.ply, ks.getFullPrice());
    		}
    		if(ps != null)
				ps.close();
    		if(rs != null)
				rs.close();
    		if(ps2 != null)
				ps2.close();
    		if(!found)
    			return -1;
    		
    		return ret;

		} catch (SQLException e)
		{
			System.out.println((new StringBuilder()).append("[KS] unable to buy IDItem: ").append(e).toString());
			return -1;
		}
	}
	
	//Kaufe Item, gibt zurück wieviele er wirklich gekauft hat
	public int buyItems(ItemStack i, int maxPrice, String p)
	{
		try
		{
			int amount = i.getAmount();
    		Connection conn = Main.Database.getConnection();
        	PreparedStatement ps;
        	StringBuilder b = (new StringBuilder()).append("SELECT amount,id FROM ").append(configManager.SQLTable).append("_offer WHERE type = ? AND subtype = ? AND price <= ? ORDER BY price ASC LIMIT 0,50");
    		ps = conn.prepareStatement(b.toString());
    		ps.setInt(1, i.getTypeId());
    		ps.setInt(2, i.getDurability());
    		ps.setInt(3, maxPrice);
    		
    		ResultSet rs = ps.executeQuery();
			int tmp = 0;
    		while(rs.next())
    		{
    			if(amount > 0)
    			{
    				System.out.println("[KS] Buying "+rs.getInt("id")+" - "+amount+"/"+rs.getInt("amount") + " by player "+p);
    				tmp = this.buyItem(rs.getInt("id"),amount,p);
    				if(tmp != -1)
    					amount -= tmp;
    			} 
    		}

    		if(ps != null)
				ps.close();
    		if(rs != null)
				rs.close();
    		
    		return i.getAmount() - amount;

		} catch (SQLException e)
		{
			System.out.println((new StringBuilder()).append("[KS] unable to buy Items: ").append(e).toString());
			return -1;
		}
		
		
	}
	
	//Entferne Deliverys, welche über 30 Tage zurückliegen
	public void pruneDelivery()
	{
		//TODO
	}
	
	//Entferne Auktionen, die über 30 Tage alt sind
	public void pruneAuctions()
	{
		//TODO
	}
	
	//Setze in Abarbeitsungstabelle - nur Geld
	public boolean addDelivery(String p, int money)
	{
		if(money < 1)
			return false;
		
		try
		{
    		Connection conn = Main.Database.getConnection();
        	PreparedStatement ps;
        	StringBuilder b = (new StringBuilder()).append("INSERT INTO ").append(configManager.SQLTable).append("_deliver (money,player) VALUES (?,?)");
    		ps = conn.prepareStatement(b.toString());
    		ps.setInt(1, money);
    		ps.setString(2, p);
    		ps.executeUpdate();
    		
    		if(ps != null)
				ps.close();
    		
    		this.pokeDelivery(p);
    		return true;

		} catch (SQLException e)
		{
			System.out.println((new StringBuilder()).append("[KS] unable to add money delivery: ").append(e).toString());
			return false;
		}
	}
	
	//Setze in Abarbeitungstabelle - Items
	public boolean addDelivery(String p, ItemStack i)
	{
		try
		{
    		Connection conn = Main.Database.getConnection();
        	PreparedStatement ps;
        	StringBuilder b = (new StringBuilder()).append("INSERT INTO ").append(configManager.SQLTable).append("_deliver (type,subtype,amount,player) VALUES (?,?,?,?)");
    		ps = conn.prepareStatement(b.toString());
    		ps.setInt(1, i.getTypeId());
    		ps.setInt(2, i.getDurability());
    		ps.setInt(3, i.getAmount());
    		ps.setString(4, p);
    		ps.executeUpdate();
    		
    		if(ps != null)
				ps.close();
    		
    		this.pokeDelivery(p);
    		return true;

		} catch (SQLException e)
		{
			System.out.println((new StringBuilder()).append("[KS] unable to add item delivery: ").append(e).toString());
			return false;
		}
	}
	
	public boolean canbeSold(ItemStack i)
	{
		if(i != null && i.getType() != null)
		{
			//Keine benutzten Gegenstände verkaufbar
			if(i.getType().getMaxDurability() != 0)
			{
				if(i.getType().getMaxDurability() * 0.1f < i.getDurability())
					return false;
			}
			//Keine Verzauberten Gegenstände verkaufbar
			if(i.getEnchantments() != null && i.getEnchantments().size() > 0)
				return false;
			
			if(i.getType() == Material.AIR)
				return false;
			
			if(i.getAmount() == 0)
				return false;
			
		} else return false;
		
		return true;
	}
	
	//Nimm Items vom Spieler
	public int removeItemsFromPlayer(Player p, ItemStack i, int amount)
	{
		int taken = 0;
		HashMap<Integer, ? extends ItemStack> stacks = p.getInventory().all(i.getTypeId());
		for (Entry<Integer, ? extends ItemStack> en : stacks.entrySet())
		{
			ItemStack stack = en.getValue();
			if(stack != null)
			{
				if(stack.getDurability() == i.getDurability())
				{
					if(canbeSold(stack))
					{
						if(amount > 0)
						{
							if(stack.getAmount() <= amount)
							{
								taken += stack.getAmount();
								amount -= stack.getAmount();
								p.getInventory().removeItem(stack);
							} else
							{
								stack.setAmount(stack.getAmount() - amount);
								taken += amount;
								amount = 0;
							}
						}
					}
				}
			}
			
			if(amount <= 0)
				return taken;
		}
		return taken;
	}
	
	//Sende die Infos über das Item wie zb aktueller Preis usw
	public void sendInfos(Player p, ItemStack i)
	{
		String name = KrimBlockName.getNameByItemStack(i);
		p.sendMessage("Details about: "+name);
		if(this.canbeSold(i) == false)
			p.sendMessage("Cannot be traded");
		else
		{
			int am = this.getMaxAmount(i);
			p.sendMessage("Amount for sale: "+am);
			
			if(am > 0)
			{
				for(Map.Entry<Integer,KSOffer> m : this.getPrices(i, 5).entrySet())
				{
					p.sendMessage("Offer: "+m.getValue().amount+" for "+m.getKey()+" "+Main.econ.currencyNamePlural()+" each");
				}
			}
		}
	}
	
	//Player hat nun etwas im Delivery
	public void pokeDelivery(String p)
	{
		try
		{
    		Player plx = Bukkit.getServer().getPlayerExact(p);
    		plx.sendMessage("You can collect some items in the auction house");
		} catch(Exception e)
		{
			
		}
	}
}
