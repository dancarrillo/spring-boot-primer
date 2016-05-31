package com.stayhungry.primer.data;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

public class ScoreResult {
	private final int score;
	private final String reasonCodes;
	
	public ScoreResult(int score,String reasonCodes){
		this.score = score;
		this.reasonCodes = reasonCodes;
	}
	
	public int getScore() {
		return score;
	}

	public String getReasonCodes() {
		return reasonCodes;
	}	
	
	public String toString(){
		JSONObject result = new JSONObject();
		try {
			result.put("score", this.score);
			result.put("reasonCode", this.reasonCodes);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}