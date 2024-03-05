
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
       Client client1=new Client("Emilia",ClientType.PREMIUM,"13:30-14:00");
       Client client2=new Client("Emma",ClientType.REGULAR,"12:10-13:30");
       Depot depot=new Depot("Sun Street, number 1122");
       Vehicle vehicle1=new Vehicle("Miata","red", "Car");
       Vehicle vehicle2=new Vehicle("Embraer Phenom 100","grey", "Private jet");


       System.out.println(client1);
       System.out.println(client2);
       System.out.println(depot);
       System.out.println(vehicle1);
       System.out.println(vehicle2);

    }
}