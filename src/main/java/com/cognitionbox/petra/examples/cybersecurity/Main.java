package com.cognitionbox.petra.examples.cybersecurity;

/**
 * ==========================================
 * LEVEL 4: ENTRY POINT
 * ==========================================
 */

public class Main {
    public static void main(String[] args){
        ZeroTrustEnclave enclave = new ZeroTrustEnclave();
        
        System.out.println("--- Booting Zero Trust System ---");
        
        // Simulating an incoming user connection request
        enclave.establishSecureSession();
        
        System.out.println("--- Secure Communications Active ---");
        
        // Simulating a session termination or a detected breach
        enclave.terminateSession();
        
        System.out.println("--- System Safely Locked Down ---");
    }
}