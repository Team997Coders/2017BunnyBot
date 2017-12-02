
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
		
		//CAN ID - bucket lifter
		bucketLifter = 1,
		
		//DIO *TEMPORARY*
		leftEncoderFirstPort = 8,
		leftEncoderSecondPort = 9,
		rightEncoderFirstPort = 6,
		rightEncoderSecondPort = 7,
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
		GamePad2Port = 1,
		
		//Gamepad 1:
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
		//Gamepad 2:
		/*Button|number|command called
		        |      |
		   A    |1	   |armPos1
		   B    |2     |armPos2
		   X    |3     |armPos3
		   Y    |4     |armPos4
		   Lb   |5     |
		   Rb   |6     |
		   Back |7     |
		   Start|8     |
		 */
		
    //BUTTONS
		
		ArmPosButton1 = 1,
		ArmPosButton2 = 2,
		ArmPosButton3 = 3,
		ArmPosButton4 = 4,
		//ArmPosButton5 = ?
		ArmFwdButton = 5,
		ArmBwdButton = 6,
		ZeroArm = 7,
		GoToVertical = 8,
    
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
		
		//*TEMPORARY* PID Values
				armPidP = 0.5,
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
			
				ArmPos1 = 0,
				ArmPos2 = 45,
				ArmPos3 = 90,
				ArmPos4 = 135,
				ArmPos5 = 180,
		
		//Deccel
		DeccelSpeed = 0.2,
		DeccelDivider = 1.2,
		DriveSpeedMod = 1;
		
		public static String 
		reverseMotor = "right";
	

	
	}
		
}
	
