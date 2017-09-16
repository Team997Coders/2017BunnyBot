package org.usfirst.frc.team997.robot;

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
		
		//ANALOG
		
		//JOYSTICKS *TEMPORARY*
		GamePadPort = 0,
		
		//BUTTONS
		speedShiftButton = 2,
		bunnyCollectorButton = 3,
		bucketLifterButton = 1,
		
		//THE PLURAL TO AXIS *TEMPORARY*
		leftXAxisPort = 0,
		leftYAxisPort = 1,
		rightXAxisPort = 2,
		rightYAxisPort = 3;
	}
	
	public static class Values{
		public static double
		
		highSpeed = 1,
		lowSpeed = 0.5;
		
	}
	
}
