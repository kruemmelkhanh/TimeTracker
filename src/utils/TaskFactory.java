package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// Parse den Json nach richtigen Daten

public final class TaskFactory {

    private static ArrayList<String> subTaskLinks;
    private static Task task;
    private static String tag;

    private static boolean valid = false;

    private TaskFactory() {}

    public static void init(String jsonResponse) {

        System.out.println("JSON: " + jsonResponse);

        try {
            JSONObject obj = new JSONObject(jsonResponse);
            JSONObject fields = obj.getJSONObject("fields");

            JSONArray label = fields.getJSONArray("labels");
            JSONObject timetracking = fields.getJSONObject("timetracking");
            JSONArray subtasks = fields.getJSONArray("subtasks");

            String time = timetracking.get("timeSpentSeconds").toString();

            tag = label.get(0).toString();
            task = new Task(tag, new Integer(time));
            System.out.println(tag + " : " + time);

            subTaskLinks = new ArrayList<>();

            for(int i = 0; i < subtasks.length(); i++) {
                JSONObject curTask = (JSONObject) subtasks.get(i);
                subTaskLinks.add(curTask.getString("key"));
            }

            valid = true;
        } catch(Exception e) {
            valid = false;
        }

    }

    public static Task getTask() {
        return task;
    }

    public static String getTag() {
        return tag;
    }

    public static ArrayList<String > getSubTaskLinks() {
        return subTaskLinks;
    }

    public static boolean isValid() {
        return valid;
    }
}
