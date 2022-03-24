package com.spiritlight.chestapi;
import java.io.IOException;
import org.json.JSONObject;
/*
Wynncraft API Rate-limits
Limit: 750/30 minutes per-IP (Player Statistics API)
Execute method: java -jar ChestAPI.jar [names]
 */
public class Main {
    public static void main(String[] args) {
        HttpSpirit request = new HttpSpirit();
        JSONSpirit json = new JSONSpirit();
        UtilitySpirit util = new UtilitySpirit();
        NotificationSpirit notify = new NotificationSpirit();
        Object[] data;
        boolean ran = false;
        int loopCount = 0;
        int interval = 300 * 1000; // Delay between requests (seconds); consensus is to keep this value above 300 seconds as it does not refresh that much
        // Check if detection will get rate-limited, up to 720 requests/30m
        if (args.length > 120) {
            System.out.println("Wynncraft API only accepts up to 750 requests per 30 minutes, consider decreasing supplied players to prevent HTTP 429");
        }
        Object[][] playerData = new Object[args.length][4]; // PlayerName, chestCount, lastWorld, reportLevel (1, 2, 3, 4 on 50, 100, 150, 200)
        while(true) {
            for (int i=0; i<args.length; i++) {
                try {
                    if (args[i] == null || args[i].equals("")) {
                        System.out.println("Empty args[i] supplied for this loop.");
                        continue;
                    }
                    data = json.parse(request.get("https://api.wynncraft.com/v2/player/" + args[i] + "/stats"));
                    if (!ran) { // Format: playerName, chestCount, lastWorld
                        playerData[i][0] = args[i]; // Player Name
                        playerData[i][1] = data[0]; // Chest Count
                        playerData[i][2] = data[1]; // Last World
                        playerData[i][3] = 0;
                    }
                    if (data[1] == JSONObject.NULL) {
                        System.out.println("Player " + args[i] + " is offline :: Chest opened: " + data[0] + " Timestamp: " + data[2] + " (HTTP " + data[3] + ")");
                    } else {
                        System.out.println("Player " + args[i] + " is online on " + data[1] + "  :: Chest opened: " + data[0] + " (+" + ((int)data[0] - (int)playerData[i][1]) + ") :: Timestamp: " + data[2] + " (HTTP " + data[3] + ")");
                    }
                    /*
                    if ((int) data[3] == 429) {
                        System.out.println("Hit rate-limit (HTTP ERR 429)");
                    }
                     */
                    if(((int)data[0] - (int)playerData[i][1]) > 50 && (int)(playerData[i][3]) < 1) {
                        notify.send("Player " + args[i] + " has opened over 50 chests on " + data[1] + "!");
                        playerData[i][3] = 1;
                    }
                    if(((int)data[0] - (int)playerData[i][1]) > 100 && (int)(playerData[i][3]) < 2) {
                        notify.send("Player " + args[i] + " has opened over 100 chests on " + data[1] + "!");
                        playerData[i][3] = 2;
                    }
                    if(((int)data[0] - (int)playerData[i][1]) > 150 && (int)(playerData[i][3]) < 3) {
                        notify.send("Player " + args[i] + " has opened over 150 chests on " + data[1] + "!");
                        playerData[i][3] = 3;
                    }
                    if(((int)data[0] - (int)playerData[i][1]) > 200 && (int)(playerData[i][3]) < 4) {
                        notify.send("Player " + args[i] + " has opened over 200 chests on " + data[1] + "!");
                        playerData[i][3] = 4;
                    }
                    if (!(playerData[i][2].equals(data[1])) && ran) {
                        if(data[1] == JSONObject.NULL) {
                            System.out.println("User " + args[i] + " has logged off!");
                        } else if (playerData[i][2] == JSONObject.NULL ) {
                            System.out.println("User " + args[i] + " has logged on at " + data[1]);
                        } else {
                            System.out.println("User " + args[i] + " changed world! " + playerData[i][2] + " -> " + data[1]);
                        }
                        playerData[i][1] = data[0];
                        System.out.println("Their totaled chest count will also be re-set.");
                        playerData[i][2] = data[1];
                        playerData[i][3] = false;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!ran) {
                ran=true;
            }
            loopCount++;
            if(loopCount >= 6)
                loopCount = 0;
            util.wait(interval);
            System.out.println("--------------------------------------------------");
        }
    }
}