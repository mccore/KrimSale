package de.bdh.ks;

import java.util.HashMap;
import java.util.Map;


public class KrimBlockName 
{
	public static HashMap<String,String> names = new HashMap<String,String>();
	
	public KrimBlockName()
	{
		//INIT VALUES
		names.put("air", "0");
		names.put("stone", "1");
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
}
