package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Claw extends Subsystem {

    public DoubleSolenoid clawSolenoid;
    
    //variables go here plz
    public boolean clawOpen; //true if claw is open, false if closed.
    
    public Claw() {
		clawSolenoid = new DoubleSolenoid(RobotMap.Ports.clawLeftSolenoidPort,RobotMap.Ports.clawRightSolenoidPort);
		
		clawOpen = true;
		clawSolenoid.set(DoubleSolenoid.Value.kForward);
	
    }	
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void openClaw() {
    	if(clawOpen == false) {
    		clawSolenoid.set(DoubleSolenoid.Value.kForward);
    		clawOpen = true;
    	}
    }
    
    public void closeClaw() {
    	if(clawOpen == true) {
    		clawSolenoid.set(DoubleSolenoid.Value.kReverse);
    		clawOpen = false;
    	}
    }
    
    public void stopClaw() {
    	clawSolenoid.set(DoubleSolenoid.Value.kOff);
    }
 
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

