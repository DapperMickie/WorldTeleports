package com.dapper.worldteles.commands;

import com.dapper.worldteles.models.WorldTeleportEntity;
import com.dapper.worldteles.repositories.IRepository;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveCommand extends BaseCommand {

    public RemoveCommand(final IRepository<WorldTeleportEntity> repository) {
        super(repository);
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        if (args.length != 1) {
            return false;
        }

        try {

            int id = Integer.parseInt(args[0]);
            removeById(sender, id);
        } catch (NumberFormatException ex) {

            removeByName(sender, args[0]);
        }

        return true;
    }

    private void removeById(CommandSender sender, final int id) {

        sender.sendMessage(ChatColor.GREEN + "Succesfully removed world teleport with id: " + id);
    }

    private void removeByName(CommandSender sender, final String name) {

        final WorldTeleportEntity model = repository.findByName(name);
        repository.remove(model.getId());
        sender.sendMessage(ChatColor.GREEN + "Succesfully removed world teleport with name: " + name);
    }

}
