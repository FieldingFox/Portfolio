package rgco;

//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertEquals;
import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {
    @Test
    public void testWeaponSet(){
        Player p = new Player();
        Sword s = new Sword();
        p.setWeaponType(s);
        Assert.assertEquals(s, p.type);
    }

    @Test
    public void testAttackList(){
        Player p = new Player();
        Sword s = new Sword();
        String expected = "Slash";
        p.setAttackList(s);
        Assert.assertEquals(expected, p.attackList[0].name);
    }

    @Test
    public void testIsFullHealthTrue(){
        Player p = new Player();
        boolean check = p.isFullHealth();
        Assert.assertTrue(check);
    }

    @Test
    public void testIsFullHealthFalse(){
        Player p = new Player();
        p.currentHealth -= 3;
        boolean check = p.isFullHealth();
        Assert.assertFalse(check);
    }

    @Test
    public void testAddItem(){
        Player p = new Player();
        Potion p2 = new Potion();
        Potion p3 = new Potion();
        Potion p4 = new Potion();
        p.addItem(p2);
        p.addItem(p3);
        p.addItem(p4);
        Assert.assertEquals(p2, p.itemList[1]);
        Assert.assertEquals(p3, p.itemList[2]);
    }
}
