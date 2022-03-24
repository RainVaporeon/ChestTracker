package com.spiritlight.chestapi;

public class config {
    public static String[] getPlayers() {
        return players;
    }

    public void setPlayers(String[] players) {
        this.players = players;
    }

    public static int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public static boolean isTrackOnline() {
        return trackOnline;
    }

    public void setTrackOnline(boolean trackOnline) {
        this.trackOnline = trackOnline;
    }

    public static boolean isTrackChests() {
        return trackChests;
    }

    public void setTrackChests(boolean trackChests) {
        this.trackChests = trackChests;
    }

    private static String[] players;
    private static int interval;
    private static boolean trackOnline;
    private static boolean trackChests;

}
