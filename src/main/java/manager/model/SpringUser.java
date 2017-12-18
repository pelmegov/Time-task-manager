package manager.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Decorator for User entity in spring security
 */
public class SpringUser extends org.springframework.security.core.userdetails.User {

    public SpringUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SpringUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
