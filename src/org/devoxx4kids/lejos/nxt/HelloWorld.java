package org.devoxx4kids.lejos.nxt;

import lejos.nxt.Button;
import lejos.nxt.LCD;

public class HelloWorld {

	public static void main(String[] args) {
		LCD.drawString("Hello World!", 0, 1);
		Button.waitForAnyPress();
	}

}
