package com.mowitnow.mower;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.mowitnow.mower.core.MowerApplicationResult;
import com.mowitnow.mower.core.MowerResult;
import com.mowitnow.mower.input.InstructionReader;

public class MowerApplicationTest {

	@Test
	public void test() {
		
		final MowerApplication application = new MowerApplication();
		MowerApplicationResult applicationResult = application.execute(InstructionReader.class.getResourceAsStream("/enonce.txt"));
		List<MowerResult> results = applicationResult.getResults();
		
		assertEquals(5, applicationResult.getLawn().getWidth());
		assertEquals(5, applicationResult.getLawn().getHeight());
		
		assertEquals(2, results.size());
		assertEquals("1 3 N", results.get(0).getFinalState().toString());
		assertEquals("5 1 E", results.get(1).getFinalState().toString());
	}

}
