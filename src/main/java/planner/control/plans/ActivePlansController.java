package planner.control.plans;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import planner.control.tasks.ActiveTasksController;
import planner.ent.Plan;
import planner.repo.PlanRepo;
import planner.control.view.ViewLoader;
import planner.repo.StatusRepo;

public class ActivePlansController implements Initializable {

    private final PlanRepo planRepo = new PlanRepo();
    private final StatusRepo statusRepo = new StatusRepo();

    @FXML private TableView<Plan> table;
    @FXML private BorderPane content;

    @FXML
    private void editPlan(ActionEvent event) throws Exception {
        Plan plan = table.getSelectionModel().getSelectedItem();
        if (plan == null) {
            return;
        }
        ActiveTasksController controller = (ActiveTasksController) ViewLoader.load(getClass()
                .getResource("/ui/plans/plan_edit.fxml"), "Edit plan");
        controller.setEditable(plan);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureTable();
        populateTable();
    }

    private void configureTable() {
        TableColumn<Plan, Long> column1 = new TableColumn<>("Id");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Plan, String> column2 = new TableColumn<>("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Plan, String> column3 = new TableColumn<>("Status Id");
        column3.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
    }

    private void populateTable() {
        ObservableList<Plan> list = FXCollections.observableArrayList();
        list.addAll(planRepo.findActive());
        table.setItems(list);
    }

    @FXML
    private void fulfillPlan(ActionEvent event){
        Plan plan = table.getSelectionModel().getSelectedItem();
        if (plan == null) {
            return;
        }
        plan.setStatus(statusRepo.fulfilledStatus());
        planRepo.merge(plan);
        populateTable();
    }
}

