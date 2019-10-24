package dialog;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
//erstellt das Dialog Fenster und läd fxml
public class ImportDialog {

    public void display(String title, String message) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("importDialog.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                onDialogFinischListener.onDialogFinish();
            }
        });

        stage.showAndWait();
    }

    private OnDialogFinischListener onDialogFinischListener;

    public ImportDialog(Object context) {
        onDialogFinischListener = (OnDialogFinischListener) context;
    }
}
