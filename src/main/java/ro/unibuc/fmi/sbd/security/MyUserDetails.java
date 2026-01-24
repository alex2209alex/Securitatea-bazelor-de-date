package ro.unibuc.fmi.sbd.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ro.unibuc.fmi.sbd.dto.UserDto;
import ro.unibuc.fmi.sbd.entity.Rol;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private final UserDto dto;

    public MyUserDetails(UserDto dto) {
        this.dto = dto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(dto.getRol().name()));
    }

    @Override
    public String getPassword() {
        return dto.getPassword();
    }

    @Override
    public String getUsername() {
        return dto.getUsername();
    }

    public Long getUserId() {
        return dto.getId();
    }

    public Rol getRol() {
        return dto.getRol();
    }
}
