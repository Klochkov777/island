package priv.klochkov.island.models.animals.herbivores;

import priv.klochkov.island.models.animals.interfaces.IEatableAnimal;

//kaban
public class Boar extends Herbivore implements IEatableAnimal {
    public Boar(String fileNameProbabilityEat) {
        super(fileNameProbabilityEat);
    }
}
