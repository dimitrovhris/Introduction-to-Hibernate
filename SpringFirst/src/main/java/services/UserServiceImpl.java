package services;

import models.User;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        Optional<User> byUsername = userRepository.findByUsername(user.getUsername());

        if(byUsername.isPresent()){
            throw new IllegalArgumentException("Username already taken");
        } else{
            userRepository.save(user);
        }
    }
}
