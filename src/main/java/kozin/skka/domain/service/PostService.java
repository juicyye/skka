package kozin.skka.domain.service;

import kozin.skka.domain.entity.Category;
import kozin.skka.domain.entity.Post;
import kozin.skka.domain.repository.PostRepository;
import kozin.skka.domain.service.dto.PostForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;


    @Transactional
    public Long save(PostForm postForm) {
        Post post = Post.toEntity(postForm);
        return postRepository.save(post).getId();

    }

    public PostForm getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found post " + id));
        return PostForm.toDto(post);
    }

    public List<PostForm> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return PostForm.toDto(posts);
    }
    @Transactional
    public void update(Long id, PostForm form) {
        postRepository.updatePost(id, form.getTitle(),form.getContent(), Category.findCategory(form.getCategory()));
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found post " + id));
        postRepository.delete(post);
    }
}
