package labO.world.organisms.plants.species;

import labO.world.organisms.plants.Plant;

import javax.swing.*;

public class Dandelion extends Plant {
    private final static ImageIcon icon = new ImageIcon(assetDirPath + "dandelion.png");
    private final static String name = "Mlecz";

    public Dandelion() {
        super(20, 0);
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
    public void action() {
        for (int i = 0; i < 3; i++) {
            multiply(null);
        }
    }
}
