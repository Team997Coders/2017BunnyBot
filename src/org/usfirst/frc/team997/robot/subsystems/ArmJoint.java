package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArmJoint extends Subsystem {
	
	public TalonSRX Motor;
	public SensorCollection sensorCollection;
	public static final double absoluteTolerance = 0.01;
	public boolean isZeroed = false;
	public int absolutePosition;
	
    // Initialize your subsystem here
    public ArmJoint() {
    	
    	Motor = new TalonSRX(RobotMap.Ports.bucketLifter);
    	
    	absolutePosition = Motor.getSelectedSensorPosition(0); // & 0xFFF;
    	Motor.setSelectedSensorPosition(absolutePosition, 0, 10);
    	
    	Motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    	Motor.setSensorPhase(false);
    	Motor.clearStickyFaults(10);
    	Motor.enableCurrentLimit(false);
    	Motor.configNominalOutputForward(0, 10);
    	Motor.configNominalOutputReverse(0, 10);
    	Motor.configPeakOutputForward(1, 10);
    	Motor.configPeakOutputReverse(-1, 10);
    	Motor.configAllowableClosedloopError(0, 0, 10);
    	Motor.selectProfileSlot(0, 0);
    	Motor.config_kP(0, RobotMap.Values.armPidP, 10);
    	Motor.config_kI(0, RobotMap.Values.armPidI, 10);
    	Motor.config_kD(0, RobotMap.Values.armPidD, 10);
    	Motor.config_kF(0, 0, 10);
    	
    	sensorCollection = new SensorCollection(Motor);

    	//Motor.set(ControlMode.PercentOutput, 0);
    	
    	//LiveWindow.addActuator("ArmJoint", 1, (Sendable) Motor);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public double AngleToEncoderTick(double angle) {
    	double countPerDegree = 1024 / 360;
    	return countPerDegree * angle;
    }
    
    public void autozero() {
    	if (sensorCollection.isRevLimitSwitchClosed() && !isZeroed) {
    		isZeroed = true;
    		Motor.setSelectedSensorPosition(0, 0, 10);
    		System.out.println("Zeroed " + Motor.getSelectedSensorPosition(0));
    	}
    }
    
    public void getPosition(double NewAngle) {
    	absolutePosition = Motor.getSelectedSensorPosition(0);// & 0xFFF;
    	Motor.setSelectedSensorPosition(absolutePosition, 0, 10);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void stop() {
    	//System.out.println("Stop Arm");
    	Motor.set(ControlMode.PercentOutput, 0.0);
    }
    
    public void lock(double angle) {
    	Motor.set(ControlMode.Position, angle);
    }
    
    public void setVoltage(double volts) {
    	Motor.set(ControlMode.PercentOutput, volts);
    }
    
    public void setArmSetpoint(double angle) {
    }    
    
    public void updateSmartDashboard() {
    	absolutePosition = Motor.getSelectedSensorPosition(0);// & 0xFFF;
    	
       	SmartDashboard.putNumber("TalonSRX Mode", Motor.getControlMode().value);
    	SmartDashboard.putNumber("Absolute position", absolutePosition);
    	SmartDashboard.putNumber("Arm Voltage", Motor.getMotorOutputVoltage());
    	SmartDashboard.putBoolean("Holo1", sensorCollection.isFwdLimitSwitchClosed());
    	SmartDashboard.putBoolean("Holo2", sensorCollection.isRevLimitSwitchClosed());
    	SmartDashboard.putBoolean("ArmZeroed", Robot.armJoint.isZeroed);
    	SmartDashboard.putNumber("ArmPIDError", Motor.getClosedLoopError(0));
    	SmartDashboard.putNumber("Arm Position ", Motor.getSelectedSensorPosition(0));
    }
}
