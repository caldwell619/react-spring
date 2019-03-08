package com.reactspring.Repositories;

import com.reactspring.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE id = ?1", nativeQuery = true)
    User getUserById(long id);
}
