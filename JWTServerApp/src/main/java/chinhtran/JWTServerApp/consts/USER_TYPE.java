package chinhtran.JWTServerApp.consts;

import lombok.Getter;

public enum USER_TYPE {
  USER(1),
  AGENCY(2);

  @Getter private Integer value;

  USER_TYPE(Integer value) {
    this.value = value;
  }
}
