package com.dapper.worldteles.commands;

import com.dapper.worldteles.models.WorldTeleportEntity;
import com.dapper.worldteles.repositories.IRepository;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleCommand extends BaseCommand {

    public TeleCommand(final IRepository<WorldTeleportEntity> repository) {
        super(repository);
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {

            return false;
        }

        Player player = (Player) sender;

        if (args.length <= 0) {
            return false;
        }

        WorldTeleportEntity model;

        try {

            int id = Integer.parseInt(args[0]);
            model = repository.get(id);

        } catch (NumberFormatException ex) {

            model = repository.findByName(args[0]);
        }

        World world = Bukkit.getServer().getWorld(model.getWorld());
        player.teleport(new Location(world, model.getX(), model.getY(), model.getZ()));
        player.sendMessage(ChatColor.GREEN + "Successfully teleported to " + args[0]);

        return true;
    }
}
