package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Claw extends Subsystem {

    public DoubleSolenoid clawSolenoid;
    
    public final boolean openState = true;
    public final boolean closeState = false;
    
    //variables go here plz
    public boolean clawOpen; //true if claw is open, false if closed.
    
    public Claw() {
		clawSolenoid = new DoubleSolenoid(RobotMap.Ports.clawLeftSolenoidPort,RobotMap.Ports.clawRightSolenoidPort);
		
		clawOpen = this.openState;
		clawSolenoid.set(DoubleSolenoid.Value.kForward);
	
    }	
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void openClaw() {
    	
    		clawSolenoid.set(DoubleSolenoid.Value.kReverse);
    		clawOpen = this.openState;
    	
    }
    
    public void closeClaw() {
    
    		clawSolenoid.set(DoubleSolenoid.Value.kForward);
    		clawOpen = this.closeState;
    	
    }
    
    public void stopClaw() {
    	clawSolenoid.set(DoubleSolenoid.Value.kOff);
    }
 
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void clawSmartDashboard() {
    	SmartDashboard.putBoolean("ClawOpen?", clawOpen);
    }
}

