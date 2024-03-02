# ExoticDB-updater
updates hypixel skyblock exotic databases


In the game Hypixel Skyblock, there are any items that are now unobtainable.
One of the most popular and precious items are dyed armor sets: armor sets
that are colored differently from their supposed color. But these sets are rare, 
thus making them extremely valuable/expensive. 
In 2019, these items were made unobtainable. However, players who had made these items
before they were made unobtainable were able to keep their armors. As a result, many old players
have these exotic armor sets.

Over time, people have tried to track all of these exotic pieces, and there were some very accurate databases
containing information on thousands of pieces. Players would then contact those players in order to obtain those pieces off of them.
However, since feburary of 2023, Hypixel updated their API such that it became impossible to track said pieces


Instead, this program goes through the old, outdated databases, and updates them through a public api known as Sky.shyiiu.moe 
It checks if the player still has the armors, and also checks to see if the player is consided to be "snipeable".
Generally, many old players who no longer play the gamemode have these sets, and since they were easily obtainable before 2019,
many of those players would be willing to sell the armorsets for cheap. This program factors in their last login date, how long they have played the game,
and their game progress in order to estimate if these players know the value of their pieces. Players are able to then add this updated database to notifiers,
which notify them when a player on their list logs on. 

To start the program, run convert.java
the program should output into the file chestplates.in, with a secondary output in System.out
if this is not working, add this line of code to skyblockViewer.java:
connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

after line 136
and before the line connection.setRequestMethod("GET");

paste the outdated database into message.txt
