package de.bdh.ks;

import org.bukkit.inventory.ItemStack;

public class KSOffer 
{
	ItemStack i;
	String ply,to;
	int amount = 0;
	int price = 0;
	int time = 0;
	public KSOffer(ItemStack i,String ply, int priceEach)
	{
		this.reg(i, ply, priceEach);
	}
	
	public KSOffer(ItemStack i,String ply,int priceEach, int am)
	{
		this.reg(i, ply, priceEach);
		this.amount = am;
		this.i.setAmount(am);
	}
	
	//Vergangenheitseintrag
	public KSOffer(ItemStack i,String ply, String to, int priceEach, int am, int time)
	{
		this.reg(i, ply, priceEach);
		this.amount = am;
		this.i.setAmount(am);
		this.time = time;
		this.to = to;
	}
	
	public boolean isDone()
	{
		if(this.time == 0) return false; else return true;
	}
	
	public void reg(ItemStack i,String ply, int priceEach)
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
