package chinhtran.JWTServerApp.service.impl;

import chinhtran.JWTServerApp.consts.Message;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

  private final UserRoleRepository userRoleRepository;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final MessageSource messageSource;

  @Autowired
  public AuthorizationServiceImpl(
      UserRoleRepository userRoleRepository,
      UserRepository userRepository,
      RoleRepository roleRepository,
      MessageSource messageSource) {
    this.userRoleRepository = userRoleRepository;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.messageSource = messageSource;
  }

  @Override
  public AuthorizationPostRes authrorize(AuthorizationPostReq req) {
    List<UserRoleEntity> userRoleEntityList =
        AuthorizationConverter.convertAuthorizationPostReqToEntityList(req);
    Set<Long> userIdSet = new TreeSet<>();
    Set<Long> roleIdSet = new TreeSet<>();
    userRoleEntityList.forEach(
        userRoleEntity -> {
          userIdSet.add(userRoleEntity.getUserId());
          roleIdSet.add(userRoleEntity.getRoleId());
        });
    // Check exist for userId.
    List<Long> userIdList =
        userRepository.findByIdInOrderById(new ArrayList<>(userIdSet)).stream()
            .map(UserEntity::getId)
            .collect(Collectors.toList());
    List<String> messageError = validateExistUserId(userIdSet, userIdList);

    // Check exist for roleId.
    List<Long> roleIdList =
        roleRepository.findByIdInOrderById(new ArrayList<>(roleIdSet)).stream()
            .map(RoleEntity::getId)
            .collect(Collectors.toList());
    messageError = validateExistRoleId(roleIdSet, roleIdList);
    if (!CollectionUtils.isEmpty(messageError)) {
      throw new RuntimeException(StringUtils.join(messageError));
    }

    userRoleEntityList = userRoleRepository.saveAll(userRoleEntityList);
    return AuthorizationConverter.convertEntityListToAuthorizationPostRes(userRoleEntityList);
  }

  /**
   * Validate existence of source at the destination.
   *
   * @param source Set<Long>
   * @param destination List<Long>
   * @return error message list.
   */
  private List<String> validateExistUserId(final Set<Long> source, List<Long> destination) {
    return ListUtil.getInexistList(source, destination).stream()
        .map(
            userId ->
                messageSource.getMessage(
                    Message.USER_ERR_001, new String[] {String.valueOf(userId)}, Locale.ENGLISH))
        .collect(Collectors.toList());
  }

  /**
   * Validate existence of source at the destination.
   *
   * @param source Set<Long>
   * @param destination List<Long>
   * @return error message list.
   */
  private List<String> validateExistRoleId(final Set<Long> source, List<Long> destination) {
    return ListUtil.getInexistList(source, destination).stream()
        .map(
            roleId ->
                messageSource.getMessage(
                    Message.ROLE_ERR_001, new String[] {String.valueOf(roleId)}, Locale.ENGLISH))
        .collect(Collectors.toList());
  }
}
