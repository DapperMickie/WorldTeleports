package com.dapper.worldteles.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dapper.worldteles.models.WorldTeleportModel;
import com.mysql.jdbc.PreparedStatement;

public class WorldTeleportRepository extends BaseRepository<WorldTeleportModel> {

	@Override
	public WorldTeleportModel Add(WorldTeleportModel item) {
		try {
			PreparedStatement statement = (PreparedStatement) this.connection
					.prepareStatement("INSERT INTO worldteles  (Name, X, Y, Z, CreatedBy, World) VALUES (?,?,?,?,?,?)");
			statement.setString(1, item.Name);
			statement.setDouble(2, item.X);
			statement.setDouble(3, item.Y);
			statement.setDouble(4, item.Z);
			statement.setString(5, item.CreatedBy);
			statement.setString(6, item.World);

			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Remove(int id) {
		try {
			PreparedStatement statement = (PreparedStatement) this.connection
					.prepareStatement("DELETE FROM worldteles WHERE Id = ?");
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
			PreparedStatement statement = (PreparedStatement) this.connection
					.prepareStatement("DELETE FROM worldteles WHERE Name = ?");
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
	public List<WorldTeleportModel> All() {
		try {
			PreparedStatement statement = (PreparedStatement) this.connection
					.prepareStatement("SELECT Id, Name, X, Y, Z, CreatedBy, World FROM worldteles");

			ResultSet results = statement.executeQuery();
			List<WorldTeleportModel> wtmList = new ArrayList<WorldTeleportModel>();
			while (results.next()) {
				WorldTeleportModel model = new WorldTeleportModel();

				model.Id = results.getInt(1);
				model.Name = results.getString(2);
				model.X = results.getDouble(3);
				model.Y = results.getDouble(4);
				model.Z = results.getDouble(5);
				model.CreatedBy = results.getString(6);
				model.World = results.getString(7);

				wtmList.add(model);
			}

			return wtmList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<WorldTeleportModel> GetForUser(String createdBy) {
		try {
			PreparedStatement statement = (PreparedStatement) this.connection
					.prepareStatement("SELECT Id, Name, X, Y, Z, CreatedBy, World FROM worldteles WHERE CreatedBy = ?");
			statement.setString(1, createdBy);
			ResultSet results = statement.executeQuery();
			List<WorldTeleportModel> wtmList = new ArrayList<WorldTeleportModel>();
			while (results.next()) {
				WorldTeleportModel model = new WorldTeleportModel();

				model.Id = results.getInt(1);
				model.Name = results.getString(2);
				model.X = results.getDouble(3);
				model.Y = results.getDouble(4);
				model.Z = results.getDouble(5);
				model.CreatedBy = results.getString(6);
				model.World = results.getString(7);

				wtmList.add(model);
			}

			return wtmList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public WorldTeleportModel Get(int id) {
		try {
			PreparedStatement statement = (PreparedStatement) this.connection
					.prepareStatement("SELECT Id, Name, X, Y, Z, CreatedBy, World FROM worldteles WHERE Id = ?");
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			WorldTeleportModel model = null;
			while (results.next()) {
				model = new WorldTeleportModel();
				model.Id = results.getInt(1);
				model.Name = results.getString(2);
				model.X = results.getDouble(3);
				model.Y = results.getDouble(4);
				model.Z = results.getDouble(5);
				model.CreatedBy = results.getString(6);
				model.World = results.getString(7);
			}

			return model;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public WorldTeleportModel Get(String name) {
		try {
			PreparedStatement statement = (PreparedStatement) this.connection
					.prepareStatement("SELECT Id, Name, X, Y, Z, CreatedBy, World FROM worldteles WHERE Name = ?");
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();
			WorldTeleportModel model = null;
			while (results.next()) {
				model = new WorldTeleportModel();
				model.Id = results.getInt(1);
				model.Name = results.getString(2);
				model.X = results.getDouble(3);
				model.Y = results.getDouble(4);
				model.Z = results.getDouble(5);
				model.CreatedBy = results.getString(6);
				model.World = results.getString(7);
			}

			return model;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
