public class Goblin extends Enemy{
    public Goblin(){
        KnifeJab knifeJab = new KnifeJab();
        name = "goblin";
        speed = 3;
        maxHealth = 6;
        currentHealth = 6;
        attackList[0] = knifeJab;
    }

    //note that this will be changed in the final deliverable
    //placeholder for enemy AI
    public Attack attackTurn(){
        System.out.println("The goblin used Knife Jab");
        return attackList[0];
    }

    public void printCurrentHealth(){
        System.out.println(name + "'s current health: " + currentHealth);
    }
}
