import java.util.*;

public class Problem {
    private List<Depot> depots;
    private List<Client> clients;
    private Map<Vehicle, List<Client>> distances;
    private int[][] costMatrix;

    public Problem() {
        this.depots = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.distances = new HashMap<>();
    }
    public Problem(List<Depot> depots, List<Client> clients, int[][] costMatrix) {
        this.depots = depots;
        this.clients = clients;
        this.costMatrix = costMatrix;
    }
    public List<Depot> getDepots() {
        return depots;
    }
    public void setDepots(List<Depot> depots) {
        this.depots = depots;
    }
    public List<Client> getClients() {
        return clients;
    }
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    public void addClient(Client client){
        if(!clients.contains(client))
            clients.add(client);
    }
    public void addDepot(Depot depot){
        if(!depots.contains(depot))
            depots.add(depot);
    }
    public List<Vehicle> getVehicles(){
        List<Vehicle> vehicles=new ArrayList<>();
        for(Depot depot : depots)
            vehicles.addAll(depot.getVehicles());
        return vehicles;
    }
    public void calculateShortestPaths() {
        int n = costMatrix.length;

        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(costMatrix[i], 0, dist[i], 0, n);
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.arraycopy(dist[i], 0, costMatrix[i], 0, n);
        }
    }

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
