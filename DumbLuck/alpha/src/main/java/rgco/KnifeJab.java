package rgco;
public class KnifeJab extends Attack{
    int bloodLoss;

    public KnifeJab(){
        name = "KnifeJab";
        description = "user stabs at enemy with a knife; may cause blood-loss";
        damage = 3;
        accuracy = 90;
        critChance = 10;
        bloodLoss = 10;
    }
}
