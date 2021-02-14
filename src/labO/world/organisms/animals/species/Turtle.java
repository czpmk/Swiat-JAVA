package labO.world.organisms.animals.species;

import labO.world.exceptions.FieldInaccessibleException;
import labO.world.messages.Message;
import labO.world.organisms.Organism;
import labO.world.organisms.animals.Animal;

import javax.swing.*;

public class Turtle extends Animal {
    private final static ImageIcon icon = new ImageIcon(assetDirPath + "turtle.png");
    private final static String name = "Zolw";

    public Turtle() {
        super(1,2, 1);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public void defendAgainst(Organism aggressor) throws Exception {
        super.defendAgainst(aggressor);
        if (aggressor.getStrength() < 5 && aggressor instanceof Animal) {
            world_.addMessage(new Message(0,
                    this.getLocation().toString() + " - " +
                            this.getName() + " przepedza ze swojego pola "
                            + aggressor.getName()));
            throw new FieldInaccessibleException("Zwierze o sile < 5 probowalo wejsc na pole zajmowane przez zolwia");
        }
    }

    @Override
    public void action() {
        if (rand.nextInt(4) == 3) {
            super.action();
        }
    }
}
