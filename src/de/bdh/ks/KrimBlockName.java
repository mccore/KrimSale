package de.bdh.ks;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
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
	
	public static String getNameById(int type, int typeid)
	{
		String strg = "";
		strg += type;
		if(typeid > 0)
			strg += ":"+typeid;
		
		return getNameById(strg);
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
	
	public static ItemStack parseName(String nam)
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
}
