package com.dapper.worldteles.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dapper.worldteles.models.WorldTeleportModel;
import com.dapper.worldteles.repositories.WorldTeleportRepository;

import net.md_5.bungee.api.ChatColor;

public class TeleCommand {

	public boolean execute(CommandSender sender, String label, String[] args) {

		if (!(sender instanceof Player)) {

			return true;
		}

		Player player = (Player) sender;

		if (args.length <= 0) {
			return true;
		}

		WorldTeleportRepository repo = new WorldTeleportRepository();
		WorldTeleportModel model;

		try {

			int id = Integer.parseInt(args[0]);
			model = repo.Get(id);

		} catch (NumberFormatException ex) {

			model = repo.Get(args[0]);
		}

		World world = Bukkit.getServer().getWorld(model.World);

		player.teleport(new Location(world, model.X, model.Y, model.Z));

		player.sendMessage(ChatColor.GREEN + "Successfully teleported to " + args[0]);

		return true;
	}

}
