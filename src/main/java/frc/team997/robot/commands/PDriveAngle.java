package main.java.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.java.frc.team997.robot.Robot;

//Allows us to turn the robot to a specified angle.
public class PDriveAngle extends Command {
	private double angSetpoint;
	private double minError = 5;
	private double initYaw = -999;
	//private double Ktheta = 0.015; 
	private double Ktheta = 0.02;

    public PDriveAngle(double _ang) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	angSetpoint = _ang;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	initYaw = 0; //Robot.driveTrain.ahrs.getAngle();
    	System.out.println("Init PAngle");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// calculate yaw correction
    	double yawcorrect = piderror() * Ktheta;
    	Robot.driveTrain.SetVoltages(-yawcorrect, yawcorrect);
    	// Debug information to be placed on the smart dashboard.
    	SmartDashboard.putNumber("Angle Error", piderror());
    	SmartDashboard.putNumber("Theta Angle Correction", yawcorrect);
    	SmartDashboard.putBoolean("On Angle Target", onTarget());
    	SmartDashboard.putNumber("Init Angle Yaw", initYaw);
    }

    private double piderror() {
    	return 0; //initYaw + angSetpoint - Robot.driveTrain.ahrs.getAngle();
    }
    
    private boolean onTarget() {
    	return Math.abs(piderror()) < minError;
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return onTarget();   
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.StopVoltage();
    	System.out.println("PAngle End");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
