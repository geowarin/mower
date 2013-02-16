package com.mowitnow.mower.input;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import com.mowitnow.mower.command.MowerCommand;
import com.mowitnow.mower.core.Mower;
import com.mowitnow.mower.geom.Direction;
import com.mowitnow.mower.geom.Surface;

public class InstructionReaderTest {

	@Test
	public void test() throws FileNotFoundException {
		
		final InstructionReader reader = new InstructionReader(InstructionReader.class.getResourceAsStream("/enonce.txt"));
		final Surface map = reader.loadLawn();
		assertEquals(5, map.getHeight(), 0);
		assertEquals(5, map.getWidth(), 0);
		
		final Mower mower = reader.loadMower(map);
		assertEquals(1, mower.getX(), 0);
		assertEquals(2, mower.getY(), 0);
		assertEquals(Direction.NORTH, mower.getDirection());
		
		final List<MowerCommand> commands = reader.loadCommands();
		assertEquals(9, commands.size());
		System.out.println(commands);
		
		reader.close();
	}

}
