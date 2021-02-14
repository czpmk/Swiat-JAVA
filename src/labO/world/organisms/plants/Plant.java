package labO.world.organisms.plants;

import labO.world.organisms.Organism;


public abstract class Plant extends Organism {
    protected Plant(int multiplicationChance, int strength) {
        super(strength, 0);
        multiplicationChance_ = multiplicationChance;
    }

    @Override
    public void action() {
        multiply(null);
    }
}
