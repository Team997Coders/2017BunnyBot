package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArmToAngle extends Command {
	
	public static boolean locked;
	private final double angle;
	
	//Preset Levels
	//public static ArmToAngle low() { return new ArmToAngle(RobotMap.Values.armPosOne); }
	//public static ArmToAngle medium() { return new ArmToAngle(RobotMap.Values.armPosTwo); }
	//public static ArmToAngle high() { return new ArmToAngle(RobotMap.Values.armPosThree); }

    public ArmToAngle(double angle) {
        // Use requires() here to declare subsystem dependencies
    	this.angle = angle;
        requires(Robot.armJoint);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (!Robot.armJoint.isZeroed) {
    		System.out.println("Not zeroed!");
    		end();
    		cancel();
    	}
    	//SmartDashboard.putNumber("setting arm to angle", angle);
    	//Robot.armJoint.Motor.changeControlMode(TalonControlMode.Position);
    	
    	//Robot.armJoint.setSetpoint(angle);
    	//Robot.armJoint.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.armJoint.Motor.set(ControlMode.Position, angle);
    	System.out.println("setting arm to angle " + angle);
    	//Robot.armJoint.Motor.enable();
    	//Robot.armJoint.setSetpoint(angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	int closedLoopError = Robot.armJoint.Motor.getClosedLoopError(0);
    	System.out.println("in arm2angle isfinished: " + closedLoopError);
    	System.out.println("   ... output voltage " + Robot.armJoint.Motor.getOutputCurrent());
       // return Robot.armJoint.onTarget();
    	return !Robot.armJoint.isZeroed || (Math.abs(closedLoopError) < 60);
    	//return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.armJoint.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
