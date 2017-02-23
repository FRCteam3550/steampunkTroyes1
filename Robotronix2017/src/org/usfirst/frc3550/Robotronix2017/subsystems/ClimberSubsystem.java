package org.usfirst.frc3550.Robotronix2017.subsystems;

import org.usfirst.frc3550.Robotronix2017.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberSubsystem extends Subsystem {
	 private final VictorSP climberMotor = RobotMap.moteurGrimpeur;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    } 
    public void stopClimber(){
    	climberMotor.set(0);
     }
    
    public void climbUp(){
    	climberMotor.set(-1.0);
    
    
    }  
    
    public void climbDown(){
    	climberMotor.set(0.9);
    	
    }

}

