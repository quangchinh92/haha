package chinhtran.JWTServerApp.repository;

import chinhtran.JWTServerApp.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  public Optional<UserEntity> findByUsername(String username);

  public Optional<UserEntity> findByUsernameAndPassword(String username, String password);

  public List<UserEntity> findByIdInOrderById(List<Long> idList);
}
