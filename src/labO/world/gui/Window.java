package labO.world.gui;

import labO.world.World;
import labO.world.gui.components.board.Board;
import labO.world.gui.components.controlPanel.ControlPanel;

import javax.swing.*;


public class Window extends JFrame {
    private final ControlPanel controlPanel;
    private final Board board;

    public Window(World world) {
        controlPanel = new ControlPanel(world, this);
        board = new Board(world);
        this.setLocation(100,100);
        this.setTitle("Świat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.LINE_AXIS));
        this.add(board);
        this.add(controlPanel);

        pack();
        setResizable(false);
    }

    public Board getBoard() {
        return board;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }
}
