package ro.unibuc.fmi.sbd.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ro.unibuc.fmi.sbd.dto.UserDto;
import ro.unibuc.fmi.sbd.entity.Utilizator;
import ro.unibuc.fmi.sbd.repository.UtilizatorRepository;

public class MyUserDetailsService implements UserDetailsService {
    private final UtilizatorRepository utilizatorRepository;

    public MyUserDetailsService(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilizator utilizator = utilizatorRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        UserDto userDto = new UserDto();
        userDto.setId(utilizator.getId());
        userDto.setUsername(utilizator.getUsername());
        userDto.setRol(utilizator.getRol());
        userDto.setPassword(utilizator.getHashParola().toLowerCase());
        return new MyUserDetails(userDto);
    }
}
