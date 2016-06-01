package com.stayhungry.primer.services;

import com.stayhungry.primer.data.ScoreResult;
import com.stayhungry.primer.data.ScoringApplication;

/**
 * A simple interface so that we can demonstrate how to handle two implementations
 * and how Spring decides which one to inject.
 * 
 * @author dcarrillo
 *
 */
public interface ScoringService {
	String getName();
	ScoreResult score(ScoringApplication app);
}
