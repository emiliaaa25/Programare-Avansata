import java.util.ArrayList;
import java.util.List;

public class Problem {
    private List<Depot> depots;
    private List<Client> clients;
    private int[][] costMatrix;

    public Problem() {
        this.depots = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public Problem(int[][] costMatrix) {
        this.depots = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.costMatrix = costMatrix;
    }

    public List<Depot> getDepots() {
        return depots;
    }

    public void addDepot(Depot depot) {
        if (!depots.contains(depot)) depots.add(depot);
    }

    public int[][] getCostMatrix() {
        return costMatrix;
    }

    public void addClient(Client client) {
        if (!clients.contains(client)) clients.add(client);
    }

    /*public List<Vehicle> getVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        for (Depot depot : depots) {
            vehicles.addAll(depot.getVehicles());
        }
        return vehicles;
    }*/

    public int getTravelCost(int node1, int node2) {
        return costMatrix[node1][node2];
    }

    public void allocateClientsToVehicles() {
        int nrDepots = depots.size();
        int nrClients = clients.size();
        int nodes = nrDepots + nrClients + 1;

        int[][] distances = new int[nodes][nodes];

        for (int i = 0; i < nodes; i++)
            for (int j = 0; j < nodes; j++)
                if (i != j)
                    distances[i][j] = Integer.MAX_VALUE;
                else
                    distances[i][j] = 0;

        for (int i = 0; i < nrDepots; i++) {
            for (int j = 0; j < nrClients; j++) {
                int depotIndex = i + 1;
                int clientIndex = nrDepots + j + 1;
                if (depotIndex < costMatrix.length && clientIndex < costMatrix.length) {
                    distances[depotIndex][clientIndex] = costMatrix[i][j];
                    distances[clientIndex][depotIndex] = costMatrix[j][i];
                }
            }
        }

        for (int k = 0; k < nodes; k++) {
            for (int i = 0; i < nodes; i++) {
                for (int j = 0; j < nodes; j++) {
                    if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE && distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }

        for (int i = 0; i < nrClients; i++) {
            Client client = clients.get(i);
            Vehicle nearestVehicle = findNearestVehicle(client, distances);
            if (nearestVehicle != null) {
                System.out.println("Assigning " + client.getName() + " to vehicle with model: " + nearestVehicle.getModel());
            }
        }
    }

    private Vehicle findNearestVehicle(Client client, int[][] distances) {
        int numDepots = depots.size();
        int clientIndex = numDepots + clients.indexOf(client) + 1;

        int minDistance = Integer.MAX_VALUE;
        Depot nearestDepot = null;
        Vehicle nearestVehicle = null;

        for (Depot depot : depots) {
            int depotIndex = depots.indexOf(depot) + 1;
            if (distances[depotIndex][clientIndex] < minDistance) {
                minDistance = distances[depotIndex][clientIndex];
                nearestDepot = depot;
                nearestVehicle = findNearestVehicleForDepot(depot, distances);
            }
        }

        if (nearestDepot == null) {
            System.out.println("No nearest depot found for client " + client.getName());
            return null;
        }

        if (nearestVehicle == null) {
            System.out.println("No nearest vehicle found for client " + client.getName());
        }

        return nearestVehicle;
    }

    private Vehicle findNearestVehicleForDepot(Depot depot, int[][] distances) {
        List<Vehicle> vehicles = depot.getVehicles();
        Vehicle nearestVehicle = null;
        int minDistance = Integer.MAX_VALUE;

        for (Vehicle vehicle : vehicles) {
            int vehicleIndex = depots.indexOf(depot) + 1;
            if (distances[vehicleIndex][vehicle.getId()] < minDistance) {
                minDistance = distances[vehicleIndex][vehicle.getId()];
                nearestVehicle = vehicle;
            }
        }

        return nearestVehicle;
    }

    public void printProblem() {
        System.out.println("Depots:");
        for (Depot depot : depots) {
            System.out.println(depot);
        }

        System.out.println("\nClients:");
        for (Client client : clients) {
            System.out.println(client);
        }

        System.out.println("\nCost Matrix:");
        int[][] costMatrix = getCostMatrix();
        for (int i = 0; i < costMatrix.length; i++) {
            for (int j = 0; j < costMatrix[i].length; j++)
                System.out.print(" " + costMatrix[i][j]+" ");
            System.out.println();
        }
    }
}
