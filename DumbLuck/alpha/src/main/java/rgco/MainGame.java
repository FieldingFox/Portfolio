package rgco;
/*
 * Author: Scott Rincon
 * Title: Dumb Luck
 * Description: This program runs a basic, turn-based dungeon crawl story game that's 100% RNG in Java. 
 * Note: This is a massive work in progress. This program is lacking multiple key functions for
 * a working game and doesn't have a working UI(yet). Please keep this in mind. Also make sure to open
 * the entire folder in order to make sure it functions
 * Current massive bugs to fix: 
 * Passives, buffs, and debuffs on attacks not programmed
 * goblin, evil eye, and demon attacks not created/not implemented
 * flush out demon(give demon name, voicelines, etc)
 */

import java.util.Scanner;

import java.util.Random;

 public class MainGame {
    public static Player p = new Player();          //creates new player object called p
    public static Goblin g = new Goblin();          //creates goblin enemy called g
    public static EvilEye e = new EvilEye();        //creates evil eye enemy called e
    public static FinalDemon d = new FinalDemon();  //creates final boss demon enemy called d
    public static void main(String[] args){
        /* testing zone
        if(true){
            Player x = new Player();
            Sword sword = new Sword();
            x.setWeaponType(sword);
            x.printWeapon(sword);
            System.exit(0);
        }
        */
        Scanner userIn = new Scanner(System.in);    //takes user's input
        System.out.println("***************************************");
        System.out.println("\tWelcome to Dumb Luck");
        System.out.println("\tCreator: Scott Rincon");
        System.out.println("***************************************");
        System.out.println("Press Enter to begin!");
        userIn.nextLine();
        System.out.println("What is your username?");
        p.name = userIn.nextLine();     //sets player name to user input
        System.out.println("What weapon do you want to use?");
        System.out.println("a. Sword, b. Bow and Arrow, or c. Magic Staff");
        System.out.println("Type your answer as the letter next to your choice");
        String choice = userIn.nextLine();   //sets choice to user's weapon choice
        p.setWeaponType(weaponChoice(choice));      //calls player class set weapon type function using the return of the weaponChoice function
        p.setAttackList(weaponChoice(choice));  //sets the players attack options
        System.out.println("Hello " + p.name + "\n" + "Here are your stats");
        p.printPlayerStats();
        gameStart();
        userIn.close();
    }

    public static Weapon weaponChoice(String choice){
        if(choice.equals("a")){
            Sword sword = new Sword();
            return sword;
        }
        if(choice.equals("b")){
            BowAndArrow bow = new BowAndArrow();
            return bow;
        }
        if(choice.equals("c")){
            MagicStaff staff = new MagicStaff();
            return staff;
        }
        else {
            Scanner scan = new Scanner(System.in);
            System.out.println("Invalid input: please try again");
            choice = scan.nextLine();
            scan.close();
            return weaponChoice(choice);
        }
    }
    /*
     * Starts the game
     */
    public static void gameStart(){
        Random rand = new Random();     //random number generator for RNG rolls
        int random = rand.nextInt(100);
        Scanner userIn = new Scanner(System.in);   
        System.out.println("You see two tunnels ahead of you. Which tunnel do you enter?");
        System.out.println("a. Right or b. Left");
        System.out.println("Type your answer as the letter of your choice");
        String choice = userIn.nextLine();      //takes user's input

        if(choice.equals("a") || choice.equals("b")){
            caveOne(random, choice);
        }

        else {
            System.out.println("Invalid input. Please try again");
            gameStart();
        }
        userIn.close();
    }

    /* 
     * The first cave the player must get through
     * takes in a random number as r for the cave challenge
     * then uses the choice from previous input to determine which interaction multiplier to use
    */
    public static void caveOne(int r, String choice){
        Random rand = new Random();
        int random = rand.nextInt(100);
        Scanner userIn = new Scanner(System.in);
        caveOneInteraction(r, choice);
        
    }

    /*
     * helper function for caveOne; makes random choice on what the player will see in cave one
     */
    public static void caveOneInteraction(int r, String choice){
        int fightChance = 25;
        int trapChance = 25;

        //choosing cave a makes fight chance twice as likely while removing chance to hit a trap
        if(choice.equals("a")){
            fightChance *= 2;
            //the following lines use the random number r to determine what event occurs in cave one
            if(r <= fightChance){
                System.out.println("Fight!");
                fightInteraction(1);
            }
            else if(r > fightChance && r <= 75){
                System.out.println("Nothing!");
                nadaCave(1);
            }
            else if(r > 75 && r <= 100){
                System.out.println("Treasure!");
                treasureInteraction(1);
            }
        }

        else if(choice.equals("b")){
            trapChance *= 2;
            //the following lines use the random number r to determine what event occurs in cave one
            if(r <= trapChance){
                System.out.println("Trap!");
                trapInteraction(1);
            }
            else if(r > fightChance && r <= 75){
                System.out.println("Nothing!");
                nadaCave(1);
            }
            else if(r > 75 && r <= 100){
                System.out.println("Treasure!");
                treasureInteraction(1);
            }
        }
    }
    /* 
     * The second cave the user enters before the final level
    */
    public static void caveTwo(){
        Scanner userIn = new Scanner(System.in);
        Random rand = new Random();
        int random = rand.nextInt(100);
        System.out.println("As you leave the last cave, you see two tunnels in front of you.");
        System.out.println("Which tunnel do you pick?");
        System.out.println("a. Right or b. Left");
        String choice = userIn.nextLine();
        if(choice.equals("a")){
            if(random <= 50){
                System.out.println("Nothing!");
                nadaCave(2);
            }

            else if(random > 50 && random <= 75){
                System.out.println("Trap!");
                trapInteraction(2);
            }

            else if(random > 75 && random <= 100){
                System.out.println("Fight!");
                fightInteraction(2);
            }
        }

        else if(choice.equals("b")){
            if(random <= 50){
                System.out.println("Treasure!");
                treasureInteraction(2);
            }

            else if(random > 50 && random <= 75){
                System.out.println("Trap!");
                trapInteraction(2);
            }

            else if(random > 75 && random <= 100){
                System.out.println("Fight!");
                fightInteraction(2);
            }
        }

        userIn.close();
    }

    /* 
     * The last cave needed to beat before finishing the game
    */
    public static void caveFinal(){
        System.out.println("As you enter the final cave, there is nothing but pure darkness before you.");
        //spelling may be off; check later
        System.out.println("You cautiously make your way into the room, and then torches light up throughout the large room.");    
        System.out.println("At the end of the room, you see a large throne hidden in the shadows created by the torches.");
        System.out.println("");        //placeholder for demon voice lines TBD
        System.out.println("A figure stands up from the throne and reveals himself as a demon.");
        System.out.println("");         //placeholder for demon introduction lines TBD
        System.out.println("[demon name TBD] unsheathes his sword and prepares to attack!");
        System.out.println("Fight!"); 

        //Final Boss fight sequence
        finalBoss();
    }

    /*
     * The fight interaction that can be set to 2 different difficulties depending on the cave it occured in
     * Note: not called in caveFinal b/c the last cave is set to be a guranteed battle level
     */
    public static void fightInteraction(int rank){
        Scanner userIn = new Scanner(System.in);
        String response;
        Random rand = new Random();
        int acc;
        int crit;

        //goblin fight in cave one
        if(rank == 1){
            System.out.println("As you enter the cave, a goblin appears before you.");

            while(g.currentHealth > 0 && p.currentHealth > 0){
                p.printCurrentHealth();
                g.printCurrentHealth();
                if(p.speed > g.speed){
                    Attack attack = p.attackTurn();
                    //fixing later, doesnt check if player is full
                    if(attack.name.equals("potion")){
                        System.out.println("You have used a potion(Health +3)");
                        p.currentHealth += 3;
                        p.printCurrentHealth();
                    }

                    else {
                        acc = rand.nextInt(100);
                        if (acc > 100 - attack.accuracy || attack.accuracy == 100){
                            crit = rand.nextInt(100);
                            if (crit <= attack.critChance){
                                System.out.println("Critical Hit!");
                                g.currentHealth = g.currentHealth - 2 * (attack.damage);
                            }

                            else {
                                g.currentHealth = g.currentHealth - (attack.damage);
                            }
                        }

                        else {
                            System.out.println("Your attack missed!");
                        }
                    }

                    if(g.currentHealth <= 0){
                        break;
                    }
                    g.printCurrentHealth();

                    Attack eattack = g.attackTurn();
                    acc = rand.nextInt(100);
                    if (acc > 100 - eattack.accuracy || eattack.accuracy == 100){
                        crit = rand.nextInt(100);
                        if (crit <= eattack.critChance){
                            System.out.println("Critical Hit!");
                            p.currentHealth = p.currentHealth - 2 * (eattack.damage);
                        }

                        else {
                            p.currentHealth = p.currentHealth - (eattack.damage);
                        }
                    }

                    else {
                        System.out.println("The goblin's attack missed!");
                    }
                    p.printCurrentHealth();
                }

                /*else {
                    g.attackTurn();
                    Attack eattack = g.attackTurn();
                    acc = rand.nextInt(100);
                    if (acc > 100 - eattack.accuracy || eattack.accuracy == 100){
                        crit = rand.nextInt(100);
                        if (crit <= eattack.critChance){
                            p.currentHealth = p.currentHealth - 2 * (eattack.damage);
                        }

                        else {
                            p.currentHealth = p.currentHealth - (eattack.damage);
                        }
                    }

                    else {
                        System.out.println("The goblin's attack missed!");
                    }
                    p.attackTurn();
                    Attack attack = p.attackTurn();
                    acc = rand.nextInt(100);
                    if (acc > 100 - attack.accuracy || attack.accuracy == 100){
                        crit = rand.nextInt(100);
                        if (crit <= attack.critChance){
                            g.currentHealth = g.currentHealth - 2 * (attack.damage);
                        }

                        else {
                            g.currentHealth = g.currentHealth - (attack.damage);
                        }
                    }

                    else {
                        System.out.println("Your attack missed!");
                    }
                }*/
            }
            if(g.currentHealth <= 0){
                System.out.println("The goblin has been defeated.");
                System.out.println("You have gained a level. All stats have been increased by 1.");
                p.level++;
                p.strength++;
                p.maxMana++;
                p.currentMana++;
                p.maxHealth++;
                p.currentHealth++;
                p.speed++;
                p.refillMana();
                p.printPlayerStats();
                caveTwo();
            }

            else if(p.currentHealth <= 0){
                System.out.println("You Died!");
                System.out.println("Play again?(y/n)");
                response = userIn.nextLine();
                if(response.equals("y")){
                    MainGame.main(null);
                }
                else if(response.equals("n")){
                    System.exit(0);
                }
            }
        }

        //evil eye fight in cave two
        if(rank == 2){
            System.out.println("As you find your way throught the darkeness, you run into a giant black evil eye!");

            while (p.currentHealth >= 0 && e.currentHealth >=0){
                p.printCurrentHealth();
                e.printCurrentHealth();
                if(p.speed > e.speed){
                    Attack attack = p.attackTurn();
                    //fixing later, doesnt check if player is full health
                    if(attack.name.equals("potion")){
                        System.out.println("You have used a potion(Health +3)");
                        p.currentHealth += 3;
                        p.printCurrentHealth();
                    }

                    else {
                        acc = rand.nextInt(100);
                        if (acc > 100 - attack.accuracy || attack.accuracy == 100){
                            crit = rand.nextInt(100);
                            if (crit <= attack.critChance){
                                System.out.println("Critical Hit!");
                                e.currentHealth = e.currentHealth - 2 * (attack.damage);
                            }

                            else {
                                e.currentHealth = e.currentHealth - (attack.damage);
                            }
                        }

                        else {
                            System.out.println("Your attack missed!");
                        }
                    }

                    if(e.currentHealth <= 0){
                        break;
                    }
                    e.printCurrentHealth();

                    Attack eattack = e.attackTurn();
                    acc = rand.nextInt(100);
                    if (acc > 100 - eattack.accuracy || eattack.accuracy == 100){
                        crit = rand.nextInt(100);
                        if (crit <= eattack.critChance){
                            System.out.println("Critical Hit!");
                            p.currentHealth = p.currentHealth - 2 * (eattack.damage);
                        }

                        else {
                            p.currentHealth = p.currentHealth - (eattack.damage);
                        }
                    }

                    else {
                        System.out.println("The Evil Eye's attack missed!");
                    }
                    p.printCurrentHealth();
                }
            }

            if(e.currentHealth <= 0){
                System.out.println("The Evil Eye has been defeated.");
                System.out.println("You have gained a level. All stats have been increased by 1.");
                p.level++;
                p.strength++;
                p.maxMana++;
                p.currentMana++;
                p.maxHealth++;
                p.currentHealth++;
                p.speed++;
                p.refillMana();
                p.printPlayerStats();
                caveFinal();
            }

            else if(p.currentHealth <= 0){
                System.out.println("You Died!");
                System.out.println("Play again?(y/n)");
                response = userIn.nextLine();
                if(response.equals("y")){
                    MainGame.main(null);
                }
                else if(response.equals("n")){
                    System.exit(0);
                }
            }
        }
        userIn.close();
    }

    /*
     * The scenario if the cave is empty
     */
    public static void nadaCave(int x){
        System.out.println("You enter the cave and find that nothing is there.");
        System.out.println("You decide to take a rest and fix your weapons.(Health +5)");
        if(!p.isFullHealth() && p.currentHealth + 5 <= p.maxHealth){
            p.currentHealth = p.currentHealth + 5;
        }
        else if(p.currentHealth + 5 > p.maxHealth){
            int healthIncrease = p.maxHealth - p.currentHealth;         //uses healthIncrease so that current health doesn't exceed max health
            p.currentHealth = p.currentHealth + healthIncrease;
        }
        p.printPlayerStats();
        if (x == 1){
            caveTwo();
        }
        
        if (x ==2){
            caveFinal();
        }
    }

    /*
     * The trap interaction that can be set to to 2 different difficulties depending on the cave it occured in
     */
    public static void trapInteraction(int rank){
        if (rank == 1){
            System.out.println("As you enter the cave, a bat is spooked and flys out of the cave.");
            System.out.println("As it flies out, it hits you square in the face.(Health -1)");
            p.currentHealth -= 1;
            p.printPlayerStats();
            caveTwo();
        }
        
        else if (rank == 2){
            System.out.println("The first step you take down the path, the ground drops from under you.");
            System.out.println("The brief second before you hit the ground, you see that the bottom of the pit is filled with spikes.(Health -4)");
            p.currentHealth -= 4;
            if (p.currentHealth <= 0){
                String response = p.playerDeath();
                if (response.equals("y")){
                    MainGame.main(null);
                }
                else if (response.equals("n")){
                    System.exit(0);
                }
            }

            else {
                p.printPlayerStats();
                caveFinal();
            }
        }
    }

    /*
     * The treasure interaction that can be set to 2 different rarities depending on the cave it occured in
     */
    public static void treasureInteraction(int rank){
        System.out.println("You enter the cave and see a treasure chest in the back corner.");
        if (rank == 1){
            treasureRoll1();
            p.printPlayerStats();
            caveTwo();
        }
        if ( rank == 2){
            treasureRoll2();
            p.printPlayerStats();
            caveFinal();
        }
    }

    /*
     * This roll function is for when the player finds a chest in the first cave
     * The probibility of finding certain objects changes in cave two
     */
    public static void treasureRoll1(){
        Random rand = new Random();
        int random = rand.nextInt(10);

        //50% chance to find a potion
        if (random <= 5){
            System.out.println("You open the chest and find a potion.");
            Potion potion = new Potion();
            p.addItem(potion);
        }

        //20% chance to find some armor
        if (random > 5 && random <= 7){
            System.out.println("You open the chest and find a new set of armor.");
            p.level += 2;
            p.maxHealth += 2;
            p.speed -= 1;
        }

        //10% chance to find the legendary weapon
        if (random > 7 && random <= 8){
            if (p.type.name.equals("sword")){
                System.out.println("You open the chest and find the legendary weapon Excalibur");
                Excalibur e = new Excalibur();
                p.setWeaponType(e);
                p.setAttackList(e);
            }

            if (p.type.name.equals("staff")){
                System.out.println("You open the chest and find the legendary weapon Celeste");     //changing later
                Celeste c = new Celeste();
                p.setWeaponType(c);
                p.setAttackList(c);
            }

            if (p.type.name.equals("bow")){
                System.out.println("You open the chest and find the legendary weapon WindBreaker");     //changing later
                WindBreaker w = new WindBreaker();
                p.setWeaponType(w);
                p.setAttackList(w);
            }
            
        }

        //20% chance to find a rat
        if (random > 8 && random <= 10){
            System.out.println("You open the chest and there is nothing but a rat. It bites you as it escapes.(health -1)");
            p.currentHealth = p.currentHealth - 1;
        }
    }

    /*
     * second treasure roll function for treasure cave interaction in caveTwo
     */
    public static void treasureRoll2(){
        Random rand = new Random();
        int random = rand.nextInt(10);

        //40% chance to find a potion
        if (random <= 4){
            System.out.println("You open the chest and find a potion.");
            Potion potion = new Potion();
            p.addItem(potion);
        }

        //20% chance to find some armor
        if (random > 4 && random <= 6){
            System.out.println("You open the chest and find a new set of armor.");
            p.level += 3;
            p.maxHealth += 3;
            p.speed -= 2;
        }

        //20% chance to find the legendary weapon
        if (random > 6 && random <= 8){
            if (p.type.name.equals("sword")){
                System.out.println("You open the chest and find the legendary weapon Excalibur");
                Excalibur e = new Excalibur();
                p.setWeaponType(e);     //sets player's weapon as excalibur and the new attack list
                p.setAttackList(e);
            }

            if (p.type.name.equals("staff")){
                System.out.println("You open the chest and find the legendary weapon Celeste");     //legendary magic staff
                Celeste c = new Celeste();
                p.setWeaponType(c);
                p.setAttackList(c);
            }

            if (p.type.name.equals("bow")){
                System.out.println("You open the chest and find the legendary weapon WindBreaker");     //legendary bow weapon
                WindBreaker w = new WindBreaker();
                p.setWeaponType(w);
                p.setAttackList(w);
            }
            
        }

        //20% chance to find a trap
        if (random > 8 && random <= 10){
            System.out.println("You open the chest and the chest becomes a trap and bites you back.(health -3)");
            p.currentHealth = p.currentHealth - 3;
        }
    }

    /*
     * Begins final boss fight, and creates the victory screen at end if player beats the boss
     */
    public static void finalBoss(){
        Scanner userIn = new Scanner(System.in);
        String response;        //used if player dies
        Random rand = new Random();
        int acc;
        int crit;
        
        while (p.currentHealth > 0 && d.currentHealth > 0){
            p.printCurrentHealth();
            d.printCurrentHealth();
            if(p.speed >= d.speed){
                Attack attack = p.attackTurn();
                //fixing later, doesnt check if player is full health
                if(attack.name.equals("potion")){
                    System.out.println("You have used a potion(Health +3)");
                    p.currentHealth += 3;
                    p.printCurrentHealth();
                }

                else {
                    acc = rand.nextInt(100);
                    if (acc > 100 - attack.accuracy || attack.accuracy == 100){
                        crit = rand.nextInt(100);
                        if (crit <= attack.critChance){
                            System.out.println("Critical Hit!");
                            d.currentHealth = d.currentHealth - 2 * (attack.damage);
                        }

                        else {
                            d.currentHealth = d.currentHealth - (attack.damage);
                        }
                    }

                    else {
                        System.out.println("Your attack missed!");
                    }
                }

                if(d.currentHealth <= 0){
                    break;
                }
                d.printCurrentHealth();

                Attack eattack = d.attackTurn();    //enemies attack variable
                acc = rand.nextInt(100);
                if (acc > 100 - eattack.accuracy || eattack.accuracy == 100){
                    crit = rand.nextInt(100);
                    if (crit <= eattack.critChance){
                        System.out.println("Critical Hit!");
                        p.currentHealth = p.currentHealth - 2 * (eattack.damage);
                    }

                    else {
                        p.currentHealth = p.currentHealth - (eattack.damage);
                    }
                }

                else {
                    System.out.println(d.name + "'s attack missed!");
                }
                p.printCurrentHealth();
            }

            else if (p.speed < d.speed){
                Attack eattack = d.attackTurn();    //enemies attack variable
                acc = rand.nextInt(100);
                if (acc > 100 - eattack.accuracy || eattack.accuracy == 100){
                    crit = rand.nextInt(100);
                    if (crit <= eattack.critChance){
                        System.out.println("Critical Hit!");
                        p.currentHealth = p.currentHealth - 2 * (eattack.damage);
                    }

                    else {
                        p.currentHealth = p.currentHealth - (eattack.damage);
                    }
                }

                else {
                    System.out.println(d.name + "'s attack missed!");
                }
                p.printCurrentHealth();

                Attack attack = p.attackTurn();
                //fixing later, doesnt check if player is full health
                if(attack.name.equals("potion")){
                    System.out.println("You have used a potion(Health +3)");
                    p.currentHealth += 3;
                    p.printCurrentHealth();
                }

                else {
                    acc = rand.nextInt(100);
                    if (acc > 100 - attack.accuracy || attack.accuracy == 100){
                        crit = rand.nextInt(100);
                        if (crit <= attack.critChance){
                            System.out.println("Critical Hit!");
                            d.currentHealth = d.currentHealth - 2 * (attack.damage);
                        }

                        else {
                            d.currentHealth = d.currentHealth - (attack.damage);
                        }
                    }

                    else {
                        System.out.println("Your attack missed!");
                    }
                }

                if(d.currentHealth <= 0){
                    break;
                }
                d.printCurrentHealth();
            }
        }

        //if demon dies
        if(d.currentHealth <= 0){
            System.out.println("The demon " + d.name + " has been defeated.");
            System.out.println("Congratulations on beating Dumb Luck(demo)!");
            System.out.println("Here are your final stats.");
            p.level++;
            p.strength++;
            p.maxMana++;
            p.currentMana++;
            p.maxHealth++;
            p.currentHealth++;
            p.speed++;
            p.refillMana();
            p.printPlayerStats();
            System.out.println("Thank you for playing!");
        }

        //if player dies
        else if(p.currentHealth <= 0){
            System.out.println("You Died!");
            System.out.println("Play again?(y/n)");
            response = userIn.nextLine();
            if(response.equals("y")){
                MainGame.main(null);
            }
            else if(response.equals("n")){
                System.exit(0);
            }
        }
        userIn.close();
    }
 }