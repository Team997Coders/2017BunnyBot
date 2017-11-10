package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShiftCommand extends Command {

    public ShiftCommand() {
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.driveTrain.gear == 1) {
    		Robot.driveTrain.shift(0);
    	} else if(Robot.driveTrain.gear == 0) {
    		Robot.driveTrain.shift(1);
    	} else {
    		SmartDashboard.putNumber("Oh Noes! driveTrain shifter expected 1 or 0 at Robot.driveTrain.gear, "
    				+ "got this instead.", Robot.driveTrain.gear);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end(); //might be a problem when driving
    }
}
