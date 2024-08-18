package chinhtran.JWTServerApp.consts;

public class Message {
  // auth-err-001: username or password is wrong!
  public static String AUTH_ERR_001 = "auth-err-001";
  // auth-err-002: Token is expired.
  public static String AUTH_ERR_002 = "auth-err-002";
  // auth-err-003: Token is wrong.
  public static String AUTH_ERR_003 = "auth-err-003";
  // auth-err-001: Something went wrong.
  public static String SYS_ERR_001 = "sys-err-001";
  // bad-req-err-001: Can not read request body
  public static String BAD_REQ_ERR_001 = "bad-req-err-001";
  // Default message.
  public static String BAD_REQ_ERR_002 = "bad-req-err-002";
  // user-err-001: UserId {0} is not exist
  public static String USER_ERR_001 = "user-err-001";
  // user-err-002 = Username is used!
  public static String USER_ERR_002 = "user-err-002";
  // role-err-001: RoleId {0} Is not exist
  public static String ROLE_ERR_001 = "role-err-001";
}
