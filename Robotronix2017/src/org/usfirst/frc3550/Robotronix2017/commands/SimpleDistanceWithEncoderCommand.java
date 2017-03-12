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
public class SimpleDistanceWithEncoderCommand extends Command {
	
	
	//private PIDController encoderPID;
	private double S_encoderSetpoint = 0;
	private double PositionValue = 0;
	private double driveForwardSpeed = -0.7;
	private double S_error = 0 ;
	private final double TOLERANCE = .1;
	private final double kTolerance = 100;
	
	private double firstPart = 0.8;
	private double secondPart = 0.2;
	
	private static final double Kp_encoder    = -0.5;   // 0.022708; //other choices are: (0.10)*0.5 or (0.11)*0.5
	private static final double Ki_encoder    = 0;   //0.00081308; //0.0001 ok before ziegler-Nicols
	private static final double Kd_encoder    =  0;  //0.0084398;

	//private static final double Kp_gyro = 1.98; // 2.35 3
	//private static final double Ki_gyro = 0.0058; // 0.01
	//private static final double Kd_gyro = 0.0001;

    public SimpleDistanceWithEncoderCommand(double S_encoderSetpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg.. requires(chassis);
    	requires(Robot.deplacement);
    	//this.distance        = distance;
    	//this.maxSpeed        = maxSpeed;
    	//this.S_encoderSetpoint    = S_encoderSetpoint*4096;//One Turn corresponds to 4096 position   	
    	this.S_encoderSetpoint    = (S_encoderSetpoint/((15.2)*Math.PI))*4096;//One Turn corresponds to 4096 position   	
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//encoderPID.enable();
    	//Robot.deplacement.setLeftRearMotorEncoderMode();
    	//Robot.deplacement.getLeftRearMotor().reset();
    	//
    	Robot.deplacement.clearLeftRearEncoder();
    	Robot.deplacement.clearLeftRearEncoder();
    	SmartDashboard.putNumber("EncoderInit_InitialPosition", Robot.deplacement.getPositionLeftRearMotor());
    	SmartDashboard.putNumber("EncoderTargetPosition", S_encoderSetpoint);
    	//gyroPID.reset();
    	//encoderPID.setSetpoint(S_encoderSetpoint);
    	setTimeout(10);
    	
    }

   // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//S_error = 50;
    	SmartDashboard.putNumber("Encoder_actualPositionN", Robot.deplacement.getPositionLeftRearMotor());
    	this.S_error = S_encoderSetpoint - Robot.deplacement.getPositionLeftRearMotor();
    	 // to delete after tests
    	 SmartDashboard.putNumber("S_errorN", S_error);// to delete after tests
    	 SmartDashboard.putNumber("S_encoderSetpointN", S_encoderSetpoint);// to delete after tests
    	 SmartDashboard.putString("Inside ","autonomous");// to delete after tests
    	 /*
		if (driveForwardSpeed *Kp_encoder * S_error >= driveForwardSpeed) {
			Robot.deplacement.driveTank(driveForwardSpeed, ((1+0.1)*(driveForwardSpeed)));
		} else {
			Robot.deplacement.driveTank(driveForwardSpeed * Kp_encoder * S_error, driveForwardSpeed * Kp_encoder * S_error);
		}*/
    	 if(Robot.deplacement.getPositionLeftRearMotor() > S_encoderSetpoint){
    		// Robot.deplacement.clearLeftRearEncoder();
    		 Robot.deplacement.driveTank(0,0);
    	 }
    	 else if(S_error > S_encoderSetpoint*0.2) {
    		 SmartDashboard.getNumber("EncoderValue*0.2 ", S_encoderSetpoint*0.2);// to delete after tests
    		 Robot.deplacement.drive(driveForwardSpeed, 0);
    		// Robot.deplacement.driveTank(driveForwardSpeed, (1+0.08)*(driveForwardSpeed));
    		 SmartDashboard.putString("ForwardDriveInsideUpperBound2 ","yes");// to delete after tests
    	 } else {
    		// Robot.deplacement.driveTank(driveForwardSpeed*0.5, ((1+0.08)*(driveForwardSpeed)*0.5));
    		 Robot.deplacement.driveTank(driveForwardSpeed*0.4, -0.47);
    		 SmartDashboard.putString("ForwardDriveInsideLowerBound2 ","yes");// 
    	 }	 
	}
    	//SmartDashboard.putNumber("encoderActualPosition", PositionValue);// to delete after tests
    	//SmartDashboard.putNumber("DifferenceInPosition", (S_encoderSetpoint-PositionValue));// to delete after tests
    	
  //  } 

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//return encoderPID.onTarget() || isTimedOut();
    	//return encoderPID.onTarget();
    	//
    	//return (Math.abs(S_error) <= kTolerance);
    	//return isTimedOut();
    	SmartDashboard.putNumber("S_error in isFinished", Math.abs(S_error));
    	return (Math.abs(S_error) <= kTolerance) || isTimedOut();
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	//encoderPID.reset();
    	//encoderPID.reset();
    	//encoderPID.disable();
    	Robot.deplacement.clearLeftRearEncoder();
    	Robot.deplacement.clearLeftRearEncoder();
    	//S_error =  Robot.deplacement.getPositionLeftRearMotor();
    	//this.S_error = 0;
    	//this.S_encoderSetpoint = 0;
    	SmartDashboard.putNumber("Encoder_AfterDistancePID", Robot.deplacement.getPositionLeftRearMotor());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}