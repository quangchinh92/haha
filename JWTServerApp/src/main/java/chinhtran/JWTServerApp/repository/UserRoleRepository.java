package chinhtran.JWTServerApp.repository;

import chinhtran.JWTServerApp.entity.UserRoleEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

  public Integer deleteByUserId(Long userId);

  public Optional<UserRoleEntity> findByRoleId(Long roleId);
}
