package org.usfirst.frc3550.Robotronix2017.commands;

import org.usfirst.frc3550.Robotronix2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AscendClimberCommand extends Command {

    public AscendClimberCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.grimpeur);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.grimpeur.climbUp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;//((Robot.oi.jPilote.getRawButton(5))==false);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.grimpeur.stopClimber();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
