package planner.control.tasks;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import planner.control.plans.PlansAddController;
import planner.control.view.ViewLoader;
import planner.ent.Plan;
import planner.ent.Task;
import planner.repo.StatusRepo;
import planner.repo.TaskRepo;

public class ActiveTasksController implements Initializable {

    private final TaskRepo taskRepo = new TaskRepo();
    private final StatusRepo statusRepo = new StatusRepo();
    private Plan editablePlan;

    @FXML private TableView<Task> table;

    @FXML private AnchorPane rootPane;

    @FXML private Text planTitle;

    public void setEditable(Plan plan) {
        this.editablePlan = plan;
        planTitle.setText(plan.getName());
        populateTable();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureTable();
        populateTable();
    }

    private void configureTable() {
        TableColumn<Task, Long> column1 = new TableColumn<>("Id");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Task, String> column2 = new TableColumn<>("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Task, String> column3 = new TableColumn<>("Status Id");
        column3.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
    }

    private void populateTable() {
        long planId = 0;
        if(this.editablePlan != null){
            planId = this.editablePlan.getId();
        }
        ObservableList<Task> list = FXCollections.observableArrayList();
        list.addAll(taskRepo.findActivePlanTask(planId));
        table.setItems(list);
    }

    @FXML
    private void fulfillTask(ActionEvent event){
        Task task = table.getSelectionModel().getSelectedItem();
        if (task == null) {
            return;
        }
        task.setStatus(statusRepo.fulfilledStatus());
        taskRepo.merge(task);
        populateTable();
    }

    @FXML
    private void addTask(ActionEvent event){
        TasksAddController controller = (TasksAddController) ViewLoader
                .load(getClass().getResource("/ui/tasks/task_reg.fxml"), "Add Task");
        controller.setEditablePlan(editablePlan);
        controller.addPostOperationCallback(this::populateTable);
    }

    @FXML
    private void close(){ closeStage(); }

    private void closeStage() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

}

