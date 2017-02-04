package org.usfirst.frc3550.Robotronix2017.subsystems;

import org.usfirst.frc3550.Robotronix2017.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GrimpeurSubsystem extends Subsystem {
	 private final VictorSP GrimpeurMoteur = RobotMap.moteurGrimpeur;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    } 
    public void Stop(){
    	GrimpeurMoteur.set(0);
     }
    
    public void MonterGrimpeur(){
    	GrimpeurMoteur.set(-0.9);
    
    
    }  
    
    public void DescendreGrimpeur(){
    	GrimpeurMoteur.set(0.9);
    	
    }

}

