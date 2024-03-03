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

    @Override
    public UserEntity getUserByUsernameAndPassword(String username, String password) {
        // encrypt password
        String encryptedPassword = aesService.encrypt(password);
        // Get userEntity and return.
        return userRepository.findByUsernameAndPassword(username, encryptedPassword)
                .orElseThrow(() -> new RuntimeException());
    }
}
