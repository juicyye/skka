package kozin.skka.domain.entity;

import jakarta.persistence.*;
import kozin.skka.domain.service.dto.PostForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //== 편의메서드
    public void addUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }

    // == ToEntity
    public static Post toEntity(PostForm postForm) {
        return Post.builder()
                .title(postForm.getTitle())
                .content(postForm.getContent())
                .category(Category.findCategory(postForm.getCategory()))
                .build();
    }
}
