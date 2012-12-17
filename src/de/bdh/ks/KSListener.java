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

	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event)
    {
		Main.helper.hasDelivery(event.getPlayer()); 
		Main.lng.msg(event.getPlayer(), "welcome");
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
		if(configManager.ender == 0)  
			return;  
		
		if(event.getClickedBlock() != null) 
		{  
	 		if(event.getClickedBlock().getTypeId() == configManager.interactBlock)  
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
