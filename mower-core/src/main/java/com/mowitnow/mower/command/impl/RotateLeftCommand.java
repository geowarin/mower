package com.mowitnow.mower.command.impl;

import com.mowitnow.mower.command.MowerCommand;
import com.mowitnow.mower.core.Mower;

public class RotateLeftCommand implements MowerCommand {

	@Override
	public void execute(Mower mower) {
		mower.rotateLeft();
	}

	@Override
	public String toString() {
		return "RotateLeft";
	}
}
