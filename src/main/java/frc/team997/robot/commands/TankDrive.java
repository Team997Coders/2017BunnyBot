package main.java.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.java.frc.team997.robot.Robot;
import main.java.frc.team997.robot.RobotMap;

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

    	if (Robot.oi.reverseBool) {
    		Robot.driveTrain.setReverseVoltages(volts[0], volts[1]);
    	} else {
            if (Robot.oi.decellOn) {
                Robot.driveTrain.driveDeccel(volts[0], volts[1]);
            } else {
                Robot.driveTrain.SetVoltages(volts[0], volts[1]);
            }
    	}
  
    }
    public double[] getVolts() {
    	double[] volts = new double[2];
    	double leftMotorSpeed = Robot.JoystickDeadband(Robot.oi.GamePad.getRawAxis(RobotMap.Ports.leftYAxisPort));
    	double rightMotorSpeed = Robot.JoystickDeadband(Robot.oi.GamePad.getRawAxis(RobotMap.Ports.rightYAxisPort));
    	
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
