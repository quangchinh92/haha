package chinhtran.JWTServerApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinhtran.JWTServerApp.entity.AuthorizationEntity;

@Repository
public interface AuthorizationRepository extends JpaRepository<AuthorizationEntity, Long> {

    public Integer deleteByRoleId(Long roleId);
}
