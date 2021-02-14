package labO.world.organisms.animals;

import labO.world.Location;
import labO.world.exceptions.FieldInaccessibleException;
import labO.world.messages.Message;
import labO.world.organisms.Organism;

public abstract class Animal extends Organism {
    protected int movementRange_;

    protected Animal(int movementRange, int strength, int initiative) {
        super(strength, initiative);
        movementRange_ = movementRange;
        multiplicationChance_ = 100;
    }

    @Override
    public void action() {
        // Losowanie nowej lokacji
        Location newLocation = getRandomLocationNearby(movementRange_, null);
        Location oldLocation = location_;

        if (collision(newLocation)) {
            // Rozmnażanie
            if (world_.getOrganismAt(newLocation).getClass() == this.getClass()) {
                multiply(newLocation);
                // Atak
            } else {
                try {
                    attack(world_.getOrganismAt(newLocation));
                    if (this.alive) {
                        location_ = newLocation;
                        world_.moveOrganism(this, oldLocation);
                    } else {
                        world_.removeOrganism(this);
                    }
                } catch (FieldInaccessibleException e) {
                    // Nie przemieszcza sie
                }
            }
            // Zwykłe przemieszczenie organizmu
        } else {
            this.location_ = newLocation;
            world_.moveOrganism(this, oldLocation);
            world_.addMessage(new Message(3,
                    this.getLocation().toString() + " - " +
                            this.getName() + " przemescil sie z lokacji "
                            + oldLocation.toString()));
        }
    }
}
