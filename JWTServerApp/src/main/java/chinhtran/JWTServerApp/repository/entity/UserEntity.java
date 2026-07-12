package chinhtran.JWTServerApp.repository.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

@Data
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "USERNAME")
  private String username;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "NAME")
  private String name;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "PHONE_NUMBER")
  private String phoneNumber;

  @Column(name = "TYPE")
  private Integer type;

  @Column(
      name = "UPDATED_PASSWORD_DATE",
      insertable = false,
      columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Date updatedPasswordDate;

  @Column(
      name = "UPDATED_DATE",
      insertable = false,
      columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Date updatedDate;

  @Column(
      name = "CREATED_DATE",
      insertable = false,
      columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Date createdDate;

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

  public Date getCreatedDate() {
    return Objects.isNull(createdDate) ? null : (Date) createdDate.clone();
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = Objects.isNull(createdDate) ? null : (Date) createdDate.clone();
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
