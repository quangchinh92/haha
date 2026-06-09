package chinhtran.JWTServerApp.repository;

import chinhtran.JWTServerApp.repository.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  public Optional<UserEntity> findByUsername(String username);

  public Optional<UserEntity> findByUsernameAndPassword(String username, String password);

  public List<UserEntity> findByIdInOrderById(List<Long> idList);

  @Modifying
  @Query(
      value =
          "UPDATE user u SET u.PASSWORD = :password, u.UPDATED_PASSWORD_DATE = NOW() WHERE u.ID = :id",
      nativeQuery = true)
  public void changePassword(@Param("id") Long id, @Param("password") String password);
}
