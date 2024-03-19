import java.util.Random;

public class ProblemGenerator {
    private static Random random = new Random();

    public static Problem generateProblem(int numDepots, int numClients, int cost) {
        int[][] costMatrix = generateCostMatrix(numDepots + numClients, cost);
        Problem problem = new Problem(costMatrix);

        for (int i = 0; i < numDepots; i++) {
            Depot depot = new Depot("D" + i + 1);
            depot.addVehicle(generateRandomVehicle("Truck"));
            depot.addVehicle(generateRandomVehicle("Drones"));
            problem.addDepot(depot);
        }

        ClientType type;
        for (int i = 0; i < numClients; i++) {
            if (random.nextBoolean())
                type = ClientType.REGULAR;
            else
                type = ClientType.PREMIUM;
            int startTime = random.nextInt(24);
            int endTime = startTime + random.nextInt(24 - startTime) + 1;
            Client client = new Client("C" + (i + 1), type, startTime, endTime);
            problem.addClient(client);
        }
        return problem;
    }

    private static int[][] generateCostMatrix(int nodes, int cost) {
        int[][] costMatrix = new int[nodes][nodes];
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                if (i != j) {
                    costMatrix[i][j] = random.nextInt(cost) + 1;
                } else {
                    costMatrix[i][j] = 0;
                }
            }
        }
        return costMatrix;
    }

    private static Vehicle generateRandomVehicle(String type) {
        if (type.equals("Truck")) {
            return new Truck("T" + random.nextInt(100), random.nextInt(10), random.nextInt(200) + 50);
        } else if (type.equals("Drones")) {
            return new Drones("D" + random.nextInt(100), random.nextInt(10), random.nextInt(120) + 30);
        } else {
            throw new IllegalArgumentException("Invalid vehicle type: " + type);
        }
    }
}
