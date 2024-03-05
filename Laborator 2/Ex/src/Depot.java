public class Depot {
private String location;

    public Depot(String location) {
        this.location = location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString(){
        return "You can find the depot in this location "+location;
    }
}
