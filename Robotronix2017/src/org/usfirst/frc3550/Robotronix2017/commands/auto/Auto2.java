package org.usfirst.frc3550.Robotronix2017.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3550.Robotronix2017.commands.*;
/**
 *
 */
public class Auto2 extends CommandGroup {
	private double distance1;
	private double distance2;
	private double distance3;
	private double angle1;
	private double angle2;
	
	/**
	 * drive method provides a way to explicitly choose the joystick or gamePad axis in order to operate the robot
	 * 
	 * @param moveValue The speed that the robot should drive in the y direction in range [-1.0..1.0]
	 * @param rotateValue The rate of rotation for the robot that is dependent of the translation. [-1.0..1.0]
	 */
	
    public Auto2(double distance1, double angle1,double distance2,double angle2, double distance3) {
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
    	this.distance1=distance1;
    	this.distance2=distance2;
    	this.distance2=distance3;
    	this.angle1=angle1;
    	this.angle2=angle2;
    	
    	//addSequential(new DriveNewDistanceWithEncoderCommand(distance1));//moves forward |237cm?
    	addSequential(new SimpleDistanceWithEncoderCommand(distance1)); 
    	addSequential(new TurnToAngleGyroCommand(angle1));
    	//addSequential(new DriveNewDistanceWithEncoderCommand(distance2));
    	addSequential(new SimpleDistanceWithEncoderCommand(distance2));
    	addSequential(new TurnToAngleGyroCommand(angle2));
    	//addSequential(new DriveNewDistanceWithEncoderCommand(distance2));
    	addSequential(new SimpleDistanceWithEncoderCommand(distance3));
    }
}
