import java.util.ArrayList;
import java.util.List;

public class Trip {
    private String city;
    private int startTrip;
    private int endTrip;

    private List<Attraction> attractions;

    Trip(String city, int startTrip, int endTrip){
        this.city=city;
        this.startTrip=startTrip;
        this.endTrip=endTrip;
        this.attractions = new ArrayList<>();

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStartTrip() {
        return startTrip;
    }

    public void setStartTrip(int startTrip) {
        this.startTrip = startTrip;
    }

    public int getEndTrip() {
        return endTrip;
    }

    public void setEndTrip(int endTrip) {
        this.endTrip = endTrip;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    public void addAttraction(Attraction a) {
        attractions.add(a);
    }

    @Override
    public String toString(){
        StringBuilder result=new StringBuilder();
        result.append("I m going to ").append(city).append("and I'm visiting: ");
        for(Attraction attraction: attractions)
            result.append(attraction).append(", ");
        return result.toString();
    }

}
