package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.commands.ArmToAngle;
import org.usfirst.frc.team997.robot.commands.ClawButtonCommand;
import org.usfirst.frc.team997.robot.commands.ReverseToggle;
import org.usfirst.frc.team997.robot.commands.MoveArm;
import org.usfirst.frc.team997.robot.commands.ShiftCommand;
import org.usfirst.frc.team997.robot.commands.ZeroArmJoint;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public boolean reverseBool = false;
	public boolean decellOn = false;
	public boolean arcadeDrive = true;
  
	public final Joystick
	GamePad2, GamePad;
	
	public final JoystickButton
	//decellToggleButton,
	shiftButton,
	clawButton,
	clawButton2,
	reverseButton,
	MoveArmFwdButton,
	MoveArmRevButton,
	ZeroArmButton,
	GoToVerticalButton,
	ArmVerticalButton,
	ArmLevelForwardButton,
	ArmLevelBackwardButton,
	ArmFloorForwardButton,
	ArmFloorBackwardButton,
	ArmMidForwardButton,
	ArmMidBackwardButton;
	
	public OI() {
		//Joystick Init
		GamePad = new Joystick(RobotMap.Ports.GamePadPort);
		GamePad2 = new Joystick(RobotMap.Ports.GamePad2Port);
		
		//ARM CONTROL BUTTONS
		clawButton = new JoystickButton(GamePad, RobotMap.Ports.clawButton2);
		clawButton.whenPressed(new ClawButtonCommand());
		
		clawButton2 = new JoystickButton(GamePad2, RobotMap.Ports.clawButton);
		clawButton2.whenPressed(new ClawButtonCommand());
		
		MoveArmFwdButton = new JoystickButton(GamePad, RobotMap.Ports.ArmFwdButton);
		MoveArmFwdButton.whileHeld(new MoveArm(0.5));
		
		MoveArmRevButton = new JoystickButton(GamePad, RobotMap.Ports.ArmBwdButton);
		MoveArmRevButton.whileHeld(new MoveArm(-0.5));
		
		ZeroArmButton = new JoystickButton(GamePad, RobotMap.Ports.ZeroArm);
		ZeroArmButton.whenPressed(new ZeroArmJoint());
		
		//ARM POS BUTTONS
		ArmVerticalButton = new JoystickButton(GamePad2, RobotMap.Ports.armPositionVertical);
		ArmVerticalButton.whenPressed(new ArmToAngle(RobotMap.Values.armPositionVertical));
		
		ArmLevelForwardButton = new JoystickButton(GamePad2, RobotMap.Ports.armPositionForwardLevel);
		ArmLevelForwardButton.whenPressed(new ArmToAngle(RobotMap.Values.armPositionForwardLevel));
		
		ArmLevelBackwardButton = new JoystickButton(GamePad2, RobotMap.Ports.armPositionBackwardLevel);
		ArmLevelBackwardButton.whenPressed(new ArmToAngle(RobotMap.Values.armPositionBackwardLevel));
		
		ArmFloorForwardButton = new JoystickButton(GamePad2, RobotMap.Ports.armPositionForwardFloor);
		ArmFloorForwardButton.whenPressed(new ArmToAngle(RobotMap.Values.armPositionForwardFloor));
		
		ArmFloorBackwardButton = new JoystickButton(GamePad2, RobotMap.Ports.armPositionBackwardFloor);
		ArmFloorBackwardButton.whenPressed(new ArmToAngle(RobotMap.Values.armPositionBackwardFloor));
		
		ArmMidForwardButton = new JoystickButton(GamePad2, RobotMap.Ports.armPositionForwardMid);
		ArmMidForwardButton.whenPressed(new ArmToAngle(RobotMap.Values.armPositionMidForward));
		
		ArmMidBackwardButton = new JoystickButton(GamePad2, RobotMap.Ports.armPositionBackwardMid);
		ArmMidBackwardButton.whenPressed(new ArmToAngle(RobotMap.Values.armPositionMidBackward));
		
		GoToVerticalButton = new JoystickButton(GamePad, RobotMap.Ports.GoToVertical);
		GoToVerticalButton.whenPressed(new ArmToAngle(3.81));
		//GoToVerticalButton.whenPressed(new ArmToAngle(15792));
		
		//DRIVETRAIN BUTTONS
		shiftButton = new JoystickButton(GamePad, RobotMap.Ports.shiftButton);
		shiftButton.whenPressed(new ShiftCommand());
		
		reverseButton = new JoystickButton(GamePad, RobotMap.Ports.reverseToggButton);
		reverseButton.whenPressed(new ReverseToggle());
		
		//decellToggleButton = new JoystickButton(GamePad, RobotMap.Ports.decellToggleButton);
		//decellToggleButton.whenPressed(new DecellToggle());
	
	}
	
	public double getLeftY() {
		return GamePad.getRawAxis(RobotMap.Ports.leftYAxisPort);
	}
	
	public double getRightX() {
		return GamePad.getRawAxis(RobotMap.Ports.rightXAxisPort);
	}
  
  public int get_pov() {
		int val = GamePad.getPOV();
		SmartDashboard.putNumber("POV = ", val);
		return val;
	}
	
	public void updateDashboard() {
		SmartDashboard.putBoolean("Decel on/off", decellOn);
		SmartDashboard.putBoolean("LB", GamePad.getRawButton(RobotMap.Ports.ArmFwdButton));
		SmartDashboard.putBoolean("RB", GamePad.getRawButton(RobotMap.Ports.ArmBwdButton));
	}
	
}
	