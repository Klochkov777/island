package priv.klochkov.island.model.animal;

import priv.klochkov.island.constants.Gender;
import priv.klochkov.island.model.Inhabitant;


public abstract class Animal extends Inhabitant {

    protected final int speedMovement;
    protected final int needFoodKg;
    protected final Gender gender;

    public Animal(int speedMovement, int weight, int needFoodKg) {
        super(weight);
        this.needFoodKg = needFoodKg;
        this.speedMovement = speedMovement;
        this.gender = Gender.randomGender();
    }

    @Override
    public String toString() {
        return "{" + this.getClass().getSimpleName() +
                " gender=" + gender +
                '}';
    }
}
