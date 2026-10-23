package com.cognitionbox.petra.examples.cybersecurity;

import com.cognitionbox.petra.ast.terms.Initial;

import static com.cognitionbox.petra.ast.interp.util.Program.par;
import static com.cognitionbox.petra.ast.interp.util.Program.seq;

/**
 * ==========================================
 * LEVEL 3: HIGH-LEVEL ORCHESTRATOR
 * ==========================================
 */

public class ZeroTrustEnclave {
    private final SecureIdentityGateway gateway = new SecureIdentityGateway();
    private final FirewallPort port = new FirewallPort();

    @Initial
    public boolean lockedDown() { return gateway.unauthenticated() && port.closed(); }
    public boolean activeSession() { return gateway.authenticated() && port.open(); }

    public void establishSecureSession() {
        if (lockedDown()){
            // 1. Fully authenticate the user/device
            // 2. ONLY THEN open the firewall port
            seq(()->gateway.authenticate(), ()->port.openPort());
            assert(activeSession());
        }
    }

    public void terminateSession() {
        if (activeSession()){
            // Close the firewall and revoke identity data simultaneously
            par(()->port.closePort(), ()->gateway.revoke());
            assert(lockedDown());
        }
    }
}