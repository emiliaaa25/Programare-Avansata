public abstract class Attraction implements Comparable<Attraction> {
    private String title;

    public Attraction(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int compareTo(Attraction o) {
        return this.title.compareTo(o.title);
    }

}
