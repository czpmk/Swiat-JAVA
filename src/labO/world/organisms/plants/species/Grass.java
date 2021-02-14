package labO.world.organisms.plants.species;

import labO.world.Location;
import labO.world.organisms.plants.Plant;

import javax.swing.*;

public class Grass extends Plant {
    private final static ImageIcon icon = new ImageIcon(assetDirPath + "grass.png");
    private final static String name = "Trawa";

    public Grass() {
        super(20,0);
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
