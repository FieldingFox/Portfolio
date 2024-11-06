package rgco;

import java.util.Random;

public class Shock extends Debuff {
    public Shock(){
        name = "Shock";
    }

    public void playerShocked(Player p){
        Random rand = new Random();
        int random = rand.nextInt(100);
        if(random >= 90){   //If this is true, player has been shocked and will miss a turn
            p.turnSkip = 1;
        }

        else {
            p.turnSkip = 0;
        }
    }

    public void enemyShocked(Enemy e){
        Random rand = new Random();
        int random = rand.nextInt(100);
        if(random >= 90){   //If this is true, enemy has been shocked and will miss a turn
            e.turnSkip = 1;
        }

        else {
            e.turnSkip = 0;
        }
    }
}
