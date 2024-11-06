package rgco;
public class Enemy extends Being{
    String name;
    int speed;
    int maxHealth;
    int currentHealth;
    Attack[] attackList = new Attack[4];
}
