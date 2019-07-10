package com.dapper.worldteles.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dapper.worldteles.models.WorldTeleportEntity;
import com.dapper.worldteles.models.WorldTeleportEntity_;

public class WorldTeleportRepository extends BaseRepository<WorldTeleportEntity> {

	public WorldTeleportRepository(final Connection connection) {
		super(connection);
	}

	@Override
	public WorldTeleportEntity findByName(final String name) {
		try {
			final PreparedStatement stmt = createStatement("SELECT * FROM " + WorldTeleportEntity_.table + " WHERE name = ?");
			stmt.setString(1, name);

			final ResultSet result = stmt.executeQuery();

			if (!result.next()) {
				return null;
			}

			return convert(result);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public WorldTeleportEntity add(WorldTeleportEntity item) {
		try {
			PreparedStatement statement = createStatement("INSERT INTO " + WorldTeleportEntity_.table +" (Name, X, Y, Z, CreatedBy, World) VALUES (?,?,?,?,?,?)");
			statement.setString(1, item.getName());
			statement.setDouble(2, item.getX());
			statement.setDouble(3, item.getY());
			statement.setDouble(4, item.getZ());
			statement.setString(5, item.getCreatedBy());
			statement.setString(6, item.getWorld());

			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(int id) {
		try {
			PreparedStatement statement = createStatement("DELETE FROM worldteles WHERE Id = ?");
			statement.setInt(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean Remove(String name) {
		try {
			PreparedStatement statement = createStatement("DELETE FROM worldteles WHERE Name = ?");
			statement.setString(1, name);

			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<WorldTeleportEntity> all() {
		try {
			PreparedStatement statement = createStatement("SELECT Id, Name, X, Y, Z, CreatedBy, World FROM worldteles");

			ResultSet results = statement.executeQuery();
			List<WorldTeleportEntity> wtmList = new ArrayList<WorldTeleportEntity>();
			while (results.next()) {
				wtmList.add(convert(results));
			}

			return wtmList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<WorldTeleportEntity> GetForUser(String createdBy) {
		try {
			PreparedStatement statement = createStatement("SELECT Id, Name, X, Y, Z, CreatedBy, World FROM worldteles WHERE CreatedBy = ?");
			statement.setString(1, createdBy);
			ResultSet results = statement.executeQuery();
			List<WorldTeleportEntity> wtmList = new ArrayList<WorldTeleportEntity>();
			while (results.next()) {
				wtmList.add(convert(results));
			}

			return wtmList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public WorldTeleportEntity get(int id) {
		try {
			PreparedStatement statement = createStatement("SELECT Id, Name, X, Y, Z, CreatedBy, World FROM worldteles WHERE Id = ?");
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			WorldTeleportEntity model = null;
			while (results.next()) {
				model = convert(results);
			}

			return model;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public WorldTeleportEntity Get(String name) {
		try {
			PreparedStatement statement = createStatement("SELECT Id, Name, X, Y, Z, CreatedBy, World FROM worldteles WHERE Name = ?");
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();
			WorldTeleportEntity model = null;
			while (results.next()) {
				model = convert(results);
			}

			return model;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private WorldTeleportEntity convert(ResultSet rs) throws SQLException {
		WorldTeleportEntity model = new WorldTeleportEntity();

		model.setId(rs.getInt("Id"));
		model.setName(rs.getString("Name"));
		model.setX(rs.getDouble("X"));
		model.setY(rs.getDouble("Y"));
		model.setZ(rs.getDouble("Z"));
		model.setCreatedBy(rs.getString("CreatedBy"));
		model.setWorld(rs.getString("World"));

		return model;
	}

}
