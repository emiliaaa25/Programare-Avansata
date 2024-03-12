import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a depot
 */
public class Depot {
    private String name;
    private List<Vehicle> vehicles;

    /**
     * Constructs a new Depot
     *
     * @param name Depot's name
     */
    public Depot(String name) {
        this.name = name;
        this.vehicles = new ArrayList<>();
    }

    /**
     * Setting the name of the depot
     *
     * @param name The name of the depot to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getting the name of the depot
     *
     * @return The name of the depot
     */
    public String getName() {
        return name;
    }

    /**
     * Setting the list of vehicles stored in the depot
     *
     * @param vehicles The list of vehicles to set
     */
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        for (Vehicle v : vehicles) {
            v.setDepot(this);
        }
    }

    /**
     * Getting the list of vehicles stored in the depot
     *
     * @return The list of vehicles stored in the depot
     */
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * Adding a vehicle to the depot
     *
     * @param vehicle The vehicle to add to the depot
     */
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        vehicle.setDepot(this);
    }

    /**
     * Returns a string representation of the depot
     *
     * @return A string representation of the depot
     */
    @Override
    public String toString() {
        return "Depot's name is " + name;
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
        if (!(obj instanceof Depot))
            return false;
        Depot depot = (Depot) obj;
        return Objects.equals(depot.name, name);
    }
}
