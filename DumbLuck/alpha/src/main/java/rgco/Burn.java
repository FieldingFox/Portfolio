package rgco;

public class Burn extends Debuff{
    public Burn(){
        name = "Burned";
    }

    public void playerBurned(Player p){
        p.currentHealth -= 1;
    }

    public void enemyBurned(Enemy e){
        e.currentHealth -= 1;
    }
}
