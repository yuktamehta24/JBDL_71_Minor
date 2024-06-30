package org.gfg.JBDL_71_Minor.repository;

import org.gfg.JBDL_71_Minor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
