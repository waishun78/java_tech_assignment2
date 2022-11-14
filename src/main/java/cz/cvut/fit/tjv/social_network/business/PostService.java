/*
 * Project Social Network. Created for Java Technology course at Czech Technical University in Prague,
 * Faculty of Information Technology.
 *
 * Author: Ondřej Guth (ondrej.guth@fit.cvut.cz)
 *
 * This code is intended for educational purposes only.
 */

package cz.cvut.fit.tjv.social_network.business;

import cz.cvut.fit.tjv.social_network.dao.PostRepository;
import cz.cvut.fit.tjv.social_network.dao.in_memory.UserInMemRepository;
import cz.cvut.fit.tjv.social_network.domain.Post;
import cz.cvut.fit.tjv.social_network.domain.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Business logic operations related to any Post.
 */
@Service
public class PostService extends AbstractCrudService<Post, Long> {
    protected final UserService userService = new UserService(new UserInMemRepository());

    public PostService(PostRepository postRepository) {
        super(postRepository); //invocation of constructor of the superclass (AbstractCrudService)
    }

    /**
     * Add like of specified user to specified post.
     *
     * @param id       key of the post
     * @param username key of the user
     * @throws NoSuchElementException if the specified post or user do not exist
     */
    public void likePost(long id, String username) {
        //an example of complex business operation
        Optional<Post> optionalPost = readById(id);
        Optional<User> optionalUser = userService.readById(username);

        Post post = optionalPost.orElseThrow(); //throws NoSuchElementException if the Optional is empty
        User user = optionalUser.orElseThrow();
        post.addLike(user);
        try {
            update(post);
        } catch (EntityStateException e) {
            // this should never happen as we already checked existence of the post
            throw new RuntimeException(e);
        }
    }

    /**
     * Read Posts liked by more than specified number of users.
     * @param likes number of users
     * @return a Collection of Posts that are liked by more than likes number of users
     */
    public Collection<Post> findLikedMoreThan(int likes) {
        return ((PostRepository) repository).findByLikedGreaterThan(likes);
    }

    /**
     * Read posts created by specified user.
     *
     * @param authorId username of the posts' author
     * @return a collection of posts created by specified user; it is never null
     */
    public Collection<Post> readAllByAuthor(String authorId) {
        return ((PostRepository) repository).findAllByAuthorUsername(authorId); //typecast from AbstractFileRepository to PostFileRepository
    }
}
