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
    private Long plan_id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public Plan() {
    }

    public Plan(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public Long getId() {
        return plan_id;
    }

    public void setId(Long plan_id) {
        this.plan_id = plan_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}