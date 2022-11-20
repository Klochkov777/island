package priv.klochkov.island.model.animal.predators;


public class Wolf extends Predator {

    public Wolf() {
        super(3, 30, 8);
    }

    public static void main(String[] args) {
        Wolf wolf = new Wolf();
        System.out.println("Speed " + wolf.speedMovement);
        System.out.println("Weight " + wolf.weight);
        System.out.println("NeedFoodKg " + wolf.maxSatiety);
        System.out.println();
    }
}
