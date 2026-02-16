package com.cognitionbox.petra.examples.light;

import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.ast.terms.Initial;

@Base public final class Control {
    private boolean bool = false;

    @Initial
    public boolean off() { return bool==false; }
    public boolean on() { return bool==true; }

    public void turnOn() {
        if (on() || off()){
            bool=true;
            System.out.println("control on.");
            assert(on());
        }
    }

    public void turnOff() {
        if (on() || off()){
            bool=false;
            System.out.println("control off.");
            assert(off());
        }
    }
}
