package kozin.skka.domain.entity;

import jakarta.persistence.*;
import kozin.skka.domain.service.dto.CommentForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    //== 편의 메서드
    public void addUser(User user) {
        this.user = user;
        user.getComments().add(this);
    }

    public void setPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }

    /**
     * DTO -> Entity
     */
    public static Comment toEntity(CommentForm commentForm,Post post) {
        Comment comment = Comment.builder().content(commentForm.getContent()).build();
        comment.setPost(post);
        return comment;
    }


}
