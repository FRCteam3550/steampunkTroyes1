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

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3550.Robotronix2017.commands.auto.*;
//import edu.wpi.first.wpilibj.CameraServer;

import org.usfirst.frc3550.Robotronix2017.commands.*;
import org.usfirst.frc3550.Robotronix2017.subsystems.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static GearCollectorSubsystem ramasseur;
    public static BaseMobileSubsystem deplacement;
    public static ClimberSubsystem grimpeur;
    public static Compressor compresseur;
   
    
    
    public SendableChooser<Command> autoSelecteur;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        ramasseur = new GearCollectorSubsystem();
        deplacement = new BaseMobileSubsystem();
        grimpeur = new ClimberSubsystem();
        //leds = new LedsSubsystem();
        compresseur = new Compressor(1);
       // CameraServer.getInstance().startAutomaticCapture();
        //deplacement.clearLeftRearEncoder();
        

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        
        
        autoSelecteur = new SendableChooser<>();
        autoSelecteur.addObject("->StraightTurn", new ForwardWithEncoderTurnCommand(2, 90));
        autoSelecteur.addObject("->Turn", new TurnToAngleGyroCommand(90));
        autoSelecteur.addObject("->SimpleForward", new AutoSimpleForward(247));
        //SimpleBackward: @ negative distance in cm to travel in backward mode
        autoSelecteur.addObject("->SimpleBackward", new DriveBackwardDistanceWithEncoderCommand(-200));//DriveNewDistanceWitehEncoderCommand(50));
        //Uturn: @ positive distance in cm to travel @ angle to turn in degrees
        autoSelecteur.addObject("->Uturn", new UTurnCommand(100,45,100));
        autoSelecteur.addObject("Auto2", new Auto2(100, 90, 100, 90, 100));
        autoSelecteur.addObject("Auto3", new Auto3(100,50));
        //Auto4 : @inputs angle1 to turn, distance1(positive value) distance2(negative value)
        autoSelecteur.addObject("Auto4", new Auto4(90,100,-50));
        /*autoSelecteur.addObject("->Composer", new RbtxAutoCompoundCommand());
        //DriveEncoder : @inputs distance to drive in ft and speed
        //autoSelecteur.addObject("->Going Forward", new RbtxDriveToDistanceWithEncoders(-13.0, 0.6));
        autoSelecteur.addObject("->Going Forward", new RbtxDriveForwardAutonomeCommand(-13.0, 0.6));
        autoSelecteur.addObject("->Shooter Autonome", new RbtxShooterAutomatiqueCommand());
        //MoveShoot : @input distance to drive in ft
        autoSelecteur.addObject("->MoveShoot:", new RbtxDeplacerStraightEtShooterAutonomeCommand(5));
        //ArmLowMoveTurnMoveShoot : @inputs distanc1 19.09 in ft distance2 8.17 in ft speed 0.5 turnAngle 30 in degrees
        autoSelecteur.addObject("->ArmLowMoveTurnMoveShoot:", new RbtxDeplacerStraightTurnStraightShooterAutonomeCommand(-19.09, -8.20, 0.5, 30));
        //ArmLowMoveShootLow      : @inputs distance1  to drive forward in ft distance2 to drive backward in ft SPEED turnAngle in degrees
        autoSelecteur.addObject("->ArmLowMoveShootLow:", new RbtxDeplacerStraightShooterLowTargetmoveBackAutonomeCommand(-19.09, 8.17, 0.5,30));
        //autoSelecteur.addObject("", new Command()); // Template*/
        autoSelecteur.addDefault("->Robotronix Ne fais rien", new NeFaitRien());
        SmartDashboard.putData("Selection Autonomes", autoSelecteur);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	deplacement.clearLeftRearEncoder();
   	    deplacement.clearGyroAngle();

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	
   	   // SmartDashboard.putString("allo", "allo");
    	autonomousCommand = (Command) autoSelecteur.getSelected();
    	deplacement.clearLeftRearEncoder();
   	    deplacement.clearGyroAngle();
    	//System.out.println(".");
    	SmartDashboard.putNumber("ValeurENcodeurAutonomousInit",deplacement.getPositionLeftRearMotor());
    	SmartDashboard.putNumber("ValeurENcodeurAutonomousInit2",deplacement.getPositionLeftRearMotor());
    	// SmartDashboard.putString("allo", "allo");
        if (autonomousCommand != null) autonomousCommand.start();
       
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	 SmartDashboard.putNumber("ValeurENcodeurAutonomousPeriodic",deplacement.getPositionLeftRearMotor());
    	// SmartDashboard.putString("passe2","passe2");
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	 deplacement.clearLeftRearEncoder();
    	 deplacement.clearGyroAngle();
        if (autonomousCommand != null) autonomousCommand.cancel();
        //deplacement.clearLeftRearEncoder();
   	   // deplacement.clearGyroAngle();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        Robot.deplacement.display();
        SmartDashboard.putNumber("AxisX",oi.getfilteredJPiloteXAxis());
        SmartDashboard.putNumber("EncoderPosition",deplacement.getPositionLeftRearMotor());
        SmartDashboard.putNumber("GyroAngle",deplacement.getGyroAngle());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
