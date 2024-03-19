import java.util.Objects;
abstract class Vehicle {
    private int id;
    private String model;
    private Depot depot;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Vehicle(String model, int id){
        this.model=model;
        this.id=id;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    @Override
    public String toString(){
        return "Vehicle with id: "+id+ " and model" +model;
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if (!(obj instanceof Vehicle))
            return false;
        Vehicle vehicle = (Vehicle) obj;
        return Objects.equals(vehicle.id, id);

    }


}
