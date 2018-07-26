package main.java.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.java.frc.team997.robot.Robot;

//This auto routine is for when we are on the left or right starting position and our switch
//is on the opposite side.
public class CrossLine extends CommandGroup {

    public CrossLine() {
    	addSequential(new PDriveDistance(12 * Robot.driveTrain.ticksPerFoot));
    }
}
