package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.commands.ArmJointToAngle;
import org.usfirst.frc.team997.robot.commands.AutomatedTest;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public final Joystick
	GamePad,
	GamePad2;
	
	public final JoystickButton
	shiftUpButton,
	shiftDownButton,
	bunnyCollectorButton,
	bucketLifterButton,
	automatedTestButton,
	ArmPosOne,
	ArmPosTwo,
	ArmPosThree,
	ArmPosFour/*,
	ArmPosFive*/;
	
	public OI() {
		//Joystick Init
		GamePad = new Joystick(RobotMap.Ports.GamePadPort);
		GamePad2 = new Joystick(RobotMap.Ports.GamePad2Port);
		
		//Buttons Init
		shiftUpButton = new JoystickButton(GamePad, RobotMap.Ports.shiftUpButton);
		shiftDownButton = new JoystickButton(GamePad, RobotMap.Ports.shiftDownButton);
		bunnyCollectorButton = new JoystickButton(GamePad, RobotMap.Ports.bunnyCollector);
		bucketLifterButton = new JoystickButton(GamePad, RobotMap.Ports.bucketLifterButton);
		automatedTestButton = new JoystickButton(GamePad, 1);
		automatedTestButton.whileHeld(new AutomatedTest());
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
	
	public int get_pov() {
		int val = GamePad.getPOV();
		SmartDashboard.putNumber("POV = ", val);
		return val;
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
}
