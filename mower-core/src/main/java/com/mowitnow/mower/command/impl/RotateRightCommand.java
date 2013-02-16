package com.mowitnow.mower.command.impl;

import com.mowitnow.mower.command.MowerCommand;
import com.mowitnow.mower.core.Mower;

public class RotateRightCommand implements MowerCommand {

	@Override
	public void execute(Mower mower) {
		mower.rotateRight();
	}

	@Override
	public String toString() {
		return "RotateRight";
	}
}
