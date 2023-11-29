package repository.user;

import model.Role;
import model.User;
import model.validator.Notification;

import java.util.*;

public interface UserRepository {

    List<User> findAll();

    Notification<User> findByUsernameAndPassword(String username, String password);

    boolean save(User user);

    void removeAll();

    User findById(Long id);

    boolean update(Long id, Long rolesList);

    void removeById(Long id);

    boolean existsByUsername(String username);
}
