package com.mowitnow.mower.command.impl;

import com.mowitnow.mower.command.MowerCommand;
import com.mowitnow.mower.core.Mower;

public class MoveForwardCommand implements MowerCommand {

	@Override
	public void execute(Mower mower) {
		mower.moveForward();
	}

	@Override
	public String toString() {
		return "MoveForward";
	}
}
