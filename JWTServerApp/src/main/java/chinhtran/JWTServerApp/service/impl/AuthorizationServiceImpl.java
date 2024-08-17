package chinhtran.JWTServerApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import chinhtran.JWTServerApp.controller.auths.model.AuthorizationPostReq;
import chinhtran.JWTServerApp.controller.auths.model.AuthorizationPostRes;
import chinhtran.JWTServerApp.converter.AuthorizationConverter;
import chinhtran.JWTServerApp.entity.RoleEntity;
import chinhtran.JWTServerApp.entity.UserEntity;
import chinhtran.JWTServerApp.entity.UserRoleEntity;
import chinhtran.JWTServerApp.repository.RoleRepository;
import chinhtran.JWTServerApp.repository.UserRepository;
import chinhtran.JWTServerApp.repository.UserRoleRepository;
import chinhtran.JWTServerApp.service.AuthorizationService;
import chinhtran.JWTServerApp.utils.ListUtil;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthorizationServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository,
            RoleRepository roleRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public AuthorizationPostRes authrorize(AuthorizationPostReq req) {
        List<UserRoleEntity> userRoleEntityList = AuthorizationConverter.convertAuthorizationPostReqToEntityList(req);
        Set<Long> userIdSet = new TreeSet<>();
        Set<Long> roleIdSet = new TreeSet<>();
        userRoleEntityList.forEach(userRoleEntity -> {
            userIdSet.add(userRoleEntity.getUserId());
            roleIdSet.add(userRoleEntity.getRoleId());
        });
        // Check exist for userId.
        List<UserEntity> userEntityList = userRepository.findByIdInOrderById(new ArrayList<>(userIdSet));
        List<Long> userIdList = userEntityList.stream().map(UserEntity::getId).collect(Collectors.toList());
        List<String> messageError = validateUserId(userIdSet, userIdList);
        if (!CollectionUtils.isEmpty(messageError)) {
            throw new RuntimeException(StringUtils.join(messageError));
        }

        // Check exist for roleId.
        List<RoleEntity> roleEntityList = roleRepository.findByIdInOrderById(new ArrayList<>(roleIdSet));
        List<Long> roleIdList = roleEntityList.stream().map(RoleEntity::getId).collect(Collectors.toList());
        messageError = validateRoleId(roleIdSet, roleIdList);
        if (!CollectionUtils.isEmpty(messageError)) {
            throw new RuntimeException(StringUtils.join(messageError));
        }

        userRoleEntityList = userRoleRepository.saveAll(userRoleEntityList);
        return AuthorizationConverter.convertEntityListToAuthorizationPostRes(userRoleEntityList);
    }

    private List<String> validateUserId(final Set<Long> userIdSet, List<Long> userIdList) {
        return ListUtil.getInexistList(userIdSet, userIdList).stream()
                .map(userId -> "UserId: " + userId + " Is not exist.").collect(Collectors.toList());

    }

    private List<String> validateRoleId(final Set<Long> roleIdSet, List<Long> roleIdList) {
        return ListUtil.getInexistList(roleIdSet, roleIdList).stream()
                .map(userId -> "RoleId: " + userId + " Is not exist.").collect(Collectors.toList());

    }
}
