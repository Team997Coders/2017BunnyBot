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
		
		//PNEUMATICS
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
		GamePad2Port = 1,
		
		//BUTTONS
		shiftDownButton = 0,
		shiftUpButton = 2,
		bunnyCollectorButton = 3,
		bucketLifterButton = 1,
		ArmPosButton1 = 0,
		ArmPosButton2 = 1,
		ArmPosButton3 = 2,
		ArmPosButton4 = 3,
		//ArmPosButton5 = ?
		
		//THE PLURAL TO AXIS *TEMPORARY*
		leftXAxisPort = 0,
		leftYAxisPort = 1,
		rightXAxisPort = 2,
		rightYAxisPort = 3;
	}
	
	public static class Values{
		public static double
		
		highSpeed = 1,
		lowSpeed = 0.5,
		
		//*TEMPORARY* PID Values
				armPidP = 0.0,
				armPidI = 0.0,
				armPidD = 0.0,
				armPidF = 0.0,
				armPidNominalOutput = 0.0,
				armPidPeakOutput = 6.0,
				
				//Arm Values
				maxArmSpeed = 0.3,
				//Arm Position
				armPosOne = 0,
				armPosTwo = 1,
				armPosThree = 2,
				
				//Decell
				DecellSpeed = 0.15,
				DecellDivider = 1.2,
				DriveSpeedMod = 1,
		
				ArmPos1 = 0,
				ArmPos2 = 45,
				ArmPos3 = 90,
				ArmPos4 = 135,
				ArmPos5 = 180;
		
		public static String 
			reverseMotor = "right";
	}
	
}
