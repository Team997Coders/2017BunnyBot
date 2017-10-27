package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.commands.HardwareTestCommand;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HardwareTest extends Subsystem {

	public static Talon talon;
	
	public HardwareTest(){
		talon = new Talon(1);
	}
    // here. Call these from Commands.

	public void moveTalon(double voltage) {
		talon.set(voltage);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	new HardwareTestCommand();
    }
}

