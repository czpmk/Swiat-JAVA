package labO.world;

public class Location {
    private int x_;
    private int y_;
    private boolean initialized;

    public Location() { }

    public Location(int x, int y) {
        x_ = x;
        y_ = y;
        initialized = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Location) {
            Location l = (Location) obj;
            return x_ == l.x_ && y_ == l.y_;
        } else {
            return false;
        }
    }

    public final int getX() {
        if (initialized) {
            return x_;
        } else {
            throw new Error("Proba dostepu do niezainicjalizowanej lokacji");
        }
    }

    public final int getY() {
        if (initialized) {
            return y_;
        } else {
            throw new Error("Proba dostepu do niezainicjalizowanej lokacji");
        }
    }

    public final void set(int x, int y) {
        if (x >= 0 || y >= 0) {
            x_ = x;
            y_ = y;
            initialized = true;
        } else {
            throw new Error("Proba przypisania ujemnej wartosci koordynatow");
        }
    }

    @Override
    public String toString() {
        return "[" + x_ + ", " + y_ + "]";
    }
}
