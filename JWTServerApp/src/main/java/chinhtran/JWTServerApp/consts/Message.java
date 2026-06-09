package chinhtran.JWTServerApp.consts;

/** All constant messages are used in the resources. */
public class Message {
  // auth-err-001: username or password is wrong!
  public static final String AUTH_ERR_001 = "auth-err-001";
  // auth-err-002: Token is expired.
  public static final String AUTH_ERR_002 = "auth-err-002";
  // auth-err-003: Token is wrong.
  public static final String AUTH_ERR_003 = "auth-err-003";
  // auth-err-001: Something went wrong.
  public static final String SYS_ERR_001 = "sys-err-001";
  // bad-req-err-001: Can not read request body
  public static final String BAD_REQ_ERR_001 = "bad-req-err-001";
  // Default message.
  public static final String BAD_REQ_ERR_002 = "bad-req-err-002";
  // user-err-001: UserId {0} is not exist
  public static final String USER_ERR_001 = "user-err-001";
  // user-err-002 = Username is used!
  public static final String USER_ERR_002 = "user-err-002";
  // role-err-001: RoleId {0} Is not exist
  public static final String ROLE_ERR_001 = "role-err-001";
}
