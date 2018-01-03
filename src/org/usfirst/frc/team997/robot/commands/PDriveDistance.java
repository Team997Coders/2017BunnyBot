package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PDriveDistance extends Command {
	private double distSetpoint;
	private double minError = 10;
	public Timer timer = new Timer();
	private double lastTime = 0;
	private double lastVoltage = 0;
	private double deltaT = 0;
	private double speed = 0.5;
	private double initYaw = -999;
	private double Ktheta = 0.05;

    public PDriveDistance(double _speed, double _dist) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	distSetpoint = _dist;
    	speed = _speed;
    }
    
    public PDriveDistance(double _dist) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	distSetpoint = _dist;
    	speed = 0.5;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	lastVoltage = 0;
    	Robot.driveTrain.resetEncoders();
    	initYaw = Robot.driveTrain.ahrs.getAngle();
    	timer.reset();
    	timer.start();
    	System.out.println("Init PDrive");
    	lastTime = 0;
    }
    
    // current algorithm assumes that we are starting
    // from a stop
    private double linearAccel(double input) {
    	double Klin = 0.8;
    	double deltaT = timer.get() - lastTime;
    	lastTime = timer.get();
    	
    	double Volts = lastVoltage + Klin * (deltaT);
    	if (Volts > input) {
    		Volts = input;
    	}
    	lastVoltage = Volts;
    	return Volts;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// compute the pid P value
    	double pfactor = speed * Robot.clamp(1, -1, RobotMap.Values.driveDistanceP * piderror());
    	double pfactor2 = linearAccel(pfactor);
    	double deltaTheta = Robot.driveTrain.ahrs.getAngle() - initYaw;
    	deltaT = timer.get() - lastTime;
    	lastTime = timer.get();

    	// calculate yaw correction
    	double yawcorrect = deltaTheta * Ktheta;
    	
    	// set the output voltage
    	Robot.driveTrain.SetVoltages(-pfactor2 + yawcorrect, -pfactor2 - yawcorrect);
    	//Robot.driveTrain.SetVoltages(-pfactor, -pfactor); //without yaw correction, accel

    	// Debug information to be placed on the smart dashboard.
    	SmartDashboard.putNumber("Setpoint", distSetpoint);
    	SmartDashboard.putNumber("Encoder Distance", Robot.driveTrain.getEncoderTicks());
    	SmartDashboard.putNumber("Encoder Rate", Robot.driveTrain.getEncoderRate());
    	SmartDashboard.putNumber("Distance Error", piderror());
    	SmartDashboard.putNumber("K-P factor", pfactor);
    	SmartDashboard.putNumber("K-P factor Accel", pfactor2);
    	SmartDashboard.putNumber("deltaT", deltaT);
    	SmartDashboard.putNumber("Theta Correction", yawcorrect);
    	SmartDashboard.putBoolean("On Target", onTarget());
    	SmartDashboard.putNumber("NavX Heading", Robot.driveTrain.ahrs.getAngle());
    	SmartDashboard.putNumber("Init Yaw", initYaw);
    }

    private double piderror() {
    	return distSetpoint - Robot.driveTrain.getEncoderTicks();
    }
    
    private boolean onTarget() {
    	return piderror() < minError;
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("PDrive End");
    	timer.stop();
    	Robot.driveTrain.StopVoltage();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
