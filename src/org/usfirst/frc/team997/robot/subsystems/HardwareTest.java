package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.commands.HardwareTestCommand;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HardwareTest extends Subsystem {

	public static VictorSP mvictor;
	
	public HardwareTest(){
		//mvictor = new VictorSP(5);
	}
    // here. Call these from Commands.

	public void moveTalon(double voltage) {
		mvictor.set(voltage);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new HardwareTestCommand());
    }
}

