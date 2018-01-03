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
    private VictorSP leftMotor, rightMotor;
    private Encoder leftEncoder, rightEncoder;
    private DoubleSolenoid shiftSolenoid;
    public AHRS ahrs;
   
    //variables here
    public int gear; 
    public boolean gyroPresent = true;
    
    public double PrevLeftV, PrevRightV;
    
    public DriveTrain() {
    	leftMotor = new VictorSP(RobotMap.Ports.leftMotorPort);
    	rightMotor = new VictorSP(RobotMap.Ports.rightMotorPort);
    	leftEncoder = new Encoder(RobotMap.Ports.leftEncoderFirstPort, RobotMap.Ports.leftEncoderSecondPort);
    	rightEncoder = new Encoder(RobotMap.Ports.rightEncoderFirstPort, RobotMap.Ports.rightEncoderSecondPort);
    	rightEncoder.setReverseDirection(true);
    	rightEncoder.setDistancePerPulse(1/2873); //2873 ticks/foot
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
    	shiftSolenoid.set(DoubleSolenoid.Value.kReverse);
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
    	leftMotor.set(LeftVolts);
    	rightMotor.set(-RightVolts);
    	
    }
    
    public void SetVoltages(double LeftVolts, double RightVolts) {
    	leftMotor.set(-Robot.clamp(1, -1, LeftVolts));
    	rightMotor.set(Robot.clamp(1, -1, RightVolts));
    	SmartDashboard.putNumber("leftvoltage", -Robot.clamp(1, -1, LeftVolts));
    	SmartDashboard.putNumber("rightvoltage", Robot.clamp(1, -1, RightVolts));
    	//Clamp is being a voltage limiter here, in case you wanted to know.
    	
    }
    
    public void pidWrite(double output) {
    	leftMotor.pidWrite(output);
    	rightMotor.pidWrite(output);
    }
    
    public void StopVoltage() {
	    leftMotor.set(0);
    	rightMotor.set(0);
    }
    
    public void initDefaultCommand() {
    	if(Robot.oi.arcadeDrive) {
    		setDefaultCommand(new ArcadeDrive());
    	} else {
    		setDefaultCommand(new TankDrive());
    	}
    	
    }
    
    public void resetEncoders() {
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    public void resetGyro() {
    	ahrs.reset();
    }
    
    public double getPidEncoder() {
    	return rightEncoder.pidGet();
    }
    
    public double getEncoderRate() {
    	return rightEncoder.getRate();
    }
    
    public double getEncoderDistance() {
    	return rightEncoder.getDistance();
    }
    
    public double getEncoderTicks() {
    	return rightEncoder.get();
    }

    
    public void updateSmartDashboard() {
    	//dash.setDefaultNumber("Gear iN Use", gear);
    	//dash.setDefaultNumber("Left Voltage", LeftVolts);
    	//dash.setDefaultNumber("Right Voltage", RightVolts);
    	SmartDashboard.putNumber("Left encoder value", leftEncoder.get());
    	SmartDashboard.putNumber("Right encoder value", rightEncoder.get());
    	SmartDashboard.putNumber("right encoder distance", rightEncoder.getDistance());
    	SmartDashboard.putNumber("NavX angle", ahrs.getAngle());
    	SmartDashboard.putBoolean("gyroPresent", gyroPresent);
    	SmartDashboard.putBoolean("Decell (drivetrain)", Robot.oi.decellOn);
    	SmartDashboard.putNumber("Gear", gear);
    	SmartDashboard.putBoolean("Arcadedrive (drivetrain)", Robot.oi.arcadeDrive);
    }
}


