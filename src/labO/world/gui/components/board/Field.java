package labO.world.gui.components.board;

import labO.world.Location;
import labO.world.World;
import labO.world.gui.prompts.AddOrganismPrompt;
import labO.world.organisms.Organism;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Field extends JLabel implements MouseListener {
    private final Location location;
    private final World world_;
    private final static ImageIcon emptyFieldIcon = new ImageIcon("src/labO/world/assets/empty.png");
    private final static Color borderColor = new Color(120, 120, 120);
    private final static int width = 27;
    private final static int height = 27;
    private final static Dimension size = new Dimension(width, height);
    private Organism organism_ = null;

    public Field(int x, int y, World world) {
        location = new Location(x, y);
        world_ = world;
        setSize(width, height);
        setMinimumSize(size);
        setMaximumSize(size);
        refreshIcon();
        setBorder(BorderFactory.createLineBorder(borderColor));
        this.addMouseListener(this);
    }

    public static int getSizeX() {
        return width;
    }

    public static int getSizeY() {
        return height;
    }

    public Organism getOrganism() {
        return organism_;
    }

    public void setOrganism(Organism organism) {
        this.organism_ = organism;
        refreshIcon();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        new AddOrganismPrompt(location, world_);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    private void refreshIcon() {
        if (organism_ == null) {
            setIcon(emptyFieldIcon);
        } else {
            setIcon(organism_.getIcon());
        }
    }
}
