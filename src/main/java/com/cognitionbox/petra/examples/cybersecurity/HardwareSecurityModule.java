package com.cognitionbox.petra.examples.cybersecurity;

import com.cognitionbox.petra.ast.terms.Base;
import com.cognitionbox.petra.ast.terms.Initial;

// Simulates an internal hardware module for cryptographic keys.
@Base
public class HardwareSecurityModule {
    private boolean keysLoaded = false;

    @Initial
    public boolean locked() { return keysLoaded == false; }
    public boolean unlocked() { return keysLoaded == true; }

    public void loadKeys() {
        if (locked() || unlocked()){
            keysLoaded = true;
            System.out.println("HSM: Cryptographic keys loaded into memory.");
            assert(unlocked());
        }
    }

    public void purgeKeys() {
        if (locked() || unlocked()){
            keysLoaded = false;
            System.out.println("HSM: Keys securely purged.");
            assert(locked());
        }
    }
}