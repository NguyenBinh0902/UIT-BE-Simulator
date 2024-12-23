package com.example.uit_simulator.services;

import com.example.uit_simulator.models.Profile;
import com.example.uit_simulator.repositories.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

}
