public class Church extends Attraction implements Visitable{

    private String title;
    private String startVisit;
    private String endVisit;
    private int visitStart;
    private int visitEnd;

    public Church(String title, int visitStart, int visitEnd, String startVisit, String endVisit) {
        super(title);
        this.title = title;
        this.visitEnd=visitEnd;
        this.startVisit=startVisit;
        this.visitStart=visitStart;
        this.endVisit=endVisit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void visit() {
        System.out.println("You can visit "+title+" form "+startVisit+" to "+endVisit+" starting with "+visitStart+"a.m until "+visitEnd+" p.m!");
    }
    @Override
    public String toString(){
        StringBuilder result=new StringBuilder();
        result.append(title);
        return result.toString();
    }
}
