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


import org.usfirst.frc3550.Robotronix2017.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc3550.Robotronix2017.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Joystick jPilote;
    public Joystick coPilote;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        jPilote = new Joystick(1);
        coPilote = new Joystick(0);
        
        int BUTTON_1 = 1;
		//int BUTTON_A = 2;
		//int BUTTON_B = 3;
		//int BUTTON_Y = 4;
		int BUTTON_LB = 5;
		int SLOW_BUTTON = 2;
		int NORMAL_BUTTON = 9;
		int SLOW_INVERSE_BUTTON = 8;
		int NORMAL_INVERSE_BUTTON = 7;
        
		Button buttonInverseDrive = new JoystickButton(jPilote, NORMAL_INVERSE_BUTTON);// Button4
		buttonInverseDrive.whenPressed(new ArcadeDriveInversedCommand());
		//buttonInverseDrive.whenPressed(new RbtxInverseTankDriveCommand());
		
		Button buttonDrive = new JoystickButton(jPilote, NORMAL_BUTTON);// Button2
		buttonDrive.whenPressed(new ArcadeDriveCommand());
		//buttonDrive.whenPressed(new RbtxTankDriveCommand());
		
		Button buttonInverseSlowTankDrive = new JoystickButton(jPilote, SLOW_INVERSE_BUTTON);//Button1
		buttonInverseSlowTankDrive.whenPressed(new SlowDriveInverseCommand());
		//buttonInverseSlowTankDrive.whenPressed(new RbtxInverseSlowTankDriveCommand());
				
		Button buttonSlowTankDrive = new JoystickButton(jPilote, SLOW_BUTTON);//Button3
		buttonSlowTankDrive.whileHeld(new SlowDriveCommand());
		//buttonSlowTankDrive.whenPressed(new RbtxSlowTankDriveCommand());
        
        Button AcquireGear = new JoystickButton(coPilote, 1);
        //button1.whenPressed(new AspirerEngrenageCommand());
        AcquireGear.whileHeld(new AcquireGearCommand());
        
        Button ArmUp = new JoystickButton(coPilote, 5);
        ArmUp.whenPressed(new ArmUpCommand());
        
        Button ArmDown = new JoystickButton(coPilote, 3);
        ArmDown.whenPressed(new ArmDownCommand()); //to inverse 
        
        Button RejectGear = new JoystickButton(coPilote, 2);
        //button4.whenPressed(new RejeterEngrenageCommand());
        RejectGear.whileHeld(new RejectGearCommand());
        
        Button AscendClimber = new JoystickButton(coPilote, 6);
        //button5.whenPressed(new MonterGrimpeur());
        AscendClimber.whileHeld(new AscendClimberCommand());
        
        //Button button6 = new JoystickButton(coPilote, 6);
       // button6.whenPressed(new DriveWithMagnEncoderCommand(2));//input parameter: Nombre de tours
       // button6.whileHeld(new DescendClimberCommand());
        
        Button button7 = new JoystickButton(coPilote, 7);
        button7.whenPressed(new RiseGearCollectorCommand());
        
        Button button8 = new JoystickButton(coPilote, 8);
        button8.whenPressed(new LowerGearCollectorCommand());

        Button button9 = new JoystickButton(coPilote, 9);
        button9.whenPressed(new LockGearCommand());
        
        Button button10 = new JoystickButton(coPilote, 10);
        button10.whenPressed(new UnlockGearCommand());
        
        Button button11 = new JoystickButton(coPilote, 11);
        button11.whenPressed(new TurnToAngleGyroCommand(45));//input parameter: angle to turn in degrees
        
        Button button12 = new JoystickButton(coPilote, 12);
       // button12.whenPressed(new TurnLedsOn());
        
        
        


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("deplacementJoystick", new DriveWithJoystickCommand());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getJPilote() {
        return jPilote;
    }
    
    public double getJPiloteXAxis() {
        //return jPilote.getRawAxis(0);
        return jPilote.getRawAxis(2);
    }
    
    public double getJPiloteYAxis() {
        return jPilote.getRawAxis(1);
        
    }
    public double getfilteredJPiloteXAxis() {
    	//return filterJoystickAxis(jPilote.getRawAxis(0));
    	return filterJoystickXAxis(jPilote.getRawAxis(2));
    }
    public double getfilteredJPiloteYAxis() {
    	return filterJoystickYAxis(jPilote.getRawAxis(1));
    }
    

    public Joystick getcoPilote() {
        return coPilote;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
  }
    public double filterJoystickYAxis(double inputAxis){
    	if ((inputAxis < -0.10)||(inputAxis > 0.1)){
    	//if ((Math.abs(inputAxis) < 0.1)){
    		return inputAxis;
    	}
    	else {
    		return 0.0;
    	}
    }
        public double filterJoystickXAxis(double inputAxis){
        	if ((inputAxis < -0.3)||(inputAxis > 0.4)){
        	//if ((Math.abs(inputAxis) < 0.2)){
        		return inputAxis;
        	}
        	else {
        		return 0.0;
        	}
    }
        
}

