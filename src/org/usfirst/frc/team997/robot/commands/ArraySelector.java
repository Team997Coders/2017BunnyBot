package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ArraySelector extends CommandGroup {
   
	public ArraySelector(boolean input) {
    	
    	if(input) {
    		addSequential(new ArrayUp()); 
    		//System.out.println("in ArraySelector increment to " + Robot.armJoint.selector);
    	} else { 
    		addSequential(new ArrayDown());	
    	}
    	
    	addSequential(new ArmToArrayAngle());
    }
}
