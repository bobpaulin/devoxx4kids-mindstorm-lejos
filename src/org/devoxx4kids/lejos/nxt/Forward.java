package org.devoxx4kids.lejos.nxt;

import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.robotics.RegulatedMotor;
import lejos.util.PilotProps;

public class Forward implements RobotAction {
	
	RegulatedMotor leftMotor;
	
	RegulatedMotor rightMotor;
	
	int distance;
	
	public Forward(RegulatedMotor leftMotor, RegulatedMotor rightMotor, int distance) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		this.distance = distance;
	}
	
	@Override
	public void runAction() {
		forward(100, distance);
		
	}

	
	public static void main(String[] args) throws IOException {
		PilotProps pp = new PilotProps();
    	pp.loadPersistentValues();
    	RegulatedMotor leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "C"));
    	RegulatedMotor rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "A"));
    	
    	Forward forwardAction = new Forward(leftMotor, rightMotor, 100);
    	
    	forwardAction.forward(100, 200);
    	
    	Button.waitForAnyPress();
    	

	}
	
	
	
	public void forward(int speed)
	{
		leftMotor.backward();
		leftMotor.setSpeed(speed);
		rightMotor.backward();
		rightMotor.setSpeed(speed);
	}
	
	public void forward(int speed, int distance)
	{
		distance = -1 * distance;
		leftMotor.resetTachoCount();
		forward(speed);
		while(distance < leftMotor.getTachoCount())
		{
			LCD.drawString("Tacho: " + ( leftMotor.getTachoCount()), 0, 1, false);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		leftMotor.stop(true);
		rightMotor.stop(true);
	}

}
