package rgco;

public class FinalDemon extends Enemy{
    public FinalDemon(){
        name = "TBD";
        speed = 12;
        maxHealth = 20;
        currentHealth = 20;

    }

    public void printCurrentHealth(){
        System.out.println(name + "'s current health: " + currentHealth);
    }

    public Attack attackTurn(){
        Attack attack = new Attack();
        return attack;
    }
}
