import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a solution to a vehicle routing problem
 */
public class Solution {
    private List<Client> visitedClients;
    private Map<Vehicle, List<Client>> vehicleRoutes;

    /**
     * Constructs a new Solution object
     */
    public Solution() {
        this.visitedClients = new ArrayList<>();
        this.vehicleRoutes = new HashMap<>();
    }

    /**
     * Getting the list of visited clients
     *
     * @return The list of visited clients
     */
    public List<Client> getVisitedClients() {
        return visitedClients;
    }

    /**
     * Setting the list of visited clients
     *
     * @param visitedClients The list of visited clients to set
     */
    public void setVisitedClients(List<Client> visitedClients) {
        this.visitedClients = visitedClients;
    }

    /**
     * Getting the map of vehicle routes
     *
     * @return The map of vehicle routes
     */
    public Map<Vehicle, List<Client>> getVehicleRoutes() {
        return vehicleRoutes;
    }

    /**
     * Setting the map of vehicle routes
     *
     * @param vehicleRoutes The map of vehicle routes to set
     */
    public void setVehicleRoutes(Map<Vehicle, List<Client>> vehicleRoutes) {
        this.vehicleRoutes = vehicleRoutes;
    }

    /**
     * Adding a visited client to the list of visited clients
     *
     * @param client The client to add
     */
    public void addVisitedClients(Client client) {
        if (!visitedClients.contains(client))
            visitedClients.add(client);
    }

    /**
     * Prints the solution to the console
     */
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
                    System.out.print(client.getName() + " ");
                System.out.println();
            }
        }
    }
}
