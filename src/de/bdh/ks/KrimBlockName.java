package de.bdh.ks;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.inventory.ItemStack;


public class KrimBlockName 
{
	public static HashMap<String,String> names = new HashMap<String,String>();
	
	public KrimBlockName()
	{
		//INIT VALUES
		//TODO
		names.put("air", "0");
		names.put("stone", "1");
	}
	
	
	public static String searchName(String name)
	{
		name = name.toLowerCase();
		for (Map.Entry<String,String> entry : names.entrySet())
    	{
			if(entry.getKey().contains(name))
				return entry.getKey();
    	}
		return "null";
	}
	
	public static String getIdByName(String name)
	{
		name = name.trim().toLowerCase();
		if(names.get(name) != null)
			return names.get(name);
		else
			return "-1";
	}
	
	public static String getNameById(String id)
	{
		for (Map.Entry<String,String> entry : names.entrySet())
    	{
			if(entry.getValue().equalsIgnoreCase(id))
				return entry.getKey();
    	}
		return ""+id;
	}
	
	public static String getNameByItemStack(ItemStack i)
	{
		String str = "";
		str += i.getTypeId();
		if(i.getDurability() > 0 && i.getType().getMaxDurability() == 0)
			str += ":"+i.getDurability();
		return getNameById(str);
	}
}
