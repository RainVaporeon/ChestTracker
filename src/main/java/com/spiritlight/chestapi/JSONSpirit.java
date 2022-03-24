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
        JSONObject base = pdata.getJSONObject(0); // (JSONObject)global contains chestsFound
        JSONObject meta = base.getJSONObject("meta"); // location of (bool)online and (String)server || null (Offline)
        JSONObject global = base.getJSONObject("global"); // (int)chestsFound
        JSONObject location = meta.getJSONObject("location");
        Long timestamp = root.getLong("timestamp"); // Timestamp is in milliseconds
        int responseCode = root.getInt("code");
        // Checks for world, null for offline
        currentWorld = location.get("server");
        chestOpened = global.getInt("chestsFound"); // Chests found tracking
        Object[] playerData = {chestOpened, currentWorld, timestamp, responseCode};
    return playerData;
    }
}
