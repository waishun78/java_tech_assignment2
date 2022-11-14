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
import java.io.Serializable;
import java.util.Objects;

/**
 * Domain type User. Its primary key is username (natural key: String).
 */
@Entity
@Table(name = "tbl_user")
public class User implements Serializable, DomainEntity<String> { //Serializable may be used by ObjectInputStream and ObjectOutputStream
    /**
     * primary key of User
     */
    @Id
    private String username;

//    @Transient
    @Column(name = "real_name")
    private String realName;

    /**
     * Store given username in the instance.
     *
     * @param username given username; cannot be null
     * @throws NullPointerException if the given username is null
     */
    public User(String username) {
        this.username = Objects.requireNonNull(username);
    }

    public User(String username, String realName) {
        this(username);
        this.realName = realName;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String getId() {
        return getUsername();
    }

    /**
     * Compare this and another instance of User by username.
     *
     * @param o other user to compare
     * @return true if other instance is also User and has the same username
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getUsername().equals(user.getUsername());
    }

    @Override
    public int hashCode() {
        return getUsername().hashCode();
    } //hashCode must work the same way as equals

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }
}
