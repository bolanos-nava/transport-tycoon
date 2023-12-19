package season1.exercise1;

import java.util.ArrayList;

public class Factory extends Warehouse {
    public final ArrayList<Parcel> storedCargo;

    Factory(ArrayList<Parcel> parcels) {
        super(0);
        this.storedCargo = parcels;
    }

    public boolean hasCargo() {
        return this.storedCargo.size() > 0;
    }

    public void removeParcel() {
        this.storedCargo.remove(0);
    }


}
