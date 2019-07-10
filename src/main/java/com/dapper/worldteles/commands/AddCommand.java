package com.dapper.worldteles.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dapper.worldteles.models.WorldTeleportModel;
import com.dapper.worldteles.repositories.WorldTeleportRepository;

public class AddCommand {

	public boolean execute(CommandSender sender, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}

		Player player = (Player) sender;
		try {
			Integer.parseInt(args[0]);
			player.sendMessage(ChatColor.RED + "The name cannot be a number");

			return true;
		} catch (NumberFormatException ex) {

		}

		WorldTeleportRepository repo = new WorldTeleportRepository();

		if (repo.Get(args[0]) != null) {
			player.sendMessage(ChatColor.RED + "Teleport with this name already exists");
			return true;
		}

		Location loc = player.getLocation();
		WorldTeleportModel model = new WorldTeleportModel();

		model.X = loc.getX();
		model.Y = loc.getY();
		model.Z = loc.getZ();
		model.CreatedBy = player.getUniqueId().toString();
		model.Name = args[0];
		model.World = loc.getWorld().getName();

		repo.Add(model);

		player.sendMessage(ChatColor.GREEN + "Successfully added the " + args[0] + " world teleport");

		return true;
	}
}
