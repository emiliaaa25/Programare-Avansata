public class Statue extends Attraction implements Payable,Visitable {
    private String title;
    private float ticket;

    private String startVisit;
    private String endVisit;
    private int visitStart;
    private int visitEnd;
    public Statue(String title, int visitStart, int visitEnd, String startVisit, String endVisit, float ticket) {
        super(title);
        this.title = title;
        this.visitEnd=visitEnd;
        this.startVisit=startVisit;
        this.visitStart=visitStart;
        this.endVisit=endVisit;
        this.ticket=ticket;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getTicket() {
        return ticket;
    }

    public void setTicket(float ticket) {
        this.ticket = ticket;
    }

    @Override
    public void setPrice(float price) {
        ticket=price;
    }

    @Override
    public float getPrice() {
        return ticket;
    }

    @Override
    public void visit() {
            System.out.println("You can visit "+title+" form "+startVisit+" to "+endVisit+" starting with "+visitStart+"a.m until "+visitEnd+" p.m!");
            System.out.println("Ticket price is: "+getTicket());
    }
    @Override
    public String toString(){
        StringBuilder result=new StringBuilder();
        result.append(title);
        return result.toString();
    }
}
