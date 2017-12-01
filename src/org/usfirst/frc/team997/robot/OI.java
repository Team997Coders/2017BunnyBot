package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.commands.AutomatedTest;
import org.usfirst.frc.team997.robot.commands.ClawButtonCommand;

import org.usfirst.frc.team997.robot.commands.ReverseToggle;

import org.usfirst.frc.team997.robot.commands.DecellToggle;

import org.usfirst.frc.team997.robot.commands.ShiftCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
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
	GamePad;
	
	public final JoystickButton
	decellToggleButton,
	shiftButton,
	clawButton,
	reverseButton
   ArmPosOne,
	ArmPosTwo,
	ArmPosThree,
	ArmPosFour/*,
	ArmPosFive*/;
	
	//automatedTestButton;
	
	public OI() {
		//Joystick Init
		GamePad = new Joystick(RobotMap.Ports.GamePadPort);
    GamePad2 = new Joystick(RobotMap.Ports.GamePad2Port);
		
		//Buttons Init
		
		decellToggleButton = new JoystickButton(GamePad, RobotMap.Ports.decellToggleButton);
		decellToggleButton.whenPressed(new DecellToggle());
		shiftButton = new JoystickButton(GamePad, RobotMap.Ports.shiftButton);
		shiftButton.whenPressed(new ShiftCommand());
		clawButton = new JoystickButton(GamePad, RobotMap.Ports.clawButton);
		clawButton.whenPressed(new ClawButtonCommand());
		reverseButton = new JoystickButton(GamePad, RobotMap.Ports.reverseToggButton);
		reverseButton.whenPressed(new ReverseToggle());
    
    ArmPosThree = new JoystickButton(GamePad2, RobotMap.Ports.ArmPosButton3);
		ArmPosOne = new JoystickButton(GamePad2, RobotMap.Ports.ArmPosButton1);
		ArmPosTwo = new JoystickButton(GamePad2, RobotMap.Ports.ArmPosButton2);
		ArmPosFour = new JoystickButton(GamePad2, RobotMap.Ports.ArmPosButton4);
		
		ArmPosOne.whenPressed(new ArmJointToAngle(RobotMap.Values.ArmPos1));
		ArmPosTwo.whenPressed(new ArmJointToAngle(RobotMap.Values.ArmPos2));
		ArmPosThree.whenPressed(new ArmJointToAngle(RobotMap.Values.ArmPos3));
		ArmPosFour.whenPressed(new ArmJointToAngle(RobotMap.Values.ArmPos4));
		//ArmPosFive.whenPressed(new ArmJointToAngle(RobotMap.Values.ArmPos5));
		
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
	}
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

