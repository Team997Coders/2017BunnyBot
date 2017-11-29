package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.SerialPort;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static class Ports{
		public static int 
		
		//NAME
		
		//PWM *TEMPORARY*
		leftMotorPort = 1,  
		rightMotorPort = 0,
		bucketLifter = 1,
		
		//DIO *TEMPORARY*
		leftEncoderFirstPort = 2,
		leftEncoderSecondPort = 3,
		rightEncoderFirstPort = 0,
		rightEncoderSecondPort = 1,
		armEncoderFirstPort = 4,
		armEncoderSecondPort = 5,
		
		//PNEUMATICS
		//Need to ask electrical to clarify on i/o map which "solenoid - big piston" is which
		shifterSolenoidLow = 0,
		shifterSolenoidHigh = 1,
		clawLeftSolenoidPort = 2,
		clawRightSolenoidPort = 3,
		trapdoorSolenoidPort = 5,
		
		//ANALOG
		
		//SPIKE
		ringLightPort = 0,
		flashLightPort = 1,
		
		//JOYSTICKS *TEMPORARY*
		GamePadPort = 0,
		
		//BUTTONS
		
		/*Button|number|command called
		        |      |
		   A    |1	   |decell toggle	
		   B    |2     |shift gear
		   X    |3     |open/close claw
		   Y    |4     |reverse drivetrain
		   Lb   |5     |
		   Rb   |6     |
		   Back |7     |
		   Start|8     |
		 */
		
		decellToggleButton = 1,
		shiftButton = 2,
		clawButton = 3,
		reverseToggButton = 4,
		
		//THE PLURAL TO AXIS 
		leftXAxisPort = 0,
		leftYAxisPort = 1,
		rightXAxisPort = 4,
		rightYAxisPort = 5;
    
		//GYRO
		public static final SerialPort.Port AHRS = SerialPort.Port.kUSB;
		
	}
	
	public static class Values{
		public static double
		
		highSpeed = 1,
		lowSpeed = 0.5,
		driveMult = -0.05,
		autoDriveDistance = 360,
		
		//Drive Distance PID
		
		driveDistanceP = 0,
		driveDistanceI = 0,
		driveDistanceD = 0,
		
		//Drive Angle PID
		
		driveAngleP = 0,
		driveAngleI = 0,
		driveAngleD = 0,
		
		//PID Values
		armPidP = 0.0,
		armPidI = 0.0,
		armPidD = 0.0,
		armPidMinimumInput = 0.0,
		armPidMaximumInput = 0.0,
		
		//Arm Position
		armPosOne = 0,
		armPosTwo = 1,
		armPosThree = 2,
		
		//Deccel
		DeccelSpeed = 0.2,
		DeccelDivider = 1.2,
		DriveSpeedMod = 1;
	
	}
		
}
	
