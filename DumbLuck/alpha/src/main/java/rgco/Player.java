package rgco;
import java.util.Scanner;

public class Player {
    String name;
    int level ;
    int strength;
    int speed;
    int maxHealth;
    int currentHealth;
    int mana;
    Weapon type;
    Item itemList[] = new Item[3];
    Attack attackList[] = new Attack[4];

    public Player(){
        level = 10;
        strength = 10;
        speed = 10;
        maxHealth = 10;
        currentHealth = 10;
        mana = 10;
        Potion potion = new Potion();
        itemList[0] = potion;
    }

    /*
     * Description: When run will print out all of player's stats
     */
    public void printPlayerStats(){
        System.out.println(name + "'s stats");
        System.out.println("Level: " + level);
        System.out.println("Strength: " + strength);
        System.out.println("Speed: " + speed);
        System.out.println("Current Health: " + currentHealth);
        System.out.println("Mana: " + mana);
        System.out.println("Weapon: " + type.name);
        System.out.print("Item list: ");
        for (int i = 0; i < itemList.length; i++){
            if(itemList[i] != null){
                System.out.print(itemList[i].name);
                if(itemList[i+1] != null){
                    System.out.print(", ");
                }
            }
            else {
                System.out.println(" ");
            }
        }
    }

    /*
     * Description: sets the players weapon type, either sword, bow, or magic staff
     */
    public void setWeaponType(Weapon weapon){
        type = weapon;
    }

    /*
     * Description: sets the players list of possible attacks depending on their weapon of choice
     */
    public void setAttackList(Weapon weapon){
        if (weapon.name.equals("sword")){
            Slash slash = new Slash();
            Thrust thrust = new Thrust();
            Flurry flurry = new Flurry();
            ShieldBash shieldBash = new ShieldBash();
            attackList[0] = slash;
            attackList[1] = thrust;
            attackList[2] = flurry;
            attackList[3] = shieldBash;
        }

        if (weapon.name.equals("staff")){
            Heal heal = new Heal();
            FireBall fireBall = new FireBall();
            ThunderBolt thunderBolt = new ThunderBolt();
            RockThrow rockThrow = new RockThrow();
            attackList[0] = heal;
            attackList[1] = fireBall;
            attackList[2] = thunderBolt;
            attackList[3] = rockThrow;
        }

        if (weapon.name.equals("bow")){
            ArrowStorm arrowStorm = new ArrowStorm();
            FireArrow fireArrow = new FireArrow();
            SharpShot sharpShot = new SharpShot();
            HeavyShot heavyShot = new HeavyShot();
            attackList[0] = arrowStorm;
            attackList[1] = fireArrow;
            attackList[2] = sharpShot;
            attackList[3] = heavyShot;
        }

        if (weapon.name.equals("excalibur")){
            HolyLight holylight = new HolyLight();
            Smite smite = new Smite();
            Disintegration disin = new Disintegration();
            PerfectSlash perfectSlash = new PerfectSlash();
            attackList[0] = holylight;
            attackList[1] = smite;
            attackList[2] = disin;
            attackList[3] = perfectSlash;
        }

        if (weapon.name.equals("Celeste")){
            HellBlaze hellBlaze = new HellBlaze();
            Meteor meteor = new Meteor();
            OtherWorldlyBlessing owb = new OtherWorldlyBlessing();
            PureWhite pureWhite = new PureWhite();
            attackList[0] = hellBlaze;
            attackList[1] = meteor;
            attackList[2] = owb;
            attackList[3] = pureWhite;
        }

        //in progress
        if (weapon.name.equals("Wind Breaker")){
            Sniper sniper = new Sniper();
            Meteor meteor = new Meteor();
            OtherWorldlyBlessing owb = new OtherWorldlyBlessing();
            PureWhite pureWhite = new PureWhite();
            attackList[0] = sniper;
            attackList[1] = meteor;
            attackList[2] = owb;
            attackList[3] = pureWhite;
        }
    }
    
    public void printWeapon(Weapon weapon){
        System.out.println(weapon.name);
    }

    /*
     * Description: checks if the player is at full health or not. will be used whenever player heals
     */
    public boolean isFullHealth(){
        if (maxHealth != currentHealth){
            return false;
        }
        else {
            return true;
        }
    }

    public String playerDeath(){
        Scanner userIn = new Scanner(System.in);
        System.out.println("You have died!");
        System.out.println("Would you like to play again?");
        String choice = userIn.nextLine();
        userIn.close();
        return choice;
    }

    /*
     * Description: after obtaining an item from a chest, this will be called to add the item to the 
     * player's item list
     */
    public void addItem(Item item){
        for (int i = 0; i < itemList.length; i++){
            if(itemList[i] == null){
                itemList[i] = item;
                return;
            }
        }
        System.out.println("You have too many items!");
    }

    public Attack attackTurn(){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Which attack would you like to use?");
        //printAttacks();
        System.out.println("a. " + attackList[0].name + " b. " + attackList[1].name + " c. " + attackList[2].name + " d. " + 
            attackList[3].name + " e. Item Bag");
        String choice = userIn.nextLine();

        if (choice.equals("a")){
            System.out.println("You used " + attackList[0].name);
            return attackList[0];
        }

        if (choice.equals("b")){
            System.out.println("You used " + attackList[1].name);
  
            return attackList[1];
        }

        if (choice.equals("c")){
            System.out.println("You used " + attackList[2].name);

            return attackList[2];
        }

        if (choice.equals("d")){
            System.out.println("You used " + attackList[3].name);

            return attackList[3];
        }

        if (choice.equals("e")){
            System.out.println("Which item would you like to use?");
            printItemList();
            choice = userIn.nextLine();
            /*if(choice.equals("a")){

            }*/
            Attack attack = new Attack();
            attack.name = "potion";
            return attack;
        }

        return null;
    }

    public void printItemList(){
        if(itemList[0] != null){
            System.out.print("a. " + itemList[0].name);
            if(itemList[1] != null){
                System.out.print(" b. " + itemList[1].name);
                if(itemList[2] != null){
                    System.out.print(" c. " + itemList[2].name);
                }
            }
        }
        System.out.println(" ");
    }

    public void printCurrentHealth(){
        System.out.println(name + "'s current health: " + currentHealth);
    }
}
