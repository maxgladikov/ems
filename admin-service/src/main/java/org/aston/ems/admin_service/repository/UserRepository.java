package org.aston.ems.admin_service.repository;


import java.util.Optional;

import org.aston.ems.admin_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByUsername(String username);

	Optional<User> findByUsername(String username);
	void deleteByUsername(String username);
}
