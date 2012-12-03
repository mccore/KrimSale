package de.bdh.ks;

import java.util.HashMap;
import java.util.Map;


public class KrimBlockName 
{
	public static HashMap<String,Integer> names = new HashMap<String,Integer>();
	
	public KrimBlockName()
	{
		//INIT VALUES
		names.put("air", 0);
		names.put("stone", 1);
	}
	
	public static int getIdByName(String name)
	{
		name = name.trim().toLowerCase();
		if(names.get(name) != null)
			return names.get(name);
		else
			return -1;
	}
	
	public static String getNameById(int id)
	{
		for (Map.Entry<String,Integer> entry : names.entrySet())
    	{
			if(entry.getValue() == id)
				return entry.getKey();
    	}
		return ""+id;
	}
}
