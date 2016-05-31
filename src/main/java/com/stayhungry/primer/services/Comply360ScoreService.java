package com.stayhungry.primer.services;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.stayhungry.primer.data.ScoreResult;
import com.stayhungry.primer.data.ScoringApplication;

/**
 * Note that we have two beans that implement the ScoringService interface.  Because of that, Spring will 
 * not know which one you want to inject.  You will see the following exception at started:
 * NoUniqueBeanDefinitionException: No qualifying bean of type [com.blah] is defined: expected single matching bean
 * but found 2: Comply360ScoreService, IDScoreService.
 * 
 * To get around that, you must annotate one of the classes with @Primary.
 * @author dcarrillo
 *
 */
@Component
public class Comply360ScoreService implements ScoringService {
	private final int defaultScore;
	
	@Inject
	public Comply360ScoreService(@Value("${default.score}") int score){
		this.defaultScore = score;
	}
	
	@Override
	public ScoreResult score(ScoringApplication app) {
		return new ScoreResult(defaultScore + 200, "Because this person is a huge risk!");
	}
	
	@Override
	public String getName() {
		return this.getClass().getName();
	}
}
