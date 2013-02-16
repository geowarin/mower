package com.mowitnow.mower.geom;

/**
 * Un point orienté lié à une certaine surface.
 * Ce point ne peut avancer que s'il ne sort pas de sa surface.
 * 
 * @author Geoffroy Warin
 *
 */
public class BoundedOrientedPoint extends OrientedPoint {

	protected final Surface surface;

	public BoundedOrientedPoint(int x, int y, Direction direction, Surface surface) {
		super(x, y, direction);
		this.surface = surface;
	}
	
	public BoundedOrientedPoint(Point point, Direction direction, Surface surface) {
		this(point.x, point.y, direction, surface);
	}
	
	public BoundedOrientedPoint(OrientedPoint orientedPoint, Surface surface) {
		this(orientedPoint, orientedPoint.getDirection(), surface);
	}
	
	public BoundedOrientedPoint(BoundedOrientedPoint boundedOrientedPoint) {
		this(boundedOrientedPoint, boundedOrientedPoint.surface);
	}
	
	@Override
	public void moveForward() {
		if (canMoveForward())
			super.moveForward();
	}
	
	public boolean canMoveForward() {
		final OrientedPoint afterMove = new OrientedPoint(this);
		afterMove.moveForward();
		return afterMove.isIncluded(surface);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((surface == null) ? 0 : surface.hashCode());
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
		BoundedOrientedPoint other = (BoundedOrientedPoint) obj;
		if (surface == null) {
			if (other.surface != null)
				return false;
		} else if (!surface.equals(other.surface))
			return false;
		return true;
	}
}
