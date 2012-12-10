package de.bdh.ks;

import org.bukkit.inventory.ItemStack;

public class KBOffer 
{
	ItemStack i;
	String ply;
	int amount = 0;
	int price = 0;
	public KBOffer(ItemStack i,String ply, int priceEach)
	{
		this.ply = ply;
		this.i = i.clone();
		this.price = priceEach;
	}
	
	public KBOffer(ItemStack i,String ply,int priceEach, int am)
	{
		this.ply = ply;
		this.i = i.clone();
		this.amount = am;
		this.i.setAmount(am);
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
	
	public String getPlayer()
	{
		return this.ply;
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
