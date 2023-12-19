package season1.exercise1;

public class Parcel {
    private char destination;
    private boolean delivered;

    Parcel(char destination) {
        this.destination = destination;
    }

    public char getDestination() {
        return destination;
    }

    public void setDestination(char destination) {
        this.destination = destination;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered() {
        this.delivered = true;
    }


}
