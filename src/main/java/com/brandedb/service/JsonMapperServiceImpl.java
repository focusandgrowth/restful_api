package com.brandedb.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Impl that will be responsible for converting model objects to a JSON string
 * representation. Uses the Jackson libraries for conversion.
 *
 */
public class JsonMapperServiceImpl implements JsonMapperService {

	ObjectMapper jsonMapper = new ObjectMapper();

	@Override
	public <T> String toJSON(T t) {
		String json = new String();
		try {
			json = jsonMapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			Logger.getLogger("com.brandedb").log(Level.WARNING, "Could not convert class to json: Classname -> " + t.getClass());
		}
		return json;
	}
}
