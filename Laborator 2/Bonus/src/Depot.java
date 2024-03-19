import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Depot {
    private String name;
    private List<Vehicle> vehicles;
    public Depot(String name) {
        this.name = name;
        this.vehicles = new ArrayList<>();
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        for(Vehicle v : vehicles) {
            v.setDepot(this);
        }
    }
    public List<Vehicle> getVehicles() {
        return vehicles;
    }
    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
        vehicle.setDepot(this);
    }
    @Override
    public String toString(){
        return "Depot's name is "+name;
    }
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if (!(obj instanceof Depot))
            return false;
        Depot depot = (Depot) obj;
        return Objects.equals(depot.name, name);

    }

}
