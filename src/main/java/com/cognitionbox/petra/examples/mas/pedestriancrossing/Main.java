package com.cognitionbox.petra.examples.mas.pedestriancrossing;

import com.cognitionbox.petra.examples.mas.pedestriancrossing.agent.TrafficControlMAS;

// --- MAIN ENTRY POINT ---
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TrafficControlMAS mas = new TrafficControlMAS();
        while (true) {
            mas.act();
            Thread.sleep(1000);
        }
    }
}







