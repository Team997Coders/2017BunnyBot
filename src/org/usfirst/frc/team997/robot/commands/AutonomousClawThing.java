package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousClawThing extends CommandGroup {
	// FIX THIS LATER

	public AutonomousClawThing() {
		addSequential(new ClawButtonCommand(Robot.claw.openState));
		addSequential(new DriveToDistance(30));
		addSequential(new ClawButtonCommand(Robot.claw.closeState));
		// Lift claw plz :^)

		// requires(Robot.claw); //uncomment these
	}
}
