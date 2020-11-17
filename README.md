# Project Name

This repository is a final project (Java GUI) from Object-Oriented Programming Class, Teknik Informatika Universitas Padjadjaran. 

[Challenge Guidelines](challenge-guideline.md)

**Cresser Project** merupakan sebuah Video Game ber-genre Beat 'em up, dimana player akan melawan sejumlah besar musuh. Cresser Project
akan menggunakan sebuah Combo sistem yang di-kontrol oleh State Player Character.

## Credits
| NPM           | Name        |
| ------------- |-------------|
| 140810190058  | Alfathar Yusvi H |
| 140810190024  | Birgitta Laura T |
| 140810170048  | Ghema Allan F    |

## Change log
- **[Sprint Planning](changelog/sprint-planning.md) - 17/11/2020** 
   - Short changes 1
   - Short changes 2

- **[Sprint 1](changelog/sprint-1.md) - 18/11/2020 - 24/11/2020** 
- **By the end of the sprint, Players should be able to move their character left and right, and also attack using combos**
   - Initializing JFrame (Classes Map, World, and interface Drawable all should be available)
   - Create Class Character
   - Create Class Player (extends Character)
   - Abstract Class ComboState to dictate what current combo the player is on
   - Enumeration Class ECombo and EControls
   - Create Player Sprites, at least 1 frame for each set of attacks

- **[Sprint 2](changelog/sprint-2.md) - 25/11/2020 - 1/12/2020** 
- **By the end of the sprint, enemy AIs must be able to determine when to spawn, move, and attack the player**
   - Create Class Enemy (extends Character)
   - Enemy AI configuration 
      - Checks enemy count every 3 ticks. If there are less than 5 enemy  instances, spawn one enemy.
      - For individual enemy instances, if they are far from the player's current position, move towards them.
      - For individual enemy instances, if they are within atacking range of the player, attack.
   
- **[Sprint 3](changelog/sprint-3.md) - 2/12/2020 - 8/12/2020** 
- **Mainly polishing**
   - Short changes 1
   - Short changes 2

## Running The App

TO;DO with steps

## Classes Used

TO;DO

![UML](/images/UML.png "UML")

## Notable Assumption and Design App Details

TO;DO
