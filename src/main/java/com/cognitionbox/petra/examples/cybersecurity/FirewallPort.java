package com.cognitionbox.petra.examples.cybersecurity;

import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.ast.terms.Initial;

// Simulates a strictly controlled internal network port.
@Base
public class FirewallPort {
    private boolean open = false;

    @Initial
    public boolean closed() { return open == false; }
    public boolean open() { return open == true; }

    public void openPort() {
        if (closed() || open()){
            open = true;
            System.out.println("Firewall: Port 443 opened for encrypted traffic.");
            assert(open());
        }
    }

    public void closePort() {
        if (closed() || open()){
            open = false;
            System.out.println("Firewall: Port tightly closed.");
            assert(closed());
        }
    }
}