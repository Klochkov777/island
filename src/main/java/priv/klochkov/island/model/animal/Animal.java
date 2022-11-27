package priv.klochkov.island.model.animal;

import priv.klochkov.island.constants.Direction;
import priv.klochkov.island.constants.Gender;
import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.animal.interfaces.IEatable;


public abstract class Animal extends Inhabitant implements IEatable {

    protected final int speedMovement;
    protected final float maxSatiety;
    protected final float minSatiety;
    protected final Gender gender;
    protected float satiety;
    protected boolean isFullSatiety;

    protected boolean isAlive;


    public Animal(int speedMovement, float weight, float maxSatiety) {
        super(weight);
        this.maxSatiety = maxSatiety;
        this.minSatiety = maxSatiety / 2;
        this.speedMovement = speedMovement;
        this.gender = Gender.randomGender();
        this.isAlive = true;

    }

    @Override
    public void eat(Inhabitant inhabitant) {
        if (isFullSatiety) {return;}
        satiety += inhabitant.getWeight();
        if (satiety >= maxSatiety) {isFullSatiety = true;}
    }

    public int getSpeedMovement() {
        return speedMovement;
    }

    public float getMaxSatiety() {
        return maxSatiety;
    }

    public float getSatiety() {
        return satiety;
    }

    public float getMinSatiety() {
        return minSatiety;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean isFullSatiety() {
        return isFullSatiety;
    }

    @Override
    public String toString() {
        return "{" + this.getClass().getSimpleName() +
                " gender=" + gender +
                '}';
    }
}
