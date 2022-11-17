package priv.klochkov.island.model.animal.herbivores;

import priv.klochkov.island.model.animal.interfaces.IEatableAnimal;

//kaban
public class Boar extends Herbivore implements IEatableAnimal {
    public Boar(int x, int y) {
        super(3, 30, 8, x, y);
    }
}
