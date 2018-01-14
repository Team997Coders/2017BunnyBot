package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchDelivery extends CommandGroup {
	
	int driveAngle = 45;

    public CenterSwitchDelivery() {
    	String gameData;
    	//gameData = DriverStation.getInstance().getGameSpecificMessage();
    	//Use this when we actually get 2018 bot.
    	gameData = Robot.getGameData();
    	
    	addSequential(new PDriveDistance(2 * PDriveDistance.ticksPerFoot));
    	if(gameData.charAt(0) == 'L') {
    		driveAngle = -driveAngle;
    	}
    	addSequential(new PDriveAngle(driveAngle));
    	addSequential(new PDriveDistance(6.36 * PDriveDistance.ticksPerFoot));
    	addSequential(new PDriveAngle(-driveAngle));
    	//addSequential(new PDriveDistance( Distance to Switch from Current Pos ))
    	
    	//Use manipulator to place cube on switch.
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
