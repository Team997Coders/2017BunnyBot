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
		leftMotorPort = 1,  
		rightMotorPort = 0,
		bunnyCollector = 3,
		bucketLifter = 4,
		
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
		 * A    |1	   |decell toggle	
		 * B    |2     |shift gear
		 * X    |3     |open/close claw
		 * Y    |4     |reverse drivetrain
		 * Lb   |5     |
		 * Rb   |6     |
		 * Back |7     |
		 * Start|8     |
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
	}
	
	public static class Values{
		public static double
		
		//PID Values
		armPidP = 0.0,
		armPidI = 0.0,
		armPidD = 0.0,
		armPidMinimumInput = 0.0,
		armPidMaximumInput = 0.0,
		
		//Arm Position
		armPosOne = 0,
		armPosTwo = 1,
		armPosThree = 2;
	
	}
		
}
	
