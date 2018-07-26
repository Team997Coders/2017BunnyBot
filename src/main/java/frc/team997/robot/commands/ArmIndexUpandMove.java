package main.java.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.java.frc.team997.robot.Robot;

/**
 *
 */
public class ArmIndexUpandMove extends CommandGroup {

    public ArmIndexUpandMove() {
    	System.out.println("Select and Move");
    	//addSequential(new ArmToAngle(Robot.armJoint.movelist[Robot.armJoint.selector]));
    }
}
