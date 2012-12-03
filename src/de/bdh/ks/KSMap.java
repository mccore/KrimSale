package de.bdh.ks;


//PROOF OF CONCEPT CODE - eventuell für spätere version

import org.bukkit.craftbukkit.map.CraftMapView;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapCursorCollection;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MapView.Scale;


public class KSMap extends MapRenderer
{
	int id;
	Main m;
	public KSMap(Main m,int id)
	{
		this.id = id;
		this.m = m;
	}
	
    public void initialize(CraftMapView mv)
    {
    }
    
    public void render(MapView map, MapCanvas canvas, Player player)
    {
    	map.setCenterX(0);
    	map.setCenterZ(0);
    	map.setScale(Scale.NORMAL);
    	MapCursorCollection cursors;
    	for(cursors = canvas.getCursors(); cursors.size() > 0; cursors.removeCursor(cursors.getCursor(0)));
    	
    	//int pos = 10;
    	//canvas.drawText(0,pos, MinecraftFont.Font,(new StringBuilder()).append(this.id).toString());
    	//pos += 10;
    	
    }
}
