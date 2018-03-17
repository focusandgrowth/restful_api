package com.brandedb.service;

/**
 * Interface used for any impl that will be responsible for converting model
 * objects to a JSON string representation
 *
 */
public interface JsonMapperService {

	public <T> String toJSON(T t);
}
