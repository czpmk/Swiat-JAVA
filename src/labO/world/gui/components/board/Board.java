package labO.world.gui.components.board;

import labO.world.Location;
import labO.world.World;
import labO.world.organisms.Organism;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private final World world_;
    private Field[][] board;
    private int fieldsX;
    private int fieldsY;

    public Board(World world) {
        world_ = world;
        fieldsX = world.getSizeX();
        fieldsY = world.getSizeY();
        initializeBoard();
    }

    public void resizeBoard(int sizeX, int sizeY) {
        this.removeAll();
        fieldsX = sizeX;
        fieldsY = sizeY;
        initializeBoard();
    }

    private void initializeBoard() {
        int width = fieldsX * Field.getSizeX();
        int height = fieldsY * Field.getSizeY();
        Dimension size = new Dimension(height, width);

        board = new Field[fieldsY][fieldsX];
        setLayout(new GridLayout(fieldsX, fieldsY));

        for (int j = 0; j < fieldsY; j++) {
            for (int i = 0; i < fieldsX; i++) {
                board[j][i] = new Field(i, j, world_);
                add(board[j][i]);
            }
        }
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void setOrganismAt(Organism organism, Location location) {
        board[location.getY()][location.getX()].setOrganism(organism);
    }

    public Organism getOrganismAt(Location location) {
        return board[location.getY()][location.getX()].getOrganism();
    }
}
