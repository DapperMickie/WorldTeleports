package com.dapper.worldteles.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dapper.worldteles.models.WorldTeleportModel;
import com.dapper.worldteles.repositories.WorldTeleportRepository;

public class ListCommand {

	public boolean execute(CommandSender sender, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage(ChatColor.YELLOW + "-- The list of world teleports --");
			WorldTeleportRepository repo = new WorldTeleportRepository();

			List<WorldTeleportModel> models = repo.All();

			for (WorldTeleportModel model : models) {
				player.sendMessage(ChatColor.BLUE + "#" + model.Id + ", " + ChatColor.DARK_GREEN + model.Name
						+ ChatColor.WHITE + " " + model.X + " " + model.Y + " " + model.Z);
			}

			player.sendMessage(ChatColor.YELLOW + "-- End of list --");
		}

		return true;
	}

}
