package com.dapper.worldteles.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract class BaseRepository<T> implements IRepository<T> {

	private Connection connection;

	public BaseRepository(final Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return this.connection;
	}

	/**
	 * Create a {@link PreparedStatement} based on the underlying {@link #connection}.
	 * @param stmt The statement
	 * @return The created prepared statement
	 */
	protected final PreparedStatement createStatement(final String stmt) {
		try {
			return connection.prepareStatement(stmt);
		} catch (SQLException e) {
			throw new RuntimeException(e); // Rethrow to upper handler
		}
	}

}
