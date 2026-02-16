package com.cognitionbox.petra.examples.homesecurity;

import static com.cognitionbox.petra.ast.interp.util.Program.sep;

public class Room {
	private final AlarmSensor centre = new AlarmSensor();
	private final AlarmSensor side = new AlarmSensor();

	public boolean armed() { return centre.armed() && side.armed(); }
	public boolean disarmed() { return centre.disarmed() && side.disarmed(); }

	public void toggle() {
		if (disarmed()){
			sep(()->centre.arm(),()->side.arm());
			assert(armed());
		} else if (armed()){
			sep(()->centre.disarm(),()->side.disarm());
			assert(disarmed());
		}
	}

	public void arm() {
		if (armed() ^ disarmed()){
			sep(()->centre.arm(),()->side.arm());
			assert(armed());
		}
	}

	public void disarm() {
		if (armed() ^ disarmed()){
			sep(()->centre.disarm(),()->side.disarm());
			assert(disarmed());
		}
	}
}