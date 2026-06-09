package chinhtran.JWTServerApp.cache;

import chinhtran.JWTServerApp.cache.models.UserCacheData;
import chinhtran.JWTServerApp.consts.CacheKeys;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.stereotype.Service;

/** Retrieve and write user data from the cache. Function list: - getById - put */
@Service
public class UserCache {
  private final HashOperations<String, byte[], byte[]> hashOperations;
  private final HashMapper<Object, byte[], byte[]> mapper = new ObjectHashMapper();

  @Autowired
  public UserCache(@Qualifier("redisTemplate") RedisTemplate<String, String> redisTemplate) {
    this.hashOperations = redisTemplate.opsForHash();
  }

  /**
   * Retrieve data from cache and convert it to UserCacheData.
   *
   * @param id Long
   * @return UserCacheData
   */
  public UserCacheData getById(Long id) {
    Map<byte[], byte[]> loadedHash = hashOperations.entries(CacheKeys.USER_KEY_PREFIX + id);

    Object data = mapper.fromHash(loadedHash);
    if (Objects.isNull(data)) {
      return null;
    }
    return (UserCacheData) data;
  }

  /**
   * Write data to cache.
   *
   * @param data
   */
  public void put(UserCacheData data) {
    hashOperations.putAll(CacheKeys.USER_KEY_PREFIX + data.getId(), mapper.toHash(data));
  }
}
