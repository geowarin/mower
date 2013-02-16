package com.mowitnow.mower.command;

import com.mowitnow.mower.core.Mower;

/**
 * Définit une commande executable sur une tondeuse.
 * 
 * @see SimpleMowerInstructor
 * @see MowerCommandFactory
 * 
 * @author Geoffroy Warin
 *
 */
public interface MowerCommand {

	void execute(Mower mower);
}
