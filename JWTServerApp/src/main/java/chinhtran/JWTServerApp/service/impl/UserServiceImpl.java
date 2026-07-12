package chinhtran.JWTServerApp.service.impl;

import chinhtran.JWTServerApp.cache.UserCache;
import chinhtran.JWTServerApp.cache.models.UserCacheData;
import chinhtran.JWTServerApp.cache.models.UserEvent;
import chinhtran.JWTServerApp.consts.CLAIMS;
import chinhtran.JWTServerApp.consts.Message;
import chinhtran.JWTServerApp.consts.USER_TYPE;
import chinhtran.JWTServerApp.controller.user.model.ChangePassReq;
import chinhtran.JWTServerApp.controller.user.model.UserGetRes;
import chinhtran.JWTServerApp.controller.user.model.UserPostReq;
import chinhtran.JWTServerApp.controller.user.model.UserPostRes;
import chinhtran.JWTServerApp.controller.user.model.UserPutReq;
import chinhtran.JWTServerApp.converter.RegisterConverter;
import chinhtran.JWTServerApp.converter.UserConverter;
import chinhtran.JWTServerApp.exceptions.BusinessException;
import chinhtran.JWTServerApp.repository.UserRepository;
import chinhtran.JWTServerApp.repository.entity.UserEntity;
import chinhtran.JWTServerApp.service.AESService;
import chinhtran.JWTServerApp.service.JwtService;
import chinhtran.JWTServerApp.service.UserService;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final AESService aesService;
  private final JwtService jwtService;
  @Autowired private UserCache userCache;

  @Autowired private ApplicationEventPublisher applicationEventPublisher;

  @Autowired
  public UserServiceImpl(
      UserRepository userRepository, AESService aesService, JwtService jwtService) {
    this.userRepository = userRepository;
    this.aesService = aesService;
    this.jwtService = jwtService;
  }

  @Override
  public UserPostRes create(UserPostReq model) {

    // Convert request model to user entity.
    UserEntity userEntity = RegisterConverter.convertPostReqModelToUserEntity(model);

    // Check userName is exist.
    Optional<UserEntity> user = userRepository.findByUsername(userEntity.getUsername());

    if (user.isPresent()) {
      throw new BusinessException(Message.USER_ERR_002);
    }

    // Encrypt password
    userEntity.setPassword(aesService.encrypt(userEntity.getPassword()));

    // Nomally User
    userEntity.setType(USER_TYPE.USER.getValue());

    userEntity = userRepository.save(userEntity);

    // Put to redis
    UserEvent userEvent =
        new UserEvent(UserConverter.convertEntityToCacheData(userEntity), "WRITE");
    applicationEventPublisher.publishEvent(userEvent);

    // Create claims
    Map<String, Object> claims = new HashMap<>();
    claims.put(CLAIMS.USER_ID.getValue(), userEntity.getId());
    claims.put(CLAIMS.UPDATED_PASSWORD_DATE.getValue(), userEntity.getUpdatedPasswordDate());

    return UserPostRes.builder()
        .jwt(jwtService.generateTokenWithClaims(userEntity.getUsername(), claims))
        .username(userEntity.getUsername())
        .build();
  }

  @Override
  public void update(Long id, UserPutReq model) {
    UserEntity userEntity = UserConverter.convertPutReqModelToUserEntity(model);
    userEntity.setId(id);
    userRepository.save(userEntity);
  }

  @Override
  public void changePass(Long id, ChangePassReq req) {
    if (req.getNewPassword().length() < 8 && req.getNewPassword().length() > 20) {
      throw new BusinessException("PASSWORD");
    }
    if (!req.getNewPassword().equals(req.getConfirmNewPassword())) {
      throw new BusinessException("PASSWORD");
    }

    // Check userName is exist.
    Optional<UserEntity> user = userRepository.findById(id);

    if (user.isPresent()) {
      throw new BusinessException(Message.USER_ERR_002);
    }

    userRepository.changePassword(id, aesService.encrypt(req.getNewPassword()));
  }

  @Override
  public UserGetRes getById(Long id) {
    UserCacheData cacheData = userCache.getById(id);
    if (!Objects.isNull(cacheData)) {
      return UserConverter.convertCacheDataToRes(cacheData);
    }

    UserEntity userEntity =
        userRepository.findById(id).orElseThrow(() -> new BusinessException("1"));

    return UserConverter.convertEntityToRes(userEntity);
  }
}
