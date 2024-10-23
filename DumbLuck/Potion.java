public class Potion extends Item {
    public Potion(){
        name = "Potion";
        description = "a flask of liquid; when consumed will regenerate 3+ health.";
    }

    public void printInfo(){
        System.out.println("Item name: " + name);
        System.out.println("Description: " + description);
    }
}
