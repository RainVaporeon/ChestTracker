# ChestTracker

### Tracks specified Wynncraft player's chest count.

## Goals

Tracks specified players' chest count, on an interval of 5 minutes.

## Features

- Tracks player current world, total chest count (and increased chests)
- Sends you a push notification whenever a tracked player has opened a specified amount of chests (30, 60, 90, 120)
- Logs server switches

## How to use

ChestTracker can be used with the command `java -jar ChestAPI-1.1.0.jar`

If you do not know how to execute that, place the `run.bat` with it.

As of ChestTracker 1.1.0, it will generate config.yaml when you use it.

## Config

The configuration file looks like this:
```yaml
# Seconds between requests; Should be over 300s due to how API updates.
interval: 300
# List of users to track
players: ["Player", "Array"]
# Should it notify you when a player's logs on / off?
trackOnline: false
# Should it notify you when a player reaches 30/60/90/120 chests?
trackChests: true
```

##Info when using

Since Wynncraft has an API limit of 750 requests/30 minutes, it's advised that you do not supply over 120 players at once (It'll warn you when you reach 120, and you may get ratelimited at 150+)

## Building

`gradlew clean build` on the base directory.

## Future plans

- World scans (Tracks specified worlds)
- Optimized tracking (Ignores offline players, might use more CPU)
