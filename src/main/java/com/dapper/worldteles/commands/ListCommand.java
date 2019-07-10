package com.dapper.worldteles.commands;

import java.util.List;

import com.dapper.worldteles.repositories.IRepository;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dapper.worldteles.models.WorldTeleportEntity;

public class ListCommand extends BaseCommand {

    public ListCommand(final IRepository<WorldTeleportEntity> repository) {
        super(repository);
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.YELLOW + "-- The list of world teleports --");

            List<WorldTeleportEntity> models = repository.all();

            for (WorldTeleportEntity model : models) {
                player.sendMessage(ChatColor.BLUE + "#" + model.getId() + ", " + ChatColor.DARK_GREEN + model.getName()
                        + ChatColor.WHITE + " " + model.getX() + " " + model.getY() + " " + model.getZ());
            }

            player.sendMessage(ChatColor.YELLOW + "-- End of list --");
        }

        return true;
    }
}
