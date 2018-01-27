package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.subsystems.ArmJoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ArraySelector extends CommandGroup {
    public ArraySelector(boolean input) {
    	if(input) {
    		addSequential(new ArrayUp());
    	} else {
    		addSequential(new ArrayDown());
    	}
    	//Works with hardcoded array index values
    	//Error seems to be in getting selector value from armJoint
    	addSequential(new ArmToAngle(Robot.armJoint.movelist[Robot.armJoint.selector]));
    }
}
