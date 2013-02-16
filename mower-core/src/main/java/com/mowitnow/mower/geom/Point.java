package com.mowitnow.mower.geom;

/**
 * Un point, représenté par ses coordonnées x, y.
 * Il est possible de le bouger, lui faire subir une translation ou vérifier qu'ils se trouve dans une surface donnée.
 * 
 * @author Geoffroy Warin
 *
 */
public class Point {

	protected int x;
	protected int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(Point point) {
		this.x = point.x;
		this.y = point.y;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void translate(int dx, int dy) {
		x += dx;
		y += dy;
	}
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isIncluded(Surface surface) {
		return x >= 0 && x <= surface.getWidth() && y >= 0 && y <= surface.getHeight();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
}
