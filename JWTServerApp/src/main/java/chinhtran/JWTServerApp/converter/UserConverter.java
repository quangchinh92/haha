package chinhtran.JWTServerApp.converter;

import chinhtran.JWTServerApp.cache.models.UserCacheData;
import chinhtran.JWTServerApp.controller.user.model.UserGetRes;
import chinhtran.JWTServerApp.controller.user.model.UserPutReq;
import chinhtran.JWTServerApp.repository.entity.UserEntity;

public class UserConverter {

  public static UserEntity convertPutReqModelToUserEntity(UserPutReq req) {
    UserEntity result = new UserEntity();
    result.setName(req.getName());
    result.setEmail(req.getEmail());
    result.setPhoneNumber(req.getPhoneNumber());
    return result;
  }

  public static UserCacheData convertEntityToCacheData(UserEntity userEntity) {
    UserCacheData data = new UserCacheData();

    data.setId(userEntity.getId());
    data.setUsername(userEntity.getUsername());
    data.setName(userEntity.getName());
    data.setEmail(userEntity.getEmail());
    data.setType(userEntity.getType());
    data.setPhoneNumber(userEntity.getPhoneNumber());
    data.setUpdatedDate(userEntity.getUpdatedDate());
    data.setUpdatedPasswordDate(userEntity.getUpdatedPasswordDate());

    return data;
  }

  public static UserGetRes convertCacheDataToRes(UserCacheData cacheData) {

    UserGetRes res = new UserGetRes();

    res.setId(cacheData.getId());
    res.setUsername(cacheData.getUsername());
    res.setName(cacheData.getName());
    res.setEmail(cacheData.getEmail());
    res.setPhoneNumber(cacheData.getPhoneNumber());
    res.setUpdatedPasswordDate(cacheData.getUpdatedPasswordDate());

    return res;
  }

  public static UserGetRes convertEntityToRes(UserEntity userEntity) {
    UserGetRes res = new UserGetRes();

    res.setId(userEntity.getId());
    res.setUsername(userEntity.getUsername());
    res.setName(userEntity.getName());
    res.setEmail(userEntity.getEmail());
    res.setPhoneNumber(userEntity.getPhoneNumber());
    res.setUpdatedPasswordDate(userEntity.getUpdatedPasswordDate());

    return res;
  }
}
