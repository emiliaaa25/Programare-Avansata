package org.example;

public class TimeInterval<T>{
    private T startVisit;
    private T endVisit;

    public TimeInterval(T startVisit, T endVisit){
        this.startVisit=startVisit;
        this.endVisit=endVisit;
    }

    public T getStartVisit() {
        return startVisit;
    }

    public void setStartVisit(T startVisit) {
        this.startVisit = startVisit;
    }

    public T getEndVisit() {
        return endVisit;
    }

    public void setEndVisit(T endVisit) {
        this.endVisit = endVisit;
    }

    public boolean overlaps(TimeInterval<T> otherTimeInterval) {
       if( this.startVisit == otherTimeInterval.startVisit)
           return true;
       return false;
    }
}
