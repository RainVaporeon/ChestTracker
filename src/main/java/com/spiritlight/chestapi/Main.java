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
        DataHandlerSpirit dataHandler = new DataHandlerSpirit();
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
                    // data: int i, String player, Object[] data, Object[][] playerData, boolean ran
                    dataHandler.handle(i, args[i], data, playerData, ran);
                } catch (IOException e) {
                    System.out.println("An connection error has occurred while performing this action.");
                    System.out.println("Please check your internet connection before proceeding.");
                    util.wait(30000); // 30s
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