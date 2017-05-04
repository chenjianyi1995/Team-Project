How to run/test the code:
PRE:: Install Android Studio here(https://developer.android.com/studio/index.html)
1) Clone and import the project into android studio
2) Go to Run/Debug Configurations in android studio
3) Add new configuration
4) Set main class to: com.mygdx.game.desktop.DesktopLauncher
   working directory: team_project/android/assets
   use classpath of module: desktop
5) Run

# Team-Project
1. Names/git id: Jianyi Chen 	/ chenjianyi1995;
		 Jonathan Young / JonYoung1123;
		 Zijun Xu	/ zixu6769;
		 Xiaoyang Dou 	/ xido3947;
		 Zhiwen Shu	/ alwynShu.
2. Title: Damn The Ram
3. Description:
	Shooting game where you are a buffalo, and those damned rams keeps coming.
	The goal is to survive as long as possible. Over time the rams will become more beefy.
	Destroying enemy rams will grant experience points. Gain enough and your abilities will be upgraded, helping with your survival.
4. Vision statement:Keeping school spirit alive, one game at a time.
5. Motivation:We are CU. SKO BUFFS
6. Risks:
	We have little experience with game making, which could hinder the overall look and feel of our project.
	Environment of game making is new to all of us.
	We are alright with python, but not gods.
	Since we all are students, time we have to meet up and converse are limited.
7. Mitigation Strategy:
	Start the project early so that we will have the time required to look up issues and learn what we don't know.
	Have preplanned meeting times to discuss and share further ideas and clarify issues. 
	Also to help fix specific issues with many minds acting as one.
8. List of requirements: Agile {1,2,3,5,8,13,20,40,100,inf,?}
	1. "As a user I want to share my stats with friends at the end of each game."
		8
	2. "As a user I want to have a record of my past stats/best times."
		5
	3. "As a developer, I want to make this game work for all platforms (i.e. iOS, Android, web)"
		40
	4. "As a user I want to be able save my game and continue later on. One can only poop for so long."
		13
	5. "As a user, I am tired of starting at the beginning, I want to be able to start at a higher difficulty."
		1
	6. "As a user, I want to be able to change the images used as my character and the enemies."
		3
9. Waterfall Methodology
10. Project Tracking Software: Trello
link: https://trello.com/b/MCnk7DWs/damn-the-ram
Screen shot please see Trello plan	


As to repo organization:
Our repo on git was mostly organized for us due to the fact that we used Android Studio. Using Android Studio it packaged our files for us.
Overall we created an overhead file called team_project and it was automatically populated by AS as we worked.
For the part of the code we wrote, excluding packages and libraries we imported, they can be found at
/team_project/core/build/classes/main/com/mygdx/game/
Within this game file are held the four types of class files we created.
Scenes holds one file called Hud.class, which is used to set up and display to the game screen.
Screen holds the two files used to generate the screens of our game.
Sprites holds the files for our different sprites we created.
Tools holds two files, B2d.class sets up our background images and collision/obstacles.
							  WorldCL.class is what we used to get information about the collisions occuring and to call specific functions based on those collisions.
This is where our project sits. The other files are background to what we did, added by AS due to its ability in allowing us to run our games on multiple system types.


======HOW TO PLAY======
Controls:
	Up Arrow - Move player up
	Down Arrow - Move player down
	Left Arrow - Move player left
	Right Arrow - Move player right
	W - shoot up
	A - shoot left
	S - shoot down
	D - shoot right
	Spacebar - Stop player
		Due to the fact that during movement our player is given a force impulse instead of a translation you will continue to float even after key release.
		Layman Terms: When you let go of keys you keep moving. Spacebar will stop you.

Overview:
	Our game is a 2D move and shoot. Every couple seconds a new enemy will spawn randomly in one of four locations around the map. Your job is to survive by shooting
	as many of them as you can. However, every 15 seconds the enemies(rams) will level up and get stronger, i.e. gaining health, movement speed, and damage.
	The enemies have a stragne interaction where if you do not deal enough damage in a short enough period of time they will heal. But if you are able to kill enough
	of them fast enough you will enter a sort of "frenzy" mode where they will die in roughly one hit. But to keep this going you must keep killing them non stop.
	When you kill an enemy you gain xp. The amount you gain is based on the level of the enemies. When you gain enough xp, you will level up, increasing your own health,
	damage, and movement speed. But be careful because the amount of xp required to gain the next level upon level up is variable to the level of the enemies.
	Meaning that if you wait to long to level, you could be faced with a nearly impassible amount of experience required for the next level.
	Upon leveling up your health will also be returned to its maximum value, the new maximum.
	You take damage when the enemies make contact with you. When your health has fallen below 25% of the maximum, the number will turn red as an indication of this.
	When you die, the gameover screen will indicate how many enemies you killed before dying. Simply click any where to play again. There is no end goal except survive.
	Good luck, and SKO BUFFS.
