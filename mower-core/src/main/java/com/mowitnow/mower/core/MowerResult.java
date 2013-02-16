package com.mowitnow.mower.core;

import java.util.LinkedList;
import java.util.List;


/**
 * Contient les états d'une tondeuse.
 * 
 * @author Geoffroy Warin
 *
 */
public class MowerResult {

	private final LinkedList<Mower> states = new LinkedList<Mower>();
	
	public Mower getInitialState() {
		return states.peekFirst();
	}
	public Mower getFinalState() {
		return states.peekLast();
	}
	public List<Mower> getAllStates() {
		return states;
	}
	
	public void addState(Mower state) {
		states.add(state);
	}
}
