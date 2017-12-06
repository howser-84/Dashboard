package org.dashboard.main.data;

import org.springframework.data.repository.CrudRepository;

public interface TaskDAO extends CrudRepository<Task, Long> {
}
