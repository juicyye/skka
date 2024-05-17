package kozin.skka.domain.entity;

import lombok.Getter;

@Getter
public enum Roles {
    ROLE_USER("유저"), ROLE_MANAGER("매니저"), ROLE_ADMIN("관리자");
    private final String role;

    Roles(String role) {
        this.role = role;
    }
}
