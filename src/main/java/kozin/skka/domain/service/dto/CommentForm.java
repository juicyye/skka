package kozin.skka.domain.service.dto;

import kozin.skka.domain.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentForm {
    private Long id;
    private Long postId;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    /**
     * Entity -> DTO
     */

    public static CommentForm toDTO(Comment comment) {
        return CommentForm.builder()
                .id(comment.getId())
                .postId(comment.getPost().getId())
                .content(comment.getContent())
                .createDate(comment.getCreateDate())
                .updateDate(comment.getUpdateDate())
                .build();
    }

    public static List<CommentForm> toDTO(List<Comment> comments) {
        List<CommentForm> commentList = new ArrayList<>();
        for (Comment comment : comments) {
            commentList.add(toDTO(comment));
        }
        return commentList;
    }
}
