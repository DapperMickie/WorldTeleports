package com.dapper.worldteles.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dapper.worldteles.repositories.WorldTeleportRepository;

public class RemoveCommand {

	public boolean execute(CommandSender sender, String label, String[] args) {

		Player player = (Player) sender;
		if (!(sender instanceof Player)) {
			return true;
		}

		if (args.length <= 0) {
			return true;
		}

		WorldTeleportRepository repo = new WorldTeleportRepository();

		try {

			int id = Integer.parseInt(args[0]);
			repo.Remove(id);
			player.sendMessage(ChatColor.GREEN + "Succesfully removed world teleport with id: " + id);

		} catch (NumberFormatException ex) {

			repo.Remove(args[0]);
			player.sendMessage(ChatColor.GREEN + "Succesfully removed world teleport with name: " + args[0]);
		}

		return true;
	}

}
