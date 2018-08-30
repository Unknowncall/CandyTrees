package net.pixelcade.candytrees;

import org.bukkit.plugin.java.JavaPlugin;

import net.pixelcade.candytrees.executors.CandyAdminExecutor;
import net.pixelcade.candytrees.listeners.BlockBreakListener;
import net.pixelcade.candytrees.listeners.CandyHarvestedListener;
import net.pixelcade.candytrees.listeners.PlayerInteractListener;
import net.pixelcade.candytrees.objects.Candy;


public class CandyDriver extends JavaPlugin {
	
	private CandyManager candyManager;

	public void onEnable() {
		this.saveDefaultConfig();
		this.candyManager = new CandyManager(this);
		this.getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
		this.getServer().getPluginManager().registerEvents(new CandyHarvestedListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
		this.getCommand("candyadmin").setExecutor(new CandyAdminExecutor(this));
	}
	
	public void onDisable() {
		for (Candy candy : this.candyManager.getCandyLocations()) {
			candy.getStand().remove();
		}
	}

	public CandyManager getCandyManager() {
		return candyManager;
	}

	public void setCandyManager(CandyManager candyManager) {
		this.candyManager = candyManager;
	}

}
