package chinhtran.JWTServerApp.config;

import chinhtran.JWTServerApp.cache.UserCache;
import chinhtran.JWTServerApp.converter.UserConverter;
import chinhtran.JWTServerApp.repository.UserRepository;
import chinhtran.JWTServerApp.repository.entity.UserEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/** This class is executed when the project starts. */
// @Component
public class RedisCommandLineRunner implements CommandLineRunner {

  @Autowired private UserRepository userRepository;
  @Autowired private UserCache userCache;

  @Override
  public void run(String... args) throws Exception {
    List<UserEntity> userList = userRepository.findAll();
    // Write user data to redis.
    userList.parallelStream()
        .forEach(
            user -> {
              userCache.put(UserConverter.convertEntityToCacheData(user));
            });
  }
}
