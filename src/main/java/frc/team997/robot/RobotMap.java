
package main.java.frc.team997.robot;

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
		   B    |2     |
		   X    |3     |
		   Y    |4     |reverse drivetrain
		   Lb   |5     |Shift Gear
		   Rb   |6     |Open/Close Claw
		   Back |7     |Zero Claw
		   Start|8     |
		 */
		//Gamepad 2:
		/*Button|number|command called
		        |      |
		   A    |1	   |ForwardMid
		   B    |2     |Open/Close Claw
		   X    |3     |BackwardMid
		   Y    |4     |Vertical
		   Lb   |5     |ForwardLevel
		   Rb   |6     |BackwardLevel
		   Back |7     |ForwardFloor
		   Start|8     |BackwardFloor
		 */
		
    //BUTTONS
		ArmFwdButton = 1,
		ArmBwdButton = 3,
		ZeroArm = 7,
		GoToVertical = 8,
    
		//decellToggleButton = 0,
		shiftButton = 2,
		clawButton = 5,
		reverseToggButton = 4,
		
		//THE PLURAL TO AXIS 
		leftXAxisPort = 0,
		leftYAxisPort = 1,
		rightXAxisPort = 2,
		rightYAxisPort = 3//,
		
		//GAMEPAD2 ARM BUTTONS
		/*
		armPositionVertical = 4,
		armPositionForwardLevel = 5,
		armPositionForwardFloor = 7,
		armPositionBackwardLevel = 6,
		armPositionBackwardFloor = 8,
		armPositionBackwardMid = 3,
		armPositionForwardMid = 1;
		*/;
    
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
		
		driveDistanceP = 0.005,
		driveDistanceI = 0,
		driveDistanceD = 0,
		
		//Drive Angle PID
		
		driveAngleP = 0,
		driveAngleI = 0,
		driveAngleD = 0,

		// Constants to define arm position for encoder
		armPositionBackwardFloor = 0,
		armPositionBackwardLevel = 2706,
		armPositionBackwardMid = 9000,
		armPositionVertical = 16239,
		armPositionForwardMid = 22823,
		armPositionForwardLevel = 29755,
		armPositionForwardFloor = 32647,
		
		
		//*TEMPORARY* PID Values
				armPidP = 0.7,
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
	
	/*public static class Arrays{
		
		public static double[]
		movelist = new double[] {RobotMap.Values.armPositionBackwardLevel, 
				RobotMap.Values.armPositionVertical, RobotMap.Values.armPositionForwardLevel};
	}*/
		
}
	
