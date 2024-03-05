package chinhtran.JWTServerApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinhtran.JWTServerApp.entity.UserEntity;
import chinhtran.JWTServerApp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AESService aesService;

    /**
     * Get user by username
     *
     * @param username
     * @return User
     */
    @Override
    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UserEntity getUserByUsernameAndPassword(String username, String password) {
        // Encrypt password
        String encryptedPassword = aesService.encrypt(password);
        // Get userEntity and return.
        return userRepository.findByUsernameAndPassword(username, encryptedPassword)
                .orElseThrow(() -> new RuntimeException());
    }

    @Override
    public UserEntity insert(UserEntity userEntity) {
        // Encrypt password
        userEntity.setPassword(aesService.encrypt(userEntity.getPassword()));
        // Insert into database
        return userRepository.save(userEntity);
    }
}
