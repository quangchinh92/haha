package chinhtran.JWTServerApp.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import chinhtran.JWTServerApp.controller.role.models.AuthorizationModel;
import chinhtran.JWTServerApp.controller.role.models.RoleGetRes;
import chinhtran.JWTServerApp.controller.role.models.RolePostReq;
import chinhtran.JWTServerApp.controller.role.models.RolePostRes;
import chinhtran.JWTServerApp.entity.AuthorizationEntity;
import chinhtran.JWTServerApp.entity.RoleEntity;

public class RoleConverter {

    public static RoleEntity convertRolePostReqToRoleEntity(RolePostReq rolePostReq) {
        RoleEntity roleEntity = new RoleEntity();
        if (rolePostReq.getId() != null) {
            roleEntity.setId(rolePostReq.getId());
        }
        roleEntity.setValue(rolePostReq.getValue());

        // set AuthorizationEntity
        List<AuthorizationEntity> authorizationEntitieList = new ArrayList<>();
        rolePostReq.getAuthorizationModelList().forEach(authorizationModel -> {
            AuthorizationEntity authorizationEntity = new AuthorizationEntity();
            if (authorizationModel.getId() != null) {
                authorizationEntity.setId(authorizationModel.getId());
            }
            authorizationEntity.setValue(authorizationModel.getValue());
            authorizationEntitieList.add(authorizationEntity);
        });

        roleEntity.setAutorizationList(authorizationEntitieList);
        return roleEntity;
    }

    public static RolePostRes convertRoleEntityToRolePostRes(RoleEntity roleEntity) {
        RolePostRes rolePostRes = new RolePostRes();
        rolePostRes.setId(roleEntity.getId());
        rolePostRes.setValue(roleEntity.getValue());
        List<AuthorizationModel> authorizationModelList = new ArrayList<>();
        if (CollectionUtils.isEmpty(roleEntity.getAutorizationList())) {
            rolePostRes.setAuthorizationModelList(authorizationModelList);
            return rolePostRes;
        }
        roleEntity.getAutorizationList().forEach(authorizationEntity -> {
            AuthorizationModel authorizationModel = new AuthorizationModel();
            authorizationModel.setId(authorizationEntity.getId());
            authorizationModel.setValue(authorizationEntity.getValue());
            authorizationModelList.add(authorizationModel);
        });
        rolePostRes.setAuthorizationModelList(authorizationModelList);
        return rolePostRes;
    }

    public static List<RoleGetRes> convertRoleEntityListToRoleGetResList(List<RoleEntity> roleEntityList) {
        if (CollectionUtils.isEmpty(roleEntityList)) {
            return new ArrayList<>();
        }
        List<RoleGetRes> result = new ArrayList<>();
        roleEntityList.forEach(roleEntity -> {
            result.add(convertRoleEntityToRoleGetRes(roleEntity));
        });
        return result;
    }

    public static RoleGetRes convertRoleEntityToRoleGetRes(RoleEntity roleEntity) {
        RoleGetRes roleGetRes = new RoleGetRes();
        roleGetRes.setId(roleEntity.getId());
        roleGetRes.setValue(roleEntity.getValue());
        List<AuthorizationModel> authorizationModelList = new ArrayList<>();
        if (CollectionUtils.isEmpty(roleEntity.getAutorizationList())) {
            roleGetRes.setAuthorizationModelList(authorizationModelList);
            return roleGetRes;
        }
        roleEntity.getAutorizationList().forEach(authorizationEntity -> {
            AuthorizationModel authorizationModel = new AuthorizationModel();
            authorizationModel.setId(authorizationEntity.getId());
            authorizationModel.setValue(authorizationEntity.getValue());
            authorizationModelList.add(authorizationModel);
        });
        roleGetRes.setAuthorizationModelList(authorizationModelList);
        return roleGetRes;
    }
}
