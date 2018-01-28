package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArmToArrayAngle extends Command {
	
	private double angle;

    public ArmToArrayAngle() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.armJoint);
    }
   
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	angle = Robot.armJoint.getItem();
    	Robot.armJoint.Motor.set(ControlMode.Position, angle);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	int closedLoopError = Robot.armJoint.Motor.getClosedLoopError(0);
    	
    	return !Robot.armJoint.isZeroed || (Math.abs(closedLoopError) < 60);
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.armJoint.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("Interrupted armToArrayAngle");
    	end();
    }
}
