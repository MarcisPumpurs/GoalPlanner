package planner.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainController implements Initializable {

    @FXML
    private BorderPane content;

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    @FXML
    public void openList(ActionEvent event) throws Exception {
        Pane pane = FXMLLoader.load(getClass().getResource("/ui/plans/list.fxml"));
        content.setCenter(pane);
    }
}