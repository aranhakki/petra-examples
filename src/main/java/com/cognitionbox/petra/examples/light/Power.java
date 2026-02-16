package com.cognitionbox.petra.examples.light;

import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.ast.terms.Initial;

@Base public final class Power {
    private boolean bool = true;

    public boolean off() { return bool==false; }
    @Initial
    public boolean on() { return bool==true; }

    public void turnOn() {
        if (on() || off()){
            bool=true;
            System.out.println("power on.");
            assert(on());
        }
    }

    public void turnOff() {
        if (on() || off()){
            bool=false;
            System.out.println("power off.");
            assert(off());
        }
    }
}
