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
public class DriveToTargetWithEncoderCommand extends Command {
	
	private PIDController pid;
	private double PidOutputValue;
	private double encoderSetpoint;
	private double vitesse = -0.5;
	private double kp = -1;
	private double ki = 0;
	private double kd = 0;
	
	public DriveToTargetWithEncoderCommand(double distance) {
		requires(Robot.deplacement);
this.encoderSetpoint    = distance*4096;
    	
    	SmartDashboard.putNumber("DistanceToTarget", encoderSetpoint);
    	
    	PIDSource  EncoderPosition = new PIDSource(){
    		 PIDSourceType m_sourceType = PIDSourceType.kDisplacement;
    		public double pidGet(){
    			return Robot.deplacement.getPositionLeftRearMotor();
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
    	
    	PIDOutput targetDistanceWrite = new PIDOutput() {
			
			@Override
			public void pidWrite(double output) {
				// TODO Auto-generated method stub
				
				PidOutputValue = output;
				
				//if(PidOutputValue > 0.5) 
				//	PidOutputValue = 0.5;
				//if(PidOutputValue < -0.5)
					//PidOutputValue = -0.5;
					
				SmartDashboard.putNumber("EncoderAutoDistanceinside", PidOutputValue);// to delete after tests
				SmartDashboard.putNumber("ErrorEncoderinside", (encoderSetpoint-PidOutputValue));// to delete after tests
			}
		};
		
		pid = new PIDController( kp,  ki,  kd,0, EncoderPosition, targetDistanceWrite);
		pid.setAbsoluteTolerance(0.01);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.deplacement.clearLeftRearEncoder();
    	pid.setSetpoint(encoderSetpoint);
    	pid.enable();
    	setTimeout(2);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("gyroAutoAngle", encoderSetpoint);// to delete after tests
    	Robot.deplacement.drive(vitesse, vitesse);// to delete after tests
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return pid.onTarget() || isTimedOut();
    	//return gyroPID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.reset();
    	//gyroPID.reset();
    	Robot.deplacement.clearLeftRearEncoder();
    	pid.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}