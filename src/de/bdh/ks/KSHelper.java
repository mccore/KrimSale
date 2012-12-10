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


public class KSHelper 
{
	Main m;
	public KSHelper(Main main) {
		this.m = main;
	}
	
	public int getDelivery(Player p)
	{
		//Bearbeite die Delivery Tabelle ab
		//Und gib dem Spieler Geld/Items
		//returns menge an Deliveries übrig. Prüfen ob Inventar voll ist ausserdem noch sinnvoll bevor das item rausgedrückt wird
		
		//wenn money kleiner 0 => nicht durchführen
		//TODO
		return 0;
	}
	
	//Hat er was im AH?
	public int hasDelivery(Player p)
	{
		//TODO
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
			System.out.println((new StringBuilder()).append("[KB] unable to get prices: ").append(e).toString());
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
			System.out.println((new StringBuilder()).append("[KB] unable to get lowest price: ").append(e).toString());
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
			System.out.println((new StringBuilder()).append("[KB] unable to get max amount: ").append(e).toString());
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
			System.out.println((new StringBuilder()).append("[KB] unable to enlist Item: ").append(e).toString());
			return false;
		}
		
		return true;
		
	}
	
	//Kaufe Item, gibt zurück wieviele er wirklich gekauft hat
	public int buyItem(ItemStack i, int maxPrice)
	{
		//TODO
		return 0;
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
		//TODO
		if(money < 1)
			return false;
		
		this.pokeDelivery(p);
		return false;
	}
	
	//Setze in Abarbeitungstabelle - Items
	public boolean addDelivery(String p, ItemStack i)
	{
		//TODO
		this.pokeDelivery(p);
		return false;
	}
	
	public boolean canbeSold(ItemStack i)
	{
		if(i != null)
		{
			//Keine benutzten Gegenstände verkaufbar
			if((i.getType().getMaxDurability() != 0) && i.getType().getMaxDurability() * 0.1f < i.getDurability())
				return false;
			
			//Keine Verzauberten Gegenstände verkaufbar
			if(i.getEnchantments().size() > 0)
				return false;
			
			if(i.getType() == Material.AIR)
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
						if(stack.getAmount() <= amount)
						{
							taken += stack.getAmount();
							amount -= stack.getAmount();
							p.getInventory().remove(stack);
						} else
						{
							stack.setAmount(stack.getAmount() - amount);
							taken += amount;
							amount = 0;
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
