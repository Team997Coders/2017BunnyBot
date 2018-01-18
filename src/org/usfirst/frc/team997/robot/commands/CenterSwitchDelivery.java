package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

//This auto command is for when we are positioned on the inner side of the exchange
//zone and we want to deliver our preloaded cube to either of the sides.
public class CenterSwitchDelivery extends CommandGroup {
	
	//int driveAngle = 90;

    public CenterSwitchDelivery() {
    	String gameData;
    	//gameData = DriverStation.getInstance().getGameSpecificMessage();
    	//Use this when we actually get 2018 bot.
    	gameData = Robot.getGameData();
    	
    	addSequential(new PDriveDistance(2 * PDriveDistance.ticksPerFoot));
    	if(gameData.charAt(0) == 'L') {
    		//driveAngle = -driveAngle;
    		
    		addSequential(new PDriveAngle(-65));
    		addSequential(new PDriveDistance(4.8 * PDriveDistance.ticksPerFoot));
    		addSequential(new PDriveAngle(65));
    		addSequential(new PDriveDistance(3.62 * PDriveDistance.ticksPerFoot));
    		//NEED TO DROP CUBE AFTERWARDS IN SWITCH.
    		//NEEDS TESTING!!
    		//When our side of the switch is on the left, this will deliver the cube to
    		//that side.
    	}
    	else {
    		addSequential(new PDriveAngle(65));
    		addSequential(new PDriveDistance(4.8 * PDriveDistance.ticksPerFoot));
    		addSequential(new PDriveAngle(-65));
    		addSequential(new PDriveDistance(3.62 * PDriveDistance.ticksPerFoot));
    		//NEEDS TESTING!!
    		//When our side of the switch is on the right, this will deliver the cube to
    		//that side.
    	}
    	addSequential(new ArmToAngle(RobotMap.Values.armPositionMidForward));
    	addSequential(new Timercommand(0.5));
    	addSequential(new ClawButtonCommand());
    }
}
