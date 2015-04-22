package org.devoxx4kids.lejos.nxt;

import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;

public class CheckColorSensor implements RobotAction {
	
	private ColorSensor colorSensor;
	
	private Dispatcher dispatcher;
	
	private int lightThreshold;
	
	private RobotAction preAction;
	
	private RobotAction postAction;
	
	public CheckColorSensor(ColorSensor colorSensor, Dispatcher dispatcher, int lightThreshold, RobotAction preAction, RobotAction postAction)
	{
		this.colorSensor = colorSensor;
		
		this.dispatcher = dispatcher;
		
		this.lightThreshold = lightThreshold;
		
		this.preAction = preAction;
		
		this.postAction = postAction;
	}
	
	@Override
	public void runAction() {
		int lightValue = this.colorSensor.getNormalizedLightValue();
		
		LCD.drawString("Light Value: " + lightValue, 0, 1);
		
		if(lightValue >= lightThreshold)
		{
			if(postAction != null)
			{
				dispatcher.submitAction(postAction);
			}
		}
		else
		{
			if(preAction != null)
			{
				dispatcher.submitAction(preAction);
				dispatcher.submitAction(new CheckColorSensor(this.colorSensor, this.dispatcher, this.lightThreshold, this.preAction, this.postAction));
			}
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
