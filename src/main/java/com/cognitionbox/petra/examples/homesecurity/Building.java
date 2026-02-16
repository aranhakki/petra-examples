package com.cognitionbox.petra.examples.homesecurity;

import com.cognitionbox.petra.ast.terms.Entry;
import com.cognitionbox.petra.ast.terms.Initial;

import static com.cognitionbox.petra.ast.interp.util.Program.sep;

@Entry
public class Building {
	private final Room front = new Room();
	private final Room kitchen = new Room();
	private final Room bedroom = new Room();
	public boolean all() { return front.armed() && kitchen.armed() && bedroom.armed(); }
	public boolean downstairs() { return front.armed() && kitchen.armed() && !bedroom.armed(); }
	public boolean upstairs() { return !front.armed() && !kitchen.armed() && bedroom.armed(); }
	@Initial public boolean none() { return !front.armed() && !kitchen.armed() && !bedroom.armed(); }

	public boolean other() { return !(front.armed() && kitchen.armed() && bedroom.armed()) &&
								!(front.armed() && kitchen.armed() && !bedroom.armed()) &&
								!(!front.armed() && !kitchen.armed() && bedroom.armed()) &&
								!(!front.armed() && !kitchen.armed() && !bedroom.armed()); }

	@Entry public void toggle() {
		if (other()){
			sep(()->front.disarm(),()->kitchen.disarm(),()->bedroom.disarm());
			assert(none());
		} else if (all()){
			bedroom.disarm();
			assert(downstairs());
		} else if (downstairs()){
			sep(()->front.disarm(),()->kitchen.disarm(),()->bedroom.arm());
			assert(upstairs());
		} else if (upstairs()){
			bedroom.disarm();
			assert(none());
		} else if (none()){
			sep(()->front.arm(),()->kitchen.arm(),()->bedroom.arm());
			assert(all());
		}
	}
}