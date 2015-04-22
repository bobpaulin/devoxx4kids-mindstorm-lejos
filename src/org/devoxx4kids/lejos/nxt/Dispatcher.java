package org.devoxx4kids.lejos.nxt;

import java.util.Queue;

public class Dispatcher {
	
	private Queue eventQueue;


	public Dispatcher()
	{
		this.eventQueue = new Queue();
	}
	
	public synchronized void submitAction(RobotAction action)
	{
		eventQueue.addElement(action);
	}
	
	public synchronized boolean hasAction()
	{
		return !eventQueue.empty();
	}
	
	public synchronized void runAction()
	{
		RobotAction action = (RobotAction)eventQueue.pop();
		action.runAction();
	}

}
