package rgco;

public class TornadoBlast extends Attack{
    public TornadoBlast(){
        name = "Tornado Blast";
        description = "Fire an arrow that creates a decimating tornado in its path; damage and crit chance increase based on number of debuffs on enemy";
        damage = 5;
        accuracy = 100;
        critChance = 10;
    }
}
