public class Player {
    String name;
    int level ;
    int strength;
    int speed;
    int maxHealth;
    int currentHealth;
    int mana;
    Weapon type;
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
        System.out.println("Health: " + maxHealth);
        System.out.println("Mana: " + mana);
        System.out.println("Weapon: " + type.name);
    }

    /*
     * Description: sets the players weapon type, either sword, bow, or magic staff
     */
    public void setWeaponType(Weapon weapon){
        type = weapon;
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
}
