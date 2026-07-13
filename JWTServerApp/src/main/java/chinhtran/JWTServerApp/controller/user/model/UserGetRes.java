package chinhtran.JWTServerApp.controller.user.model;

import java.util.Date;
import java.util.Objects;
import lombok.Data;

@Data
public class UserGetRes {

  private Long id;

  private String username;

  private String name;

  private String email;

  private String phoneNumber;

  private Date updatedPasswordDate;

  private Date updatedDate;

  public Date getUpdatedPasswordDate() {
    return Objects.isNull(updatedPasswordDate) ? null : (Date) updatedPasswordDate.clone();
  }

  public void setUpdatedPasswordDate(Date updatedPasswordDate) {
    this.updatedPasswordDate =
        Objects.isNull(updatedPasswordDate) ? null : (Date) updatedPasswordDate.clone();
  }

  public Date getUpdatedDate() {
    return Objects.isNull(updatedDate) ? null : (Date) updatedDate.clone();
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = Objects.isNull(updatedDate) ? null : (Date) updatedDate.clone();
  }
}
