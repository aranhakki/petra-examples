package com.cognitionbox.petra.examples.droneroutesystem;

import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.ast.terms.Initial;

@Base public class SysWrapper {
    private final Sys sys = new Sys();

    @Initial
    public boolean ok(){return true;}

    public void exit() {
        if (ok()){
            sys.exit();
            assert(ok());
        }
    }

    public void logTurnOn(){
        if (ok()){
            System.out.println("turnOn");
            assert(ok());
        }
    }

    public void logTurnOff(){
        if (ok()){
            System.out.println("turnOff");
            assert(ok());
        }
    }

    public void logLand() {
        if (ok()){
            sys.logLand();
            assert(ok());
        }
    }

    public void logRouteActive() {
        if (ok()){
            sys.logRouteActive();
            assert(ok());
        }
    }

    public void logTemperatureWarning() {
        if (ok()){
            sys.logTemperatureWarning();
            assert(ok());
        }
    }

}
