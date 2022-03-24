@echo off
title ChestTracker by OriTheSpirit - ^[Exit: Ctrl-C^]
if not exist ChestAPI-1.1.0.jar (
echo ChestAPI-1.1.0.jar not found, did you put the file in the same directory?
pause
goto :EOF
)
java -jar ChestAPI-1.1-0.jar