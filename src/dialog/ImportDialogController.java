package dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DataSet;
//Controller vom Dialog

public class ImportDialogController {

    @FXML
    Button importBtn;
    @FXML
    Button cancelBtn;

    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;
    @FXML
    TextField hostField;
    @FXML
    TextField taskField;
//funktion der gedrückten Buttons
    @FXML
    protected void btnCancelPressed() {
        System.out.println("TEST!");
        DataSet.setCanceled(true);

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void btnImportPressed() {
        DataSet dataSet = DataSet.getInstance();
        dataSet.setUserName(usernameField.getText());
        dataSet.setPassword(passwordField.getText());
        dataSet.setHostUrl(hostField.getText());
        dataSet.setTaskName(taskField.getText());

        DataSet.setCanceled(false);

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

}
