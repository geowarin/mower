package com.mowitnow.mower.input;

import java.io.Closeable;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mowitnow.mower.command.MowerCommand;
import com.mowitnow.mower.command.MowerCommandFactory;
import com.mowitnow.mower.core.Mower;
import com.mowitnow.mower.geom.Direction;
import com.mowitnow.mower.geom.Surface;

/**
 * Cette classe permet de charger un fichier d'instruction
 * 
 * @author Geoffroy Warin
 *
 */
public class InstructionReader implements Closeable {

	private static Logger log = LoggerFactory.getLogger(InstructionReader.class);
	
	private final Scanner scanner;
	
	public InstructionReader(InputStream inputStream) {
		scanner = new Scanner(inputStream, "UTF8");
	}
	
	/**
	 * @return True S'il reste une ou plusieurs lignes à lire
	 */
	public boolean hasNextLine() {
		return scanner.hasNextLine();
	}
	
	/**
	 * @return Une map dont les dimensions sont chargées à partir du fichier
	 * @throws IllegalStateException s'il n'y a pas de ligne à lire
	 */
	public Surface loadLawn() {
		
		if (!scanner.hasNextLine())
			throw new IllegalStateException("Nothing to read");
			
		final String mapLine = scanner.nextLine();
		
		final Scanner mapScanner = new Scanner(new StringReader(mapLine));
		
		final int width = mapScanner.nextInt();
		final int height = mapScanner.nextInt();
		
		mapScanner.close();
		
		return new Surface(width, height);
	}
	
	/**
	 * Charge une tondeuse
	 * 
	 * @param lawn Une map qui définit les dimensions du terrain.
	 * @return
	 */
	public Mower loadMower(Surface lawn) {
		
		if (lawn == null)
			throw new IllegalArgumentException("Map must not be null");
		if (!scanner.hasNextLine())
			throw new IllegalStateException("Nothing to read");
		
		final String mowerLine = scanner.nextLine();
		
		final Scanner mowerScanner = new Scanner(new StringReader(mowerLine));
		
		int x = mowerScanner.nextInt();
		int y = mowerScanner.nextInt();
		final String directionString = mowerScanner.next();

		mowerScanner.close();
		log.debug("Initialized mower : {} {} {}", x, y, directionString);
		
		final Direction direction = Direction.fromString(directionString);
		
		return new Mower(x, y, direction, lawn);
	}
	
	/**
	 * Charge les commandes d'une tondeuse
	 */
	public List<MowerCommand> loadCommands() {
		
		if (!scanner.hasNextLine())
			return Collections.emptyList();
		
		final String commandLine = scanner.nextLine();
		final List<MowerCommand> commands = new ArrayList<MowerCommand>(commandLine.length());
		
		for (char c : commandLine.toCharArray()) {
			
			final MowerCommand command = MowerCommandFactory.createCommandFromChar(c);
			commands.add(command);
		}
		
		return commands;
	}
	
	@Override
	public void close() {
		scanner.close();
	}
}
