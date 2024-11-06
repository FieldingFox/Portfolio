package rgco;

import java.util.Random;

public class Shock extends Debuff {
    public Shock(){
        name = "Shock";
    }

    public void playerShocked(Player p){
        Random rand = new Random();
        int random = rand.nextInt(100);
        if(random >= 90){
            p.turnSkip = 1;
        }

        else {
            p.turnSkip = 0;
        }
    }
}
