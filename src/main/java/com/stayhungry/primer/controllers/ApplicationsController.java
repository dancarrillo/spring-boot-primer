package com.stayhungry.primer.controllers;

import javax.inject.Inject;

import org.apache.http.client.HttpClient;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.stayhungry.primer.data.Identity;
import com.stayhungry.primer.data.ScoreResult;
import com.stayhungry.primer.data.ScoringApplication;
import com.stayhungry.primer.services.ScoringService;

@RestController
/**
 * With the RestController annotation, this instructs Spring to create a singleton instance to service any
 * requests which maps to the corresponding URI's below.
 * 
 * @author dcarrillo
 *
 */
public class ApplicationsController {
	private static final Logger log = Logger.getLogger(ApplicationsController.class);
	private ScoringService scoringService;
	private HttpClient httpClient;
	
	/**
	 * The httpClient is not used by this code, but is here to demonstrate that Spring will
	 * inject it into the constructor.
	 * @param scoringService
	 * @param httpClient
	 */
	@Inject
	public ApplicationsController(ScoringService scoringService, HttpClient httpClient){
		this.scoringService = scoringService;
		this.httpClient = httpClient;
	}
	
	/**
	 * This example demonstrates a simple approach to taking in a String JSON payload
	 * and converting it to a JSON object within the method.  In an actual application 
	 * this approach may be too simplistic because it floods your code with validation
	 * and other logic.
	 * @param payload
	 * @return
	 */
	@RequestMapping(value = "/v1/applications", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<String> scoreApplication(@RequestBody String payload){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;	
		String responseBody = null;
		JSONObject app;
		try {
			app = new JSONObject(payload);
			JSONObject id = app.getJSONObject("identity");
			ScoringApplication scoringApp = new ScoringApplication(
					app.getString("client"), 
					app.getString("affiliate"),
					new Identity(id.getString("firstName"), id.getString("lastName"))
					);
			
			ScoreResult scoreResult = scoringService.score(scoringApp);
			httpStatus = HttpStatus.OK;
			responseBody = scoreResult.toString();
		} catch (JSONException e) {
			log.warn("Invalid json payload received: " + payload);
		}
		return new ResponseEntity<String>(responseBody, httpStatus);
	}
	
	/**
	 * This example utilizes the ScoringApplicationDeserializer class to handle the validation
	 * and pre-processing of the input so that by the time this method is invoked, we know we 
	 * have a good working ScoringApplication object to process.  The Spring framework takes
	 * care of calling the deserializer and subsequently this method, so there is nothing more you 
	 * need to do.
	 * @param scoringApp
	 * @return
	 */
	@RequestMapping(value = "/v2/applications", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<String> scoreApplication(@RequestBody ScoringApplication scoringApp){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;	
		String responseBody = null;
		try {			
			log.info("I'm scoring with the " + scoringService.getName());
			ScoreResult scoreResult = scoringService.score(scoringApp);
			httpStatus = HttpStatus.OK;
			responseBody = scoreResult.toString();
		} catch (Exception e) {
			log.warn("Error occurred");
		}
		return new ResponseEntity<String>(responseBody, httpStatus);
	}
}
