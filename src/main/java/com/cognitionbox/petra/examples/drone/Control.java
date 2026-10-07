package com.cognitionbox.petra.examples.drone;

import com.cognitionbox.petra.ast.terms.Initial;

import static com.cognitionbox.petra.ast.interp.util.Program.sep;

public class Control {
	private final SysWrapper sys = new SysWrapper();
	private final Flag active = new Flag();

	@Initial
	public boolean on() { return active.on(); }

	public boolean off() { return active.off(); }

	public void turnOn() {
		if (on() ^ off()){
            sep(()->{active.turnOn();},()->{sys.logTurnOn();});
			assert(on());
		}
	}

	public void turnOff() {
		if (on()){
            sep(()->{active.turnOff();},()->{sys.logTurnOff();});
			assert(off());
		}
	}

	public void exit() {
		if (off()){
			sys.exit();
			assert(off());
		}
	}
}