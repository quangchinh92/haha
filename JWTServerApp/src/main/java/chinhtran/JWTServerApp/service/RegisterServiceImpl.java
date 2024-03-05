package chinhtran.JWTServerApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinhtran.JWTServerApp.controller.register.model.RegisterPostReq;
import chinhtran.JWTServerApp.converter.RegisterConverter;
import chinhtran.JWTServerApp.entity.UserEntity;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserService userService;

    @Override
    public UserEntity regist(RegisterPostReq registerPostReq) {
        UserEntity userEntity = RegisterConverter.convertRegisterPostReqToUserEntity(registerPostReq);

        // Check username is exist.
        UserEntity existedUserEntity = userService.getUserByUsername(userEntity.getUsername());
        if (existedUserEntity != null) {
            throw new RuntimeException("Username is used!");
        }
        return userService.insert(userEntity);
    }
}
