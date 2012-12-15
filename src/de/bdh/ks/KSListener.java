package de.bdh.ks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;


public class KSListener implements Listener 
{
	public Main m;
	public KSListener(Main main) 
	{
		this.m = main;
	}

	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event)
    {
    }
	
	/*
	//Idee: Verkaufe automatisch alle Items, die man in eine Kiste legt
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event)
	{
		if(event.getPlayer() instanceof Player)
		{
			//Player p = (Player) event.getPlayer();
			if(event.getInventory().getHolder() instanceof Chest)
			{
				for(ItemStack i : event.getInventory().getContents())
				{
					if(i != null)
					{
						//Items aus Truhe werden ausgegeben
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent event)
	{
		if(event.getPlayer() instanceof Player)
		{
			
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{	
		if(event.getWhoClicked() instanceof Player)
		{
			
		}
	}*/
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event)
    {

    }
	
}
