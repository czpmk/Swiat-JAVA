package labO.world.gui.components.controlPanel;

import labO.world.World;
import labO.world.gui.prompts.InfoPrompt;
import labO.world.gui.prompts.PlaceRandomOrganismsPrompt;
import labO.world.gui.prompts.SetSizePrompt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel implements ActionListener {
    private final Dimension buttonSize = new Dimension(300, 30);
    private final World world_;
    private final Window window_;
    private boolean buttonsActive = true;

    //Buttons
    private final JButton nextRoundButton = new JButton("Następna runda");
    private final JButton clearButton = new JButton("Wyczyść świat");
    private final JButton resizeButton = new JButton("Zmień rozmiar planszy");
    private final JButton placeOrganismsButton = new JButton("Umiesc losowe organizmy");
    private final JButton saveButton = new JButton("Zapisz");
    private final JButton loadButton = new JButton("Wczytaj");
    private final JButton exitButton = new JButton("Wyjdź");

    //Labels
    private final JLabel title = new JLabel("github.com/czpmk");
    private final JLabel populationInfo = new JLabel("Populacja świata: " + 0);
    private final JLabel messageTitle = new JLabel("Ostatnie zdarzenie: ");
    private final JLabel messageText = new JLabel(" ");

    public ControlPanel(World world, Window window) {
        world_ = world;
        window_ = window;
        setBorder(BorderFactory.createLineBorder(Color.white));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addComponents();

        setPreferredSize(new Dimension(300, 280));
    }

    private void addComponents() {
        add(title);
        add(populationInfo);
        add(messageTitle);
        add(messageText);
        // nastepna runda
        nextRoundButton.setMaximumSize(buttonSize);
        nextRoundButton.addActionListener(this);
        add(nextRoundButton);
        // umiesc organizmy
        placeOrganismsButton.setMaximumSize(buttonSize);
        placeOrganismsButton.addActionListener(this);
        add(placeOrganismsButton);
        // zmien rozmiar
        resizeButton.setMaximumSize(buttonSize);
        resizeButton.addActionListener(this);
        add(resizeButton);
        // wyczysc swiat
        clearButton.setMaximumSize(buttonSize);
        clearButton.addActionListener(this);
        add(clearButton);
        // save
        saveButton.setMaximumSize(buttonSize);
        saveButton.addActionListener(this);
        add(saveButton);
        // load
        loadButton.setMaximumSize(buttonSize);
        loadButton.addActionListener(this);
        add(loadButton);
        // exit
        exitButton.setMaximumSize(buttonSize);
        exitButton.addActionListener(this);
        add(exitButton);
    }

    public void setMessage(String message) {
        messageText.setText(message);
    }

    public void setPopulation(int population) {
        populationInfo.setText("Populacja świata: " + population);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (buttonsActive) {
            if (source == nextRoundButton) {
                world_.nextRound();

            } else if (source == placeOrganismsButton) {
                new PlaceRandomOrganismsPrompt((world_));

            } else if (source == resizeButton) {
                new SetSizePrompt(world_);

            } else if (source == clearButton) {
                world_.clear();

            } else if (source == saveButton) {
                buttonsActive = false;
                world_.saveWorld();
                new InfoPrompt("Stan świata został zapisyny");
                buttonsActive = true;

            } else if (source == loadButton) {
                buttonsActive = false;
                world_.loadWorld();
                new InfoPrompt("Stan świata został wczytany");
                buttonsActive = true;

            } else if (source == exitButton) {
                window_.dispose();
            }
        }
    }
}
