package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ArmIndexUpandMove extends CommandGroup {

    public ArmIndexUpandMove() {
    	System.out.println("Select and Move");
    	//addSequential(new ArmToAngle(Robot.armJoint.movelist[Robot.armJoint.selector]));
    }
}
