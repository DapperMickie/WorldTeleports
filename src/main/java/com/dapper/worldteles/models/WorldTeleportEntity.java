package com.dapper.worldteles.models;

public class WorldTeleportEntity {

	private int id;
	private String name;
	private double x;
	private double y;
	private double z;
	private String world;
	private String createdBy;

	public WorldTeleportEntity() {
		//
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public double getX() {
		return x;
	}

	public void setX(final double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(final double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(final double z) {
		this.z = z;
	}

	public String getWorld() {
		return world;
	}

	public void setWorld(final String world) {
		this.world = world;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}
}
