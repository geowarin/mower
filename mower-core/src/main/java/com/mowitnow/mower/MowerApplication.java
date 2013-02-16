package com.mowitnow.mower;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mowitnow.mower.command.MowerCommand;
import com.mowitnow.mower.command.SimpleMowerInstructor;
import com.mowitnow.mower.core.Mower;
import com.mowitnow.mower.core.MowerApplicationResult;
import com.mowitnow.mower.core.MowerResult;
import com.mowitnow.mower.geom.Surface;
import com.mowitnow.mower.input.InstructionReader;

/**
 * Point d'entrée de l'application.
 * Les arguments passés en paramètres chargent différents fichiers d'instructions pour les tondeuses.
 * Si aucun argument n'est passé, le fichier enonce.txt est chargé.
 * 
 * @author Geoffroy Warin
 *
 */
public class MowerApplication {

	private static Logger log = LoggerFactory.getLogger(MowerApplication.class);

	public static void main(String[] args) {

		if (args.length == 0)
			args = new String[] { "/enonce.txt" };
		
		for (String inputFile : args) {
			
			final InputStream instructionStream = InstructionReader.class.getResourceAsStream(inputFile);
			
			final MowerApplicationResult result = new MowerApplication().execute(instructionStream);
			for (Mower finalState : result.getAllFinalStates()) {
				log.info(finalState.toString());
			}
		}
	}

	/**
	 * Exécute un fichier d'instructions
	 * 
	 * @param instructionFilePath le chemin d'un fichier d'instructions
	 * @return La liste des tondeuses dans leur position finale, une liste vide
	 *  		si le fichier n'est pas présent dans le classPath
	 */
	public MowerApplicationResult execute(InputStream instructionStream) {

		if (instructionStream == null)
			return new MowerApplicationResult();
		
		final InstructionReader reader = new InstructionReader(instructionStream);
		final Surface lawn = reader.loadLawn();
		
		final List<MowerResult> results = new ArrayList<MowerResult>();
		while (reader.hasNextLine()) {

			final Mower mower = reader.loadMower(lawn);
			final List<MowerCommand> commands = reader.loadCommands();

			final SimpleMowerInstructor instructor = new SimpleMowerInstructor(mower);
			final MowerResult result = instructor.execute(commands);

			results.add(result);
		}
		
		reader.close();
		return new MowerApplicationResult(lawn, results);
	}

}
