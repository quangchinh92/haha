package chinhtran.JWTServerApp.consts;

import lombok.Getter;

public enum CLAIMS {
  USER_ID("userId"),
  UPDATED_PASSWORD_DATE("updatedPasswordDate");

  @Getter private String value;

  CLAIMS(String value) {
    this.value = value;
  }
}
