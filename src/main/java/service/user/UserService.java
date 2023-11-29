package service.user;

import model.Role;
import model.User;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    boolean save(User user);

   // Optional<User> update(User user);

    boolean update(Long id, Long roles);

    void removeById(Long id);


}
