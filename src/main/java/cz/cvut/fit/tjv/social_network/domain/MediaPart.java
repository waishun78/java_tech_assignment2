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

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Domain type MediaPart. Its primary key is composed of Post and URI (identifier of the contents).
 */
public class MediaPart implements DomainEntity<MediaPartKey> {
    private MediaPartKey key;
    private LocalDateTime added = LocalDateTime.now();

    /**
     * Initialize this instance.
     * @param key primary key instance
     * @throws NullPointerException if the key is {@code null}
     */
    public MediaPart(MediaPartKey key) {
        this.key = Objects.requireNonNull(key);
    }

    /**
     * Initialize this instance.
     * @param postEmbeddedIn the post that is composed of this media part
     * @param mediaLocation identifier of the contents of this media part
     * @throws NullPointerException if either of the arguments is {@code null}
     */
    public MediaPart(Post postEmbeddedIn, URI mediaLocation) {
        this(new MediaPartKey(postEmbeddedIn, mediaLocation));
    }

    @Override
    public MediaPartKey getId() {
        return key;
    }

    public void setAdded(LocalDateTime added) {
        this.added = added;
    }

    public MediaPartKey getKey() {
        return key;
    }

    public LocalDateTime getAdded() {
        return added;
    }

    public Post getPostFor() {
        return key.getPostFor();
    }

    public void setKey(MediaPartKey key) {
        this.key = key;
    }

    public URI getUri() {
        return key.getUri();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MediaPart mediaPart = (MediaPart) o;

        return getKey().equals(mediaPart.getKey());
    }

    @Override
    public int hashCode() {
        return getKey().hashCode();
    }

    @Override
    public String toString() {
        return "MediaPart{" +
                "key=" + key +
                ", added=" + added +
                '}';
    }
}
