package priv.klochkov.island.model.animal.predators;


public class Wolf extends Predator {

    public Wolf(int x, int y) {
        super(3, 30, 8, x, y);
    }

    public static void main(String[] args) {
        Wolf wolf = new Wolf(2,2);
        System.out.println("Speed " + wolf.speedMovement);
        System.out.println("Weight " + wolf.weight);
        System.out.println("NeedFoodKg " + wolf.maxSatiety);
        System.out.println();
    }
}
