package planner.ent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;

    @Column(name = "name")
    private String name;

    //@Column(name = "status_id")
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status statusId;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    public Task() {
    }

    public Task(String name, Plan plan,  Status statusId) {
        this.name = name;
        this.plan = plan;
        this.statusId = statusId;
    }

    public Long getId() {
        return task_id;
    }

    public void setId(Long task_id) {
        this.task_id = task_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return statusId.getName();
    }

    public void setStatus(Status statusId) {
        this.statusId = statusId;
    }

    public String getPlan() {
        return plan.getName();
    }

    public void setPlan(Plan plan) { this.plan = plan; }
}