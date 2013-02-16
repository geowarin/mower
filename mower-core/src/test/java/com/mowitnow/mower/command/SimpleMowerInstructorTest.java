package com.mowitnow.mower.command;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.mowitnow.mower.command.impl.MoveForwardCommand;
import com.mowitnow.mower.core.Mower;
import com.mowitnow.mower.core.MowerResult;
import com.mowitnow.mower.geom.Direction;
import com.mowitnow.mower.geom.Surface;

public class SimpleMowerInstructorTest {

	@Test
	public void test() {
		
		final Surface lawn = new Surface(5, 5);
		final Mower mower = new Mower(0, 0, Direction.NORTH, lawn);
		final Mower initialMower = mower.copy();
		final SimpleMowerInstructor simpleMowerInstructor = new SimpleMowerInstructor(mower);
		
		List<MowerCommand> commands = Arrays.asList(new MowerCommand[] { new MoveForwardCommand() });
		MowerResult result = simpleMowerInstructor.execute(commands);
		
		List<Mower> allStates = result.getAllStates();
		assertEquals(2, allStates.size());
		assertEquals(initialMower, result.getInitialState());
		assertEquals(mower, result.getFinalState());
	}

}
