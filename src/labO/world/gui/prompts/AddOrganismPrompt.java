package labO.world.gui.prompts;

import labO.world.Location;
import labO.world.World;
import labO.world.organisms.Organism;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddOrganismPrompt extends JFrame implements ActionListener {
    private final Location location_;
    private final World world_;
    private final Dimension buttonSize = new Dimension(300,100);
    private final JButton wolfButton = new JButton("Wilk");
    private final JButton sheepButton = new JButton("Owca");
    private final JButton turtleButton = new JButton("Żółw");
    private final JButton lionButton = new JButton("Lew");
    private final JButton hummingbirdButton = new JButton("Koliber");
    private final JButton grassButton = new JButton("Trawa");
    private final JButton dandelionButton = new JButton("Mlecz");
    private final JButton guaranaButton = new JButton("Guarana");

    public AddOrganismPrompt(Location location, World world) {
        setLocation(MouseInfo.getPointerInfo().getLocation());
        this.setTitle("Dodawanie organizmów");
        location_ = location;
        world_ = world;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        addPanels();
        setPreferredSize(new Dimension(new Dimension(220, 248)));
        pack();
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        try {
            if (source == this.wolfButton) {
                Organism.getNewInstanceOf(0).addToWorld(location_);
                world_.refresh();
                this.dispose();

            } else if (source == sheepButton) {
                Organism.getNewInstanceOf(1).addToWorld(location_);
                world_.refresh();
                this.dispose();

            } else if (source == turtleButton) {
                Organism.getNewInstanceOf(2).addToWorld(location_);
                world_.refresh();
                this.dispose();

            } else if (source == lionButton) {
                Organism.getNewInstanceOf(3).addToWorld(location_);
                world_.refresh();
                this.dispose();

            } else if (source == hummingbirdButton) {
                Organism.getNewInstanceOf(4).addToWorld(location_);
                world_.refresh();
                this.dispose();

            } else if (source == grassButton) {
                Organism.getNewInstanceOf(5).addToWorld(location_);
                world_.refresh();
                this.dispose();

            } else if (source == dandelionButton) {
                Organism.getNewInstanceOf(6).addToWorld(location_);
                world_.refresh();
                this.dispose();

            } else if (source == guaranaButton) {
                Organism.getNewInstanceOf(7).addToWorld(location_);
                world_.refresh();
                this.dispose();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addPanels() {
        wolfButton.addActionListener(this);
        wolfButton.setMaximumSize(buttonSize);
        add(wolfButton);
        sheepButton.addActionListener(this);
        sheepButton.setMaximumSize(buttonSize);
        add(sheepButton);
        turtleButton.addActionListener(this);
        turtleButton.setMaximumSize(buttonSize);
        add(turtleButton);
        lionButton.addActionListener(this);
        lionButton.setMaximumSize(buttonSize);
        add(lionButton);
        hummingbirdButton.addActionListener(this);
        hummingbirdButton.setMaximumSize(buttonSize);
        add(hummingbirdButton);
        grassButton.addActionListener(this);
        grassButton.setMaximumSize(buttonSize);
        add(grassButton);
        dandelionButton.addActionListener(this);
        dandelionButton.setMaximumSize(buttonSize);
        add(dandelionButton);
        guaranaButton.addActionListener(this);
        guaranaButton.setMaximumSize(buttonSize);
        add(guaranaButton);
    }
}
