package de.bdh.ks;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class KSHelper 
{
	Main m;
	public KSHelper(Main main) {
		this.m = main;
	}
	
	public void getDeliver(Player p)
	{
		//Bearbeite die Delivery Tabelle ab
		//Und gib dem Spieler Geld/Items
	}
	
	public Map<Integer,Integer> getPrices(int material)
	{
		//Hole Liste von 
		return new HashMap<Integer,Integer>();
	}
	
	//Hole Maximale Angebotsmenge für Material
	public int getMaxAmount(int material)
	{
		return this.getMaxAmount(material, 999999999);
	}
	
	//Hole maximale Angebotsmenge für Material mit max. Preis
	public int getMaxAmount(int material, int maxPrice)
	{
		return 0;
	}
	
	//Füge Item in das AH ein
	public boolean enlistItem(int material, int amount)
	{
		return false;
	}
	
	//Kaufe Item
	public boolean buyItem(int material, int amount, int maxPrice)
	{
		return false;
	}
	
	//Entferne Deliverys, welche über 30 Tage zurückliegen
	public void pruneDelivery()
	{
		
	}
	
	//Entferne Auktionen, die über 30 Tage alt sind
	public void pruneAuctions()
	{
		
	}
	
	//Setze in Abarbeitsungstabelle
	public boolean addDelivery(Player p, int money)
	{
		return false;
	}
	
	//Setze in Abarbeitungstabelle
	public boolean addDelivery(Player p, int typeid, int amount)
	{
		return false;
	}
}
