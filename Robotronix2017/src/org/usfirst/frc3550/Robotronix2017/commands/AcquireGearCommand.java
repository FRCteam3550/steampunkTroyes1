package org.usfirst.frc3550.Robotronix2017.commands;

import org.usfirst.frc3550.Robotronix2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AcquireGearCommand extends Command {

    public AcquireGearCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ramasseur);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//
    	Robot.ramasseur.pushArmDown();
    	Robot.ramasseur.pushGearDown();
    	Robot.ramasseur.uncoverGear();
    	//setTimeout(2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.ramasseur.pushArmDown();
    	//Robot.ramasseur.disableArm();
    	Robot.ramasseur.getGear();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; //isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ramasseur.stopCollecting();
    	Robot.ramasseur.turnLedOff();
    	//Robot.ramasseur.pushArmUp();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.ramasseur.stopCollecting();
    }
}
