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
        System.out.println(name + "'s stats");
        System.out.println("Level: " + level);
        System.out.println("Strength: " + strength);
        System.out.println("Speed: " + speed);
        System.out.println("Health: " + currentHealth);
        System.out.println("Mana: " + mana);
        System.out.println("Weapon: " + type.name);
        for (int i = 0; i < itemList.length; i++){
            if(itemList[i] != null){
                System.out.println("Item list: " + itemList[i].name);
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
            attackList[0] = slash;
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
        System.out.println("Which attack would you like to use");
        System.out.println("a. " + attackList[0].name);
        String choice = userIn.nextLine();
        userIn.close();
        if (choice.equals("a")){
            System.out.println("You used Slash");
            return attackList[0];
        }
        else {
            return attackList[1];
        }
    }
}
