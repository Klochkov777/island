package priv.klochkov.island.model.animal.herbivores;

import priv.klochkov.island.model.animal.interfaces.IEatableAnimal;

public class Duck extends Herbivore implements IEatableAnimal {
    public Duck(String fileNameProbabilityEat) {
        super("animals/probability/duck.properties", 3, 30, 8);
    }
}
