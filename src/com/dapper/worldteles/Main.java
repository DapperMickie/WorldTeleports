package com.dapper.worldteles;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;

import com.dapper.worldteles.commands.AddCommand;
import com.dapper.worldteles.commands.ListCommand;
import com.dapper.worldteles.commands.RemoveCommand;
import com.dapper.worldteles.commands.TeleCommand;
import com.mysql.jdbc.Connection;

import org.bukkit.entity.Player;

public class Main extends JavaPlugin {
	private Connection connection;
	public String host, database, username, password;
	public int port;

	public void mysqlSetup() {
		host = "host";
		database = "database";
		username = "username";
		password = "password";
		port = 3306;

		try {

			synchronized (this) {
				if (getConnection() != null && !getConnection().isClosed()) {
					return;
				}

				Class.forName("com.mysql.jdbc.Driver");
				setConnection((Connection) DriverManager.getConnection(
						"jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username,
						this.password));

				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MYSQL CONNECTED");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void onEnable() {
		getLogger().info("Worldteles v0.1 has been enabled.");
		mysqlSetup();
	}

	@Override
	public void onDisable() {
		getLogger().info("Worldteles v0.1 has been disabled.");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		String lowerCmd = cmd.getName().toLowerCase();
		Player player = (Player) sender;
		if (sender instanceof Player) {
			switch (lowerCmd) {

			case "wtslist":
				ListCommand listCommand = new ListCommand();
				listCommand.execute(sender, label, args);
				break;
			case "wtstele":
				TeleCommand teleCommand = new TeleCommand();
				teleCommand.execute(sender, label, args);
				break;
			case "wtsadd":
				AddCommand addCommand = new AddCommand();
				addCommand.execute(sender, label, args);
				break;
			case "wtsremove":
				RemoveCommand removeCommand = new RemoveCommand();
				removeCommand.execute(sender, label, args);
				break;
			default:
				player.sendMessage("Your command was not recognized");
			}

			return true;
		}

		player.sendMessage("Your command was not recognized");
		return true;

	}

}
