package priv.klochkov.island.controller;

import priv.klochkov.island.Factory.FactoryInhabitant;
import priv.klochkov.island.model.Inhabitant;
import priv.klochkov.island.model.island.Location;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ControllerLocation {
    Location location;

    public ControllerLocation(Location location) {
        this.location = location;
    }

    public void settleInhabitantsToLocation(Location location) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        location.setInhabitants(FactoryInhabitant.createInhabitants());
    }
}
