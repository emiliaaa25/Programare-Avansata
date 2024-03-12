/**
 * Represents a drone vehicle, extending the Vehicle class
 */
public class Drones extends Vehicle {
    private int duration;

    /**
     * Constructs a new Drones object
     *
     * @param model Drone's model
     * @param id Drone's id
     * @param duration Drone's duration
     */
    public Drones(String model, String id, int duration) {
        super(model, id);
        this.duration = duration;
    }

    /**
     * Getting the duration of the drone's operation
     *
     * @return The duration of the drone's operation
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Setting the duration of the drone's operation
     *
     * @param duration The duration of the drone's operation to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Returns a string representation of the drone
     *
     * @return A string representation of the drone
     */
    @Override
    public String toString(){
        return "Drone with id: " + getId() + " and duration: " + duration;
    }
}
