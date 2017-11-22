package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousClawThing extends CommandGroup {
	

    public AutonomousClawThing() {
    	addSequential (new OpenClaw());
        addSequential (new DriveTo(30));
        addSequential (new CloseClaw());
        //Lift claw plz :^)
      
        
        
        //requires(Robot.claw); //uncomment these
    }
}
