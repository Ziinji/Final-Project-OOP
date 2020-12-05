# Cresser Project

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
   - Initial discussion and sprint planning

- **[Sprint 1](changelog/sprint-1.md) - 18/11/2020 - 24/11/2020**<br/>
**By the end of the sprint, all classes should be available, and Players should be able to move their character**
   - Initializing JFrame (Classes Map, World, and interface Drawable all should be available)
   - Create Class Character
   - Create Class Player and Enemy (extends Character)
   - Abstract Class ComboState to dictate what current combo the player is on
   - Enumeration Class ECombo and EControls
   - Create Player Sprites, at least 1 frame for each set of attacks

- **[Sprint 2](changelog/sprint-2.md) - 25/11/2020 - 1/12/2020**<br/>
**By the end of the sprint, collision, attack function, game and character states are available, and enemy AIs should be implemented**
   - Enemy AI configuration 
      - If enemy were to die, spawn another one at a random locatio
      - If enemy is far from the player's current position, move towards them.
      - If enemy is within atacking range of the player, attack.
   - Collision function between the player and the enemy
   - Create attack function
   - Game States (Play, Pause, Game Over) to dictate the game's flow
   - Character States (Running, Standing, Attacking, and Facing Left/Right) to dictate what the characters are doing
   - Create Enemy Sprites
   
- **[Sprint 3](changelog/sprint-3.md) - 2/12/2020 - 8/12/2020**<br/>
**Mainly polishing**
   - Add extra frames for smoother animations
   - Other aesthetical changes if it were to lack
   - Finalization on Enemy AI and Combo System

## Running The App
   - Open root folder
   - Navigate to /out/production/CresserProject
   - Go to terminal/cmd
   - Run 'java CresserMain' (without ')

## Classes Used

![UML](/images/UML.png "UML")

## Notable Assumption and Design App Details
   - Player spawn di tengah screen
   - Player bisa mulai menggerakkan karakter setelah menekan tombol spasi.
   - 2 musuh akan spawn di posisi acak, lalu 1 lagi untuk setiap 3 tick sampai jumlah musuh dalam screen mencapai 5.
   - Jika player berhasil membunuh musuh, mereka mendapat *1 kill*, dan program akan terus men-cek ulang berapa jumlah instansi musuh (Max 5)
   - Jika *health* player mencapai 0, game selesai dan akan ditampilkan berapa skor player sesuai kill count mereka
