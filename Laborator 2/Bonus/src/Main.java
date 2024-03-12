import java.util.ArrayList;
import java.util.List;

public class Main {
        public static void main(String[] args) {
                Problem problem = new Problem();

                Depot depot1 = new Depot("D1");
                depot1.addVehicle(new Truck("T1", "Truck", 100));
                depot1.addVehicle(new Drones("D1", "Drone", 60));
                problem.addDepot(depot1);

                Depot depot2 = new Depot("D2");
                depot2.addVehicle(new Truck("T2", "Truck", 150));
                depot2.addVehicle(new Drones("D2", "Drone", 90));
                problem.addDepot(depot2);
                Client client1=new Client("C1", ClientType.REGULAR, 12, 14);
                Client client2=new Client("C2", ClientType.PREMIUM, 11, 15);
                Client client3=new Client("C3", ClientType.REGULAR, 12, 16);
                Client client4=new Client("C4", ClientType.PREMIUM, 15, 17);
                Client client5=new Client("C5", ClientType.REGULAR, 17, 19);
                Client client6=new Client("C6", ClientType.PREMIUM, 15, 16);

                problem.addClient(client1);
                problem.addClient(client2);
                problem.addClient(client3);
                problem.addClient(client4);
                problem.addClient(client5);
                problem.addClient(client6);

                Solution solution = problem.solveProblem();

                solution.printSolution();

                System.out.println("All Vehicles:");
                for (Vehicle vehicle : problem.getVehicles()) {
                        System.out.println(vehicle);
                }
                List<Depot> depots = new ArrayList<>();
                depots.add(depot1);
                depots.add(depot2);

                List<Client> clients = new ArrayList<>();
                clients.add(client1);
                clients.add(client2);
                clients.add(client3);
                clients.add(client4);
                clients.add(client5);
                clients.add(client6);

                int[][] costMatrix = new int[5][5];
                Problem problem2 = new Problem(depots, clients, costMatrix);


        }

}