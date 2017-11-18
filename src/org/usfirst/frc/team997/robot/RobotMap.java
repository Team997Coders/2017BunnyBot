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
		leftMotorPort = 0,
		rightMotorPort = 1,
		bunnyCollector = 3,
		bucketLifter = 4,
		
		//DIO *TEMPORARY*
		leftEncoderFirstPort = 0,
		leftEncoderSecondPort = 1,
		rightEncoderFirstPort = 2,
		rightEncoderSecondPort = 3,
		
		//PNEUMATICS
		shifterSolenoidLow = 0,
		shifterSolenoidHigh = 1,
		armMoverSolenoidPort = 2,
		armOpenerSolenoidPort = 3,
		trapdoorSolenoidPort = 5,
		
		//ANALOG
		
		//SPIKE
		ringLightPort = 0,
		flashLightPort = 1,
		
		//JOYSTICKS *TEMPORARY*
		GamePadPort = 0,
		
		//BUTTONS
		shiftDownButton = 0,
		shiftUpButton = 2,
		bunnyCollectorButton = 3,
		bucketLifterButton = 1,
		
		//THE PLURAL TO AXIS *TEMPORARY*
		leftXAxisPort = 0,
		leftYAxisPort = 1,
		rightXAxisPort = 2,
		rightYAxisPort = 3;
		
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
		driveAngleD = 0;
		
		
		
	}
	
}
