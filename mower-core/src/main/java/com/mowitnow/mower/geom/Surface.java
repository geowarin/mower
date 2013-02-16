package com.mowitnow.mower.geom;

/**
 * Une surface représentée par une longueur et une largeur
 * 
 * @author Geoffroy Warin
 *
 */
public class Surface {

	private final int width;
	private final int height;

	public Surface(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
