// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3550.Robotronix2017;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.VictorSP;
// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static CANTalon deplacementleftFrontMotor;
    public static CANTalon deplacementrightFrontMotor;
    public static CANTalon deplacementleftRearMotor;
    public static CANTalon deplacementrightRearMotor;
    public static RobotDrive deplacementRobotDrive41;
    public static VictorSP ramasseurMoteurDroit;
    public static VictorSP ramasseurMoteurGauche;
    public static Solenoid pistonDroit;
    public static Solenoid pistonGauche;
    public static VictorSP moteurGrimpeur;
   // public static VictorSP motorTest;
    
    

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    	ramasseurMoteurDroit = new VictorSP(1);
        LiveWindow.addActuator("Ramasseur", "ramasseurMoteurDroit", ramasseurMoteurDroit);
        
        ramasseurMoteurGauche = new VictorSP(0);
        LiveWindow.addActuator("Ramasseur", "ramasseurMoteurGauche", ramasseurMoteurGauche);
        
        pistonDroit = new Solenoid(0, 1);
        LiveWindow.addActuator("Piston", "pistonDroit", pistonDroit);
        
        pistonGauche = new Solenoid(0, 2);
        LiveWindow.addActuator("Piston", "pistonGauche", pistonGauche);
        
         deplacementleftFrontMotor = new CANTalon(1);
        LiveWindow.addActuator("Deplacement", "leftFrontMotor", deplacementleftFrontMotor);
        
        deplacementleftRearMotor = new CANTalon(2);
        LiveWindow.addActuator("Deplacement", "rightFrontMotor", deplacementleftRearMotor);
        
        deplacementrightFrontMotor = new CANTalon(3);
        LiveWindow.addActuator("Deplacement", "leftRearMotor", deplacementrightFrontMotor);
        
        deplacementrightRearMotor = new CANTalon(4);
        LiveWindow.addActuator("Deplacement", "rightRearMotor", deplacementrightRearMotor);
        
        deplacementRobotDrive41 = new RobotDrive(deplacementleftFrontMotor, deplacementleftRearMotor,
              deplacementrightFrontMotor, deplacementrightRearMotor);
        
        moteurGrimpeur = new VictorSP(2);
        LiveWindow.addActuator("Grimper", "moteurGrimper", moteurGrimpeur);
        
        
       /// motorTest = new VictorSP(0);
       // LiveWindow.addActuator("Deplacement", "leftFrontMotor", motorTest);
        
        deplacementRobotDrive41.setSafetyEnabled(true);
        deplacementRobotDrive41.setExpiration(0.1);
        deplacementRobotDrive41.setSensitivity(0.5);
        deplacementRobotDrive41.setMaxOutput(1.0);


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}