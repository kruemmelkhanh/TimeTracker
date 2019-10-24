package utils;
// Taskobject mit Zeit und Tag

public class Task {

    private String tag;
    private int timeSpent;

    public Task(String tag, int timeSpent) {
        this.tag = tag;
        this.timeSpent = timeSpent;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }
}
