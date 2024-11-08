package rgco;

import org.junit.Assert;
import org.junit.Test;

public class BurnTest {
    @Test
    public void testPlayerBurn(){
        Player p = new Player();
        Burn b = new Burn();
        b.playerBurned(p);
        Assert.assertEquals(p.currentHealth, 9);
    }

    @Test
    public void testEnemyBurnGoblin(){
        Goblin g = new Goblin();
        Burn b = new Burn();
        b.enemyBurned(g);
        Assert.assertEquals(g.currentHealth, 5);
    }

    @Test
    public void testEnemyBurnEvilEye(){
        EvilEye e = new EvilEye();
        Burn b = new Burn();
        b.enemyBurned(e);
        Assert.assertEquals(e.currentHealth, 9);
    }
}
