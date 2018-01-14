package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchSameSideDelivery extends CommandGroup {

    public SwitchSameSideDelivery() {
    	
    	addSequential(new PDriveDistance(14 * PDriveDistance.ticksPerFoot));
    	if (Robot.getGameData().charAt(0) == 'L') {
    		addSequential(new PDriveAngle(90));
    	} else {
    		addSequential(new PDriveAngle(-90));
    	}
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
