package chinhtran.JWTServerApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinhtran.JWTServerApp.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    public List<RoleEntity> findByIdInOrderById(List<Long> idList);
}
