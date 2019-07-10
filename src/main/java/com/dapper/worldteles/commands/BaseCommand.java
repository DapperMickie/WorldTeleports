package com.dapper.worldteles.commands;

import com.dapper.worldteles.models.WorldTeleportEntity;
import com.dapper.worldteles.repositories.IRepository;
import org.bukkit.command.CommandExecutor;

public abstract class BaseCommand implements CommandExecutor {

    protected IRepository<WorldTeleportEntity> repository;

    public BaseCommand(IRepository<WorldTeleportEntity> repository) {
        this.repository = repository;
    }
}
