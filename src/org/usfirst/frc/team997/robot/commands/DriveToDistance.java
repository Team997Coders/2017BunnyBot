package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class DriveToDistance extends PIDCommand {

    public DriveToDistance(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("DriveToDistance", 1, 0, 0, 0.02);
    	requires(Robot.driveTrain);
    	getPIDController().setSetpoint(distance);
    	getPIDController().setContinuous(false);
    	getPIDController().setAbsoluteTolerance(0.2);
    }
    
    protected double returnPIDInput() {
    	return Robot.driveTrain.rightEncoder.pidGet();
    }
    
    protected void usePIDOutput(double output) {
    	Robot.driveTrain.leftMotor.pidWrite(output);
    	Robot.driveTrain.rightMotor.pidWrite(output);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.SetVoltages(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
