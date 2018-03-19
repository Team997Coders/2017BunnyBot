package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawButtonCommand extends Command {
	private boolean state;

	public ClawButtonCommand(boolean _state) {
		requires(Robot.claw);
		this.state = _state;
	}

	public ClawButtonCommand() {
		requires(Robot.claw);
		this.state = !Robot.claw.clawOpen;
		System.out.println("Set claw to " + state);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		state = !Robot.claw.clawOpen;
		if (state) {
			Robot.claw.openClaw();
		} else {
			Robot.claw.closeClaw();
		}
		System.out.println("State = " + state);
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
		System.out.println("bad!");
		end();
	}
}
