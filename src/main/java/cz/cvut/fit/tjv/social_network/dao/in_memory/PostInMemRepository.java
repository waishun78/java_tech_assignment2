/*
 * Copyright (c) 2022. Czech Technical University in Prague, Faculty of Information Technology.
 *
 * Project Social Network. Created for Java Technology course.
 *
 *  Authors:
 *  Ondřej Guth (ondrej.guth@fit.cvut.cz)
 *  Jan Blizničenko (jan.bliznicenko@fit.cvut.cz)
 *  Filip Glazar (glazafil@fit.cvut.cz)
 *
 *  This code is intended for educational purposes only.
 *
 */

package cz.cvut.fit.tjv.social_network.dao.in_memory;

import cz.cvut.fit.tjv.social_network.dao.PostRepository;
import cz.cvut.fit.tjv.social_network.domain.Post;

import java.util.Collection;

public class PostInMemRepository extends InMemoryRepository<Post, Long> implements PostRepository {
    public Collection<Post> findByLikedGreaterThan(int likes) {
        return findAll().stream().filter(p -> p.getLiked().size() > likes).toList();
    }

    public Collection<Post> findAllByAuthorUsername(String userId) {
        return findAll().stream().filter(p -> p.getAuthor().getUsername().equals(userId)).toList();
    }
}
