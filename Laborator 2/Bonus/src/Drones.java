public class Drones extends Vehicle {
    private int duration;
    public Drones(String model, String id, int duration) {
        super(model, id);
        this.duration=duration;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    @Override
    public String toString(){
        return "Drone with id: "+getId()+" and duration: "+duration;
    }
}
