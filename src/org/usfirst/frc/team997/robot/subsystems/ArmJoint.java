package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ArmJoint extends Subsystem {
	
	public CANTalon Motor;
	public Encoder ArmAngle;
	public static final double absoluteTolerance = 0.01;

    // Initialize your subsystem here
    public ArmJoint() {
    	
    	Motor = new CANTalon(RobotMap.Ports.bucketLifter);
    	Motor.clearStickyFaults();
    	Motor.changeControlMode(TalonControlMode.PercentVbus);
    	Motor.setSafetyEnabled(false);
    	Motor.reset();
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
