package kozin.skka.domain.repository;

import kozin.skka.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByLoginId(String loginId);
}
