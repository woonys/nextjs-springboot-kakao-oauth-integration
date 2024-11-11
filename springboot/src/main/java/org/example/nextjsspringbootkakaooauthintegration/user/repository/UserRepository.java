package org.example.nextjsspringbootkakaooauthintegration.user.repository;

import java.nio.channels.FileChannel;
import java.util.Optional;

import org.example.nextjsspringbootkakaooauthintegration.user.domain.Provider;
import org.example.nextjsspringbootkakaooauthintegration.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndProvider(String email, Provider provider);
}
