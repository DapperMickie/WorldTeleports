package com.dapper.worldteles.repositories;

import java.util.List;

/**
 * Repository to interact with a database
 * @param <T>
 */
public interface IRepository<T> {

	T findByName(String name);

	T add(T item);

	boolean remove(int id);

	List<T> all();

	T get(int id);

}
