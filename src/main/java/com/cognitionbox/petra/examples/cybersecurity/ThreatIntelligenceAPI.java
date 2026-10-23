package com.cognitionbox.petra.examples.cybersecurity;

import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.ast.terms.Initial;

@Base
public class ThreatIntelligenceAPI {
    private boolean clean = false;

    @Initial
    public boolean unknown() { return clean == false; }
    public boolean verifiedSafe() { return clean == true; }

    public void verifyIP() {
        if (unknown() || verifiedSafe()){
            clean = true;
            System.out.println("ThreatIntel: IP verified against external DB.");
            assert(verifiedSafe());
        }
    }

    public void reset() {
        if (unknown() || verifiedSafe()){
            clean = false;
            System.out.println("ThreatIntel: State reset to unknown.");
            assert(unknown());
        }
    }
}