package com.spiritlight.chestapi;
import java.awt.*;
import java.awt.TrayIcon.MessageType;

// Code mostly yoinked from https://stackoverflow.com/a/34490485

public class NotificationSpirit {
    public static void send(String msg) {
        if (SystemTray.isSupported()) {
            NotificationSpirit notif = new NotificationSpirit();
            try {
                notif.displayTray(msg);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("System tray not supported! Notifications will not be sent.");
        }
    }
    public void displayTray(String msg) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("ChestAPI Notification", msg, MessageType.INFO);
    }
}
