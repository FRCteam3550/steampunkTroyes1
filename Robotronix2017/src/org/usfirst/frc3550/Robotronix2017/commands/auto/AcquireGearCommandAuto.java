package org.usfirst.frc3550.Robotronix2017.commands.auto;

import org.usfirst.frc3550.Robotronix2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AcquireGearCommandAuto extends Command {
double time;
    public AcquireGearCommandAuto(double time) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ramasseur);
        this.time=time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//
    	Robot.ramasseur.pushArmDown();
    	Robot.ramasseur.pushGearDown();
    	
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.ramasseur.pushArmDown();
    	//Robot.ramasseur.disableArm();
    	Robot.ramasseur.getGear();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
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
