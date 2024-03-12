import java.util.*;

/**
 * Represents a vehicle routing problem.
 */
public class Problem {
    private List<Depot> depots;
    private List<Client> clients;
    private Map<Vehicle, List<Client>> distances;

    /**
     * Constructs a new Problem object
     */
    public Problem() {
        this.depots = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.distances = new HashMap<>();
    }

    /**
     * Getting a list of depots
     *
     * @return A list of depots
     */
    public List<Depot> getDepots() {
        return depots;
    }

    /**
     * Setting the list of depots
     *
     * @param depots The list of depots to set
     */
    public void setDepots(List<Depot> depots) {
        this.depots = depots;
    }

    /**
     * Getting the list of clients
     *
     * @return The list of clients
     */
    public List<Client> getClients() {
        return clients;
    }

    /**
     * Setting the list of clients
     *
     * @param clients The list of clients to set
     */
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    /**
     * Adding a client to the list of clients
     *
     * @param client The client to add
     */
    public void addClient(Client client) {
        if (!clients.contains(client))
            clients.add(client);
    }

    /**
     * Adding a depot to the list of depots
     *
     * @param depot The depot to add
     */
    public void addDepot(Depot depot) {
        if (!depots.contains(depot))
            depots.add(depot);
    }

    /**
     * Getting a list of all vehicles across all depots
     *
     * @return A list of all vehicles across all depots
     */
    public List<Vehicle> getVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        for (Depot depot : depots)
            vehicles.addAll(depot.getVehicles());
        return vehicles;
    }

    /**
     * Solves the vehicle routing problem and returns a solution
     *
     * @return A solution to the vehicle routing problem
     */
    public Solution solveProblem() {
        Solution solution = new Solution();

        Map<Vehicle, Integer> vehicleLoadMap = new HashMap<>();
        for (Vehicle vehicle : getVehicles()) {
            vehicleLoadMap.put(vehicle, 0);
        }
        clients.sort(Comparator.comparing(Client::getStartVisit));

        for (Client client : clients) {
            Vehicle selectedVehicle = null;
            int minLoad = Integer.MAX_VALUE;
            for (Vehicle vehicle : getVehicles()) {
                int load = vehicleLoadMap.get(vehicle);
                if (load < minLoad) {
                    minLoad = load;
                    selectedVehicle = vehicle;
                }
            }
            if (selectedVehicle != null) {
                List<Client> route = solution.getVehicleRoutes().getOrDefault(selectedVehicle, new ArrayList<>());
                route.add(client);
                solution.addVisitedClients(client);
                solution.getVehicleRoutes().put(selectedVehicle, route);
                vehicleLoadMap.put(selectedVehicle, vehicleLoadMap.get(selectedVehicle) + 1);
            }
        }

        return solution;
    }
}
