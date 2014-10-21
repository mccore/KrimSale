package de.bdh.ks;

import java.sql.Timestamp;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class KSOffer 
{
	ItemStack i;
	UUID ply,to;
	int amount = 0;
	int price = 0;
	public int type = 0;
	public boolean signit = false;
	
	Timestamp time = null;
	int admin = 0;
	public KSOffer(ItemStack i,UUID ply, int priceEach)
	{
		this.reg(i, ply, priceEach);
	}
	
	public KSOffer(ItemStack i,UUID ply,int priceEach, int am)
	{
		this.reg(i, ply, priceEach);
		this.setAmount(am);
	}
	
	//Vergangenheitseintrag
	public KSOffer(ItemStack i,UUID ply, UUID to, int priceEach, int am, Timestamp time)
	{
		this.reg(i, ply, priceEach);
		this.setAmount(am);
		this.time = time;
		this.to = to;
	}
	
	public int getFee()
	{
		if(Bukkit.getPlayer(this.ply).hasPermission("ks.nofee"))
			return 0;
		
		return new Double(this.getFullPrice()*1.0 / 100 * configManager.fee).intValue();
	}
	
	public boolean payFee()
	{
		if(configManager.fee > 0)
		{
			try
			{
				if(Bukkit.getPlayer(this.ply).hasPermission("ks.nofee"))
					return true;
			} catch(Exception e) {}
			
			int fee = this.getFee();
			return Main.econ.withdrawPlayer(Bukkit.getPlayer(this.ply), fee).transactionSuccess();
		}
		else
			return true;

	}
	
	public void setAmount(int i)
	{
		this.amount = i;
		this.i.setAmount(i);
	}
	
	public boolean isDone()
	{
		if(this.time == null) return false; else return true;
	}
	
	public void reg(ItemStack i,UUID ply, int priceEach)
	{
		this.ply = ply;
		this.i = i.clone();
		this.price = priceEach;
	}
	
	public int getAmount()
	{
		if(this.amount == 0)
			return i.getAmount();
		else
			return this.amount;
	}
	
	public ItemStack getItemStack()
	{
		return this.i;
	}
	
	public UUID getPlayer()
	{
		if(this.ply != null)
			return this.ply;
		else
			return UUID.fromString("00000000-0000-0000-0000-000000000000");
	}
	
	public int getFullPrice()
	{
		return this.price * this.getAmount();
	}
	
	public int getPrice()
	{
		return this.price;
	}
}
