public class Truck extends Vehicle{
    private int capacity;
    public Truck(String model, int id, int capacity) {
        super(model, id);
        this.capacity=capacity;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    @Override
    public String toString(){
        return "Truck with id: "+getId()+" and capacity: "+capacity;
    }
}
