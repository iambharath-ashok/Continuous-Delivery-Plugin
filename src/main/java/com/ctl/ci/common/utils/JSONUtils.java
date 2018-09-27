/**
 * 
 */
package com.ctl.ci.common.utils;

import java.io.IOException;

import com.ctl.ci.components.STSOutput;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author AB40286
 *
 */
public final class JSONUtils {

	/**
	 * @param object
	 * @return String
	 * @throws JsonProcessingException
	 */
	public static <O extends Object> String getJsonString(final O object) throws JsonProcessingException {
		return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
	}

	/**
	 * @param jsonString
	 * @param clazz
	 * @return STSOutput
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <C extends STSOutput> STSOutput getJavaObject(final String jsonString, final Class<C> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(jsonString, clazz);
	}
}
