package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Things to expect to go wrong:
 * 		- It may be in Voltage Mode from it is supposed to be in Position. Change this using the
 * 			function 'changeControlMode' in the CANTalon.
 * 		- Check your values in RobotMap and make sure the PiD values are set accordingly and that
 * 			the positions ARE NOT set to 1, 2, 3. That will NOT F%$#ing work.
 * 		- Be sure you are using the correct 'set' function (There are 3) for your situation. My
 * 			command (and basically entire SubSystem) was ripped off of Floyd so ask him, and hopefully
 * 			it won't be too foreign to him.
 * 
 * Notes:
 * 		- I basically pulled a script kiddy on this one so if you are trying to debug this and you are
 * 			not Floyd, good f%$#ing luck m8. You can use me as tech support (Just about as good as your off brand quad copters tech support)
 * 			however so you can text your infinite complaints @(541) 745 - 8738
 */
public class Arm extends Subsystem {
	
	CANTalon armMotorCont;
	double armPosition = 0.0;
	boolean isArmEncoderReset = false;
	
	public Arm() {
		armMotorCont = new CANTalon(RobotMap.Ports.bucketLifter);
		armMotorCont.changeControlMode(TalonControlMode.PercentVbus);
		armMotorCont.setSafetyEnabled(false);
		armMotorCont.reset();
		armMotorCont.clearStickyFaults();
    	armMotorCont.enableBrakeMode(true);
    	armMotorCont.set(0);
    	
    	armMotorCont.setEncPosition(armMotorCont.getEncPosition());
    	armMotorCont.enableZeroSensorPositionOnForwardLimit(true);
    	
    	// Some initial user feedback
    	FeedbackDeviceStatus status = armMotorCont.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative);
    	switch(status) {
    	case FeedbackStatusPresent:
    		SmartDashboard.putBoolean("Arm Encoder Status", true);
    		System.out.println("Arm Joint Encoder status OK");
    		break;
    	case FeedbackStatusNotPresent:
    		SmartDashboard.putBoolean("Arm Encoder Status", false);
    		System.out.println("Arm Joint Feedback device missing!");
    		break;
    	case FeedbackStatusUnknown:
    		SmartDashboard.putBoolean("Arm Encoder Status", false);
    		System.out.println("Unknown Error in Arm Joint encoder!");
    		break;
    	}
    	
    	armMotorCont.configNominalOutputVoltage(RobotMap.Values.armPidPeakOutput, -RobotMap.Values.armPidPeakOutput);
    	armMotorCont.configPeakOutputVoltage(RobotMap.Values.armPidPeakOutput, -RobotMap.Values.armPidPeakOutput);
    	
    	armMotorCont.setAllowableClosedLoopErr(0);
    	armMotorCont.setProfile(0);
    	armMotorCont.setF(RobotMap.Values.armPidF);
    	armMotorCont.setP(RobotMap.Values.armPidP);
    	armMotorCont.setI(RobotMap.Values.armPidI);
    	armMotorCont.setD(RobotMap.Values.armPidD);
    	
    	//armMotorCont.changeControlMode(TalonControlMode.Position);
	}

    public void initDefaultCommand() {
    	
    }
    
    /*public double getArmPosition() {			Floyd used a different one some I'm using his.
    	return armMotorCont.getEncPosition();
    }*/
    
    public double getArmPosition() {
    	armPosition = armMotorCont.getEncPosition() * 0.00539;
   	
    	if (Math.abs(armPosition) > 0.0) {
    		isArmEncoderReset = false;
    	}
    	return armPosition;
    }
    
    public void setArmSetpoint(double _angle) {
    	armMotorCont.setSetpoint(_angle);
    	armMotorCont.enable();
    }
    
    public double getArmError() {
       	return armMotorCont.getClosedLoopError();
       }
    
    public void setVoltage(double Speed) {
    	if (Speed > RobotMap.Values.maxArmSpeed) {
    		Speed = RobotMap.Values.maxArmSpeed;
    	}
    	else if (Speed < -RobotMap.Values.maxArmSpeed) {
    		Speed = -RobotMap.Values.maxArmSpeed;
    	}
    	armMotorCont.set(Speed);
    }
    
    public void stop() {
    	armMotorCont.changeControlMode(TalonControlMode.PercentVbus);
    	armMotorCont.set(0);
    }
    
    public void updateSmartDashboard() {
    	//autoResetEncoder();
    	SmartDashboard.putNumber("Arm Angle Setpoint", armMotorCont.getSetpoint());
    	SmartDashboard.putNumber("ARM Pid Error", getArmError());
//    	SmartDashboard.putNumber("Absolute Encoder Loops", loops);
    	SmartDashboard.putNumber("Absolute Encoder Position", armMotorCont.getAnalogInPosition());
    	SmartDashboard.putBoolean("Is Arm Endoder In Reset Position?", isArmEncoderReset);
    	SmartDashboard.putBoolean("Forward Limit?", armMotorCont.isFwdLimitSwitchClosed());
    	SmartDashboard.putBoolean("Reverse Limit?", armMotorCont.isRevLimitSwitchClosed());
    	Robot.oi.get_pov();
    }
}

