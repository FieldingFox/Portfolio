package rgco;
public class Heal extends Attack{
    int manaRegen;
    public Heal(){
        name = "heal";
        description = "bathes the user in holy light, healing all injuries and re-energizing themselves; does damage to undead and dark creatures.";
        damage = 3;
        manaCost = 2;
        manaRegen = 3;
        accuracy = 100;
    }
}
