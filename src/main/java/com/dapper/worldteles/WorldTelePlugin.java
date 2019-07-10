package com.dapper.worldteles;

import com.dapper.worldteles.commands.AddCommand;
import com.dapper.worldteles.commands.ListCommand;
import com.dapper.worldteles.commands.RemoveCommand;
import com.dapper.worldteles.commands.TeleCommand;
import com.dapper.worldteles.models.WorldTeleportEntity;
import com.dapper.worldteles.models.WorldTeleportEntity_;
import com.dapper.worldteles.repositories.IRepository;
import com.dapper.worldteles.repositories.WorldTeleportRepository;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WorldTelePlugin extends JavaPlugin {

    private IRepository<WorldTeleportEntity> repository;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        initRepostory();
        registerCommands();

        getLogger().info("Worldteles (" + getDescription().getVersion() + ") has been enabled.");
    }

    private void initRepostory() {

        final FileConfiguration config = getConfig();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            final String datasourceUrl = config.getString("datasource.url");
            final String datasourceUsername = config.getString("datasource.username");
            final String datasourcePassword = config.getString("datasource.password");

            final Connection connection = DriverManager.getConnection(datasourceUrl, datasourceUsername, datasourcePassword);
            initDB(connection);

            repository = new WorldTeleportRepository(connection);
        } catch (ClassNotFoundException e) {
            getLogger().severe("Could not find MySQL Driver class, disabling.");
            e.printStackTrace();
            getPluginLoader().disablePlugin(this);
        } catch (SQLException e) {
            getLogger().severe("Could not establish connection to database, disabling.");
            e.printStackTrace();
            getPluginLoader().disablePlugin(this);
        }
    }

    private void initDB(final Connection connection) throws SQLException {
        connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + WorldTeleportEntity_.table + "(" + //
                WorldTeleportEntity_.id + " int not NULL auto_increment," + //
                WorldTeleportEntity_.name + " VARCHAR(255), " + //
                WorldTeleportEntity_.x + " FLOAT, " + //
                WorldTeleportEntity_.y + " FLOAT, " + //
                WorldTeleportEntity_.z + " FLOAT, " + //
                WorldTeleportEntity_.world + " VARCHAR(255), " + //
                WorldTeleportEntity_.createdBy + " VARCHAR(255), " + //
                "PRIMARY KEY (" + WorldTeleportEntity_.id + ")" + //
                ")").execute();
    }

    private void registerCommands() {

        getCommand("wtslist").setExecutor(new ListCommand(repository));
        getCommand("wtstele").setExecutor(new TeleCommand(repository));
        getCommand("wtsadd").setExecutor(new AddCommand(repository));
        getCommand("wtsremove").setExecutor(new RemoveCommand(repository));
    }

    @Override
    public void onDisable() {
        getLogger().info("Worldteles v0.1 has been disabled.");
    }

}
