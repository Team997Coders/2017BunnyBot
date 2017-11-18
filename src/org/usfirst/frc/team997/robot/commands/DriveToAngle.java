package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToAngle extends Command implements PIDOutput {
	public PIDController controller;
	private double pidRate;
	public double setPoint;
	
	public DriveToAngle(double angle) {this(angle, Robot.driveTrain.ahrs)} 
	
    public DriveToAngle(double angle, PIDSource source) {
    	requires(Robot.driveTrain);
    	setPoint = angle;
    	Robot.driveTrain.ahrs.reset();
    	controller = new PIDController(RobotMap.Values.driveAngleP, RobotMap.Values.driveAngleI, RobotMap.Values.driveAngleD, source, this);
    	controller.setInputRange (-180, 180);
    	controller.setOutputRange(-.26, .26);
    	controller.setAbsoluteTolerance(1);
    	controller.setContinuous(true);
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double setPoint = Robot.driveTrain.ahrs.getYaw() + this.setPoint;
    	if (setPoint > 180) {setPoint-=360;}
    	if (setPoint < -180) {setPoint +=360;}
    	controller.setSetpoint(setPoint);
    	controller.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.driveTrain.SetVoltages(pidRate, -pidRate);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return controller.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	controller.disable();
    	Robot.driveTrain.SetVoltages(0, 0);
    	
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    public void pidWrite(double output) {
    	final double min = .2;
    	if (output < min && output > 0) {
    		pidRate = min;
    	}
    	else if (output > -min && output <0) {
    		pidRate = -min;
    	}
    	else {
    		pidRate = output;
    	}
    }
}
