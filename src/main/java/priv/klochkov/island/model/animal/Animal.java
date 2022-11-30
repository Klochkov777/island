package priv.klochkov.island.model.animal;

import priv.klochkov.island.constants.Gender;
import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.animal.interfaces.IEatable;

import java.util.List;
import java.util.Random;


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
        this.gender = getRandomGender();
        this.isAlive = true;

    }

    @Override
    public void eat(Inhabitant inhabitant) {
        if (isFullSatiety) {
            return;
        }
        satiety += inhabitant.getWeight();
        if (satiety >= maxSatiety) {
            isFullSatiety = true;
        }
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

    private Gender getRandomGender() {
        List<Gender> genders = List.of(Gender.values());
        Random RANDOM = new Random();
        return genders.get(RANDOM.nextInt(genders.size()));
    }

    public void setSatiety(float satiety) {
        if (satiety < 0) {
            satiety = 0;
        }
        this.satiety = satiety;
    }

    @Override
    public String toString() {
        return "{" + this.getClass().getSimpleName() +
                " gender=" + gender +
                '}';
    }
}
