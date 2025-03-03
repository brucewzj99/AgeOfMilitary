# ict1009-team46-2022

# Age of Military - Team 46

ICT1009 OOP Game Project in Java.

Video Link: https://youtu.be/K0F2QgzYjY8

## Game Description: 
The game is inspired from the classic tank game genre, but change to focus more on the PvP aspect. On game start, player starts at level 1 and the goal of the game is to kill the enemy within the stipulated time (60s). If the time were to end, the player with the most remaining life will be crown the winner.

As the game progresses, player will be given the opportunity to collect power up to help them gain advantage over the enemy.

Power up includes:
<table>
  <tr>
    <th>Image</th>
    <th>Name</th>
    <th>Description</th>
  </tr>
  <tr>
    <td><img src="https://github.com/brucewzj99/AgeOfMilitary/tree/main/AgeOfMilitary/res/objects/boost.png" alt="boost" width=100px></td>
    <td>Boost</td>
    <td>Increase the movement speed of player</td>
  </tr>
  <tr>
    <td><img src="https://github.com/brucewzj99/AgeOfMilitary/tree/main/AgeOfMilitary/res/objects/heart.png" alt="boost" width=100px></td>
    <td>Health</td>
    <td>Restore 1 live to player</td>
  </tr>
  <tr>
    <td><img src="https://github.com/brucewzj99/AgeOfMilitary/tree/main/AgeOfMilitary/res/objects/shield.png" alt="boost" width=100px></td>
    <td>Shield</td>
    <td>Help player block one shot from the opponent</td>
  </tr>
  <tr>
    <td><img src="https://github.com/brucewzj99/AgeOfMilitary/tree/main/AgeOfMilitary/res/objects/powerup.png" alt="boost" width=100px></td>
    <td>Power up</td>
    <td>Advance player to the next level, increase their stats</td>
  </tr>
</table>

Upon power up, the model of the character also change to reflect their level. Starting from the level 1(left) to level 3(right).

![Level1](https://github.com/brucewzj99/AgeOfMilitary/tree/main/AgeOfMilitary/res/player/blue_down_1.png?raw=true)
![Level2](https://github.com/brucewzj99/AgeOfMilitary/tree/main/AgeOfMilitary/res/player/blue_down_2.png?raw=true)
![Level3](https://github.com/brucewzj99/AgeOfMilitary/tree/main/AgeOfMilitary/res/player/blue_down_3.png?raw=true)


## Game Configuration: 
If you want to change the configuration of how the game runs, you can access /res/config/config.ini and change the values to what you want.
For reference:
<table>
  <tr>
    <th>Header</th>
    <th>Config</th>
    <th>Purpose</th>
    <th>Range</th>
  </tr>
  <tr>
    <td rowspan="5">Player</td>
    <td>Level</td>
    <td>Set the default level player will start at.</td>
    <td>1 - 3 (default: 1)</td>
  </tr>
  <tr>
    <td>Speed</td>
    <td>Set the default speed player are moving at.</td>
    <td>3-10 (default: 4)</td>
  </tr>
  <tr>
    <td>Life: 4-8</td>
    <td>Set the number of life player starts with.</td>
  </tr>
  <tr>
    <td>Direction</td>
    <td>Set the direction that user is facing on spawn</td>
    <td>up, down, left or right (default: down)</td>
  </tr>
  <tr>
    <td>Shield</td>
    <td>Set true if want players to start with shield, false if no</td>
    <td>true or false (default: false)</td>
  </tr>
   <tr>
    <td rowspan="2">Player1 & Player2</td>
    <td>Color</td>
    <td>Allow players to choose the color they want to play as</td>
    <td>Depends on color header</td>
  </tr>
  <tr>
    <td>Spawn</td>
    <td>Set the starting spawn point for player</td>
    <td>Depends on spawn header (starting from 1)</td>
  </tr>
   <tr>
    <td rowspan="3">Bullet</td>
    <td>Downtime</td>
    <td>Set how long before player can shoot again</td>
    <td>0-120 (default:60)</td>
  </tr>
  <tr>
    <td>Speed</td>
    <td>Set how fast the bullet travel</td>
    <td>1-10 (default:6)</td>
  </tr>
    <td>Damage</td>
    <td>Set how much damage each bullet does</td>
    <td>1-4 (default:1)</td>
  </tr>
  <tr>
    <td>Color</td>
    <td>[color]</td>
    <td>State the color that is available for use</td>
    <td>red, green, blue</td>
  </tr>
  <tr>
    <td>Spawn</td>
    <td>[x, y coordinates]</td>
    <td>State the coordinates for player spawn</td>
    <td>Example: 50,50 | 1200,50</td>
  </tr>
</table>

## How to install & run: 
1. Download the file as ZIP on GitHub
2. Extract the file
3. Use your preferred IDE and open the extracted file
4. Run main.Main.java


## Members:
* Bruce Wang
* Lim Huai Fu
* Yeo Yi Xuan
* Elysia Yeo
* Donovan Lim
* Chang Bing Kang



