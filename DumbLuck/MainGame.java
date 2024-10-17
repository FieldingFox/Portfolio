
/*
 * Author: Scott Rincon
 * Title: Dumb Luck
 * Description: This program runs a basic, turn-based dungeon crawl story game that's 100% RNG in Java. 
 * Note: This is a massive work in progress. This program is lacking multiple key functions for
 * a working game and doesn't have a working UI(yet). Please keep this in mind. Also make sure to open
 * the entire folder in order to make sure it functions
 */

 import java.util.Scanner;
 import java.util.Random;

 public class MainGame {
    public static Player p = new Player();    //creates new player object p
    public static Goblin g = new Goblin();      //creates goblin enemy g
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
        Scanner userIn = new Scanner(System.in);    //takes user's input
        System.out.println("You see two tunnels ahead of you. Which tunnel do you enter?");
        System.out.println("a. Right or b. Left");
        System.out.println("Type your answer as the number of your choice");
        String choice = userIn.nextLine();

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
                nadaCave();
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
                nadaCave();
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

    }

    /* 
     * The last cave needed to beat before finishing the game
    */
    public static void caveFinal(){

    }

    /*
     * The fight interaction that can be set to 2 different difficulties depending on the cave it occured in
     * Note: not called in caveFinal b/c the last cave is set to be a guranteed battle level
     */
    public static void fightInteraction(int rank){
        Scanner userIn = new Scanner(System.in);
        String response;
        if(rank == 1){
            System.out.println("As you enter the cave, a goblin appears before you.");
            while(g.currentHealth > 0 || p.currentHealth > 0){
                if(p.speed > g.speed){
                    p.attackTurn();
                    g.attackTurn();
                }
                else {
                    g.attackTurn();
                    p.attackTurn();
                }
            }
            if(g.currentHealth == 0){
                System.out.println("The goblin has been defeated.");
                System.out.println("You have gained a level. All stats have been increased by 1.");
                p.level++;
                p.strength++;
                p.mana++;
                p.maxHealth++;
                p.currentHealth++;
                p.speed++;
                p.printPlayerStats();
                caveTwo();
            }

            else if(p.currentHealth == 0){
                System.out.println("You Died!");
                System.out.println("Try again?(y/n)");
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
    public static void nadaCave(){
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
        caveTwo();
    }

    /*
     * The trap interaction that can be set to to 2 different difficulties depending on the cave it occured in
     */
    public static void trapInteraction(int rank){
        if (rank == 1){
            System.out.println("As you enter the cave, a bat is spooked and flys out of the cave.");
            System.out.println("As it flies out, it hits you square in the face.(health -1)");
            p.currentHealth = p.currentHealth - 1;
        }
        p.printPlayerStats();
        caveTwo();
    }

    /*
     * The treasure interaction that can be set to 2 different rarities depending on the cave it occured in
     */
    public static void treasureInteraction(int rank){
        if (rank == 1){
            System.out.println("You enter the cave and see a treasure chest in the back corner.");
            treasureRoll1();
        }
        p.printPlayerStats();
        caveTwo();
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
            p.level += 1;
            p.strength += 1;
            p.speed -= 1;
        }

        //10% chance to find the legendary weapon
        if (random > 7 && random <= 8){
            System.out.println("You open the chest and find the legendary weapon [blank]");
        }

        //20% chance to find a rat
        if (random > 8 && random <= 10){
            System.out.println("You open the chest and there is nothing but a rat. It bites you as it escapes.(health -1)");
            p.currentHealth = p.currentHealth - 1;
        }
    }
 }