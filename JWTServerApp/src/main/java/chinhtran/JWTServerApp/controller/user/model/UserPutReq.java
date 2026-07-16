package chinhtran.JWTServerApp.controller.user.model;

import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class UserPutReq {

  @Getter @Setter private String name;
  @Getter @Setter private String email;
  @Getter @Setter private String phoneNumber;
  @NotNull private Date lastUpdatedDate;

  public Date getLastUpdatedDate() {
    return (Date) lastUpdatedDate.clone();
  }

  public void setLastUpdatedDate(Date lastUpdatedDate) {
    this.lastUpdatedDate = (Date) lastUpdatedDate.clone();
  }
}
