package labO.world;

import labO.world.organisms.Organism;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SaveLoadUtility {
    private final World world_;
    private File saveFile;
    private final String delimiter = ";";
    BufferedWriter writer;

    public SaveLoadUtility(World world) {
        world_ = world;
    }

    public void load() {
        try {
            saveFile = new File("save.txt");
            Scanner reader = new Scanner(saveFile);
            int numberOfOrganisms = loadWorld(reader.nextLine());
            while (reader.hasNextLine()) {
                loadOrganism(reader.nextLine());
                numberOfOrganisms -= 1;
            }
            reader.close();
            if (numberOfOrganisms != 0) throw new Exception("Problem przy wczytywaniu organizmów");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            saveFile = new File("save.txt");
            writer = new BufferedWriter(new FileWriter(saveFile));
            saveWorld();
            Set<Organism> organismSet = world_.getOrganismSet();
            for (Organism organism : organismSet) {
                saveOrganism(organism);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeNextElement(int element) throws IOException {
        writer.append(Integer.toString(element)).append(delimiter);
    }

    private void saveWorld() throws IOException {
        writeNextElement(42);
        writeNextElement(world_.getSizeX());
        writeNextElement(world_.getSizeY());
        writeNextElement(world_.getPopulationSize());
        writer.append("\n");
    }

    private void saveOrganism(Organism organism) {
        try {
            writeNextElement(Organism.getOrganismConstructorNumber(organism));
            writeNextElement(organism.getLocation().getX());
            writeNextElement(organism.getLocation().getY());
            writeNextElement(organism.getStrength());
            writer.append("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int loadWorld(String line) throws Exception {
        List<String> array = Arrays.asList(line.split(delimiter));
        if (array.size() != 4)
            throw new Exception("Corrupted save file (wrong size of first line = " + array.size() + ", zawartosc: " + array + ")");
        if (array.get(0) == "42")
            throw new Exception("Corrupted save file - first word is " + array.get(0));
        int newX = Integer.parseInt(array.get(1));
        int newY = Integer.parseInt(array.get(2));
        world_.resize(newX, newY);
        return Integer.parseInt(array.get(3));
    }

    private void loadOrganism(String line) throws Exception {
        List<String> array = Arrays.asList(line.split(delimiter));
        if (array.size() != 4)
            throw new Exception("Corrupted save file - wrong size of Organism info = " + array.size());
        Organism organism = Organism.getNewInstanceOf(Integer.parseInt(array.get(0)));
        organism.setStrength(Integer.parseInt(array.get(3)));
        organism.addToWorld(new Location(Integer.parseInt(array.get(1)), Integer.parseInt(array.get(2))));
    }
}
