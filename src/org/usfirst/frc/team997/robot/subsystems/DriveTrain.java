package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

    public VictorSP leftMotor, rightMotor;
    public Encoder leftEncoder, rightEncoder;
    public DoubleSolenoid shiftSolenoid;
    
    //variables here
    public int gear;
    
    //arbitrary motor name
    
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
    
    public void shift(int g) {
    	if (gear != g && gear != 0) {
    		shiftSolenoid.set(DoubleSolenoid.Value.kForward);
    		gear = 0;
    	} else if (gear != g) {
    		shiftSolenoid.set(DoubleSolenoid.Value.kReverse);
    		gear = 1;
    	}
    }
    
    public double[] DecellCheck(double LeftVoltage, double RightVoltage) {
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
    }
    
    public void SetVoltages(double LeftVolts, double RightVolts) {
    	leftMotor.set(LeftVolts);
    	rightMotor.set(-RightVolts);
    }
    
    public void StopVoltage() {
    	leftMotor.set(0);
    	rightMotor.set(0);
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new ArcadeDrive());
    }
}


