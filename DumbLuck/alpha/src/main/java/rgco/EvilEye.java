package rgco;

public class EvilEye extends Enemy{
    public EvilEye(){
        ParaGlare pg = new ParaGlare();
        name = "Evil Eye";
        speed = 8;
        maxHealth = 10;
        currentHealth = 10;
        attackList[0] = pg;
        weakness[0] = 'h';  //h stands for holy weakness
    }

    public void printCurrentHealth(){
        System.out.println(name + "'s current health: " + currentHealth);
    }

    //attackList[0] is placeholder for now until enemy is fully programmed
    public Attack attackTurn(){
        System.out.println("Evil Eye uses Paralyzing Glare");
        return attackList[0];
    }
}
