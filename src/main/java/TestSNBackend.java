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

import cz.cvut.fit.tjv.social_network.business.MediaPartService;
import cz.cvut.fit.tjv.social_network.business.PostService;
import cz.cvut.fit.tjv.social_network.business.UserService;
import cz.cvut.fit.tjv.social_network.domain.ImagePart;
import cz.cvut.fit.tjv.social_network.domain.MediaPartKey;
import cz.cvut.fit.tjv.social_network.domain.Post;
import cz.cvut.fit.tjv.social_network.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.URI;

/**
 * Main class to test this application.
 */
public class TestSNBackend {
    /**
     * The main method of the application. When the application is run, this method is invoked.
     *
     * @param args command line arguments of the application
     */
    public static void main(final String[] args) throws BeansException {
        // test data
        var u1 = new User("test1", "Test User 1");
        var u2 = new User("test2", "Test User 2");
        var p1 = new Post(1L, null, u1, "hello world");
        var p2 = new Post(2L, p1, u2, "bye world");
        var img2 = new ImagePart(p2, URI.create("https://imgs.xkcd.com/comics/seat_selection.png"), 100, 50);
        p2.addLike(u1);


        ApplicationContext ctx = new AnnotationConfigApplicationContext("cz.cvut.fit.tjv.social_network");

        //instances of business-layer classes
        var usrSvc = ctx.getBean("userService", UserService.class);
        var mediaSvc = ctx.getBean(MediaPartService.class);
        var postSvc = ctx.getBean(PostService.class);


        System.out.println("Initial listing of");
        System.out.println("Users:");
        System.out.println(usrSvc.readAll());
        System.out.println("Posts:");
        System.out.println(postSvc.readAll());
        System.out.println("Media parts:");
        System.out.println(mediaSvc.readAll());
        System.out.println("Initial listing END");

        System.out.println("Inserting user " + u1);
        usrSvc.create(u1);
        System.out.println("Inserting user " + u2);
        usrSvc.create(u2);
        System.out.println("Inserting post " + p1);
        p1 = postSvc.create(p1);
        System.out.println("Inserting post " + p2);
        p2 = postSvc.create(p2);
        System.out.println("Inserting media part " + img2);
        mediaSvc.create(img2);

        System.out.println("Second post after inserting:");
        System.out.println(p2);
        System.out.println("All media parts after inserting:");
        System.out.println(mediaSvc.readAll());

        try {
            System.out.println("Attempt to delete a media part");
            mediaSvc.deleteById(new MediaPartKey(p1, null));
        } catch (NullPointerException e) {
            System.out.println("bug: null passed as URI");
        }

        mediaSvc.deleteById(new MediaPartKey(p1, URI.create("https://imgs.xkcd.com/comics/seat_selection.png")));
        System.out.println("All media parts after deleting an image of post " + p1);
        System.out.println(mediaSvc.readAll());
        mediaSvc.deleteById(new MediaPartKey(p2, URI.create("https://imgs.xkcd.com/comics/seat_selection.png")));
        System.out.println("All media parts after deleting an image of post " + p2);
        System.out.println(mediaSvc.readAll());

        u1.setRealName("new real name");
        System.out.println("All users after renaming " + u1);
        usrSvc.update(u1);
        System.out.println(usrSvc.readAll());

        System.out.println("Posts that are liked by more than 0 users:");
        System.out.println(postSvc.findLikedMoreThan(0));
        System.out.println("Posts created by " + u1);
        System.out.println(postSvc.readAllByAuthor(u1.getId()));

        System.out.println("All posts");
        System.out.println(postSvc.readAll());


        var uname = u1.getUsername();
        System.out.println(usrSvc.readById(uname));
        usrSvc.setOne(uname);
        System.out.println(usrSvc.readById(uname));
    }
}
