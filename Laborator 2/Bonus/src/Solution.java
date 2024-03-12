import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private List<Client> visitedClients;
    private Map<Vehicle, List<Client>> vehicleRoutes;
    public Solution() {
        this.visitedClients = new ArrayList<>();
        this.vehicleRoutes = new HashMap<>();
    }
    public List<Client> getVisitedClients() {
        return visitedClients;
    }
    public void setVisitedClients(List<Client> visitedClients) {
        this.visitedClients = visitedClients;
    }
    public Map<Vehicle, List<Client>> getVehicleRoutes() {
        return vehicleRoutes;
    }
    public void setVehicleRoutes(Map<Vehicle, List<Client>> vehicleRoutes) {
        this.vehicleRoutes = vehicleRoutes;
    }
    public void addVisitedClients(Client client){
        if(!visitedClients.contains(client))
            visitedClients.add(client);
    }
    public void printSolution() {
        System.out.println("Visited clients are: ");
        for (Client client : visitedClients)
            System.out.println(client);
        System.out.println("Routes are: ");
        for (Map.Entry<Vehicle, List<Client>> entry : vehicleRoutes.entrySet()) {
            Vehicle vehicle = entry.getKey();
            List<Client> route = entry.getValue();
            Depot depot = vehicle.getDepot();
            if (depot != null) {
                System.out.println("Vehicle " + vehicle.getId() + " assigned to " + depot.getName() + " on route:");
                for (Client client : route)
                    System.out.print(client.getName()+" ");
                System.out.println();
            }
        }
    }

}
