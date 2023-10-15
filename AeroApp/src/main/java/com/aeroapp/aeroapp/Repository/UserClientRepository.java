package com.aeroapp.aeroapp.Repository;

import com.aeroapp.aeroapp.models.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserClientRepository extends JpaRepository<UserClient, Long> {
    Optional<UserClient> findByUsernameOrEmail(String username, String email );
}