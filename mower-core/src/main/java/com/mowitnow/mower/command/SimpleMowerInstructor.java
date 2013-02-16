package com.mowitnow.mower.command;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mowitnow.mower.core.Mower;
import com.mowitnow.mower.core.MowerResult;

/**
 * Invoker du design pattern command. Permet d'éxecuter des {@link MowerCommand} sur une tondeuse.
 * 
 * @author Geoffroy Warin
 *
 */
public class SimpleMowerInstructor {

	private static Logger log = LoggerFactory.getLogger(SimpleMowerInstructor.class);

	protected final Mower mower;

	public SimpleMowerInstructor(Mower mower) {
		this.mower = mower;
	}

	/**
	 * Exécute une commande
	 */
	private void execute(MowerCommand command) {
		command.execute(mower);
		log.debug("Executed command {}, mower is now in state {}", command, mower);
	}
	
	/**
	 * Exécute une liste de commandes séquentiellement
	 * @return 
	 */
	public MowerResult execute(List<MowerCommand> commands) {

		final MowerResult result = new MowerResult();
		// Initial state
		result.addState(mower.copy());
		for (MowerCommand command : commands) {
			execute(command);
			result.addState(mower.copy());
		}
		return result;
	}
}
