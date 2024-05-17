package kozin.skka.common.security.service;

import kozin.skka.domain.entity.User;
import kozin.skka.domain.repository.UserRepository;
import kozin.skka.domain.service.dto.LoginContext;
import kozin.skka.domain.service.dto.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component("userDetailsService")
public class FormUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));
        UserForm userForm = UserForm.toDto(user);
        return new LoginContext(userForm, authorities);
    }
}
