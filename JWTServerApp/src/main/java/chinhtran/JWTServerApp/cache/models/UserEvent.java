package chinhtran.JWTServerApp.cache.models;

import org.springframework.context.ApplicationEvent;

public class UserEvent extends ApplicationEvent {

  private static final long serialVersionUID = 1L;
  private String message;

  public UserEvent(Object source, String message) {
    super(source);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
