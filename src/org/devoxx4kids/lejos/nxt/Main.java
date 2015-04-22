package org.devoxx4kids.lejos.nxt;

import java.io.IOException;
import java.util.Queue;

import javax.microedition.sensor.ColorChannelInfo;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.RegulatedMotor;
import lejos.util.PilotProps;

public class Main {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		PilotProps pp = new PilotProps();
    	pp.loadPersistentValues();
    	RegulatedMotor leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "C"));
    	RegulatedMotor rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "A"));
    	ColorSensor colorSensor = new ColorSensor(SensorPort.S1);
		final Dispatcher mainProgram = new Dispatcher();
		
		Thread mainThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(mainProgram.hasAction())
				{
					mainProgram.runAction();
				}
			}
		});
		
		mainProgram.submitAction(new Forward(leftMotor, rightMotor, 200));
		
		mainProgram.submitAction(new CheckColorSensor(colorSensor, mainProgram, 500, new Forward(leftMotor, rightMotor, 200), null));
		
		mainThread.start();
		
		Button.waitForAnyPress();

	}

}
