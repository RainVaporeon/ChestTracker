package com.spiritlight.chestapi;

public class config {
    public static String[] getPlayers() {
        return players;
    }

    public void setPlayers(String[] players) {
        config.players = players;
    }

    public static int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        config.interval = interval;
    }

    public static boolean isTrackOnline() {
        return trackOnline;
    }

    public void setTrackOnline(boolean trackOnline) {
        config.trackOnline = trackOnline;
    }

    public static boolean isTrackChests() {
        return trackChests;
    }

    public void setTrackChests(boolean trackChests) {
        config.trackChests = trackChests;
    }

    public static boolean isTrackOffline() {
        return trackOffline;
    }

    public static void setTrackOffline(boolean trackOffline) {
        config.trackOffline = trackOffline;
    }

    private static String[] players;
    private static int interval;
    private static boolean trackOnline;
    private static boolean trackChests;
    private static boolean trackOffline;

}
