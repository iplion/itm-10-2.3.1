package web.repository;

import org.springframework.stereotype.Repository;
import web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
}
