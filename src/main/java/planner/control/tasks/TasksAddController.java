package planner.control.tasks;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import planner.ent.Plan;
import planner.ent.Task;
import planner.repo.PlanRepo;
import planner.repo.TaskRepo;
import planner.ent.Subtask;
import planner.repo.SubtaskRepo;
import planner.ent.Status;
import planner.repo.StatusRepo;

public class TasksAddController implements Initializable {

    private final TaskRepo taskRepo = new TaskRepo();
    private final StatusRepo statusRepo = new StatusRepo();

    @FXML
    private TextField name;

    @FXML private StackPane rootPane;

    private Plan editable;

    private Runnable closeDialogCallback;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void addPostOperationCallback(Runnable callback) {
        this.closeDialogCallback = callback;
    }

    public void setEditable(Plan plan) {
        this.editable = plan;
        this.name.setText(plan.getName());
    }

    @FXML
    private void addTask(ActionEvent event) {
        String taskName = name.getText();

        if (taskName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uzdevuma pievienošana");
            alert.setHeaderText(null);
            alert.setContentText("Lūdzu aizpildiet uzdevuma nosaukumu, lai pievienotu to!");
            alert.showAndWait();
            return;
        }

        if (editable == null) {
            taskRepo.save(new Task(taskName, statusRepo.newStatus()));
        } else {
            Task task = taskRepo.findOne(editable.getId());
            task.setName(taskName);
            taskRepo.merge(task);
        }
        clearEntries();
        closeStage();
        closeDialogCallback.run();
    }

    @FXML
    private void close(ActionEvent event) {
        closeStage();
    }

    private void clearEntries() {
        editable = null;
        name.clear();
    }

    private void closeStage() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}

