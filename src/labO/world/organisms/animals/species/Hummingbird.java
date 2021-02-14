package labO.world.organisms.animals.species;

import labO.world.exceptions.FieldInaccessibleException;
import labO.world.organisms.Organism;
import labO.world.organisms.animals.Animal;

import javax.swing.*;

public class Hummingbird extends Animal {
    private final static ImageIcon icon = new ImageIcon(assetDirPath + "hummingbird.png");
    private final static String name = "Koliber";

    public Hummingbird() {
        super(2, 1, 6);
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
    protected void attack(Organism defender) throws FieldInaccessibleException {
        // Nie zaatakuje innego zwierzecia
        if ((defender instanceof Animal) && !(defender instanceof Hummingbird)) {
            throw new FieldInaccessibleException("Koliber probowal zaatakowac zwierze");
        }
        super.attack(defender);
    }
}
