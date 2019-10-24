package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
// zusammenfassen der Zeit je Tag und speicherung in der Hashmap
public class TaskManager {

    private ArrayList<Task> taskList = new ArrayList<>();
    private ArrayList<String> tagList = new ArrayList<>();

    private Map<String, Integer> totalTimeMap = new HashMap<>();

    public TaskManager(ArrayList<Task> taskList, ArrayList<String> tagList) {
        this.taskList = taskList;
        this.tagList = tagList;
        getTotalTime();
    }

    public void getTotalTime() {

        for(Iterator<String> tag = tagList.iterator(); tag.hasNext(); ) {

            String curTag = tag.next();

            int totalTimePerTag = 0;

            for (Iterator<Task> task = taskList.iterator(); task.hasNext(); ) {

                Task t = task.next();

                if(t.getTag().equals(curTag)) {
                    int time = t.getTimeSpent();
                    totalTimePerTag += time;
                }
            }

            totalTimeMap.put(curTag, new Integer(totalTimePerTag));
        }
    }

    public Map<String, Integer> getTotalTimeMap() {
        return this.totalTimeMap;
    }
}
