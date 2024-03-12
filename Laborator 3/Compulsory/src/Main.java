//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Trip trip = new Trip("Paris", 14,28);

        Statue libertyStatue=new Statue("Liberty Statue", 8,16,"Tuesday","Friday", 40);
        trip.addAttraction(libertyStatue);
        Church notreDame = new Church("Notre Dame",8,16,"Monday","Sunday");
        trip.addAttraction(notreDame);

        Concert concert = new Concert("Rock concert",400);

        trip.addAttraction(concert);

        System.out.println("Price for " + concert.getTitle() + " is: " + concert.getTicket());
        libertyStatue.visit();
        notreDame.visit();
        trip.getAttractions().sort(Attraction::compareTo);
        System.out.println(trip);


    }
}