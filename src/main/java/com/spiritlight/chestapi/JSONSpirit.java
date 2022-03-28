package com.spiritlight.chestapi;
import org.json.*;

public class JSONSpirit {
    public Object[] parse(String s) throws JSONException {
    boolean status;
    int chestOpened;
    Object currentWorld;
    /*
         Playerdata [chestOpened, currentWorld, timestamp]
         JSONObject childObj=parentObj.getJSONObject("properties"); - Obtaining childObj object from parentObj properties
         String details=childObj.getString("details"); - Obtaining String data in childObj
    */
        JSONObject root = new JSONObject(s);
    // Start querying the received JSONObjects
        JSONArray pdata = root.getJSONArray("data");
        int responseCode = root.getInt("code");
        // Check for existence of data before proceeding to parse
        if(responseCode != 200) {
            System.out.println("Invalid player data! (" + responseCode + ")");
            System.out.println("Skipping this player...");
            return new Object[]{-1, -1, -1, responseCode};
        }
        JSONObject base = pdata.getJSONObject(0); // (JSONObject)global contains chestsFound
        JSONObject meta = base.getJSONObject("meta"); // location of (bool)online and (String)server || null (Offline)
        JSONObject global = base.getJSONObject("global"); // (int)chestsFound
        JSONObject location = meta.getJSONObject("location");
        long timestamp = root.getLong("timestamp"); // Timestamp is in milliseconds
        // Checks for world, null for offline
        currentWorld = location.get("server");
        chestOpened = global.getInt("chestsFound"); // Chests found tracking
    return new Object[]{chestOpened, currentWorld, timestamp, responseCode};
    }
}
