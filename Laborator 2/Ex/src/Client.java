
public class Client {
    private String name;
    private ClientType type;

    private String timeVisiting;

    public void setTimeVisiting(String timeVisiting) {
        this.timeVisiting = timeVisiting;
    }

    public String getTimeVisiting() {
        return timeVisiting;
    }

    public Client(String name, ClientType type, String timeVisiting){
        this.name=name;
        this.type=type;
        this.timeVisiting=timeVisiting;
    }
    public void setType(ClientType type) {
        this.type = type;
    }

    public ClientType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return "Client's name "+ name + " is "+ type+" and he/she must come between "+timeVisiting;
    }
}
