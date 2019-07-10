package com.dapper.worldteles.repositories;

import java.util.List;

public interface IRepository<T> {
	T Add(T item);

	boolean Remove(int id);

	List<T> All();

	T Get(int id);
}
