package org.usfirst.frc3550.Robotronix2017.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3550.Robotronix2017.commands.*;
/**
 *
 */
public class FromMiddlePlaceGear extends CommandGroup {
	private double distance1;
	private double distance2;
	private double distance3;
	private double angle1;
	private double angle2;
    public FromMiddlePlaceGear(double distance1,double distance2) {
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
    	
    	
    	//addSequential(new LockGearAutoCommand());
    	
    	//addSequential(new RiseGearCollectorAutoCommand());
    	
    	addSequential(new ForwardEncoderGyroTroyCommand(distance1,0));
    	addSequential(new UnlockGearAutoCommand());
    	//addSequential(new LockGearCommand());
    	//addSequential(new RiseGearCollectorCommand());
    	//addSequential(new UnlockGearCommand());
    	///addParallel(new ArmUpCommand());
    	//addSequential(new SimpleDistanceWithEncoderCommand(distance1));
    	///addSequential(new RiseGearCollectorCommand());
    	//addSequential(new UnlockGearCommand());
    	//addSequential(new RestGearCollectorAutoCommand());
    	addSequential(new DriveBackwardDistanceWithEncoderCommand(distance2));
    	

    }
}
