package com.stayhungry.primer.Deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.stayhungry.primer.data.Identity;
import com.stayhungry.primer.data.ScoringApplication;

public class ScoringApplicationDeserializer extends JsonDeserializer<ScoringApplication> {

	@Override
	public ScoringApplication deserialize(JsonParser parser, DeserializationContext ctx) throws IOException, JsonProcessingException {
		JsonNode root = parser.readValueAsTree();
		ScoringApplication app = null;
		JsonNode id = root.get("identity");
		
		if (root.hasNonNull("client")){
			app = new ScoringApplication(
					root.asText("cient"),
					root.asText("affiliate"),
					new Identity(id.asText("firstName"), id.asText("lastName")));
		}else{
			throw new IllegalArgumentException("Validation errors: client key is missing");
		}
		return app;
	}
}