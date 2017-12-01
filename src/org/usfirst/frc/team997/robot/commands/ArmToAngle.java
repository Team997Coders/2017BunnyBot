package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmToAngle extends Command {
	
	private final double angle;
	
	//Preset Levels
	public static ArmToAngle low() { return new ArmToAngle(RobotMap.Values.armPosOne); }
	public static ArmToAngle medium() { return new ArmToAngle(RobotMap.Values.armPosTwo); }
	public static ArmToAngle high() { return new ArmToAngle(RobotMap.Values.armPosThree); }

    public ArmToAngle(double angle) {
        // Use requires() here to declare subsystem dependencies
    	this.angle = angle;
        requires(Robot.armJoint);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.armJoint.setSetpoint(angle);
    	//Robot.armJoint.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.armJoint.setSetpoint(angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       // return Robot.armJoint.onTarget();
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
