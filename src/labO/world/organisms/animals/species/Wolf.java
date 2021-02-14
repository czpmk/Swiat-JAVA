package labO.world.organisms.animals.species;

import labO.world.organisms.animals.Animal;

import javax.swing.*;

public class Wolf extends Animal {
    private final static ImageIcon icon = new ImageIcon(assetDirPath + "wolf.png");
    private final static String name = "Wilk";

    public Wolf() {
        super(1,9, 5);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }
}
