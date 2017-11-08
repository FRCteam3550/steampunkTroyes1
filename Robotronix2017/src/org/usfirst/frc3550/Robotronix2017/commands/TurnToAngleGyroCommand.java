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
public class TurnToAngleGyroCommand extends Command {
	
	
	private PIDController gyroPID;
	private double gyroSetpoint;
	private double rotateValue=0;
	private double error;
	private final double TOLERANCE = .1;
	
	
	
	private static final double Kp_gyro    = 0.11*1; //other choices are: (0.10)*0.5 or (0.11)*0.5
	private static final double Ki_gyro    =  0; //0.0001 ok before ziegler-Nicols
	private static final double Kd_gyro    = 0;

	//private static final double Kp_gyro = 1.98; // 2.35 3
	//private static final double Ki_gyro = 0.0058; // 0.01
	//private static final double Kd_gyro = 0.0001;

    public TurnToAngleGyroCommand(double gyroSetpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg.. requires(chassis);
    	requires(Robot.deplacement);
    	//this.distance        = distance;
    	//this.maxSpeed        = maxSpeed;
    	this.gyroSetpoint    = gyroSetpoint;
    	
    	SmartDashboard.putNumber("GyroSetpontTurn", gyroSetpoint);
    	
    	PIDSource  gyroAngle = new PIDSource(){
    		 PIDSourceType m_sourceType = PIDSourceType.kDisplacement;
    		public double pidGet(){
    			return Robot.deplacement.getGyroAngle();
    		}

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				// TODO Auto-generated method stub
				m_sourceType = pidSource;
				
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				// TODO Auto-generated method stub
				return m_sourceType;
			}
    	};
    	
    	PIDOutput targetAngleWrite = new PIDOutput() {
			
			@Override
			public void pidWrite(double output) {
				// TODO Auto-generated method stub
				
				rotateValue = output;
				
				if(rotateValue > 0.5) 
					rotateValue = 0.5;
				if(rotateValue < -0.5)
					rotateValue = -0.5;
					
				SmartDashboard.putNumber("gyroAutoAngleinside", rotateValue);// to delete after tests
				SmartDashboard.putNumber("Errorinside", (gyroSetpoint-rotateValue));// to delete after tests
			}
		};
		
		gyroPID = new PIDController( Kp_gyro,  Ki_gyro,  Kd_gyro,0, gyroAngle, targetAngleWrite);
		gyroPID.setAbsoluteTolerance(0.01);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.deplacement.clearGyroAngle();
    	gyroPID.setSetpoint(gyroSetpoint);
    	gyroPID.enable();
    	setTimeout(2);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("gyroAutoAngle", rotateValue);// to delete after tests
    	Robot.deplacement.drive(0, rotateValue);// to delete after tests
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//return gyroPID.onTarget();
    	return gyroPID.onTarget() || isTimedOut();
    	//return gyroPID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	//gyroPID.reset();
    	//gyroPID.reset();
    	Robot.deplacement.clearGyroAngle();
    	gyroPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
