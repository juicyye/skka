package kozin.skka.domain.service.dto;

import kozin.skka.domain.entity.Post;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class PostForm {
    private Long id;
    private String name; // 유저이름
    private String title;
    private String content;
    @Builder.Default
    private List<CommentForm> commentForms = new ArrayList<>();
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String category;


    /**
     * Entity -> DTO
     */
    public static PostForm toDto(Post post) {
        return PostForm.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createDate(post.getCreateDate())
                .updateDate(post.getUpdateDate())
                .commentForms(CommentForm.toDTO(post.getComments()))
                .category(String.valueOf(post.getCategory()))
                .build();
    }

    public static List<PostForm> toDto(List<Post> posts) {
        List<PostForm> postForms = new ArrayList<>();
        for (Post post : posts) {
            postForms.add(PostForm.toDto(post));
        }
        return postForms;
    }


}
