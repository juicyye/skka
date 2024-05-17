package kozin.skka.domain.entity;


import lombok.Getter;


@Getter
public enum Category {
    HUMOR("유머"), SPORTS("축구"), SHOPPING("쇼핑"), GAME("게임");
    private final String description;

    Category(String description) {
        this.description = description;
    }

    public static Category findCategory(String checkDescription) {
        for (Category category : Category.values()) {
            if (category.description.equals(checkDescription)) {
                return category;
            }
        }

        throw new IllegalStateException("카테고리를 찾을수 없습니다.");
    }
}
