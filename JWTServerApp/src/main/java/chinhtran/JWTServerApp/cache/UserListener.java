package chinhtran.JWTServerApp.cache;

import chinhtran.JWTServerApp.cache.models.UserCacheData;
import chinhtran.JWTServerApp.cache.models.UserEvent;
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
      userCache.put(data);
    }
  }
}
