package com.dapper.worldteles.repositories;

import com.dapper.worldteles.Main;
import com.mysql.jdbc.Connection;

public abstract class BaseRepository<T> implements IRepository<T> {

	public BaseRepository() {

		Main main = Main.getPlugin(Main.class);
		this.connection = main.getConnection();
	}

	Connection connection;

	public Connection getConnection() {
		return this.connection;
	}
}
