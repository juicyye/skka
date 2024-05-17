package kozin.skka.domain.service;

import kozin.skka.domain.entity.User;
import kozin.skka.domain.repository.UserRepository;
import kozin.skka.domain.service.dto.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public Long join(UserForm userForm) {
        User user = User.toEntity(userForm);
        return userRepository.save(user).getId();
    }
}
