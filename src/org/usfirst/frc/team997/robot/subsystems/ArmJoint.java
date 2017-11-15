package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class ArmJoint extends PIDSubsystem {
	
	public TalonSRX Motor;
	public Encoder ArmAngle;
	public static final double absoluteTolerance = 0.01;

    // Initialize your subsystem here
    public ArmJoint() {
    	super("ArmJoint", RobotMap.Values.armPidP, RobotMap.Values.armPidI, RobotMap.Values.armPidD);
    	
    	getPIDController().setAbsoluteTolerance(absoluteTolerance);
    	getPIDController().setInputRange(RobotMap.Values.armPidMinimumInput, RobotMap.Values.armPidMaximumInput);
    	//getPIDController().setOutputRange(-0.5, 0.75); //Set Values Constant
    	
    	Motor = new TalonSRX(RobotMap.Ports.bucketLifter);
    	ArmAngle = new Encoder(RobotMap.Ports.armEncoderFirstPort, RobotMap.Ports.armEncoderSecondPort);
    	
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public double AngleToEncoderTick(double angle) {
    	double countPerDegree = 1024 / 360;
    	return countPerDegree * angle;
    }
    
    public void SetPosition(double NewAngle) {
    	double angle = ArmAngle.get();
    	NewAngle = Math.abs(NewAngle);
    	if (angle < 0) {
    		setSetpoint(-Robot.Clamp(512, -512, NewAngle));
    	} else {
    		setSetpoint(Robot.Clamp(512, -512, NewAngle));
    	}
    	
    	enable();
    }
    
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        return ArmAngle.get();
    }

    protected void usePIDOutput(double output) {
        Motor.pidWrite(output);
    }
}
