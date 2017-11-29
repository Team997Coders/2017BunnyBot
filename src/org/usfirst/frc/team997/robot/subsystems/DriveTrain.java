package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.ArcadeDrive;
import org.usfirst.frc.team997.robot.commands.TankDrive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
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
    public AHRS ahrs;
    
    public double PrevLeftV, PrevRightV;
    
    public SmartDashboard dash;
   
    //variables here
    public int gear; 
    public boolean gyroPresent = true;
    
    public DriveTrain() {
    	leftMotor = new VictorSP(RobotMap.Ports.leftMotorPort);
    	rightMotor = new VictorSP(RobotMap.Ports.rightMotorPort);
    	leftEncoder = new Encoder(RobotMap.Ports.leftEncoderFirstPort, RobotMap.Ports.leftEncoderSecondPort);
    	rightEncoder = new Encoder(RobotMap.Ports.rightEncoderFirstPort, RobotMap.Ports.rightEncoderSecondPort);
    	shiftSolenoid = new DoubleSolenoid(RobotMap.Ports.shifterSolenoidLow, RobotMap.Ports.shifterSolenoidHigh);
    	
    	try {
    		ahrs = new AHRS(RobotMap.Ports.AHRS);
    	} catch(RuntimeException e) {
    		System.out.println("Error with ahrs");
    	}
    	
    	gyroPresent = waitforgyro();
    	ahrs.reset();
    	leftEncoder.reset();
    	rightEncoder.reset();
    	
    	gear = 0;
    	shiftSolenoid.set(DoubleSolenoid.Value.kForward);
    	//this.shift(0);
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
    
    public boolean waitforgyro() {
    	int count = 0;
    	if (ahrs.isConnected()) {
    		while (ahrs.isCalibrating()) {
    			Timer.delay(0.2);
        		count +=1;
    		}
    		if (count > 20) {
        		return false;
        	}
    		return true;
        	}
    	//return false;
    	return true;
    	//so, it is reading as though the ahrs is not connected...
    	}
    	
    	
    	
    private double deccelIterate(double v, double prevV) {
    	/*if (disableDeccel == 1) {
    		return v;
    	}*/
    	
    	if ((v >= prevV && prevV >= 0) || (v <= prevV && prevV <= 0)) {
    		prevV = v;
    	} else {
    		if(Math.abs(prevV) <= RobotMap.Values.DeccelSpeed) {
    			prevV = v;
    		} else {
    			prevV = prevV / RobotMap.Values.DeccelDivider;
    		}
    	}
    	return prevV;
    }
    
    public void driveDeccel(double leftv, double rightv) {
    	PrevLeftV = deccelIterate(leftv, PrevLeftV);
    	leftMotor.set(-PrevLeftV*RobotMap.Values.DriveSpeedMod);
    	
    	PrevRightV = deccelIterate(rightv, PrevRightV);
    	rightMotor.set(PrevRightV*RobotMap.Values.DriveSpeedMod);
    }
    
    
    public void setReverseVoltages(double LeftVolts, double RightVolts) {
    	leftMotor.set(-LeftVolts);
    	rightMotor.set(-RightVolts);
    	
    }
    
    public void SetVoltages(double LeftVolts, double RightVolts) {
    	leftMotor.set(-Robot.clamp(1, -1, LeftVolts));
    	rightMotor.set(Robot.clamp(1, -1, RightVolts));
    	//Clamp is being a voltage limiter here, in case you wanted to know.
    	//dash.setDefaultNumber("Left Voltage", LeftVolts);
    	//dash.setDefaultNumber("Right Voltage", RightVolts);
    }
    
    public void StopVoltage() {
	    //dash.setDefaultNumber("Gear iN Use", gear);
    	leftMotor.set(0);
    	rightMotor.set(0);
    }
    
    public void initDefaultCommand() {
    	//setDefaultCommand(new ArcadeDrive());
    	setDefaultCommand(new TankDrive());
    }
}


