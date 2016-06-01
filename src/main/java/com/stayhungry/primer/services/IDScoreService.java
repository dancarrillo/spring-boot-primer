package com.stayhungry.primer.services;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.stayhungry.primer.data.ScoreResult;
import com.stayhungry.primer.data.ScoringApplication;

@Component
@Primary
/**
 * Because this component is annotated with @Primary, it is preferrentially injected into any bean configured to
 * have an instance of type ScoringService injected into it.  If we removed this annotation you would see 
 * the following exception at startup:
 *  
 * NoUniqueBeanDefinitionException: No qualifying bean of type [com.blah] is defined: expected single matching bean
 * but found 2: Comply360ScoreService, IDScoreService.
 * 
 * @author dcarrillo
 *
 */
public class IDScoreService implements ScoringService {
	private final int defaultScore;
	
	@Inject
	public IDScoreService(@Value("${default.score}") int score){
		this.defaultScore = score;
	}
	
	@Override
	public ScoreResult score(ScoringApplication app) {
		return new ScoreResult(defaultScore, "Just because");
	}

	@Override
	public String getName() {
		return this.getClass().getName();
	}
}