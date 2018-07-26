package main.java.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import main.java.frc.team997.robot.Robot;

/**
 *
 */
//Maybe should be called 'AutoOpenClawCommand'.

public class OpenClawCommand extends Command {

    public OpenClawCommand() {
    	requires(Robot.claw);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("I is openClaw and I work! :D");
    	Robot.claw.openClaw();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
