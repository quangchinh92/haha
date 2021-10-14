package chinhtran.JWTServerApp.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private List<Role> roleList;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = this.getRoleList();
        List<MyGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new MyGrantedAuthority(role.getValue(), role.getEndpointList()));
        }
        return authorities;
    }

    public static class MyGrantedAuthority implements GrantedAuthority {

        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        private final String role;

        @Getter
        private final List<Endpoint> endpointList;

        public MyGrantedAuthority(String role, List<Endpoint> endpointList) {
            Assert.hasText(role, "A granted authority textual representation is required");
            this.role = role;
            this.endpointList = endpointList;
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
