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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import planner.control.plans.PlansAddController;
import planner.ent.Task;
import planner.ent.Plan;
import planner.repo.PlanRepo;
import planner.control.view.ViewLoader;
import planner.repo.StatusRepo;
import planner.repo.TaskRepo;

public class TasksController implements Initializable {

    private final TaskRepo taskRepo = new TaskRepo();
    private final StatusRepo statusRepo = new StatusRepo();

    @FXML private TableView<Task> table;

    @FXML
    private TextField planId;

    @FXML
    private void addTask(ActionEvent event) {
//        TasksAddController controller = (TasksAddController) ViewLoader
//                .load(getClass().getResource("/ui/plans/plan_reg.fxml"), "Add Plan");
//        controller.addPostOperationCallback(this::populateTable);
    }

    @FXML
    private void editTask(ActionEvent event) {
        Task task = table.getSelectionModel().getSelectedItem();
        if (task == null) {
            return;
        }
//        PlansAddController controller = (PlansAddController) ViewLoader.load(getClass()
//                .getResource("/ui/plans/edit_plan.fxml"), "Edit plan");
//        controller.setEditable(plan);
//        controller.addPostOperationCallback(this::populateTable);
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
        ObservableList<Task> list = FXCollections.observableArrayList();
        list.addAll(taskRepo.findAll());
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
    }
}

