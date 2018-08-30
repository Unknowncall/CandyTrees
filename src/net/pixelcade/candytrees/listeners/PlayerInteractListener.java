package net.pixelcade.candytrees.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.pixelcade.candytrees.CandyDriver;
import net.pixelcade.candytrees.objects.CandyItem;

public class PlayerInteractListener implements Listener {

	private CandyDriver plugin;

	public PlayerInteractListener(CandyDriver plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void playerInteractListener(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Player player = event.getPlayer();
			if (player.getItemInHand() == null) {
				return;
			}

			ItemStack handItem = player.getItemInHand();

			CandyItem candy = this.plugin.getCandyManager().getCandyFromItemStack(handItem);
			if (candy == null) {
				return;
			}

			for (PotionEffectType effect : candy.getEffects()) {
				PotionEffect newEffect = new PotionEffect(effect, 1200, 1);
				player.addPotionEffect(newEffect);
			}

			if (handItem.getAmount() > 1) {
				handItem.setAmount(handItem.getAmount() - 1);
				event.getPlayer().getInventory().setItemInHand(handItem);
				return;
			} else {
				player.getInventory().remove(handItem);
				return;
			}

		}
	}

}
