package com.cognitionbox.petra.examples.droneroutesystem;

import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.ast.terms.External;
import com.cognitionbox.petra.ast.terms.Initial;

import java.util.concurrent.atomic.AtomicBoolean;

@Base
public class Flag {
    private boolean bool = true;

    @Initial
    public boolean on() { return bool==true; }
    public boolean off() { return bool==false; }

    public void turnOn() {
        if (on() || off()){
            bool=true;
            assert(on());
        }
    }

    public void turnOff() {
        if (on() || off()){
            bool=false;
            assert(off());
        }
    }
}