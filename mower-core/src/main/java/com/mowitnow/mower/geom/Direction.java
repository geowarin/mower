package com.mowitnow.mower.geom;


/**
 * Enum définissant les 4 points cardinaux.
 * 
 * @author Geoffroy Warin
 *
 */
public enum Direction {

	NORTH,
	EAST,
	SOUTH,
	WEST;
	
	public Direction next() {
		return this == WEST ? NORTH : values()[ordinal() + 1];
	}
	public Direction previous() {
		return this == NORTH ? WEST : values()[ordinal() - 1];
	}
	
	public static Direction fromString(String pos) {
		
		if ("N".equalsIgnoreCase(pos))
			return NORTH;
		if ("E".equalsIgnoreCase(pos))
			return EAST;
		if ("S".equalsIgnoreCase(pos))
			return SOUTH;
		if ("W".equalsIgnoreCase(pos))
			return WEST;
		throw new IllegalArgumentException("Unknow position : " + pos);
	}

	public Object shortString() {
		return name().charAt(0);
	}
}
