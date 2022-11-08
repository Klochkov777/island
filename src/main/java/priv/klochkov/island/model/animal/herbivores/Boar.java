package priv.klochkov.island.model.animal.herbivores;

import priv.klochkov.island.model.animal.interfaces.IEatableAnimal;

//kaban
public class Boar extends Herbivore implements IEatableAnimal {
    public Boar(String fileNameProbabilityEat) {
        super("animals/probability/boar.properties", 3, 30, 8);
    }
}
