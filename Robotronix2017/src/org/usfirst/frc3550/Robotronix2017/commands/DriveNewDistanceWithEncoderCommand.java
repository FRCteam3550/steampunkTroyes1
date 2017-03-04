package org.usfirst.frc3550.Robotronix2017.commands;

import org.usfirst.frc3550.Robotronix2017.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveNewDistanceWithEncoderCommand extends Command {
	
	
	//private PIDController encoderPID;
	private double encoderSetpoint = 0;
	private double PositionValue=0;
	private double driveForwardSpeed = -0.5;
	private double error;
	private final double TOLERANCE = .1;
	private final double kTolerance = 100;
	
	
	
	private static final double Kp_encoder    = -0.5;   // 0.022708; //other choices are: (0.10)*0.5 or (0.11)*0.5
	private static final double Ki_encoder    = 0;   //0.00081308; //0.0001 ok before ziegler-Nicols
	private static final double Kd_encoder    =  0;  //0.0084398;

	//private static final double Kp_gyro = 1.98; // 2.35 3
	//private static final double Ki_gyro = 0.0058; // 0.01
	//private static final double Kd_gyro = 0.0001;

    public DriveNewDistanceWithEncoderCommand(double encoderSetpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg.. requires(chassis);
    	requires(Robot.deplacement);
    	//this.distance        = distance;
    	//this.maxSpeed        = maxSpeed;
    	this.encoderSetpoint    = encoderSetpoint*4096;//One Turn corresponds to 4096 position   	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//encoderPID.enable();
    	Robot.deplacement.clearLeftRearEncoder();
    	SmartDashboard.putNumber("EncoderTargetPosition", encoderSetpoint);
    	//gyroPID.reset();
    	//encoderPID.setSetpoint(encoderSetpoint);
    	setTimeout(2);
    	
    }

   // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	 error = (Math.abs(encoderSetpoint - Robot.deplacement.getPositionLeftRearMotor()));
    	 SmartDashboard.putNumber("Encoder_actualPosition", Robot.deplacement.getPositionLeftRearMotor());// to delete after tests
    	 SmartDashboard.putNumber("Setpoint-actualPosition", error);// to delete after tests
		if (driveForwardSpeed *Kp_encoder * error >= driveForwardSpeed) {
			Robot.deplacement.driveTank(driveForwardSpeed, ((1+0.1)*(driveForwardSpeed)));
		} else {
			Robot.deplacement.driveTank(driveForwardSpeed * Kp_encoder * error, driveForwardSpeed * Kp_encoder * error);
		}
	}
    	//SmartDashboard.putNumber("encoderActualPosition", PositionValue);// to delete after tests
    	//SmartDashboard.putNumber("DifferenceInPosition", (encoderSetpoint-PositionValue));// to delete after tests
    	
  //  } 

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//return encoderPID.onTarget() || isTimedOut();
    	//return encoderPID.onTarget();
    	//
    	return (Math.abs(error) <= kTolerance);
    	///return (Math.abs(error) <= kTolerance) || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	//encoderPID.reset();
    	//encoderPID.reset();
    	//encoderPID.disable();
    	Robot.deplacement.clearLeftRearEncoder();
    SmartDashboard.putNumber("Encoder_AfterDistancePID", Robot.deplacement.getPositionLeftRearMotor());
    	//this.encoderSetpoint = 0;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}