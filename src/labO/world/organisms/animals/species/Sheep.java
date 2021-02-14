package labO.world.organisms.animals.species;

import labO.world.organisms.animals.Animal;

import javax.swing.*;

public class Sheep extends Animal {
    private final static ImageIcon icon = new ImageIcon(assetDirPath + "sheep.png");
    private final static String name = "Owca";

    public Sheep() {
        super(1,4, 4);
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
