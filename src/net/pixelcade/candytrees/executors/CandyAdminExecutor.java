package net.pixelcade.candytrees.executors;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.pixelcade.candytrees.CandyDriver;

public class CandyAdminExecutor implements CommandExecutor {

	private CandyDriver plugin;

	public CandyAdminExecutor(CandyDriver plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player to use this command.");
			return true;
		}
		
		Player player = (Player) sender;
		
		if (!player.hasPermission("candtrees.admin")) {
			player.sendMessage(ChatColor.RED + "You do not have the correct permissions to use this command.");
			return true;
		}
		
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("add")) {
				try {
					Set<Material> NullSet = null;
					Block b = player.getTargetBlock(NullSet, 4);
					if (b == null) {
						player.sendMessage(ChatColor.RED + "You must be looking at a block.");
						return true;
					}
					this.plugin.getCandyManager().addCandy(b.getLocation(), Integer.parseInt(args[1]));
					player.sendMessage(ChatColor.GREEN + "Successfully added a candy harvesting location.");
					return true;
				} catch (NumberFormatException e) {
					player.sendMessage(ChatColor.RED + "Wrong usage. /candyadmin add [cooldown]");
					return true;
				}
			}
		} else {
			player.sendMessage(ChatColor.RED + "Wrong usage. /candyadmin add [cooldown]");
			return true;
		}
		return true;
	}

}
