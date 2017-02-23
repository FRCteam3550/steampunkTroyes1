package org.usfirst.frc3550.Robotronix2017.commands.auto;

import org.usfirst.frc3550.Robotronix2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AspirerEngrenageCommandAuto extends Command {

    public AspirerEngrenageCommandAuto() {
        // Use requires() here to declare subsystem dependencies
    	//
        requires(Robot.ramasseur);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//setTimeout(2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ramasseur.getGear();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;//isTimedOut();
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
