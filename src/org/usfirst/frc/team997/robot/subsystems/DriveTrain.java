package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
    //arbitrary motor names
    public VictorSP leftMotor, rightMotor;
    public Encoder leftEncoder, rightEncoder;
    public DoubleSolenoid shiftSolenoid;
    
    public SmartDashboard dash;
    
    //variables here
    public int gear; 
    
    public double PrevLeftV, PrevRightV;
    
    public DriveTrain() {
    	leftMotor = new VictorSP(RobotMap.Ports.leftMotorPort);
    	rightMotor = new VictorSP(RobotMap.Ports.rightMotorPort);
    	leftEncoder = new Encoder(RobotMap.Ports.leftEncoderFirstPort, RobotMap.Ports.leftEncoderSecondPort);
    	rightEncoder = new Encoder(RobotMap.Ports.rightEncoderFirstPort, RobotMap.Ports.rightEncoderSecondPort);
    	shiftSolenoid = new DoubleSolenoid(RobotMap.Ports.shifterSolenoidLow, RobotMap.Ports.shifterSolenoidHigh);
    	
    	leftEncoder.reset();
    	rightEncoder.reset();
    	
    	gear = 0;
    	this.shift(0);
    }
    
    // Gear 0 is low gear, gear 1 is high gear. -Timothy
    
    public void shift(int g) {
    	if (gear != g && gear != 0) {
    		shiftSolenoid.set(DoubleSolenoid.Value.kForward);
    		gear = 0;
    	} else if (gear != g) {
    		shiftSolenoid.set(DoubleSolenoid.Value.kReverse);
    		gear = 1;
    	}
    }
   
    /*public double[] DecellCheck(double LeftVoltage, double RightVoltage) {
    	double[] Volts = new double[2];
    	
    	if (Math.abs(LeftVoltage - leftMotor.get()) > 0.4) {
    		double AHH = 0;
    		if (LeftVoltage < leftMotor.get()) {
    			AHH = leftMotor.get() - 0.4;
    		} else {
    			AHH = leftMotor.get() + 0.4;
    		}
    		Volts[0] = AHH;
    	}
    	if (Math.abs(RightVoltage - rightMotor.get()) > 0.4) {
    		double AHH = 0;
    		if (LeftVoltage < leftMotor.get()) {
    			AHH = leftMotor.get() - 0.4;
    		} else {
    			AHH = leftMotor.get() + 0.4;
    		}
    		Volts[1] = AHH;
    	}
    	
    	return Volts;
    }*/
    
    /*public double[] DecellCheck(double LV, double RV, String reverseMotor) {
    	double[] Vs = new double[2];
    	
    	int LMod = 1, RMod = -1;
    	
    	if (reverseMotor.toLowerCase() == "left") {
    		LMod = -1;
    		RMod = 1;
    	}
    	
    	if (Math.abs((leftMotor.get() * LMod) - (LV * LMod)) > 0.15) {
    		if (leftMotor.get() * LMod < LV * LMod) {
    			Vs[0] = leftMotor.get() * LMod + 0.15;
    		} else {
    			Vs[0] = leftMotor.get() * LMod - 0.15;
    		}
    	} else {
    		Vs[0] = LV;
    	}
    	
    	if (Math.abs((rightMotor.get() * RMod) - (RV * RMod)) > 0.15) {
    		if (rightMotor.get() * RMod < RV * RMod) {
    			Vs[1] = rightMotor.get() * RMod - 0.15;
    		} else {
    			Vs[1] = rightMotor.get() * RMod + 0.15;
    		}
    	} else {
    		Vs[1] = RV;
    	}
    	
    	return Vs;
    }*/

    private double deccelIterate(double v, double prevV) {
    	/*if (disableDeccel == 1) {
    		return v;
    	}*/
    	
    	if ((v >= prevV && prevV >= 0) || (v <= prevV && prevV <= 0)) {
    		prevV = v;
    	} else {
    		if(Math.abs(prevV) <= RobotMap.Values.DecellSpeed) {
    			prevV = v;
    		} else {
    			prevV = prevV / RobotMap.Values.DecellDivider;
    		}
    	}
    	return prevV;
    }
    
    public void driveDeccel(double leftv, double rightv) {
    	PrevLeftV = deccelIterate(leftv, PrevLeftV);
    	leftMotor.set(PrevLeftV*RobotMap.Values.DriveSpeedMod);
    	
    	PrevRightV = deccelIterate(rightv, PrevRightV);
    	rightMotor.set(PrevRightV*RobotMap.Values.DriveSpeedMod);
    }
    
    public void SetVoltages(double LeftVolts, double RightVolts) {
    	leftMotor.set(LeftVolts);
    	rightMotor.set(-RightVolts);
    	dash.setDefaultNumber("Left Voltage", LeftVolts);
    	dash.setDefaultNumber("Right Voltage", RightVolts);
    }
    
    public void StopVoltage() {
	    dash.setDefaultNumber("Gear iN Use", gear);
    	leftMotor.set(0);
    	rightMotor.set(0);
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new ArcadeDrive());
    }
}


