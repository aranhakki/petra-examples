package com.cognitionbox.petra.examples.homesecurity;

import com.cognitionbox.petra.ast.terms.Base;

@Base public class AlarmSensor {
	private boolean power = true;
	private boolean control = false;

	public boolean armed() { return power && control; }
	public boolean disarmed() { return !power || !control; }

	public void arm() {
		if (armed() ^ disarmed()){
            control = true;
			assert(armed());
		}
	}

	public void disarm() {
		if (armed() ^ disarmed()){
            control = false;
			assert(disarmed());
		}
	}
}