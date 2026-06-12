package com.comunicacao.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonPathHelper {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private JsonPathHelper() {
	}

	public static String extract(String json, String field) throws Exception {
		JsonNode node = OBJECT_MAPPER.readTree(json);
		return node.get(field).asText();
	}
}
