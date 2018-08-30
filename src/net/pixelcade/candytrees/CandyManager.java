package net.pixelcade.candytrees;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.pixelcade.candytrees.objects.Candy;
import net.pixelcade.candytrees.objects.CandyItem;
import net.pixelcade.candytrees.util.ItemStackSerializer;
import net.pixelcade.candytrees.util.LocationSerializer;

public class CandyManager {
	
	private int totalChance;
	private ArrayList<Candy> candyLocations;
	public ArrayList<CandyItem> candies;
	private CandyDriver plugin;
	
	public CandyManager(CandyDriver plugin) {
		this.plugin = plugin;
		this.candies = new ArrayList<CandyItem>();
		this.candyLocations = new ArrayList<Candy>();
		
		for (String s : plugin.getConfig().getConfigurationSection("candies").getKeys(false)) {
			this.candies.add(this.getCandy(plugin, s));
		}
		
		if (plugin.getConfig().getConfigurationSection("candy_locations") != null) {
			for (String s : plugin.getConfig().getConfigurationSection("candy_locations").getKeys(false)) {
				this.candyLocations.add(this.getCandyLocation(plugin, s));
			}
		}
		
		new BukkitRunnable() {
			
			public void run() {
				runLoop();
			}
			
		}.runTaskTimer(plugin, 0, 1);
		
	}
	
	private int i = 0;

	public void runLoop() {
		if (i >= 20000) {
			i = 0;
		}
		i++;
		
		for (Candy candy : this.candyLocations) {
			if (candy.getCooldownCounter() >= 1 && (!candy.isMineable())) {
				candy.setCooldownCounter(candy.getCooldownCounter() - 1);
				if (i % 20 == 0) {
					candy.loadHologram();
				}
			}
			if (candy.getCooldownCounter() <= 0) {
				candy.setMineable(true);
			}
		}
	}
	
	public CandyItem getCandy(CandyDriver plugin, String name) {
		ItemStack is = ItemStackSerializer.deserialize(plugin.getConfig().getString("candies." + name + ".item_stack"));
		ArrayList<PotionEffectType> effects = new ArrayList<PotionEffectType>();
		for (String s : plugin.getConfig().getStringList("candies." + name + ".effects")) {
			try {
				effects.add(PotionEffectType.getByName(s));
			} catch (Exception e) {
				plugin.getLogger().info("Error finding PotionEffectType named: " + s + ". Use: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/potion/PotionEffectType.html");
			}
		}
		int chance = plugin.getConfig().getInt("candies." + name + ".chance");
		this.totalChance = this.totalChance + chance;
		return new CandyItem(is, effects, chance);
	}
	
	public Candy getCandyFromLocation(Location location) {
		for (Candy candy : this.candyLocations) {
			if (candy.getLocation().equals(location)) {
				return candy;
			}
		}
		return null;
	}
	
	public void addCandy(Location location, int cooldown) {
		this.plugin.getConfig().set("candy_locations." + this.candyLocations.size() + 1 + ".location", LocationSerializer.serializeBlockLocation(location));
		this.plugin.getConfig().set("candy_locations." + this.candyLocations.size() + 1 + ".cooldown", cooldown);
		this.plugin.saveConfig();
		this.candyLocations.add(new Candy(location, cooldown, 0, true));
	}
	
	public CandyItem getCandyFromItemStack(ItemStack is) {
		for (CandyItem candy : this.candies) {
			if (candy.getIs().isSimilar(is)) {
				return candy;
			}
		}
		return null;
	}

	
	private Candy getCandyLocation(CandyDriver plugin, String name) {
		Location loc = LocationSerializer.deserialize(plugin.getConfig().getString("candy_locations." + name + ".location"));
		int cooldown = plugin.getConfig().getInt("candy_locations." + name + ".cooldown");
		return new Candy(loc, cooldown, 0, true);
	}

	public int getTotalChance() {
		return totalChance;
	}

	public void setTotalChance(int totalChance) {
		this.totalChance = totalChance;
	}

	public ArrayList<Candy> getCandyLocations() {
		return candyLocations;
	}

	public void setCandyLocations(ArrayList<Candy> candyLocations) {
		this.candyLocations = candyLocations;
	}

	public ArrayList<CandyItem> getCandies() {
		return candies;
	}

	public void setCandies(ArrayList<CandyItem> candies) {
		this.candies = candies;
	}
	
	
}
