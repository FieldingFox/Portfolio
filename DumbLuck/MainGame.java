
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
        System.out.println("\tAuthor: Scott Rincon");
        System.out.println("***************************************");
        System.out.println("What is your username?");
        Player p = new Player();    //creates new player object p
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

    public static void caveOneInteraction(int r, String choice){
        int fightChance = 25;
        int nadaChance = 25;
        int treasureChance = 25;
        int trapChance = 25;
        if(choice.equals("a")){
            fightChance *= 2;
            nadaChance /= 2;
            treasureChance /= 2;
            trapChance /= 2;
        }

        else if(choice.equals("b")){
            trapChance *= 2;
            fightChance /= 2;
            nadaChance /= 2;
            treasureChance /= 2;
        }

        //the following lines use the random number r to determine what event occurs in cave one
        if(r <= fightChance){
            fightInteraction(1);
        }
        else if(r > fightChance && r <= nadaChance + fightChance){
            nadaCave();
        }
        else if(r > nadaChance + fightChance && r <= treasureChance + nadaChance + fightChance){
            treasureInteraction(1);
        }
        else if(r >= 100 - trapChance){
            trapInteraction(1);
        }
    }
    /* 
     * The second cave the user enters before the final level
    */
    public static void caveTwo(int r, String choice){

    }

    /* 
     * The last cave needed to beat before finishing the game
    */
    public static void caveFinal(int r, String choice){

    }

    /*
     * The fight interaction that can be set to 3 different difficulties depending on the cave it occured in
     */
    public static void fightInteraction(int rank){

    }

    /*
     * The scenario if the cave is empty
     */
    public static void nadaCave(){

    }

    /*
     * The trap interaction that can be set to to 3 different difficulties depending on the cave it occured in
     */
    public static void trapInteraction(int rank){

    }

    /*
     * The treasure interaction that can be set to 3 different rarities depending on the cave it occured in
     */
    public static void treasureInteraction(int rank){

    }
 }