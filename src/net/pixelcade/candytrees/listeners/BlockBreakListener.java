package net.pixelcade.candytrees.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import net.md_5.bungee.api.ChatColor;
import net.pixelcade.candytrees.CandyDriver;
import net.pixelcade.candytrees.CandyManager;
import net.pixelcade.candytrees.events.CandyHarvestedEvent;
import net.pixelcade.candytrees.objects.Candy;

public class BlockBreakListener implements Listener {

	private CandyDriver plugin;
	
	public BlockBreakListener(CandyDriver plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void blockBreakListener(BlockBreakEvent event) {
		CandyManager manager = this.plugin.getCandyManager();
		if (manager.getCandyFromLocation(event.getBlock().getLocation()) == null) {
			return;
		}
		
		Candy candy = manager.getCandyFromLocation(event.getBlock().getLocation());
		if (candy.isMineable()) {
			candy.loadHologram();
			Bukkit.getPluginManager().callEvent(new CandyHarvestedEvent(event.getPlayer(), candy, manager));
			event.setCancelled(true);
		} else {
			event.getPlayer().sendMessage(ChatColor.RED + "This candy is not ready to be harvested yet.");
			event.setCancelled(true);
		}
		

	}

}
