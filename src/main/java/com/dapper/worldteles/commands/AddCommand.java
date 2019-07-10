package com.dapper.worldteles.commands;

import com.dapper.worldteles.repositories.IRepository;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dapper.worldteles.models.WorldTeleportEntity;

public class AddCommand extends BaseCommand {

    public AddCommand(final IRepository<WorldTeleportEntity> repository) {
        super(repository);
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        try {
            Integer.parseInt(args[0]);
            player.sendMessage(ChatColor.RED + "The name cannot be a number");

            return false;
        } catch (NumberFormatException ex) {

        }

        if (repository.findByName(args[0]) != null) {
            player.sendMessage(ChatColor.RED + "Teleport with this name already exists");
            return false;
        }

        Location loc = player.getLocation();
        WorldTeleportEntity model = new WorldTeleportEntity();

        model.setX(loc.getX());
        model.setY(loc.getY());
        model.setZ(loc.getZ());
        model.setCreatedBy(player.getUniqueId().toString());
        model.setName(args[0]);
        model.setWorld(loc.getWorld().getName());

        repository.add(model);

        player.sendMessage(ChatColor.GREEN + "Successfully added the " + args[0] + " world teleport");

        return true;
    }
}
