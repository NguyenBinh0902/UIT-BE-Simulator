package com.example.uit_simulator.inits;

import com.example.uit_simulator.enums.RoleName;
import com.example.uit_simulator.models.Role;
import com.example.uit_simulator.repositories.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer {
    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void initAdminRole() {
        if (!roleRepository.existsByName(RoleName.ADMIN)) {
            Role adminRole = new Role();
            adminRole.setName(RoleName.ADMIN);
            roleRepository.save(adminRole);
        }
        if (!roleRepository.existsByName(RoleName.SV)) {
            Role adminRole = new Role();
            adminRole.setName(RoleName.SV);
            roleRepository.save(adminRole);
        }
        if (!roleRepository.existsByName(RoleName.GV)) {
            Role adminRole = new Role();
            adminRole.setName(RoleName.GV);
            roleRepository.save(adminRole);
        }
    }
}
