public class Vehicle {
    private String model;
    private String color;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Vehicle(String model, String color, String type){
        this.model=model;
        this.color=color;
        this.type=type;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }
    @Override
    public String toString(){
        return type+"'s model is "+ model+ " and color is "+color;
    }
}
