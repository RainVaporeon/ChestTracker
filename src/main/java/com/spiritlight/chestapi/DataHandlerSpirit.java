package com.spiritlight.chestapi;

import org.json.JSONObject;

public class DataHandlerSpirit {
    public static void handle(int i, String player, Object[] data, Object[][] playerData, boolean ran) {
        NotificationSpirit notify = new NotificationSpirit();
        if (data[1] == JSONObject.NULL) {
            System.out.println("Player " + player + " is offline :: Chest opened: " + data[0] + " Timestamp: " + data[2] + " (HTTP " + data[3] + ")");
        } else {
            System.out.println("Player " + player + " is online on " + data[1] + " :: Chest opened: " + data[0] + " (+" + ((int)data[0] - (int)playerData[i][1]) + ") :: Timestamp: " + data[2] + " (HTTP " + data[3] + ")");
        }
                    /*
                    if ((int) data[3] == 429) {
                        System.out.println("Hit rate-limit (HTTP ERR 429)");
                    }
                     */
        if(((int)data[0] - (int)playerData[i][1]) > 50 && (int)(playerData[i][3]) < 1) {
            notify.send("Player " + player + " has opened over 50 chests on " + data[1] + "!");
            playerData[i][3] = 1;
        }
        if(((int)data[0] - (int)playerData[i][1]) > 100 && (int)(playerData[i][3]) < 2) {
            notify.send("Player " + player + " has opened over 100 chests on " + data[1] + "!");
            playerData[i][3] = 2;
        }
        if(((int)data[0] - (int)playerData[i][1]) > 150 && (int)(playerData[i][3]) < 3) {
            notify.send("Player " + player + " has opened over 150 chests on " + data[1] + "!");
            playerData[i][3] = 3;
        }
        if(((int)data[0] - (int)playerData[i][1]) > 200 && (int)(playerData[i][3]) < 4) {
            notify.send("Player " + player + " has opened over 200 chests on " + data[1] + "!");
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
