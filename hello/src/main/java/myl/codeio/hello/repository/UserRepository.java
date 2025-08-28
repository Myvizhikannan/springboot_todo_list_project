package myl.codeio.hello.repository;

import myl.codeio.hello.model.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<user, Long> {
    Optional<user> findByEmail(String email);
}
