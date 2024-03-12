import java.util.Objects;
public class Client {
    private String name;
    private ClientType type;
    private int startVisit;
    private int endVisit;
    public Client(String name, ClientType type, int startVisit, int endVisit){
        this.name=name;
        this.type=type;
        this.startVisit=startVisit;
        this.endVisit=endVisit;
    }
    public int getEndVisit() {
        return endVisit;
    }
    public void setEndVisit(int endVisit) {
        this.endVisit = endVisit;
    }
    public int getStartVisit() {
        return startVisit;
    }
    public void setStartVisit(int startVisit) {
        this.startVisit = startVisit;
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
        return "Client's name "+ name + " is "+ type+" and he/she must come between "+startVisit+"-"+endVisit;
    }
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if (!(obj instanceof Client))
            return false;
        Client client = (Client) obj;
        return Objects.equals(client.name, name);
    }
}
