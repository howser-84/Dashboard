package org.dashboard.main.data;

import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
