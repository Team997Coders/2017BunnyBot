package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

//This auto command is for when we are on either the left or the right side of the starting
//positions and we want to deliver the cube to either of the sides.
public class SwitchSameSideDelivery extends CommandGroup {

	public SwitchSameSideDelivery() {

		addSequential(new PDriveDistance(14 * Robot.driveTrain.ticksPerFoot));
		if (Robot.getGameData().charAt(0) == 'L') {
			addSequential(new PDriveAngle(90));
		} else {
			addSequential(new PDriveAngle(-90));
		}

		// How far does the robot have to travel to get close enough to the switch?
	}
}
