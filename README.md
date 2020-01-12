// Copyright Nichita Radu, 321CA  
  
## League of OOP  
  
### Design Pattern Used  
  
  1. #### Visitor pattern 
    * (First stage) It was used between each type of Player and his abilities, in order to receive the damage modificator, which was necessary to calculate the damage given.
    * (Second stage) It was used between each type of Player and angels, in order to receive the specific help/hit from every angel.
  2. #### Builder pattern
    *(First stage) It was used when reading the input to avoid having to use too many parameters for a constructor.
    *(Second stage) Added the new parameters (when reading angels).
  3. #### Factory pattern
    *(First stage) Created the heroes based on received String from input file.
    *(Second stage) Created the angels based on received String.
  4. #### Strategy pattern
    *(Second stage) Provided an easier way to choose between each player's custom strategy.
  5. #### Observer pattern
    *(Second stage) Used by the great magician to receive notifications from angels and heroes.
  6. #### Singleton pattern
    *(Second stage) It was implemented for creating the map and the great magician, as they are unique in game.
  7. #### Command pattern
    *(Second stage) Command pattern is used to return a specific String and notify the great Magician that there is a new information he need to put in the output file.
    
### Structure of the Project:  
  
1. *(First stage)* abilites package - contain implementation for each ability ( 2 per hero).  

2. *(Second stage )* angels package - contain implementation for each type of angels, which allow interaction with both players and the Great Magician.

3. *(First and second stage)* heroes package - contain implementation for each type of hero, all classes inheriting from the Hero class  , which has the common methods for interacting with other heroes, angels and the Great Magician. 
  * **What's new**: 
    1. now the heroes can interact with angels, which are a new feature in the game. The angels can help/hit/kill/revive a player. 
    2. The hero can adopt a custom strategy, which is decided based on hp, which allows them to gain life and lose strength or vice-versa.
  
4. main package - for starting executing the program.  
  
5. *(First and second stage)* gameinput package - for reading the input from file 
* **What's new** 
  1.Now the builder pattern provided additional method for reading the number and types of angels for each round. 
  
6. *(First and second stage)* gameoutput package - used the PrintWriterClass to print the results to file.
* ***What's new**
  1.  has a method what is used by the great magician to add to file relevant information about angels and heroes.
  
7. *(First stage)* terrain package - contain a singleton class to store the map.  
  
  
### Program Flow  
  
**First_Stage** data input is read from file via gameInput package and then it is organised so we have an arraylist of player, a matrix map (and each element of matrix has a type : W, L, D, V). Then,the  game can begin. For each round, every player will try to do his move, but if stunned, then no  move is done. At the end of the moves, we'll be looking at player that are alive and in the  same cell. Those heroes will battle with different abilities. Every player will attack the other with 2 different abilities, and will try to give the attack on his favourite surface, if possible(because it gives extra damage). At the end of the fight, it's time to check if one of the player is dead, and if so, give XP Points to killer. If the killer has enough points to level up, his abilities will increase in power and his life would be regenerated (also receiving extra HP). After every round had been played, the output display all relevant parameters(hp, position, level or DEAD) for every player.
**What's new in the second stage?** Besides players, now there are angels, which spawn after each round and can help or confuse players by hitting or even killling them. Moreover, now heroes can adopt strategies in order to maximize winning chances, by giving HP in order to give more damage or vice-versa. "This trade" can be made only before starting a new round, if specific condition is satisfied.  Moreover, there is another character coming in this update, the Great Magician. He is notified whenever a new angel is spawned, a hero is helped/revived/killed/hit, or when a player levels up. In that way, it can be followed easily who is the best champion of League of OOP! Play now!


