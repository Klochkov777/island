package priv.klochkov.island.models.animals.herbivores;

import priv.klochkov.island.models.animals.interfaces.IEatableAnimal;

public class Duck extends Herbivore implements IEatableAnimal {
    public Duck(String fileNameProbabilityEat) {
        super(fileNameProbabilityEat);
    }
}
