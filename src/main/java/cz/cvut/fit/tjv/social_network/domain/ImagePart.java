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

/**
 * Domain type ImagePart (specialization of MediaPart). Its primary key is composed of Post and URI.
 */
public class ImagePart extends MediaPart {
    private int width;
    private int height;

    /**
     *
     * @param key primary key of this instance
     * @param width of this image
     * @param height of this image
     * @throws NullPointerException if key is {@code null}
     */
    public ImagePart(MediaPartKey key, int width, int height) {
        super(key);
        this.width = width;
        this.height = height;
    }

    /**
     *
     * @param postFor the post that is composed of this media part
     * @param mediaLocation identifier of the contents of this image
     * @param width of this image
     * @param height of this image
     * @throws NullPointerException if either postFor or mediaLocation is {@code null}
     */
    public ImagePart(Post postFor, URI mediaLocation, int width, int height) {
        this(new MediaPartKey(postFor, mediaLocation), width, height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ImagePart{" + super.toString() +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
