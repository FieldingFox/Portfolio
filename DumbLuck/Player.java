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
    }

    /*
     * Description: When run will print out all of player's stats
     */
    public void printPlayerStats(){
        Potion potion = new Potion();
        itemList[0] = potion;
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
            Slash slash2 = new Slash();
            Slash slash3 = new Slash();
            Slash slash4 = new Slash();
            attackList[0] = slash;
            attackList[1] = slash2;
            attackList[2] = slash3;
            attackList[3] = slash4;
        }

        if (weapon.name.equals("staff")){

        }

        if (weapon.name.equals("bow")){

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
            System.out.println("You used Slash");
            return attackList[0];
        }

        if (choice.equals("b")){
            System.out.println("You used Thrust");
  
            return attackList[1];
        }

        if (choice.equals("c")){
            System.out.println("You used Flurry");

            return attackList[2];
        }

        if (choice.equals("d")){
            System.out.println("You used Shield Bash");

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
}
