package net.pixelcade.candytrees.objects;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class Candy {

	private Location location;
	private int cooldownTime;
	private int cooldownCounter;
	private boolean isMineable;
	private ArmorStand stand;

	public Candy(Location location, int cooldownTime, int cooldownCounter, boolean isMineable) {
		this.location = location;
		this.cooldownTime = cooldownTime;
		this.cooldownCounter = cooldownCounter;
		this.isMineable = isMineable;
		this.loadHologram();
	}

	public void loadHologram() {
		if (this.stand == null) {
			Location location = new Location(this.getLocation().getWorld(), this.getLocation().getX() + .5, this.getLocation().getY() + .5, this.getLocation().getZ() + .5);
			this.setStand((ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND));
			stand.setGravity(false);
			stand.setCanPickupItems(false);
			if (this.cooldownCounter / 20 == 0) {
				stand.setCustomName(ChatColor.YELLOW + "Candy is Harvestable!");
			} else {
				stand.setCustomName(ChatColor.YELLOW + "Ready in: " + ChatColor.RED + (this.cooldownCounter / 20) + ChatColor.YELLOW + "s");
			}
			stand.setCustomNameVisible(true);
			stand.setVisible(false);
		} else {
			if (this.cooldownCounter / 20 == 0) {
				stand.setCustomName(ChatColor.YELLOW + "Candy is Harvestable!");
			} else {
				stand.setCustomName(ChatColor.YELLOW + "Ready in: " + ChatColor.RED + (this.cooldownCounter / 20) + ChatColor.YELLOW + "s");
			}
		}
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getCooldownTime() {
		return cooldownTime;
	}

	public void setCooldownTime(int cooldownTime) {
		this.cooldownTime = cooldownTime;
	}

	public int getCooldownCounter() {
		return cooldownCounter;
	}

	public void setCooldownCounter(int cooldownCounter) {
		this.cooldownCounter = cooldownCounter;
	}

	public boolean isMineable() {
		return isMineable;
	}

	public void setMineable(boolean isMineable) {
		this.isMineable = isMineable;
	}

	public ArmorStand getStand() {
		return stand;
	}

	public void setStand(ArmorStand stand) {
		this.stand = stand;
	}

}
