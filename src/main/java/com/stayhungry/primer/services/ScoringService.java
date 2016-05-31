package com.stayhungry.primer.services;

import com.stayhungry.primer.data.ScoreResult;
import com.stayhungry.primer.data.ScoringApplication;

/**
 * 
 * @author dcarrillo
 *
 */
public interface ScoringService {
	String getName();
	ScoreResult score(ScoringApplication app);
}
