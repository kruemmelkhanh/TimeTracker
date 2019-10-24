package utils;

import javafx.application.Application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
//läuft die Projekte rekursiv durch (schleife)
public class RequestManager extends Thread {

    private String baseUrl;
    private String userName;
    private String password;

    private OnRequestFinischListener onRequestFinischListener;

    private ArrayList<Task> taskList = new ArrayList<>();
    private ArrayList<String> tagList = new ArrayList<>();

    private Queue<String> queueList = new LinkedList<>();

    public RequestManager(Application context, String baseUrl, String taskKey, String userName, String password) {
        this.baseUrl = baseUrl;
        this.userName = userName;
        this.password = password;

        queueList.add(taskKey);

        if(context instanceof OnRequestFinischListener) {
            onRequestFinischListener = (OnRequestFinischListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement OnRequestFinischListener");
        }
    }

    @Override
    public void run() {
        String response;

        do {
            String curTaskLink = queueList.remove();
            response = new Request(baseUrl + "rest/api/2/issue/" + curTaskLink,
                                    this.userName, this.password).getResponse();

            TaskFactory.init(response);

            if(TaskFactory.isValid()) {
                taskList.add(TaskFactory.getTask());

                if(!tagList.contains(TaskFactory.getTag())) {
                    tagList.add(TaskFactory.getTag());
                }

                queueList.addAll(TaskFactory.getSubTaskLinks());
            }

        } while (!queueList.isEmpty());

        this.interrupt();

        if(this.isInterrupted()) {
            if(TaskFactory.isValid()) {
                onRequestFinischListener.onRequestFinish(taskList, tagList);
            }
        }
    }

    public void connect() {
        this.start();
    }

    public interface OnRequestFinischListener {
        void onRequestFinish(ArrayList<Task> taskList, ArrayList<String> tagList);
    }
}
