package chinhtran.JWTServerApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinhtran.JWTServerApp.controller.register.model.RegisterPostReq;
import chinhtran.JWTServerApp.converter.RegisterConverter;
import chinhtran.JWTServerApp.entity.UserEntity;
import chinhtran.JWTServerApp.repository.UserRepository;
import chinhtran.JWTServerApp.service.AESService;
import chinhtran.JWTServerApp.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AESService aesService;

    @Override
    public UserEntity register(RegisterPostReq registerPostReq) {
        // Convert request model to user entity.
        UserEntity userEntity = RegisterConverter.convertRegisterPostReqToUserEntity(registerPostReq);

        // Check userName is exist.
        userRepository.findByUsername(userEntity.getUsername())
        .orElseThrow(() -> new RuntimeException("Username is used!"));

        // Encrypt password
        userEntity.setPassword(aesService.encrypt(userEntity.getPassword()));

        return userRepository.save(userEntity);
    }
}
