package season1.exercise1;

public class Ship extends Vehicle {
    private final static char ORIGIN_WAREHOUSE = 'P';
    private char currentWarehouse = ORIGIN_WAREHOUSE;

    Ship() {}

    public char getOriginWarehouse() {
        return this.ORIGIN_WAREHOUSE;
    }

    public char getCurrentWarehouse() {
        return currentWarehouse;
    }

    public void noWarehouse() {
        this.currentWarehouse = '\0';
    }

    public void setAtOrigin() {
        this.currentWarehouse = ORIGIN_WAREHOUSE;
    }

    public boolean isAtOrigin() {
        return this.currentWarehouse == ORIGIN_WAREHOUSE;
    }

    public void setAtDestination(char destinationWarehouse) {
        super.destinationWarehouse = destinationWarehouse;
        this.currentWarehouse = destinationWarehouse;
    }

    public boolean isAtDestination() {
        return this.currentWarehouse == this.destinationWarehouse;
    }

    public void resetState() {
        this.currentWarehouse = ORIGIN_WAREHOUSE;
        this.timeFromLastWarehouse = 0;
        this.direction = 1;
    }
}
