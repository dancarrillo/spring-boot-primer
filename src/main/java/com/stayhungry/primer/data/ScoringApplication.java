package com.stayhungry.primer.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stayhungry.primer.Deserializers.ScoringApplicationDeserializer;

@JsonDeserialize(using = ScoringApplicationDeserializer.class)
public class ScoringApplication {
	private final String client;
	private final String affiliate;
	private final Identity identity;
	
	public ScoringApplication(String client, String affiliate, Identity identity){
		this.client = client;
		this.affiliate = affiliate;
		this.identity = identity;
	}

	public String getClient() {
		return client;
	}

	public String getAffiliate() {
		return affiliate;
	}

	public Identity getIdentity() {
		return identity;
	}
}