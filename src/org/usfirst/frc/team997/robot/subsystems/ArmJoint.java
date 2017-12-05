package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;
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
	public boolean isZeroed = false;

    // Initialize your subsystem here
    public ArmJoint() {
    	
    	Motor = new CANTalon(RobotMap.Ports.bucketLifter);
    	Motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	Motor.clearStickyFaults();
    	Motor.setSafetyEnabled(false);
    	Motor.configNominalOutputVoltage(0, 0);
    	Motor.configPeakOutputVoltage(9, -9);
    	Motor.setAllowableClosedLoopErr(10);
    	Motor.setProfile(0);
    	Motor.setP(RobotMap.Values.armPidP);
    	Motor.setI(RobotMap.Values.armPidI);
    	Motor.setD(RobotMap.Values.armPidD);
    	Motor.setF(0);

    	//Motor.configEncoderCodesPerRev(1);
    	//Motor.enableLimitSwitch(true, true);
    	//Motor.enableBrakeMode(false);
    	//Motor.enable();
    	//Motor.changeControlMode(TalonControlMode.PercentVbus);
    	Motor.enableZeroSensorPositionOnReverseLimit(true);
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
    
    public void getPosition(double NewAngle) {
    	double angle = Motor.getEncPosition();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void stop() {
    	//System.out.println("Stop Arm");
    	Motor.changeControlMode(TalonControlMode.PercentVbus);
    	Motor.set(0.0);
    }
    
    public void setVoltage(double volts) {
    	//Motor.changeControlMode(TalonControlMode.PercentVbus);
    	Motor.set(volts);
    }
    
    public void setArmSetpoint(double angle) {
    }    
    
    public void updateSmartDashboard() {
    	SmartDashboard.putNumber("TalonSRX Mode", Motor.getControlMode().value);
    	SmartDashboard.putNumber("Arm Voltage", Motor.getOutputVoltage());
    	SmartDashboard.putBoolean("Holo1", Motor.isFwdLimitSwitchClosed());
    	SmartDashboard.putBoolean("Holo2", Motor.isRevLimitSwitchClosed());
    	SmartDashboard.putBoolean("ArmZeroed", Robot.armJoint.isZeroed);
    	SmartDashboard.putNumber("ArmPIDError", Motor.getClosedLoopError());
    	SmartDashboard.putNumber("Arm Position ", Motor.getPosition());
    }
}
