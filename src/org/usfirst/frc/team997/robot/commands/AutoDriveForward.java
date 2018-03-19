package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveForward extends CommandGroup {

	public AutoDriveForward() {
		addSequential(new DriveToDistance(RobotMap.Values.autoDriveDistance));
		// Makes the robot travel thirty feet to cross half of the arena (and then
		// some).
	}
}
