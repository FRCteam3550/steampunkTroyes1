package org.usfirst.frc3550.Robotronix2017.commands;

import org.usfirst.frc3550.Robotronix2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RestGearCollectorCommand extends Command {

    public RestGearCollectorCommand() {
        // Use requires() here to declare subsystem dependencies
        //  requires(chassis);
    	requires(Robot.ramasseur);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ramasseur.stopCollecting();
    	Robot.ramasseur.pushGearUp();
    	Robot.ramasseur.pushArmUp();// desabled to test the off position
    	Robot.ramasseur.coverGear();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ramasseur.stopCollecting();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
