package com.dapper.worldteles;

import com.dapper.worldteles.commands.AddCommand;
import com.dapper.worldteles.commands.ListCommand;
import com.dapper.worldteles.commands.RemoveCommand;
import com.dapper.worldteles.commands.TeleCommand;
import com.dapper.worldteles.models.WorldTeleportEntity;
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
		getLogger().info("Worldteles v0.1 has been enabled.");

		saveDefaultConfig();

		initRepostory();
		registerCommands();
	}

	private void initRepostory() {

		final FileConfiguration config = getConfig();

		try {
			Class.forName("com.mysql.jdbc.Driver");

			final String datasourceUrl = config.getString("datasource.url");
			final String datasourceUsername = config.getString("datasource.username");
			final String datasourcePassword = config.getString("datasource.password");

			final Connection connection = DriverManager.getConnection(datasourceUrl, datasourceUsername, datasourcePassword);
			repository = new WorldTeleportRepository(connection);
		} catch (ClassNotFoundException e) {
			getLogger().severe("Could not find MySQL Driver class, disabling.");
			getPluginLoader().disablePlugin(this);
		} catch (SQLException e) {
			getLogger().severe("Could not establish connection to database, disabling.");
			getPluginLoader().disablePlugin(this);
		}
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
