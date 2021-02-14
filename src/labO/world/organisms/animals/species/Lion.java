package labO.world.organisms.animals.species;

import labO.world.exceptions.FieldInaccessibleException;
import labO.world.messages.Message;
import labO.world.organisms.Organism;
import labO.world.organisms.animals.Animal;

import javax.swing.*;


public class Lion extends Animal {
    private final static ImageIcon icon = new ImageIcon(assetDirPath + "lion.png");
    private final static String name = "Lew";

    public Lion() {
        super(1,11, 7);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void defendAgainst(Organism aggressor) throws Exception {
        super.defendAgainst(aggressor);
        if (aggressor.getStrength() < 5 && aggressor instanceof Animal) {
            world_.addMessage(new Message(0,
                    this.getLocation().toString() + " - " +
                            this.getName() + " przepedza ze swojego pola "
                            + aggressor.getName()));
            throw new FieldInaccessibleException("Zwierze o sile < 5 probowalo wejsc na pole zajmowane przez lwa");
        }
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }
}
