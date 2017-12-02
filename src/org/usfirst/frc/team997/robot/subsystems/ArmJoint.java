package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArmJoint extends Subsystem {
	
	public CANTalon Motor;
	public static final double absoluteTolerance = 0.01;

    // Initialize your subsystem here
    public ArmJoint() {
    	
    	Motor = new CANTalon(RobotMap.Ports.bucketLifter);
    	Motor.reset();
    	Motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);

    	Motor.clearStickyFaults();
    	Motor.setSafetyEnabled(false);
    	//Motor.enableLimitSwitch(true, true);
    	//Motor.enableBrakeMode(false);
    	//Motor.enable();
    	Motor.changeControlMode(TalonControlMode.PercentVbus);
    	Motor.set(0);
    	
    	LiveWindow.addActuator("ArmJoint", 1, (CANTalon) Motor);
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
    	double angle = Motor.getEncPosition();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void stop() {
    	//System.out.println("Stop Arm");
    	//Motor.changeControlMode(TalonControlMode.PercentVbus);
    	Motor.set(0.0);
    }
    
    public void setVoltage(double volts) {
    	//Motor.changeControlMode(TalonControlMode.PercentVbus);
    	Motor.set(volts);
    }
    
    public void setArmSetpoint(double angle) {
    }    
    
    public void updateSmartDashboard() {
    	SmartDashboard.putNumber("ArmJoint Encoder", Motor.getEncPosition());
    	SmartDashboard.putNumber("Arm Voltage", Motor.getOutputVoltage());
    	SmartDashboard.putBoolean("Holo1", Motor.isFwdLimitSwitchClosed());
    	SmartDashboard.putBoolean("Holo2", Motor.isRevLimitSwitchClosed());
    }
}
