package net.pixelcade.candytrees.objects;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class CandyItem {

	private ItemStack is;
	private ArrayList<PotionEffectType> effects;
	private int chance;

	public CandyItem(ItemStack is, ArrayList<PotionEffectType> effects, int chance) {
		this.is = is;
		this.effects = effects;
		this.chance = chance;
	}

	public ItemStack getIs() {
		return is;
	}

	public void setIs(ItemStack is) {
		this.is = is;
	}

	public ArrayList<PotionEffectType> getEffects() {
		return effects;
	}

	public void setEffects(ArrayList<PotionEffectType> effects) {
		this.effects = effects;
	}

	public int getChance() {
		return chance;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}

}
