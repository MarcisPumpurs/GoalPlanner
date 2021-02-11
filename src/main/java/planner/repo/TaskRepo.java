package planner.repo;

import planner.ent.Plan;
import planner.ent.Task;

import java.util.List;

public class TaskRepo extends CrudRepo<Task>{
    private static final String HIBERNATE_SELECT_QUERY = "FROM Task";
    private static final String HIBERNATE_SELECT_QUERY_ACTIVE_PLAN_TASKS = "FROM Task WHERE plan=";

    public Task findOne(Long id) {
        return super.findOne(id, Task.class);
    }

    public List<Task> findActivePlanTask(Long id) { return super.findAll(HIBERNATE_SELECT_QUERY_ACTIVE_PLAN_TASKS + id, Task.class); }

    public List<Task> findAll() {
        return super.findAll(HIBERNATE_SELECT_QUERY, Task.class);
    }
}
