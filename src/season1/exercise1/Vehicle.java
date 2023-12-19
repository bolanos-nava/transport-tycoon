package season1.exercise1;

public class Vehicle {
    private final static char ORIGIN_WAREHOUSE = 'F';
    protected char destinationWarehouse;
    protected Parcel cargo;
    protected int timeFromLastWarehouse = 0;
    protected int direction = 1;
    private char currentWarehouse = ORIGIN_WAREHOUSE;

    Vehicle() {}

    Vehicle(Parcel cargo) {
        this.cargo = cargo;
        this.destinationWarehouse = cargo.getDestination();
    }

    public char getOriginWarehouse() {
        return this.ORIGIN_WAREHOUSE;
    }

    public Parcel getCargo() {
        return cargo;
    }

    public void setCargo(Parcel cargo) {
        this.cargo = cargo;
        this.destinationWarehouse = cargo.getDestination();
    }


    public int getTimeFromLastWarehouse() {
        return timeFromLastWarehouse;
    }

    public void setTimeFromLastWarehouse(int timeFromLastWarehouse) {
        this.timeFromLastWarehouse = timeFromLastWarehouse;
    }

    public int getDirection() {
        return direction;
    }

    public void goBackwards() {
        this.direction = -1;
    }

    public void resetState() {
        this.currentWarehouse = ORIGIN_WAREHOUSE;
        this.timeFromLastWarehouse = 0;
        this.direction = 1;
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
        this.destinationWarehouse = destinationWarehouse;
        this.currentWarehouse = destinationWarehouse;
    }

    public boolean isAtDestination() {
        return this.currentWarehouse == this.destinationWarehouse;
    }

    public boolean hasCargo() {
        return this.cargo != null;
    }

    public void discharge() {
        this.cargo = null;
    }

    public String getVehicleType() {
        return this.getClass().getSimpleName();
    }
}
