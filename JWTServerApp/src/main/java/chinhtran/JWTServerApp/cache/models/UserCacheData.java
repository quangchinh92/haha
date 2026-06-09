package chinhtran.JWTServerApp.cache.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import lombok.Data;

/** User caching data model. */
@Data
public class UserCacheData implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String username;

  private String name;

  private String email;

  private String phoneNumber;

  private Integer type;

  private Date updatedPasswordDate;

  public Date getUpdatedPasswordDate() {
    return Objects.isNull(updatedPasswordDate) ? null : (Date) updatedPasswordDate.clone();
  }

  public void setUpdatedPasswordDate(Date updatedPasswordDate) {
    this.updatedPasswordDate =
        Objects.isNull(updatedPasswordDate) ? null : (Date) updatedPasswordDate.clone();
  }

  public UserCacheData updatedPasswordDate(Date updatedPasswordDate) {
    this.updatedPasswordDate =
        Objects.isNull(updatedPasswordDate) ? null : (Date) updatedPasswordDate.clone();
    return this;
  }
}
