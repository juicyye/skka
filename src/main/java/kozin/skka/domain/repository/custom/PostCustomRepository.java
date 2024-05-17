package kozin.skka.domain.repository.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kozin.skka.domain.entity.QPost;
import kozin.skka.domain.entity.QUser;
import kozin.skka.domain.repository.condition.CategoryCon;
import kozin.skka.domain.service.dto.PostForm;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kozin.skka.domain.entity.QPost.*;
import static kozin.skka.domain.entity.QUser.*;

@Repository
@Transactional(readOnly = true)
public class PostCustomRepository {

    private JPAQueryFactory queryFactory;
    private EntityManager em;

    public PostCustomRepository(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    public List<PostForm> findAllByHumor(CategoryCon condition) {
        return queryFactory.select(Projections.fields(PostForm.class,
                        post.id, user.name.as("name"),
                        post.title, post.content,post.createDate,post.updateDate,post.category.stringValue().as("category")))
                .from(post)
                .leftJoin(post.user, user)
                .where(categoryEq(condition))
                .fetch();
    }

    private BooleanExpression categoryEq(CategoryCon categoryCon) {
        return categoryCon != null ? post.category.stringValue().eq(categoryCon.getCategory()) : null;
    }
}
