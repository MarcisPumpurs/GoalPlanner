package planner.repo;

import java.util.List;
import planner.ent.Plan;

public class PlanRepo extends CrudRepo<Plan> {

    private static final String HIBERNATE_SELECT_QUERY = "FROM Plan";
    private static final String HIBERNATE_SELECT_QUERY_ACTIVE = "from Plan WHERE statusId=2";

    public Plan findOne(Long id) {
        return super.findOne(id, Plan.class);
    }

    public List<Plan> findActive() { return super.findAll(HIBERNATE_SELECT_QUERY_ACTIVE, Plan.class); }

    public List<Plan> findAll() {
        return super.findAll(HIBERNATE_SELECT_QUERY, Plan.class);
    }
}