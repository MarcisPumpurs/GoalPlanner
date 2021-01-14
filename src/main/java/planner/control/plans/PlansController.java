package planner.control.plans;


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

import planner.ent.Plan;
import planner.repo.PlanRepo;
import planner.control.view.ViewLoader;

public class PlansController implements Initializable {

    private final PlanRepo planRepo = new PlanRepo();

    @FXML private TableView<Plan> table;

    @FXML
    private void addPlan(ActionEvent event) {
        PlansAddController controller = (PlansAddController) ViewLoader
                .load(getClass().getResource("/ui/plans/new_plan_reg_title.fxml"), "Add Plan");
        controller.addPostOperationCallback(this::populateTable);
    }

    @FXML
    private void editPlan(ActionEvent event) {
        Plan plan = table.getSelectionModel().getSelectedItem();
        if (plan == null) {
            return;
        }
        //TODO ADD /ui/plans/edit_plan.fxml"
        PlansAddController controller = (PlansAddController) ViewLoader.load(getClass()
                .getResource("/ui/plans/edit_plan.fxml"), "Edit plan");
        controller.setEditable(plan);
        controller.addPostOperationCallback(this::populateTable);
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

        //TableColumn<Plan, String> column3 = new TableColumn<>("Status Id");
        //column3.setCellValueFactory(new PropertyValueFactory<>("status_id"));


        table.getColumns().add(column1);
        table.getColumns().add(column2);
        //table.getColumns().add(column3);
    }

    private void populateTable() {
        ObservableList<Plan> list = FXCollections.observableArrayList();
        list.addAll(planRepo.findAll());
        table.setItems(list);
    }

    @FXML
    private void activatePlan(ActionEvent event){

    }
}

