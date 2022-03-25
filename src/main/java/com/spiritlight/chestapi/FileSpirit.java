package com.spiritlight.chestapi;
import java.io.FileWriter;
import java.io.IOException;

public class FileSpirit {
    public void makeConfig() {
        try {
            FileWriter newcfg = new FileWriter("config.yaml");
            newcfg.write("""
                    # Seconds between requests; Should be over 300s due to how API updates.
                    interval: 300
                    # List of users to track
                    players: ["OriTheSpirit", "Salted"]
                    # Should it notify you when a player logged on?
                    trackOnline: false
                    # Should it notify you when a player logged off?
                    trackOffline = false
                    # Should it notify you when a player reaches 30/60/90/120 chests?
                    trackChests: true
                    # Should it notify you when a player switches worlds?
                    trackSwitch: false
                    """); // Create new config
            newcfg.close();
            System.out.println("A new config has been created.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
