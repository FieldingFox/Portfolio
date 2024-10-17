public class Goblin extends Enemy{
    public Goblin(){
        KnifeJab knifeJab = new KnifeJab();
        name = "goblin";
        speed = 3;
        maxHealth = 6;
        currentHealth = 6;
        attackList[0] = knifeJab;
    }

    public void attackTurn(){
        
    }
}
