package planner.ent;

import javax.persistence.*;

@Entity
@Table(name = "status")
public class Status {

    @Id
    @Column(name = "status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long status_id;

    @Column
    private String name;

    public Status() {
    }

    public Status(Long status_id, String name) {
        this.status_id = status_id;
        this.name = name;
    }

    public Long getId() {
        return status_id;
    }

    public void setId(Long status_id) {
        this.status_id = status_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}