package chinhtran.JWTServerApp.controller.user.model;

import java.util.Date;
import java.util.Objects;
import lombok.Data;

@Data
public class UserPutReq {
  private String name;
  private String email;
  private String phoneNumber;
  private Date lastUpdatedDate;

  public Date getLastUpdatedDate() {
    return Objects.isNull(lastUpdatedDate) ? null : (Date) lastUpdatedDate.clone();
  }

  public void setLastUpdatedDate(Date lastUpdatedDate) {
    this.lastUpdatedDate = Objects.isNull(lastUpdatedDate) ? null : (Date) lastUpdatedDate.clone();
  }
}
