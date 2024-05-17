package kozin.skka.domain.entity;

import jakarta.persistence.*;
import kozin.skka.domain.service.dto.UserForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String loginId;
    private String password;
    private String role;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    /**
     * Entity -> DTO
     */

    public static User toEntity(UserForm form) {
        return User.builder()
                .name(form.getName())
                .loginId(form.getLoginId())
                .password(form.getPassword())
                .role(form.getRole())
                .build();
    }
}
