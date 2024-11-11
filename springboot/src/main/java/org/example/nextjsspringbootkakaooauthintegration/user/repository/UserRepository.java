package org.example.nextjsspringbootkakaooauthintegration.user.repository;

import org.example.nextjsspringbootkakaooauthintegration.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
