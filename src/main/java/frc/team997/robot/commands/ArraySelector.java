package main.java.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.java.frc.team997.robot.Robot;
import main.java.frc.team997.robot.RobotMap;

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
