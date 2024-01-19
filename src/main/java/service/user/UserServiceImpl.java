package service.user;

import model.Role;
import model.User;
import repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{


    final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean save(User user) {
        return false;
    }

    //@Override
    //public Optional<User> update(User user) {
//        return Optional.empty();
//    }

    @Override
   public boolean update(Long id, Long rolesList) {
        return userRepository.update(id,rolesList);

    }

    @Override
    public void removeById(Long id) {
        userRepository.removeById(id);

    }
}
