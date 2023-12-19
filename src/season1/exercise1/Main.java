package season1.exercise1;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        char[] destinationList = new char[]{'A', 'B', 'B'};

//        Packages
        ArrayList<Parcel> parcels = new ArrayList<>(destinationList.length);
        for (char destination : destinationList) {
            if (destination == 'A') destination = 'P';
            parcels.add(new Parcel(destination));
        }

//        Warehouses
        Factory factory = new Factory(parcels);
        Port port = new Port(1);
        Warehouse warehouseA = new Warehouse(4);
        Warehouse warehouseB = new Warehouse(5);
        HashMap<Character, Warehouse> warehousesHash = new HashMap<>() {
            {
                put('F', factory);
                put('P', port);
                put('A', warehouseA);
                put('B', warehouseB);
            }
        };

//        Vehicles
        Vehicle truck1 = new Vehicle(parcels.get(0)); // we give the first package to the truck
        factory.removeParcel(); // we remove the package from the factory
        Vehicle truck2;
        if (parcels.size() >= 1) {
            truck2 = new Vehicle(parcels.get(0)); // if we have another package in the factory, give it to
            // the other truck
            factory.removeParcel(); // remove parcel from factory
        } else {
            truck2 = new Vehicle(); // if there is not another package in the factory, create an empty truck 2
        }
        Ship ship = new Ship(); // the ship is created empty

        ArrayList<Vehicle> vehicles = new ArrayList<>() {
            {
                add(truck1);
                add(truck2);
                add(ship);
            }
        };

        int totalElapsedTime = 0;
        Parcel lastParcel = factory.storedCargo.get(factory.storedCargo.size() - 1);

        while (!lastParcel.isDelivered()) {
            System.out.println(++totalElapsedTime);
            for (Vehicle vehicle : vehicles) {
                char currentWarehouse = vehicle.getCurrentWarehouse();
                Warehouse originWarehouse = warehousesHash.get(vehicle.getOriginWarehouse());
                if (vehicle.isAtOrigin() && !originWarehouse.hasCargo() && !vehicle.hasCargo()) {
                    // if the vehicle is at its origin, the warehouse is empty and the vehicle is also empty, it
                    // means there are no more packages to deliver, so skip this vehicle
                    continue;
                }

                if (vehicle.getVehicleType().equals("Ship") && ship.isAtOrigin() && port.hasCargo()) {
                    // if the ship is at the port and the port has cargo, load it
                    port.changeParcelDestination();
                    loadShipFromPort(ship, port);
                    continue;
                }

                // get the elapsed time, change it depending on the direction (add one or decrease one) and set
                // the new time in the vehicle
                int direction = vehicle.getDirection();
                int elapsedTimeFromWarehouse = vehicle.getTimeFromLastWarehouse() + direction;
                vehicle.setTimeFromLastWarehouse(elapsedTimeFromWarehouse);
                vehicle.noWarehouse(); // the vehicle has started to move so it isn't at a warehouse anymore
                if (elapsedTimeFromWarehouse == 0) {
                    vehicle.resetState(); // if the elapsed time changed and that lead to the vehicle being at 0
                    // again, it means that it is at its origin. We reset the time elapsed, change direction and
                    // set the current warehouse as the origin warehouse
                    if (!originWarehouse.hasCargo()) continue; // break if the origin warehouse doesn't have cargo
                    if (vehicle.getVehicleType().equals("Vehicle")) {
                        loadVehicleFromFactory(vehicle, factory);
                    } else loadShipFromPort(ship, port);
                    continue;
                }

                // if the vehicle is going backwards, it has no package and the next lines wouldn't valid
                if (direction == -1) continue;

                // get the destination warehouse from the package
                char destinationWarehouse = vehicle.getCargo().getDestination();
                // obtain the warehouse from the warehouseHash
                Warehouse warehouse = warehousesHash.get(destinationWarehouse);

                // if the vehicle has reached the warehouse
                if (elapsedTimeFromWarehouse == warehouse.timeToArrive) {
                    vehicle.setAtDestination(destinationWarehouse); // tell the vehicle that it currently is
                    // in the destination warehouse
                    vehicle.goBackwards(); // invert the direction of the vehicle
                    dischargeFromAndStoreIn(vehicle, warehouse);
                    if (lastParcel.isDelivered()) {
                        totalElapsedTime++;
                        break;
                    }
                }
            }
        }
        System.out.println(totalElapsedTime);
    }


    public static void dischargeFromAndStoreIn(Vehicle vehicle, Warehouse warehouse) {
        warehouse.storeParcel(vehicle.getCargo());
        char destinationWarehouse = (vehicle.destinationWarehouse == 'P' || vehicle.destinationWarehouse == 'A')
                ? 'A'
                : 'B';
        if (vehicle.getCurrentWarehouse() == destinationWarehouse) vehicle.getCargo().setDelivered();
        vehicle.discharge();
    }

    public static void loadVehicleFromFactory(Vehicle vehicle, Factory factory) {
        vehicle.setCargo(factory.storedCargo.get(0)); // we load the parcel into the truck
        factory.removeParcel(); // we remove the parcel from the factory

    }

    public static void loadShipFromPort(Ship ship, Port port) {
        ship.setCargo(port.getStoredCargo().get(0));
        port.removeParcel();
    }
}
