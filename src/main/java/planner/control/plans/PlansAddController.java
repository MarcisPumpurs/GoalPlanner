package planner.control.plans;

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

public class PlansAddController implements Initializable {

    private final PlanRepo planRepo = new PlanRepo();
    private final StatusRepo status = new StatusRepo();

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
    private void addPlan(ActionEvent event) {
        String planName = name.getText();

        if (planName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Plāna pievienošana");
            alert.setHeaderText(null);
            alert.setContentText("Lūdzu aizpildiet plāna nosaukumu, lai pievienotu to!");
            alert.showAndWait();
            return;
        }

        if (editable == null) {
            planRepo.save(new Plan(planName, 1L));
        } else {
            Plan plan = planRepo.findOne(editable.getId());
            plan.setName(planName);
            planRepo.merge(plan);
        }
        clearEntries();
        closeStage();
        closeDialogCallback.run();
    }

    @FXML
    private void cancel(ActionEvent event) {
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

//    @FXML
//    private void activatePlan(ActionEvent event){
//
//    }
}
