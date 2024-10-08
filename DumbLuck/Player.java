public class Player {
    String name;
    int level ;
    int strength;
    int speed;
    int health;
    int mana;
    Weapon type;
    public Player(){
        level = 10;
        strength = 10;
        speed = 10;
        health = 10;
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
        System.out.println("Health: " + health);
        System.out.println("Mana: " + mana);
        System.out.println("Weapon: " + type.name);
    }

    public void setWeaponType(Weapon weapon){
        type = weapon;
    }

    public void printWeapon(Weapon weapon){
        System.out.println(weapon.name);
    }
}
