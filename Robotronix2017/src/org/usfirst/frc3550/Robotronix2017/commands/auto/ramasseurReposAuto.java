package org.usfirst.frc3550.Robotronix2017.commands.auto;

import org.usfirst.frc3550.Robotronix2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ramasseurReposAuto extends Command {

    public ramasseurReposAuto() {
        // Use requires() here to declare subsystem dependencies
    	//
        // eg. requires(chassis);
    	requires(Robot.ramasseur);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ramasseur.stopCollecting();
    	Robot.ramasseur.pushGearDown();
    	Robot.ramasseur.pushArmDown();
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
