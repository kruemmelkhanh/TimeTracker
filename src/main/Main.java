package main;

import dialog.ImportDialog;
import dialog.OnDialogFinischListener;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.DataSet;
import utils.RequestManager;
import utils.Task;
import utils.TaskManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


public class Main extends Application implements RequestManager.OnRequestFinischListener,
                                                    OnDialogFinischListener {

    public static ImportDialog dialog;

    private Scene scene;
//fxml laden
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chart.fxml"));

        Parent root = loader.load();
        scene = new Scene(root);

        primaryStage.setTitle("Jira - TimeTracker");
        primaryStage.setScene(scene);
        primaryStage.show();

        dialog = new ImportDialog(this);
    }

    public static void main(String[] args) {
        launch(args);
    }
//füllt die Chart nach erhalten der Daten
    @Override
    public void onRequestFinish(ArrayList<Task> taskList, ArrayList<String> tagList) {
        TaskManager tm = new TaskManager(taskList, tagList);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                DataSet dataSet = DataSet.getInstance();

                if (!DataSet.isCanceled()) {

                    Label hostLabel = (Label) scene.lookup("#hostLabel");
                    hostLabel.setText(dataSet.getHostUrl());

                    Label taskLabel = (Label) scene.lookup("#taskLabel");
                    taskLabel.setText(dataSet.getTaskName());

                    tm.getTotalTime();
                    Map<String, Integer> tmMap = tm.getTotalTimeMap();

                    // BarChart erstellen
                    StackedBarChart bc = (StackedBarChart) scene.lookup("#stackedBarChart");
                    bc.setAnimated(false);
                    bc.getXAxis().setLabel("Tags");
                    bc.getYAxis().setLabel("Time (h)");

                    XYChart.Series series = new XYChart.Series();
                    series.setName(dataSet.getTaskName());

                    Iterator it = tmMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();
                        series.getData().add(new XYChart.Data(pair.getKey(), ((int) pair.getValue() / 3600)));
                        it.remove();
                    }

                    bc.getData().addAll(series);

                }
            }
        });

    }

//request wird gestartet nachdem Dialog geschlossen wurde
    @Override
    public void onDialogFinish() {
        DataSet dataSet = DataSet.getInstance();

        RequestManager rm = new RequestManager(Main.this, dataSet.getHostUrl(),
                dataSet.getTaskName(), dataSet.getUserName(), dataSet.getPassword());

        rm.connect();
    }
}
