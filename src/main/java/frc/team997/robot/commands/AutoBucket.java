package main.java.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.java.frc.team997.robot.Robot;
import main.java.frc.team997.robot.RobotMap;

/**
 *
 */
public class AutoBucket extends CommandGroup {

    public AutoBucket() {
    	//addSequential(new AutoShift(0));
    	addParallel(new Timercommand(2));
    	addSequential(new ZeroArmJoint());
    	addSequential(new ArmToAngle(RobotMap.Values.armPositionVertical));
    	//addSequential(new WaitCommand(0.5));
    	addSequential(new ClawButtonCommand(Robot.claw.openState));
    	addSequential(new ArmToAngle(RobotMap.Values.armPositionForwardLevel));
    	//addSequential(new WaitCommand(1));
    	addSequential(new ClawButtonCommand(Robot.claw.closeState));
    	//addSequential(new WaitCommand(1));
    	addSequential(new ArmToAngle(RobotMap.Values.armPositionForwardMid));
    	
    }
}
