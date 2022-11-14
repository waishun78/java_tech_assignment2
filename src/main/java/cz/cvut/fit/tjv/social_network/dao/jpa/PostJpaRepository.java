package cz.cvut.fit.tjv.social_network.dao.jpa;

import cz.cvut.fit.tjv.social_network.dao.PostRepository;
import cz.cvut.fit.tjv.social_network.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Long>, PostRepository {
    @Override
    Collection<Post> findAllByAuthorUsername(String username);

    @Override
    @Query(value = "SELECT p FROM Post p WHERE p.liked.size > :l") //JPQL
    Collection<Post> findByLikedGreaterThan(@Param("l") int likes);
}
