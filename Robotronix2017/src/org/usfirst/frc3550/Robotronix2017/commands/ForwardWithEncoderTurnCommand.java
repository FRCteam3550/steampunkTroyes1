package org.usfirst.frc3550.Robotronix2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ForwardWithEncoderTurnCommand extends CommandGroup {
	
	private double distance1;
	private double distance2;
	private double distance3;
	private double angle1;

    public ForwardWithEncoderTurnCommand(double distance1,double angle1,double distance2,double distance3 ) {
    	this.distance1=distance1;
    	this.distance2=distance2;
    	this.distance2=distance3;
    	this.angle1=angle1;
    	
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
    	//addParallel(new ArmUpCommand());
    	//addParallel(new LowerGearCollectorCommand());
    	//addParallel(new LockGearCommand());
    	addSequential(new ForwardEncoderGyroTroyCommand(distance1,0));
    	addSequential(new TurnToAngleGyroCommand(angle1));
    	addSequential(new ForwardEncoderGyroTroyCommand(distance2,0));
    	addSequential(new UnlockGearCommand());
    	addSequential(new DriveBackwardDistanceWithEncoderCommand(distance3));
    }
}
