package com.spiritlight.chestapi;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        FileSpirit fs = new FileSpirit();
        ConfigHandlerSpirit cfg = new ConfigHandlerSpirit();
        try {
            cfg.load(); // Players,
        } catch (FileNotFoundException e) {
            System.out.println("Failed to fetch a configuration file, creating a new one.");
            fs.makeConfig();
            return;
        }
        String[] players = config.getPlayers();
        Object[] data;
        boolean ran = false;
        int interval = config.getInterval() * 1000; // Delay between requests (seconds); consensus is to keep this value above 300 seconds as it does not refresh that much
        // Check if detection will get rate-limited, up to 720 requests/30m
        if (players.length * 1800000/interval > 720) { // Max 125 objects when interval is 5 minutes
            System.out.println("Wynncraft API only accepts up to 750 requests per 30 minutes, consider decreasing supplied players to prevent HTTP 429");
            System.out.println("Current interval allows up to " + (750 / (1800000 / interval)) + " players.");
            System.out.println("Your current input amount is requesting " + config.getPlayers().length + " players over " + config.getInterval() + " seconds.");
        }
        Object[][] playerData = new Object[players.length][4]; // PlayerName, chestCount, lastWorld, reportLevel (1, 2, 3, 4 on 50, 100, 150, 200)
        while(true) {
            for (int i=0; i<players.length; i++) {
                try {
                    if (players[i] == null || players[i].equals("")) {
                        System.out.println("Empty args[i] supplied for this loop.");
                        continue;
                    }
                    data = json.parse(request.get("https://api.wynncraft.com/v2/player/" + players[i] + "/stats"));
                    if (!ran) { // Format: playerName, chestCount, lastWorld
                        playerData[i][0] = players[i]; // Player Name
                        playerData[i][1] = data[0]; // Chest Count
                        playerData[i][2] = data[1]; // Last World
                        playerData[i][3] = 0;
                    }
                    // data: int i, String player, Object[] data, Object[][] playerData, boolean ran
                    dataHandler.handle(i, players[i], data, playerData, ran);
                } catch (IOException e) {
                    System.out.println("An connection error has occurred while performing this action.");
                    System.out.println("Please check your internet connection before proceeding.");
                    util.wait(30000); // 30s
                }
            }
            if(!ran) {
                ran=true;
            }
            util.wait(interval);
            System.out.println("--------------------------------------------------");
        }
    }
}