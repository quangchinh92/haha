package chinhtran.JWTServerApp.service.impl;

import chinhtran.JWTServerApp.cache.CacheBehavior;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  @Transactional
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

    Date now = new Date();
    userEntity.setCreatedDate(now);
    userEntity.setUpdatedDate(now);
    userEntity.setUpdatedPasswordDate(now);
    userEntity = userRepository.save(userEntity);

    // Put to redis
    UserEvent userEvent =
        new UserEvent(UserConverter.convertEntityToCacheData(userEntity), CacheBehavior.WRITE);
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
  @Transactional
  public void update(Long id, UserPutReq model) {

    UserGetRes userGetRes = getById(id);

    if (!model.getLastUpdatedDate().equals(userGetRes.getUpdatedDate())) {
      throw new BusinessException(Message.USER_ERR_002);
    }

    Date now = new Date();
    userRepository.update(id, model.getName(), model.getEmail(), model.getPhoneNumber(), now);

    // Put to redis
    UserCacheData cacheData = new UserCacheData();
    cacheData.setId(id);
    cacheData.setName(model.getName());
    cacheData.setEmail(model.getEmail());
    cacheData.setPhoneNumber(model.getPhoneNumber());
    cacheData.setUpdatedDate(now);
    UserEvent userEvent = new UserEvent(cacheData, CacheBehavior.WRITE);
    applicationEventPublisher.publishEvent(userEvent);
  }

  @Override
  @Transactional
  public void changePass(Long id, ChangePassReq req) {
    if (!req.getNewPassword().equals(req.getConfirmNewPassword())) {
      throw new BusinessException(Message.CHANGE_PASSWORD_001);
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

    UserEntity userEntity = userRepository.findById(id).get();

    return UserConverter.convertEntityToRes(userEntity);
  }
}
