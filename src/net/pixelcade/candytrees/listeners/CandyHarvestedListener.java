package net.pixelcade.candytrees.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.pixelcade.candytrees.events.CandyHarvestedEvent;

public class CandyHarvestedListener implements Listener {
	
	@EventHandler
	public void candyHarvestedListener(CandyHarvestedEvent event) {
		event.getCandy().getLocation().getWorld().dropItemNaturally(event.getCandy().getLocation(), event.getDrop().getIs());
		event.getCandy().setCooldownCounter(event.getCandy().getCooldownTime());
		event.getCandy().setMineable(false);
	}

}
