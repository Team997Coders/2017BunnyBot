package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTo extends Command implements PIDOutput {

	private double pidRate;
	public PIDController controller;
	public double SetPoint;
	private double initAngle;
	
    public DriveTo(double distance) {
    	this(distance, new PIDSource(Robot.driveTrain.leftEncoder));
    	}
    
    public DriveTo(double distance, PIDSource source) {
    	
    	requires(Robot.driveTrain);
    	SetPoint = distance;
    	
    	controller = new PIDController(0, 0, 0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    
    	
    
    
    
    
    	
		pidWrite();
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
    	end();
    }
    
    public void pidWrite(double output) {
    	pidRate = output;
	}
}

