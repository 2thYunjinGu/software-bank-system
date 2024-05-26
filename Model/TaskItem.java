package Model;

import java.util.Date;

public class TaskItem {
    private String content;
    private boolean completed;
    private double interestRate;
    private Date lastUpdateTime;

    public TaskItem(String content) {
        this.content = content;
        this.completed = false;
        this.interestRate = 0.0;
        this.lastUpdateTime = null;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
