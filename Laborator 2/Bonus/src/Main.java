public class Main {
        public static void main(String[] args) {
                Problem problem = ProblemGenerator.generateProblem(200, 200, 10);
                problem.printProblem();
                problem.allocateClientsToVehicles();
        }
}
