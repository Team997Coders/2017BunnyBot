package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class AutomatedTest extends Command {
	
	public Gyro gyro;

    public AutomatedTest() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftMot = 0, rightMot = 0;
    	if (Robot.driveTrain.leftEncoder.get() < 10000 || Robot.driveTrain.rightEncoder.get() < -10000) {
    		if (gyro.getAngle() > 0) {
    			if (gyro.getAngle() >= 20 && gyro.getAngle() <= 34) {
    				leftMot = 0.4;
    				rightMot = 0.55;
    			} else if (gyro.getAngle() >= 35 && gyro.getAngle() <= 89) {
    				leftMot = 0.3;
    				rightMot = 0.65;
    			} else if (gyro.getAngle() >= 90) {
    				leftMot = -0.3;
    				rightMot = 0.3;
    			} else {
    				leftMot = 0.45;
    				rightMot = 0.5;
    			}
    		} else if (gyro.getAngle() < 0) {
    			if (gyro.getAngle() <= -20 && gyro.getAngle() >= -34) {
    				leftMot = 0.55;
    				rightMot = 0.4;
    			} else if (gyro.getAngle() >= -35 && gyro.getAngle() <= -89) {
    				leftMot = 0.65;
    				rightMot = 0.3;
    			} else if (gyro.getAngle() >= -90) {
    				leftMot = 0.3;
    				rightMot = -0.3;
    			} else {
    				leftMot = 0.5;
    				rightMot = 0.45;
    			}
    		} else {
    			leftMot = 0.5;
    			rightMot = 0.45;
    		}
    		
    		if (Robot.driveTrain.leftEncoder.get() > 7500) {
    			leftMot = leftMot / 2;
    			rightMot = rightMot / 2;
    		} else if (Robot.driveTrain.leftEncoder.get() > 9000) {
    			leftMot = (leftMot / 2) / 2;
    			rightMot = (rightMot  /2) / 2;
    		}
    		
    		Robot.driveTrain.SetVoltages(leftMot, rightMot);
    	} else {
    		end();
    	}
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
