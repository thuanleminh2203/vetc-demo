package com.mbbank.vetc.config;

import com.mbbank.vetc.feign.response.UserFeignResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String cif;

    private String fullname;

    private String dob;

    private String idCardNo;

    private String mobile;

    private String email;

    private String mbToken;

    @Setter
    private String password;

    @Setter
    private Collection<? extends GrantedAuthority> authorities;

    private String sessionId;

    public static UserPrincipal create(UserFeignResponse user) {

        return new UserPrincipal(
                user.getCif(),
                user.getFullname(),
                user.getDob(),
                user.getIdCardNo(),
                user.getMobile(),
                user.getEmail(),
                null,
                null,
                null,
                user.getSessionId()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
