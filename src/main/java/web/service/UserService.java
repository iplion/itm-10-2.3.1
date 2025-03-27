package web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.entity.User;
import web.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        System.out.println(users);
        return users;
    }

    public Optional<User> findByUuid(UUID uuid) {
        return  userRepository.findByUuid(uuid);
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteByUuid(UUID uuid) {
        userRepository.deleteByUuid(uuid);
    }
}
