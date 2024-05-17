package kozin.skka.domain.service;

import kozin.skka.domain.entity.Comment;
import kozin.skka.domain.entity.Post;
import kozin.skka.domain.repository.CommentRepository;
import kozin.skka.domain.repository.PostRepository;
import kozin.skka.domain.service.dto.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long save(CommentForm form) {
        Long postId = form.getPostId();
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("comment in not found question id = {}" + form.getPostId()));
        Comment comment = Comment.toEntity(form,post);
        commentRepository.save(comment);
        return comment.getId();
    }

    public CommentForm getComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found Comment id = {}" + id));
        return CommentForm.toDTO(comment);
    }

    public List<CommentForm> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return CommentForm.toDTO(comments);
    }
}
