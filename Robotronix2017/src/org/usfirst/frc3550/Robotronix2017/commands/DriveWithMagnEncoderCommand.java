package org.usfirst.frc3550.Robotronix2017.commands;

import org.usfirst.frc3550.Robotronix2017.Robot;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithMagnEncoderCommand extends Command {
	
	//int masterId  = 2; // master CANTalon                       
	//Joystick _joy = new Joystick(1);	
	StringBuilder _sb = new StringBuilder();
	//int _loops = 0;
	//boolean _lastButton1 = false;
	/** save the target position to servo to */
	private double targetPositionRotations;

    public DriveWithMagnEncoderCommand(double targetPositionRotations) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.deplacement);
        this.targetPositionRotations = targetPositionRotations;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	 /*configure CANTalon 2 as the master and the remaining as Followers allowing us to use only one Encoder*/
		Robot.deplacement.getRightFrontMotor().reverseOutput(true);
		Robot.deplacement.getRightRearMotor().reverseOutput(true);
		
		Robot.deplacement.getLeftFrontMotor().changeControlMode(TalonControlMode.Follower);
		Robot.deplacement.getLeftFrontMotor().set(Robot.deplacement.getLeftRearMotor().getDeviceID());
		
		Robot.deplacement.getRightFrontMotor().changeControlMode(TalonControlMode.Follower);
		Robot.deplacement.getRightFrontMotor().set(Robot.deplacement.getLeftRearMotor().getDeviceID());
		
		Robot.deplacement.getRightRearMotor().changeControlMode(TalonControlMode.Follower);
		Robot.deplacement.getRightRearMotor().set(Robot.deplacement.getLeftRearMotor().getDeviceID());
		
		/* lets grab the 360 degree position of the MagEncoder's absolute position */
		int absolutePosition =Robot.deplacement.getLeftRearMotor().getPulseWidthPosition() & 0xFFF; /* mask out the bottom12 bits, we don't care about the wrap arounds */
        /* use the low level API to set the quad encoder signal */
		Robot.deplacement.getLeftRearMotor().setEncPosition(absolutePosition);
        
        /* choose the sensor and sensor direction */
		Robot.deplacement.getLeftRearMotor().setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		Robot.deplacement.getLeftRearMotor().reverseSensor(true); //was false
        
        /* set the peak and nominal outputs, 12V means full */
		Robot.deplacement.getLeftRearMotor().configNominalOutputVoltage(+0f, -0f);
		Robot.deplacement.getLeftRearMotor().configPeakOutputVoltage(+12f, -12f);
		
		/* set the allowable closed-loop error,
         * Closed-Loop output will be neutral within this range.
         * See Table in Section 17.2.1 for native units per rotation. 
         */
		Robot.deplacement.getLeftRearMotor().setAllowableClosedLoopErr(0); /* always servo */
        /* set closed loop gains in slot0 */
		Robot.deplacement.getLeftRearMotor().setProfile(0);
		Robot.deplacement.getLeftRearMotor().setF(0.0);
		Robot.deplacement.getLeftRearMotor().setP(0.095);//0.19
		Robot.deplacement.getLeftRearMotor().setI(0.0); 
		Robot.deplacement.getLeftRearMotor().setD(0.0); //0.01   
		setTimeout(2);
		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/* Position mode - button just pressed */
    	//targetPositionRotations = 3.0; /* 2 Rotations in either direction */
    	Robot.deplacement.getLeftRearMotor().enableControl();
    	//Robot.deplacement.getLeftRearMotor().getPosition();
    	Robot.deplacement.getLeftRearMotor().changeControlMode(TalonControlMode.Position);
    	Robot.deplacement.getLeftRearMotor().set(targetPositionRotations); /* 5 rotations in either direction */
    	_sb.append("\terrNative:");
    	_sb.append(Robot.deplacement.getLeftRearMotor().getClosedLoopError());
    	_sb.append("\ttrg:");
    	_sb.append(targetPositionRotations);	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.deplacement.getLeftRearMotor().disableControl();
    	Robot.deplacement.getLeftRearMotor().setPosition(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
