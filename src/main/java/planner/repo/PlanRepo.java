package planner.repo;

import java.util.List;
import planner.ent.Plan;

public class PlanRepo extends CrudRepo<Plan> {

    private static final String HIBERNATE_SELECT_QUERY = "from Book";

    public Plan findOne(Long id) {
        return super.findOne(id, Plan.class);
    }

    public List<Plan> findAll() {
        return super.findAll(HIBERNATE_SELECT_QUERY, Plan.class);
    }
}