package com.cognitionbox.petra.examples.cybersecurity;

import com.cognitionbox.petra.ast.terms.Initial;

import static com.cognitionbox.petra.ast.interp.util.Program.par;
import static com.cognitionbox.petra.ast.interp.util.Program.seq;

/**
 * ==========================================
 * LEVEL 2: MID-LEVEL COORDINATOR
 * ==========================================
 */

public class SecureIdentityGateway {
    private final ThreatIntelligenceAPI threatAPI = new ThreatIntelligenceAPI();
    private final HardwareSecurityModule hsm = new HardwareSecurityModule();

    @Initial
    public boolean unauthenticated() { return threatAPI.unknown() || hsm.locked(); }
    public boolean authenticated() { return threatAPI.verifiedSafe() && hsm.unlocked(); }

    public void authenticate() {
        if (unauthenticated()){
            // Authenticate by checking threat DB, then unlocking the HSM
            seq(()->threatAPI.verifyIP(), ()->hsm.loadKeys());
            assert(authenticated());
        }
    }
    
    public void revoke() {
        if (authenticated()){
            // Revoke immediately in parallel for maximum security
            par(()->threatAPI.reset(), ()->hsm.purgeKeys());
            assert(unauthenticated());
        }
    }
}