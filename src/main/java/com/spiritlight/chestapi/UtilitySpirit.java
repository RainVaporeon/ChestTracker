package com.spiritlight.chestapi;

public class UtilitySpirit {
    public static void wait(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
