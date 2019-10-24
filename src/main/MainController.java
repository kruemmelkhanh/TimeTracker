package main;

import javafx.fxml.FXML;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Label;

import java.io.IOException;

//Controller der Main funktionen der Menüleiste
public class MainController {

    @FXML
    StackedBarChart stackedBarChart;
    @FXML
    Label hostLabel;
    @FXML
    Label taskLabel;

    @FXML
    protected void closeItemPressed() {
        System.exit(0);
    }

    @FXML
    protected void importItemPressed() {
        try {
            Main.dialog.display("Import to chart...", null);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    @FXML
    protected void clearItemPressed() {
        taskLabel.setText("");
        hostLabel.setText("");
        stackedBarChart.getData().clear();
    }
}
