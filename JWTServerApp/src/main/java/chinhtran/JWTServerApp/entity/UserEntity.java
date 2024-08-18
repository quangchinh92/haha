package chinhtran.JWTServerApp.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

@Data
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  private String password;
  private String name;
  private String email;
  private String phoneNumber;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "USER_ROLE",
      joinColumns = @JoinColumn(name = "USER_ID"),
      inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
  private List<RoleEntity> roleList;

  public List<MyGrantedAuthority> getAuthorities() {
    List<RoleEntity> roles = this.getRoleList();
    if (CollectionUtils.isEmpty(roles)) {
      return new ArrayList<>();
    }
    List<MyGrantedAuthority> authorities = new ArrayList<>();
    for (RoleEntity role : roles) {
      authorities.add(new MyGrantedAuthority(role.getValue()));
    }
    return authorities;
  }

  public static class MyGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    @JsonProperty("authority")
    private final String role;

    @JsonCreator
    public MyGrantedAuthority(@JsonProperty("authority") String role) {
      Assert.hasText(role, "A granted authority textual representation is required");
      this.role = role;
    }

    @Override
    public String getAuthority() {
      return this.role;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj instanceof MyGrantedAuthority) {
        return this.role.equals(((MyGrantedAuthority) obj).role);
      }
      return false;
    }

    @Override
    public int hashCode() {
      return this.role.hashCode();
    }

    @Override
    public String toString() {
      return this.role;
    }
  }
}
