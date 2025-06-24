package org.app.diet.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "user")
@Schema(name = "User")
@JsonIgnoreProperties(value = { "password" })
@Getter
@Setter
public class UserEntity extends CoreEntity implements UserDetails {

    @Schema(description = "username")
    private String username;

    @Schema(description = "password")
    private String password;

    @Schema(description = "locked")
    private Boolean locked;

    @Schema(description = "role")
    private String role;

    @Override
    @jakarta.persistence.Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !getLocked();
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
