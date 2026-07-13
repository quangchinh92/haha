package chinhtran.JWTServerApp.cache;

import chinhtran.JWTServerApp.cache.models.UserCacheData;
import chinhtran.JWTServerApp.cache.models.UserEvent;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserListener implements ApplicationListener<UserEvent> {

  @Autowired private UserCache userCache;

  @Override
  @Async
  public void onApplicationEvent(UserEvent event) {
    if ("WRITE".equals(event.getMessage())) {
      UserCacheData data = (UserCacheData) event.getSource();

      UserCacheData root = userCache.getById(data.getId());

      if (Objects.isNull(root)) {
        userCache.put(data);
        return;
      }

      mergedData(root, data);

      userCache.put(root);
    }
  }

  private void mergedData(UserCacheData root, UserCacheData updatedData) {
    if (!Objects.isNull(updatedData.getName())) {
      root.setName(updatedData.getName());
    }

    if (!Objects.isNull(updatedData.getEmail())) {
      root.setEmail(updatedData.getEmail());
    }

    if (!Objects.isNull(updatedData.getPhoneNumber())) {
      root.setPhoneNumber(updatedData.getPhoneNumber());
    }

    if (!Objects.isNull(updatedData.getUpdatedDate())) {
      root.setUpdatedDate(updatedData.getUpdatedDate());
    }
  }
}
