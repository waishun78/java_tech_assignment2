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

package cz.cvut.fit.tjv.social_network.domain;

import java.net.URI;
import java.util.Objects;

/**
 * Primary key type of MediaType domain type.
 */
public class MediaPartKey {
    private final Post postFor;
    private final URI uri;

    /**
     * Initializes this key.
     * @param postFor the post that references to this key
     * @param uri identifier of the contents of this media part
     * @throws NullPointerException if either postFor or uri is {@code null}
     */
    public MediaPartKey(Post postFor, URI uri) {
        this.postFor = Objects.requireNonNull(postFor);
        this.uri = Objects.requireNonNull(uri);
    }

    public Post getPostFor() {
        return postFor;
    }

    public URI getUri() {
        return uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MediaPartKey that = (MediaPartKey) o;

        if (!getPostFor().equals(that.getPostFor())) return false;
        return getUri().equals(that.getUri());
    }

    @Override
    public int hashCode() {
        int result = getPostFor().hashCode();
        result = 31 * result + getUri().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MediaPartKey{" +
                "postFor=" + postFor +
                ", uri=" + uri +
                '}';
    }
}
