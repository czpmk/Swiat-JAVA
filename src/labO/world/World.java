package labO.world;
import labO.world.gui.components.board.Board;
import labO.world.gui.Window;
import labO.world.gui.components.controlPanel.ControlPanel;
import labO.world.messages.Message;
import labO.world.organisms.Organism;

import java.util.*;

public class World {
    private int sizeX = 20;
    private int sizeY = 20;
    private int placedOrganisms = 0;
    private final TreeSet<Message> messageTreeSet = new TreeSet<>();
    private final Stack<Organism> newOrganismStack = new Stack<>();
    private final Set<Organism> organismSet = new TreeSet<>();
    private final Board organismBoard;
    private final ControlPanel controlPanel;
    private final Window window;
    private final SaveLoadUtility slu;

    public World() {
        Organism.setWorld(this);
        window = new Window(this);
        organismBoard = window.getBoard();
        controlPanel = window.getControlPanel();
        slu = new SaveLoadUtility(this);
    }

    public void display() {
        controlPanel.setPopulation(getPopulationSize());
        controlPanel.setMessage(getLastMessage());
        window.setVisible(true);
    }

    // Board methods
    public void clear() {
        organismBoard.resizeBoard(sizeX, sizeY);
        organismSet.clear();
        newOrganismStack.clear();
        messageTreeSet.clear();
        placedOrganisms = 0;
        controlPanel.setPopulation(getPopulationSize());
        controlPanel.setMessage(getLastMessage());
        window.pack();
    }

    public void resize(int x, int y) {
        sizeX = x;
        sizeY = y;
        clear();
    }

    public void refresh() {
        pushOrganismsFromStackToSet();
        controlPanel.setPopulation(getPopulationSize());
        controlPanel.setMessage(getLastMessage());
    }

    public void nextRound() {
        messageTreeSet.clear();
        for (Organism organism : organismSet) {
            if (organism.isAlive()) {
                organism.action();
            }
        }
        cleanOrganismSet();
        refresh();
    }

    // Organism methods
    public void placeRandomOrganisms(int n) {
        try {
            for (int i = 0; i < n; i++) {
                Organism.getRandomOrganism().addToWorld(Organism.getRandomLocation());
            }
            cleanOrganismSet();
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Organism getOrganismAt(Location location) {
        return organismBoard.getOrganismAt(location);
    }

    public void placeOrganism(Organism organism) {
        int organismLimit = Integer.MAX_VALUE;
        if (placedOrganisms < organismLimit) {
            organism.setKey(getNewKey(organism));
            organismBoard.setOrganismAt(organism, organism.getLocation());
            newOrganismStack.push(organism);
        }
    }

    public void moveOrganism(Organism organism, Location oldLocation) {
        organismBoard.setOrganismAt(null, oldLocation);
        organismBoard.setOrganismAt(organism, organism.getLocation());
    }

    public void removeOrganism(Organism organism) {
        if (organism.getKey() == organismBoard.getOrganismAt(organism.getLocation()).getKey()) {
            organismBoard.setOrganismAt(null, organism.getLocation());
        } else {
            throw new Error("Proba usuniecia organizmu z pola, ktore zostalo nadpisane");
        }
    }

    // Messeges
    public void addMessage(Message newMessage) {
        this.messageTreeSet.add(newMessage);
    }

    public String getLastMessage() {
        if (messageTreeSet.isEmpty()) {
            return "Brak zdarzeń z ostatniej tury";
        } else {
            return messageTreeSet.first().getValue();
        }
    }

    // Load and Save methods
    public void saveWorld() {
        slu.save();
    }

    public void loadWorld() {
        slu.load();
        refresh();
    }

    // Getters
    public int getSizeX() {
        return this.sizeX;
    }

    public int getSizeY() {
        return this.sizeY;
    }

    public int getPopulationSize() {
        return this.organismSet.size();
    }

    public Set<Organism> getOrganismSet() {
        return this.organismSet;
    }

    // Private
    private void pushOrganismsFromStackToSet() {
        while (!newOrganismStack.empty()) {
            if (newOrganismStack.peek().isAlive()) {
                organismSet.add(newOrganismStack.pop());
            } else {
                newOrganismStack.pop();
            }
        }
    }

    private void cleanOrganismSet() {
        organismSet.removeIf(organism -> !organism.isAlive());
    }

    private Long getNewKey(Organism organism) {
        long newKey = 1024 - organism.getInitiative();
        placedOrganisms++;
        newKey = (newKey << 32) + placedOrganisms;
        return newKey;
    }
}
