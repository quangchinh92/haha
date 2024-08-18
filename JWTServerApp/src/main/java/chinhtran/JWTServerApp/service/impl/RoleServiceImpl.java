package chinhtran.JWTServerApp.service.impl;

import chinhtran.JWTServerApp.controller.role.model.RoleGetRes;
import chinhtran.JWTServerApp.controller.role.model.RolePostReq;
import chinhtran.JWTServerApp.controller.role.model.RolePostRes;
import chinhtran.JWTServerApp.converter.RoleConverter;
import chinhtran.JWTServerApp.entity.RoleEntity;
import chinhtran.JWTServerApp.entity.UserRoleEntity;
import chinhtran.JWTServerApp.repository.AuthorizationRepository;
import chinhtran.JWTServerApp.repository.RoleRepository;
import chinhtran.JWTServerApp.repository.UserRoleRepository;
import chinhtran.JWTServerApp.service.RoleService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@PreAuthorize("hasRole('ADMIN')")
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  private final UserRoleRepository userRoleRepository;

  private final AuthorizationRepository authorizationRepository;

  @Autowired
  public RoleServiceImpl(
      RoleRepository roleRepository,
      UserRoleRepository userRoleRepository,
      AuthorizationRepository authorizationRepository) {
    this.roleRepository = roleRepository;
    this.userRoleRepository = userRoleRepository;
    this.authorizationRepository = authorizationRepository;
  }

  @Override
  public RoleGetRes getByPK(Long roleId) {
    return RoleConverter.convertRoleEntityToRoleGetRes(
        roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException()));
  }

  @Override
  public List<RoleGetRes> getAll() {
    return RoleConverter.convertRoleEntityListToRoleGetResList(roleRepository.findAll());
  }

  @Override
  public RolePostRes upsert(RolePostReq rolePostReq) {
    RoleEntity roleEntity = RoleConverter.convertRolePostReqToRoleEntity(rolePostReq);
    roleEntity = roleRepository.save(roleEntity);
    if (roleEntity.getId() == null) {
      throw new RuntimeException("");
    }
    RoleEntity finalRoleEntity = roleEntity;
    roleEntity
        .getAutorizationList()
        .forEach(
            authorization -> {
              authorization.setRole(finalRoleEntity);
            });
    roleEntity.setAutorizationList(
        authorizationRepository.saveAll(roleEntity.getAutorizationList()));
    return RoleConverter.convertRoleEntityToRolePostRes(roleEntity);
  }

  @Override
  @Transactional
  public void deleteByPK(Long roleId) {
    Optional<UserRoleEntity> userRoleEntity = userRoleRepository.findByRoleId(roleId);
    if (!userRoleEntity.isPresent()) {
      throw new RuntimeException();
    }
    roleRepository.deleteById(roleId);
    authorizationRepository.deleteByRoleId(roleId);
  }
}
