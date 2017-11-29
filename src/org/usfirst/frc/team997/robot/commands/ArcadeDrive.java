package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArcadeDrive extends Command {

    public ArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//double getArcadeLeftSpeed = deadBand(Robot.oi.getLeftY() - Robot.oi.getRightX());
    	double getArcadeLeftSpeed, getArcadeRightSpeed;
    	//double getArcadeRightSpeed = deadBand(Robot.oi.getLeftY() + Robot.oi.getRightX());
    	double[] a = getVoltages();
    	getArcadeLeftSpeed = a[0];
    	getArcadeRightSpeed = a[1];

    	 SmartDashboard.putNumber("Arcade Left", getArcadeLeftSpeed);
    	 SmartDashboard.putNumber("Arcade Right", getArcadeRightSpeed);
    	 
    	 if (Robot.oi.decellOn) {
    	Robot.driveTrain.driveDeccel(getArcadeLeftSpeed, getArcadeRightSpeed);
    	 } else {
    		 Robot.driveTrain.SetVoltages(getArcadeLeftSpeed, getArcadeRightSpeed);
    	 }
    	
    	SmartDashboard.putNumber("Left encoder value", Robot.driveTrain.leftEncoder.get());
    	SmartDashboard.putNumber("Right encoder value", Robot.driveTrain.rightEncoder.get());
    	SmartDashboard.putNumber("NavX angle", Robot.driveTrain.ahrs.getAngle());
    	SmartDashboard.putBoolean("gyroPresent", Robot.driveTrain.gyroPresent);
    
    }
    
    /*private double deadBand(double a) {
    	if(Math.abs(a) > 0.15) {
    		return a;
    	} else {
    		return 0;
    	}
    }*/
    
    public double[] getVoltages() {
    	double[] volts = new double[2];
    	double left = 0;
    	double right = 0;
    	
    	double valueForward = Robot.JoystickDeadband(Robot.oi.GamePad.getRawAxis(RobotMap.Ports.leftYAxisPort));
    	double valueSide = Robot.JoystickDeadband(Robot.oi.GamePad.getRawAxis(RobotMap.Ports.rightXAxisPort));
    	
    	left = valueForward;
    	right = valueForward;
    	if (valueSide > 0) {
    		right -= valueSide;
    		left += valueSide;
    	} else if (valueSide < 0) {
    		right += valueSide;
    		left -= valueSide;
    	}
    	
    	volts[0] = left;
    	volts[1] = right;
    	return volts;
    }

    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.StopVoltage();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
