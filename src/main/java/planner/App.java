package planner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import planner.config.DbSessionHolder;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/main/main.fxml"));
        primaryStage.setTitle("Library");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {

        DbSessionHolder.getInstance();



        launch(args);
        DbSessionHolder.shutdown();
    }
}