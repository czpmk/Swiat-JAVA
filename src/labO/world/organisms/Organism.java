package labO.world.organisms;

import labO.world.Location;
import labO.world.World;
import labO.world.exceptions.FieldInaccessibleException;
import labO.world.exceptions.IncreaseStrengthException;
import labO.world.messages.Message;
import labO.world.organisms.animals.species.*;
import labO.world.organisms.plants.species.*;

import javax.swing.*;
import java.util.Random;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public abstract class Organism implements Comparable<Organism> {
    protected static String assetDirPath = "src/labO/world/assets/";
    private boolean isKeyInitialized = false;
    protected Location location_ = new Location();
    protected int strength_;
    protected int initiative_;
    protected int multiplicationChance_;
    protected static World world_;
    protected long key_;
    protected boolean alive = true;
    protected static final Random rand = new Random();

    protected Organism(int strength, int initiative) {
        this.strength_ = strength;
        this.initiative_ = initiative;
    }

    @Override
    public int compareTo(Organism secondOrganism) {
        long result = this.getKey() - secondOrganism.getKey();
        if (result > 0) return 1;
        else if (result == 0) return 0;
        else return -1;
    }

    public static void setWorld(World world) {
        world_ = world;
    }

    // Abstract methods
    public abstract void action();

    public abstract ImageIcon getIcon();

    public abstract String getName();

    // Static methods
    public static Organism getRandomOrganism() throws Exception {
        int n = rand.nextInt(8);
        return getNewInstanceOf(n);
    }

    public static Location getRandomLocation() {
        int x = rand.nextInt(world_.getSizeX());
        int y = rand.nextInt(world_.getSizeY());
        return new Location(x, y);
    }

    public static Organism getNewInstanceOf(int organismNumber) throws Exception {
        switch (organismNumber) {
            case 0:
                return new Wolf();
            case 1:
                return new Sheep();
            case 2:
                return new Turtle();
            case 3:
                return new Lion();
            case 4:
                return new Hummingbird();
            case 5:
                return new Grass();
            case 6:
                return new Dandelion();
            case 7:
                return new Guarana();
            default:
                throw new Exception("Nie ma konstruktora zwierzecia o numerze: " + organismNumber);
        }
    }

    public static int getOrganismConstructorNumber(Organism organism) throws Exception {
        switch (organism.getClass().getSimpleName()) {
            case "Wolf":
                return 0;
            case "Sheep":
                return 1;
            case "Turtle":
                return 2;
            case "Lion":
                return 3;
            case "Hummingbird":
                return 4;
            case "Grass":
                return 5;
            case "Dandelion":
                return 6;
            case "Guarana":
                return 7;
            default:
                throw new Exception("Nie ma konstruktora dla zwierzecia : " + organism.getClass().getSimpleName());
        }
    }

    // Action related methods
    public void addToWorld(Location location) {
        if (collision(location)) {
            try {
                this.attack(world_.getOrganismAt(location));
            } catch (FieldInaccessibleException e) {
                this.setDead();
            }
        }
        if (this.alive) {
            location_ = location;
            world_.placeOrganism(this);
            world_.addMessage(new Message(4,
                    this.getLocation().toString() + " - " +
                            this.getName() + " zostal umieszczony na swiecie"));
        }
    }

    public void defendAgainst(Organism aggressor) throws Exception {
        if (this.getClass() == aggressor.getClass()) {
            throw new FieldInaccessibleException("Pole zajete przez organizm tego samego gatunku");
        }
    }

    public void multiply(Location excludeLocation) {
        world_.addMessage(new Message(2,
                this.getLocation().toString() + " - " +
                        this.getName() + " rozmnaza sie "));
        if (rand.nextInt(100) < multiplicationChance_) {
            try {
                Organism child = this.getClass().newInstance();
                child.addToWorld(getRandomLocationNearby(1, excludeLocation));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Setters and getters

    public void setKey(long key) {
        if (!isKeyInitialized) {
            key_ = key;
            isKeyInitialized = true;
        } else {
            throw new Error("Key can not be changed once initialized");
        }
    }

    public void setStrength(int strength) {
        strength_ = strength;
    }

    public long getKey() {
        if (isKeyInitialized) {
            return key_;
        } else {
            throw new Error("Key uninitialized");
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public final int getInitiative() {
        return initiative_;
    }

    public final int getStrength() {
        return strength_;
    }

    public Location getLocation() {
        return location_;
    }

    // Protected

    protected Location getRandomLocationNearby(int range, Location otherLocation) {
        // Lower boundry (inclusice)
        int startX = max(0, location_.getX() - range);
        int startY = max(0, location_.getY() - range);
        // Upper boundry (exclusive!)
        int maxX = min(world_.getSizeX(), location_.getX() + range + 1);
        int maxY = min(world_.getSizeY(), location_.getY() + range + 1);
        int rangeX = maxX - startX;
        int rangeY = maxY - startY;
        Location newLocation = new Location();
        if (otherLocation == null) {
            while (true) {
                newLocation.set(startX + rand.nextInt(rangeX), startY + rand.nextInt(rangeY));
                if (!newLocation.equals(location_)) {
                    return newLocation;
                }
            }
        } else {
            while (true) {
                newLocation.set(startX + rand.nextInt(rangeX), startY + rand.nextInt(rangeY));
                if (newLocation != location_ && newLocation != otherLocation) {
                    return newLocation;
                }
            }
        }
    }

    protected boolean collision(Location location) {
        return world_.getOrganismAt(location) != null;
    }

    protected void attack(Organism defender) throws FieldInaccessibleException {
        try {
            // Sprawdzenie, czy organizm zachowuje sie nietypowo gdy zaatakowany
            defender.defendAgainst(this);

            // Zjedzenie Guarany
        } catch (IncreaseStrengthException e) {
            this.strength_ += 3;

            // Proba wejscia slabego zwierzecia na pole Lwa lub Zolwia
            // lub proba umieszczenia organizmu na pole zajmowane przez org. tego samego gatunku
        } catch (FieldInaccessibleException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error("Wystapil nieobslugiwany wyjatek");
        }
        // Standardowy atak
        if (this.getStrength() >= defender.getStrength()) {
            defender.setDead();
            world_.addMessage(new Message(2,
                    defender.getLocation().toString() + " - " +
                            this.getName() + " zjada "
                            + defender.getName()));
        } else {
            this.setDead();
            world_.addMessage(new Message(2,
                    defender.getLocation().toString() + " - " +
                            this.getName() + " zostaje zjedzony przez "
                            + defender.getName()));
        }
    }

    private void setDead() {
        this.alive = false;
    }
}
