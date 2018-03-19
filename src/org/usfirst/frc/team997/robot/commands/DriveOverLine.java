package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

//This auto routine is for when our encoders hate us and we have to use a timer instead.
public class DriveOverLine extends CommandGroup {

	public DriveOverLine() {
		addParallel(new Timercommand(2));

	}
}
