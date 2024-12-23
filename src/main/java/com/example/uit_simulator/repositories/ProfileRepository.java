package com.example.uit_simulator.repositories;

import com.example.uit_simulator.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
