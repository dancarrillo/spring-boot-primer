package com.stayhungry.primer.Deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.stayhungry.primer.data.Identity;
import com.stayhungry.primer.data.ScoringApplication;

/**
 * This class instructs the Spring framework to intercept the incoming request lifecycle and 
 * perform the deserialization of a ScoringApplication object.
 * This is where you want your validation code to take place.  In fact, a good pattern
 * to implement here is Visitor.  You can create validation visitors or even just a pojo for separation 
 * of concerns, annotate it with @Component and inject it into this class for a clean implementation.
 *  
 * @author dcarrillo
 *
 */
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
			// note the exception thrown here.  We want to be sure to return a JSON formatted message.
			// Refer to com.stayhungry.primer.controllers.ExceptionHandlerController for details.
			throw new IllegalArgumentException("Validation errors: client key is missing");
		}
		return app;
	}
}