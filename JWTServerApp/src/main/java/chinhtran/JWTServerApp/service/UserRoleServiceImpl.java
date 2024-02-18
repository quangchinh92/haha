package chinhtran.JWTServerApp.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import chinhtran.JWTServerApp.entity.UserRoleEntity;
import chinhtran.JWTServerApp.repository.UserRoleRepository;

@Service
@PreAuthorize("hasRole('ADMIN')")
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository repository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void updateRole(Long userId, List<Long> roleIdList) {
        // Delete all by userId
        repository.deleteByUserId(userId);

        // Create UserRoleEntity list
        List<UserRoleEntity> userRoleEntityList = roleIdList.stream().map(roleId -> {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(userId);
            userRoleEntity.setRoleId(roleId);
            return userRoleEntity;
        }).collect(Collectors.toList());

        // Insert Batch
        repository.saveAll(userRoleEntityList);
    }

}
