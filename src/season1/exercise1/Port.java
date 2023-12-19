package season1.exercise1;

public class Port extends Warehouse {
    Port(int timeToArrive) {
        super(timeToArrive);
    }

    public void changeParcelDestination() {
        this.storedCargo.get(storedCargo.size() - 1).setDestination('A');
    }
}
