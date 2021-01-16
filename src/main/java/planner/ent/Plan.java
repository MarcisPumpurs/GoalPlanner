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

    @Column(name = "name")
    private String name;

    //@ManyToOne
    @Column(name = "status_id")
    //@JoinColumn(name = "status_id")
    private Long statusId;

    public Plan() {
    }

    public Plan(String name, Long statusId) {
        this.name = name;
        this.statusId = statusId;
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

    public Long getStatus() {
        return statusId;
    }

    public void setStatus(Long statusId) {
        this.statusId = statusId;
    }
}