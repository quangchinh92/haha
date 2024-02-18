package chinhtran.JWTServerApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import chinhtran.JWTServerApp.entity.UserEntity;
import chinhtran.JWTServerApp.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Username " + username + " is not found!");
        }
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                user.get().getUsername(), user.get().getAuthorities());
    }

}
