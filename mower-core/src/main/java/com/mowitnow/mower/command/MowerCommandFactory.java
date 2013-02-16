package com.mowitnow.mower.command;

import com.mowitnow.mower.command.impl.MoveForwardCommand;
import com.mowitnow.mower.command.impl.RotateLeftCommand;
import com.mowitnow.mower.command.impl.RotateRightCommand;

/**
 * Simple factory permettant de créer une commande à partir d'un caractère.
 * Les valeurs connues sont 'G', 'D' et 'A'.
 * 
 * @author Geoffroy Warin
 *
 */
public class MowerCommandFactory {

	/**
	 * Crée une commande à partir d'un caractère. Les valeurs connues sont 'G', 'D' et 'A'.
	 */
	public static MowerCommand createCommandFromChar(char c) {
		
		switch (c) {
			case 'G': return new RotateLeftCommand();
			case 'D': return new RotateRightCommand();
			case 'A': return new MoveForwardCommand();

			default: throw new IllegalStateException("Unknow command : " + c);
		}
	}
}
