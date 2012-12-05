package de.bdh.ks;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
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
		Main.helper.hasDelivery(event.getPlayer());
    }
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event)
    {
		if(configManager.ender == 0)
			return;
		
		if(event.getClickedBlock() != null)
		{
			if(event.getClickedBlock().getType() == Material.ENDER_CHEST)
			{
				if(event.getAction() == Action.LEFT_CLICK_BLOCK && event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getType() != Material.AIR)
				{
					//Zeige Infos über den Block
					Main.helper.sendInfos(event.getPlayer(), event.getPlayer().getItemInHand());
					
				}
				else if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
					Main.helper.getDelivery(event.getPlayer());
				
				event.setCancelled(true);
			}
		}
    }
	
}
