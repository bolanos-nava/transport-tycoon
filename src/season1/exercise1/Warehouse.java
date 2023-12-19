package season1.exercise1;

import java.util.ArrayList;

/**
 * The type Warehouse.
 */
public class Warehouse {

    /**
     * The time it takes to arrive to this warehouse
     */
    public final int timeToArrive;

    /**
     * The cargo stored in the warehouse, an array of parcels
     */
    protected ArrayList<Parcel> storedCargo = new ArrayList<>();

    /**
     * Instantiates a new Warehouse.
     *
     * @param timeToArrive Time to arrive to this warehouse from the previous one
     */
    Warehouse(int timeToArrive) {
        this.timeToArrive = timeToArrive;
    }

    /**
     * Gets stored cargo.
     *
     * @return the stored cargo
     */
    public ArrayList<Parcel> getStoredCargo() {
        return storedCargo;
    }

    public void storeParcel(Parcel parcelToStore) {
        storedCargo.add(parcelToStore);
    }

    public boolean hasCargo() {
        return this.storedCargo.size() > 0;
    }

    public void removeParcel() {
        this.storedCargo.remove(0);
    }
}
