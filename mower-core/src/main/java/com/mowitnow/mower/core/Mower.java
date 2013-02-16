package com.mowitnow.mower.core;

import com.mowitnow.mower.geom.BoundedOrientedPoint;
import com.mowitnow.mower.geom.Direction;
import com.mowitnow.mower.geom.Surface;

/**
 * Implémentation d'une tondeuse.
 * Une tondeuse est un simple point orienté lié à une surface :  
 * elle possède une position x,y, une direction et évolue sur une pelouse de dimension définie.
 * 
 * @author Geoffroy Warin
 *
 */
public class Mower extends BoundedOrientedPoint {

	public Mower(int x, int y, Direction direction, Surface lawn) {
		super(x, y, direction, lawn);
	}
	public Mower(BoundedOrientedPoint state) {
		super(state);
	}
	public Mower copy() {
		return new Mower(this);
	}
}
