package kozin.skka.domain.repository;

import kozin.skka.domain.entity.Category;
import kozin.skka.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Post p SET p.title = :title, p.content = :content, p.category = :category WHERE p.id = :id")
    void updatePost(Long id, String title, String content, Category category);
}
