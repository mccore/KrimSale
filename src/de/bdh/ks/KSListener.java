package de.bdh.ks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class KSListener implements Listener 
{
	public Main m;
	public KSListener(Main main) 
	{
		this.m = main;
	}

	/* für später reserviert
	@EventHandler
	public void onMapLoad(MapInitializeEvent event)
	{
		List<MapRenderer> l = event.getMap().getRenderers();
		for (MapRenderer r: l)
    	{
			event.getMap().removeRenderer(r);
    	}
		
		event.getMap().addRenderer(this.m.getMap(event.getMap().getId()));
	}*/
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event)
    {
		
    }
	
}
