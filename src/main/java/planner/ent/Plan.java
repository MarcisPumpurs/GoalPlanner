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
@Table(name = "plans")
public class Plan {

    @Id
    @Column(name = "plan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @Column(name = "name")
    private String name;

    //@Column(name = "status_id")
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status statusId;

    public Plan() {
    }

    public Plan(String name, Status statusId) {
        this.name = name;
        this.statusId = statusId;
    }

    public Long getId() {
        return planId;
    }

    public void setId(Long plan_id) {
        this.planId = plan_id;
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
}