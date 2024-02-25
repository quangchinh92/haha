package chinhtran.JWTServerApp.converter;

import java.util.ArrayList;
import java.util.List;

import chinhtran.JWTServerApp.controller.auths.model.AuthorizationPostModel;
import chinhtran.JWTServerApp.controller.auths.model.AuthorizationPostReq;
import chinhtran.JWTServerApp.controller.auths.model.AuthorizationPostRes;
import chinhtran.JWTServerApp.entity.UserRoleEntity;

public class AuthorizationConverter {

    public static List<UserRoleEntity> convertAuthorizationPostReqToEntityList(AuthorizationPostReq req) {
        List<UserRoleEntity> result = new ArrayList<>();
        req.getAuthorizationList().forEach(authorization -> {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setId(authorization.getId());
            userRoleEntity.setUserId(authorization.getUserId());
            userRoleEntity.setRoleId(authorization.getRoleId());
            result.add(userRoleEntity);
        });
        return result;
    }

    public static AuthorizationPostRes convertEntityListToAuthorizationPostRes(List<UserRoleEntity> entityList) {
        List<AuthorizationPostModel> modelList = new ArrayList<>();
        entityList.forEach(entity -> {
            AuthorizationPostModel authorizationPostModel = new AuthorizationPostModel();
            authorizationPostModel.setId(entity.getId());
            authorizationPostModel.setUserId(entity.getUserId());
            authorizationPostModel.setRoleId(entity.getRoleId());
            modelList.add(authorizationPostModel);
        });
        AuthorizationPostRes result = new AuthorizationPostRes();
        result.setAuthorizationList(modelList);
        return result;
    }
}
