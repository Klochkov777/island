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
    protected boolean isDesireToMate;
    protected boolean isAlive;
    int x;
    int y;

    public Animal(int speedMovement, int weight, float maxSatiety) {
        super(weight);
        this.maxSatiety = maxSatiety;
        this.minSatiety = maxSatiety / 2;
        this.speedMovement = speedMovement;
        this.gender = Gender.randomGender();
        this.isAlive = true;
    }

    @Override
    public void eat(Inhabitant inhabitant) {
        if (!isFullSatiety) {return;}
        satiety += inhabitant.getWeight();
        if (satiety >= maxSatiety) {isFullSatiety = true;}
    }

    public void move(Direction direction){
        switch (direction){
            case UP -> x++;
            case DOWN -> x--;
            case LEFT -> y--;
            case RIGHT -> y++;
            case UP_AND_RIGHT -> {x++; y++;}
            case UP_AND_LEFT -> {x++; y--;}
            case DOWN_AND_LEFT -> {x--; y--;}
            case DOWN_AND_RIGHT -> {x--; y++;}
        }
    }

    @Override
    public String toString() {
        return "{" + this.getClass().getSimpleName() +
                " gender=" + gender +
                '}';
    }
}
