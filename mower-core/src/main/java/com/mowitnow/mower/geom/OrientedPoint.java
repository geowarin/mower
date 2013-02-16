package com.mowitnow.mower.geom;


/**
 * Un point orienté dans une direction prédéfinie.
 * Il est possible de le faire avancer en avant dans sa direction.
 * 
 * @author Geoffroy Warin
 *
 */
public class OrientedPoint extends Point {

	protected Direction direction;
	
	public OrientedPoint(int x, int y, Direction direction) {
		super(x, y);
		this.direction = direction;
	}
	
	public OrientedPoint(Point point, Direction direction) {
		this(point.x, point.y, direction);
	}

	public OrientedPoint(OrientedPoint orientedPoint) {
		this(orientedPoint, orientedPoint.direction);
	}

	/**
	 * Tourne la tondeuse sur la gauche
	 */
	public void rotateLeft() {
		direction = direction.previous();
	}
	
	/**
	 * Tourne la tondeuse sur la droite
	 */
	public void rotateRight() {
		direction = direction.next();
	}
	
	/**
	 * Déplace la tondeuse d'une case dans sa direction actuelle
	 */
	public void moveForward() {
		switch (direction) {
			case NORTH: 
				translate(0, 1); break;
			case EAST: 
				translate(1, 0); break;
			case SOUTH: 
				translate(0, -1); break;
			case WEST: 
				translate(-1, 0); break;
		}
	}

	public Direction getDirection() {
		return direction;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrientedPoint other = (OrientedPoint) obj;
		if (direction != other.direction)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%d %d %s", getX(), getY(), getDirection().shortString());
	}
}
