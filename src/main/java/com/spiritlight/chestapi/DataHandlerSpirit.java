package com.spiritlight.chestapi;

import org.json.JSONObject;

public class DataHandlerSpirit {
    public static void handle(int i, String player, Object[] data, Object[][] playerData, boolean ran) {
        NotificationSpirit notify = new NotificationSpirit();
        if (data[1] == JSONObject.NULL) {
            System.out.println("Player " + player + " is offline :: Chest opened: " + data[0] + " :: Timestamp: " + data[2] + " (HTTP " + data[3] + ")");
            if(config.isTrackOffline()) {
                notify.send("Player " + player + " has logged off!");
            }
        } else {
            System.out.println("Player " + player + " is online on " + data[1] + " :: Chest opened: " + data[0] + " (+" + ((int)data[0] - (int)playerData[i][1]) + ") :: Timestamp: " + data[2] + " (HTTP " + data[3] + ")");
            if(config.isTrackOnline()) {
                notify.send("Player " + player + " is online on " + data[1] + "!");
            }
        }
                    /*
                    if ((int) data[3] == 429) {
                        System.out.println("Hit rate-limit (HTTP ERR 429)");
                    }
                     */
        if(((int)data[0] - (int)playerData[i][1]) >= 30 && (int)(playerData[i][3]) < 1) {
            notify.send("Player " + player + " has opened over 30 chests on " + data[1] + "!");
            playerData[i][3] = 1;
        }
        if(((int)data[0] - (int)playerData[i][1]) >= 60 && (int)(playerData[i][3]) < 2) {
            notify.send("Player " + player + " has opened over 60 chests on " + data[1] + "!");
            playerData[i][3] = 2;
        }
        if(((int)data[0] - (int)playerData[i][1]) >= 90 && (int)(playerData[i][3]) < 3) {
            notify.send("Player " + player + " has opened over 90 chests on " + data[1] + "!");
            playerData[i][3] = 3;
        }
        if(((int)data[0] - (int)playerData[i][1]) >= 120 && (int)(playerData[i][3]) < 4) {
            notify.send("Player " + player + " has opened over 120 chests on " + data[1] + "!");
            playerData[i][3] = 4;
        }
        if (!(playerData[i][2].equals(data[1])) && ran) {
            if(data[1] == JSONObject.NULL) {
                System.out.println("User " + player + " has logged off!");
            } else if (playerData[i][2] == JSONObject.NULL) {
                System.out.println("User " + player + " has logged on at " + data[1]);
            } else {
                System.out.println("User " + player + " changed world! " + playerData[i][2] + " -> " + data[1]);
            }
            playerData[i][1] = data[0];
            System.out.println("Their totaled chest count will also be re-set.");
            playerData[i][2] = data[1];
            playerData[i][3] = 0;
        }
    }
}
