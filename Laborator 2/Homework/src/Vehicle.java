import java.util.Objects;

/**
 * Represents a vehicle
 */
abstract class Vehicle {
    private String id;
    private String model;
    private Depot depot;

    /**
     * Getting the ID of the vehicle
     *
     * @return The ID of the vehicle
     */
    public String getId() {
        return id;
    }

    /**
     * Setting the ID of the vehicle
     *
     * @param id The ID of the vehicle to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Constructs a new Vehicle object
     *
     * @param model Vehicle's model
     * @param id Vehicle's id
     */
    public Vehicle(String model, String id) {
        this.model = model;
        this.id = id;
    }

    /**
     * Setting the model of the vehicle
     *
     * @param model The model of the vehicle to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Getting the model of the vehicle
     *
     * @return The model of the vehicle
     */
    public String getModel() {
        return model;
    }

    /**
     * Getting the depot where the vehicle is stored
     *
     * @return The depot where the vehicle is stored
     */
    public Depot getDepot() {
        return depot;
    }

    /**
     * Setting the depot where the vehicle is stored
     *
     * @param depot The depot where the vehicle is stored to set
     */
    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    /**
     * Returns a string representation of the vehicle
     *
     * @return A string representation of the vehicle
     */
    @Override
    public String toString() {
        return "Vehicle with id: " + id + " and model: " + model;
    }

    /**
     * If an object is equal to another
     *
     * @param obj The reference object with which to compare
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vehicle))
            return false;
        Vehicle vehicle = (Vehicle) obj;
        return Objects.equals(vehicle.id, id);
    }
}
