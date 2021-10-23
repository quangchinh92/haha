package chinhtran.JWTServerApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinhtran.JWTServerApp.entity.User;
import chinhtran.JWTServerApp.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Get user by username
     * 
     * @param username
     * @return User
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
