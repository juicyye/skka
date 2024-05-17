package kozin.skka.domain.service.dto;

import kozin.skka.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {
    private Long id;
    private String name;
    private String loginId;
    private String password;
    private String role;

    public static UserForm toDto(User user) {
        return UserForm.builder()
                .id(user.getId())
                .name(user.getName())
                .loginId(user.getLoginId())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public static List<UserForm> toDto(List<User> users) {
        List<UserForm> userForms = new ArrayList<>();
        for (User user : users) {
            userForms.add(UserForm.toDto(user));
        }
        return userForms;
    }
}
