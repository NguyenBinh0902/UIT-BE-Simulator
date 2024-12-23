package com.example.uit_simulator.repositories;

import com.example.uit_simulator.enums.RoleName;
import com.example.uit_simulator.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);

    boolean existsByName(RoleName roleName);
}
