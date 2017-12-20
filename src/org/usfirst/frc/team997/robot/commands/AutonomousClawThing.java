package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team997.robot.commands.ClawButtonCommand;

/**
 *
 */
public class AutonomousClawThing extends CommandGroup {
	

    public AutonomousClawThing() {
    	addSequential (new ClawButtonCommand(Robot.claw.openState));
        addSequential (new DriveTo(30));
        addSequential (new ClawButtonCommand(Robot.claw.closeState));
        //Lift claw plz :^)
      
        
        
        //requires(Robot.claw); //uncomment these
    }
}
