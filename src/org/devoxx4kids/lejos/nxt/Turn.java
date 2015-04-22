package org.devoxx4kids.lejos.nxt;

import lejos.nxt.LCD;
import lejos.robotics.RegulatedMotor;

public class Turn implements RobotAction {
	
	public static final String LEFT = "left";
	
	public static final String RIGHT = "right";
	
	RegulatedMotor leftMotor;
	
	RegulatedMotor rightMotor;
	
	int distance;
	
	public Turn(RegulatedMotor leftMotor, RegulatedMotor rightMotor, int distance) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		this.distance = distance;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void runAction() {
		// TODO Auto-generated method stub
		
	}
	
	public void right(int speed)
	{
		leftMotor.backward();
		leftMotor.setSpeed(speed);
		rightMotor.forward();
		rightMotor.setSpeed(speed);
	}
	
	public void left(int speed)
	{
		leftMotor.forward();
		leftMotor.setSpeed(speed);
		rightMotor.backward();
		rightMotor.setSpeed(speed);
	}
	
	public void turn(String direction, int speed, int distance)
	{
		
		leftMotor.resetTachoCount();
		if(direction.equals(RIGHT))
		{
			distance = -1 * distance;
			right(speed);
		}
		else if(direction.equals(LEFT))
		{
			left(speed);
		}
		
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
