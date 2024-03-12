import java.util.Objects;

/**
 * Represents a client
 */
public class Client {
    private String name;
    private ClientType type;
    private int startVisit;
    private int endVisit;

    /**
     * Constructor for a new Client
     *
     * @param name Client's name
     * @param type Client's type
     * @param startVisit Client's start time for a visit
     * @param endVisit Client's end time for a visit
     */
    public Client(String name, ClientType type, int startVisit, int endVisit){
        this.name = name;
        this.type = type;
        this.startVisit = startVisit;
        this.endVisit = endVisit;
    }

    /**
     * Getting the end time of the client's visit
     *
     * @return The end time of the client's visit
     */
    public int getEndVisit() {
        return endVisit;
    }

    /**
     * Setting the end time of the client's visit
     *
     * @param endVisit The end time of the client's visit
     */
    public void setEndVisit(int endVisit) {
        this.endVisit = endVisit;
    }

    /**
     * Getting the start time of the client's visit
     *
     * @return The start time of the client's visit
     */
    public int getStartVisit() {
        return startVisit;
    }

    /**
     * Setting the start time of the client's visit
     *
     * @param startVisit The start time of the client's visit to set
     */
    public void setStartVisit(int startVisit) {
        this.startVisit = startVisit;
    }

    /**
     * Setting the type of the client
     *
     * @param type The type of the client to set
     */
    public void setType(ClientType type) {
        this.type = type;
    }

    /**
     * Getting the type of the client
     *
     * @return The type of the client
     */
    public ClientType getType() {
        return type;
    }

    /**
     * Setting the name of the client
     *
     * @param name The name of the client to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getting the name of the client
     *
     * @return The name of the client
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the client
     *
     * @return A string representation of the client
     */
    @Override
    public String toString(){
        return "Client's name " + name + " is " + type + " and he/she must come between " + startVisit + "-" + endVisit;
    }

    /**
     * If an object is equal to another
     *
     * @param obj The reference object with which to compare.
     * @return
     */
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof Client))
            return false;
        Client client = (Client) obj;
        return Objects.equals(client.name, name);
    }
}
