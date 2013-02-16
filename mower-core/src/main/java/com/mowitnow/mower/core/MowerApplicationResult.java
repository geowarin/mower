package com.mowitnow.mower.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mowitnow.mower.geom.Surface;

/**
 * Le résultat de l'application.
 * Contient la surface de la pelouse et les états des différentes tondeuses.
 * 
 * @author Geoffroy Warin
 *
 */
public class MowerApplicationResult {

	private final Surface lawn;
	private final List<MowerResult> results;
	
	public MowerApplicationResult() {
		lawn = new Surface(0, 0);
		results = Collections.emptyList();
	}
	
	public MowerApplicationResult(Surface lawn, List<MowerResult> results) {
		this.lawn = lawn;
		this.results = results;
	}
	
	public Surface getLawn() {
		return lawn;
	}
	public List<MowerResult> getResults() {
		return results;
	}
	
	public List<Mower> getAllStates() {
		final List<Mower> allStates = new ArrayList<Mower>();
		for (MowerResult result : results) {
			allStates.addAll(result.getAllStates());
		}
		return allStates;
	}
	
	public List<Mower> getAllFinalStates() {
		final List<Mower> finalStates = new ArrayList<Mower>();
		for (MowerResult result : results) {
			finalStates.add(result.getFinalState());
		}
		return finalStates;
	}
}
