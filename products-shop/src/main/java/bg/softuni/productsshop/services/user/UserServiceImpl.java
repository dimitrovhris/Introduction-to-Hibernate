package bg.softuni.productsshop.services.user;

import bg.softuni.productsshop.domain.entities.User;
import bg.softuni.productsshop.domain.models.UserSeedDto;
import bg.softuni.productsshop.repositories.UserRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

import static bg.softuni.productsshop.constants.Paths.*;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Gson gson) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        if(this.userRepository.count() != 0) return;

        UserSeedDto[] userSeedDtos = this.gson.fromJson(new FileReader(USERS_JSON_PATH.toFile()), UserSeedDto[].class);
        for (UserSeedDto userSeedDto : userSeedDtos) {
            User userToBeAdded = modelMapper.map(userSeedDto, User.class);
            userRepository.saveAndFlush(userToBeAdded);
        }

    }

    @Override
    public User getRandomPerson() {
        Random random = new Random();
        Long randomId = random.nextLong(1, userRepository.count()+1);
        return userRepository.findUserById(randomId).get();
    }
}
