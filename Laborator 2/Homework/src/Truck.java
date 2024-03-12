/**
 * Represents a truck vehicle, extending the Vehicle class
 */
public class Truck extends Vehicle {
    private int capacity;

    /**
     * Constructs a new Truck object
     *
     * @param model Truck's mdel
     * @param id Truck's id
     * @param capacity Truck's capacity
     */
    public Truck(String model, String id, int capacity) {
        super(model, id);
        this.capacity = capacity;
    }

    /**
     * Getting the capacity of the truck
     *
     * @return The capacity of the truck
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Setting the capacity of the truck
     *
     * @param capacity The capacity of the truck to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns a string representation of the truck
     *
     * @return A string representation of the truck
     */
    @Override
    public String toString(){
        return "Truck with id: " + getId() + " and capacity: " + capacity;
    }
}
