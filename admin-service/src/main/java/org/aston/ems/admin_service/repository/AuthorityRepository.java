package org.aston.ems.admin_service.repository;


import org.aston.ems.admin_service.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	Optional<Authority> findByName(String name);
}
