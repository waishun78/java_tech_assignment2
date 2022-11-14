/*
 * Project Social Network. Created for Java Technology course at Czech Technical University in Prague,
 * Faculty of Information Technology.
 *
 * Authors:
 * Ondřej Guth (ondrej.guth@fit.cvut.cz)
 * Jan Blizničenko (jan.bliznicenko@fit.cvut.cz)
 * Filip Glazar (glazafil@fit.cvut.cz)
 *
 * This code is intended for educational purposes only.
 */

package cz.cvut.fit.tjv.social_network.domain;

import javax.persistence.*;
import java.util.*;

/**
 * Domain type Post. Its primary key is numeric (technical).
 */
@Entity
public class Post implements DomainEntity<Long> {
    @Id
    private Long id;
    @ManyToOne
    private User author;
    /**
     * The post that this instance replies to. Equal to null if this post does not reply to anything.
     */
    @ManyToOne
    @JoinColumn(name = "reply_post_id")
    private Post replyTo;
    private String textContents;
    /**
     * The users that liked this post. Each user can give like to this post only once.
     */
    @ManyToMany
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private final Set<User> liked = new HashSet<>();


    /**
     * Initialize all fields of the new instance.
     *
     * @param replyTo the post that this one replies to; can be null if this post does not reply to anything
     * @param author   the author of this post; cannot be null
     * @param textContents the contents of this post
     * @throws NullPointerException if the author is null
     */
    public Post(Long id, Post replyTo, User author, String textContents) {
        this.id = id;
        this.replyTo = replyTo;
        this.author = Objects.requireNonNull(author);
        this.textContents = textContents;
    }

    public Post() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @GenerateValueWhenSave
    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Post replyTo) {
        this.replyTo = replyTo;
    }

    public String getTextContents() {
        return textContents;
    }

    public void setTextContents(String textContents) {
        this.textContents = textContents;
    }

    /**
     * Return the collection of all users that like this post.
     *
     * @return the read-only collection of all users that like this post
     */
    @SuppressWarnings("unused")
    public Collection<User> getLiked() {
        return Collections.unmodifiableCollection(liked);
    }

    /**
     * Add given user among those who give like to this post. It is a no-op if the user already likes this post.
     *
     * @param user the user to add
     * @throws NullPointerException if the user is null
     */
    public void addLike(User user) {
        liked.add(Objects.requireNonNull(user));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        return getId() != null ? getId().equals(post.getId()) : post.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author=" + author +
                ", textContents='" + textContents + '\'' +
                ", replyTo=" + replyTo +
                '}';
    }
}
