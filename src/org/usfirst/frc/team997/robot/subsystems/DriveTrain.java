package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
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
    public double accellRate;
    
    //accellRate controls how fast the robot can accelerate. -Timothy
    
    //arbitrary motor name
    
    public DriveTrain() {
    	leftMotor = new VictorSP(RobotMap.Ports.leftMotorPort);
    	rightMotor = new VictorSP(RobotMap.Ports.rightMotorPort);
    	leftEncoder = new Encoder(RobotMap.Ports.leftEncoderFirstPort, RobotMap.Ports.leftEncoderSecondPort);
    	rightEncoder = new Encoder(RobotMap.Ports.rightEncoderFirstPort, RobotMap.Ports.rightEncoderSecondPort);
    	shiftSolenoid = new DoubleSolenoid(RobotMap.Ports.shifterSolenoidLow, RobotMap.Ports.shifterSolenoidHigh);
    	
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
    
    //If the robot goes backwards when you push forwards, this is why. v
    
    public void driveVoltage(double leftSpeed, double rightSpeed) {
    	
    	leftMotor.set(leftSpeed);
    	rightMotor.set(rightSpeed);
    	
    }
    
    // FreeStick is a random storage double. -Timothy
    
    public double[] decellerate(double leftVolts, double rightVolts) {
    	
    	double FreeStick = 0.0;
    	double[] decellVolts = new double[2];
    	
    	if(Math.abs(leftVolts - leftMotor.get()) > 0.4) {
    		if(leftVolts < leftMotor.get()) {
    			FreeStick = leftMotor.get() - accellRate;
    		} else {
    			FreeStick = leftMotor.get() + accellRate;
    		}
    	}
    	decellVolts[0] = FreeStick;	
    	
    	if(Math.abs(rightVolts - rightMotor.get()) > 0.4) {
    		if(rightVolts < rightMotor.get()) {
    			FreeStick = rightMotor.get() - accellRate;
    		} else {
    			FreeStick = rightMotor.get() + accellRate;
    		}
    	}
    	decellVolts[1] = FreeStick;
    	
    	
    	return decellVolts;
    	
    }
    
    public void stopVoltage() {
    	
    	leftMotor.set(0);
    	rightMotor.set(0);
    }
    
    public void initDefaultCommand() {
    	//lonely and does nothing
    }
}


