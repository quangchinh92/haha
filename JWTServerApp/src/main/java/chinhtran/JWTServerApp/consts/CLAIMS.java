package chinhtran.JWTServerApp.consts;

import lombok.Getter;

public enum CLAIMS {
  ROLES("roles");

  @Getter private String value;

  CLAIMS(String value) {
    this.value = value;
  }
}
