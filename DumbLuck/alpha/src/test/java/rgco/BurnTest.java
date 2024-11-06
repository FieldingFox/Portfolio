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
}
