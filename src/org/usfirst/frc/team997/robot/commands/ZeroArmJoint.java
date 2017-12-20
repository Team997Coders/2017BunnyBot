package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroArmJoint extends Command {

    public ZeroArmJoint() {
    	requires(Robot.armJoint);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Arm 0");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (Robot.armJoint.isZeroed == false) {
    		if (Robot.armJoint.Motor.isRevLimitSwitchClosed()) {
    			Robot.armJoint.isZeroed = true;
    		}
    		else {
    			Robot.armJoint.setVoltage(-0.8);

    		}
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return Robot.armJoint.Motor.isRevLimitSwitchClosed();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.armJoint.isZeroed = true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
