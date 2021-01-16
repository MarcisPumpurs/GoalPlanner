package planner.repo;

import planner.ent.Status;

public class StatusRepo extends CrudRepo<Status> {

    public Status findOne(Long id) {
        return super.findOne(id, Status.class);
    }
    public Status activeStatus() {
        return super.findOne(2L, Status.class);
    }
    public Status newStatus() { return super.findOne(1L, Status.class); }
    public Status fulfilledStatus() {
        return super.findOne(5L, Status.class);
    }
}
