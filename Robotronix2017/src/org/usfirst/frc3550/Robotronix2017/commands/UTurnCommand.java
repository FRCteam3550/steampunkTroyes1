package org.usfirst.frc3550.Robotronix2017.commands;

import org.usfirst.frc3550.Robotronix2017.commands.auto.AcquireGearCommandAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class UTurnCommand extends CommandGroup {
	private double distance1;
	private double distance2;
	private double angle1;
	private double angle2;
    public UTurnCommand(double distance1, double angle1, double distance2) {
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
    	this.angle1=angle1;
    	this.angle2=angle2;
    	//addParallel(new AcquireGearCommand());
    	addParallel(new AcquireGearCommandAuto(2));
    	//addSequential(new DriveNewDistanceWithEncoderCommand(distance1));
    	addSequential(new SimpleDistanceWithEncoderCommand(distance1));
    	addSequential(new TurnToAngleGyroCommand(angle1));
    	//addSequential(new TurnToAngleGyroCommand(angle2));
    	//addSequential(new DriveNewDistanceWithEncoderCommand(distance1));
    	addSequential(new SimpleDistanceWithEncoderCommand(distance2));
    	addSequential(new TurnToAngleGyroCommand(angle1));
    	//addSequential(new DriveNewDistanceWithEncoderCommand(distance1));
    	addSequential(new SimpleDistanceWithEncoderCommand(distance1));
    	//addParallel(new AcquireGearCommand());
    	
    	//addSequential(new DriveNewDistanceWithEncoderCommand(0));
    	//addSequential(new DriveNewDistanceWithEncoderCommand(distance));
    	//addSequential(new TurnToAngleGyroCommand(angle));
    	//addSequential(new DriveNewDistanceWithEncoderCommand(distance));
    }
}
