package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDrive extends Command {

    public TankDrive() {
        requires(Robot.driveTrain);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double[] volts = getVolts();
    	volts = Robot.driveTrain.DecellCheck(volts[0], volts[1]);
    	Robot.driveTrain.SetVoltages(volts[0], volts[1]);
    }
    public double[] getVolts() {
    	double[] volts = new double[2];
    	double leftMotorSpeed = Robot.oi.GamePad.getRawAxis(1);
    	double rightMotorSpeed = Robot.oi.GamePad.getRawAxis(1);
    	
    	volts[0] = leftMotorSpeed;
    	volts[1] = rightMotorSpeed;
    	return volts;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.SetVoltages(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.SetVoltages(0, 0);
    }
}
