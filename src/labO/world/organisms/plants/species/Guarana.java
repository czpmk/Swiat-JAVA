package labO.world.organisms.plants.species;

import labO.world.exceptions.IncreaseStrengthException;
import labO.world.messages.Message;
import labO.world.organisms.Organism;
import labO.world.organisms.animals.Animal;
import labO.world.organisms.plants.Plant;

import javax.swing.*;

public class Guarana extends Plant {
    private final static ImageIcon icon = new ImageIcon(assetDirPath + "guarana.png");
    private final static String name = "Guarana";

    public Guarana() {
        super(10,0);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void defendAgainst(Organism aggressor) throws Exception {
        super.defendAgainst(aggressor);
        if (aggressor instanceof Animal) {
            world_.addMessage(new Message(0,
                    this.getLocation().toString() + " - " +
                            aggressor.getName() + " zjada "
                            + this.getName() + ", zwieksza swoja sile o 3"));
            throw new IncreaseStrengthException("Zwierze zjadlo guarane, jego sila powinna sie zwiekszyc o 3\n");
        }
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }
}
