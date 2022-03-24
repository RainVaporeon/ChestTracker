# ChestTracker

### Tracks specified Wynncraft player's chest count.

## Goals

Tracks specified players' chest count, on an interval of 5 minutes.

## Features

- Tracks player current world, total chest count (and increased chests)
- Sends you a push notification whenever a tracked player has opened a specified amount of chests (50, 100, 150, 200)
- Logs server switches

## How to use

ChestTracker can be used with the command
`java -jar ChestAPI-1.0.1.jar player1 player2 ...`

##Info when using

Since Wynncraft has an API limit of 750 requests/30 minutes, it's advised that you do not supply over 120 players at once (It'll warn you when you reach 120, and you may get ratelimited at 150+)

## Building

`gradlew clean build` on the base directory.

## Future plans

- World scans (Tracks specified worlds)
- Optimized tracking (Ignores offline players, might use more CPU)
- Enabling the use of config.yaml, making it easier to use the jar and have custom request interval.
