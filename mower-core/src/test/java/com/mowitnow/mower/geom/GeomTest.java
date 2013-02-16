package com.mowitnow.mower.geom;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeomTest {

	@Test
	public void testDirections() {
		
		assertEquals(Direction.EAST, Direction.NORTH.next());
		assertEquals(Direction.SOUTH, Direction.EAST.next());
		assertEquals(Direction.WEST, Direction.SOUTH.next());
		assertEquals(Direction.NORTH, Direction.WEST.next());
		
		assertEquals(Direction.WEST, Direction.NORTH.previous());
		assertEquals(Direction.NORTH, Direction.EAST.previous());
		assertEquals(Direction.EAST, Direction.SOUTH.previous());
		assertEquals(Direction.SOUTH, Direction.WEST.previous());
	}
	
	@Test
	public void testPoint() {
		
		Point point = new Point(1, 2);
		assertEquals(1, point.getX());
		assertEquals(2, point.getY());
		
		point.translate(0, 1);
		assertEquals(3, point.getY());
		
		point.translate(1, 0);
		assertEquals(3, point.getY());
		assertEquals(2, point.getX());
	}

	@Test
	public void testCanMove() {
		
		final Surface surface = new Surface(5, 5);
		assertTrue(new BoundedOrientedPoint(0, 2, Direction.SOUTH, surface).canMoveForward());
		assertFalse(new BoundedOrientedPoint(0, 2, Direction.WEST, surface).canMoveForward());
	}
	
	@Test
	public void testMoves() {
		
		final Surface surface = new Surface(5, 5);
		final BoundedOrientedPoint point = new BoundedOrientedPoint(0, 2, Direction.SOUTH, surface);
		
		point.moveForward();
		assertEquals(0, point.getX());
		assertEquals(1, point.getY());
		
		point.moveForward();
		assertEquals(0, point.getX());
		assertEquals(0, point.getY());
		
		point.moveForward();
		assertEquals(0, point.getX());
		assertEquals(0, point.getY());
		
		point.rotateRight();
		assertEquals(0, point.getX());
		assertEquals(0, point.getY());
		assertEquals(Direction.WEST, point.getDirection());
		
		point.moveForward();
		assertEquals(0, point.getX());
		assertEquals(0, point.getY());
	}
	

}
