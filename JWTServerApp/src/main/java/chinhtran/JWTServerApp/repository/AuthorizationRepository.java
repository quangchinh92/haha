package chinhtran.JWTServerApp.repository;

import chinhtran.JWTServerApp.entity.AuthorizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationRepository extends JpaRepository<AuthorizationEntity, Long> {

  public Integer deleteByRoleId(Long roleId);
}
