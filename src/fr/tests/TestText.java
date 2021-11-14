package fr.tests;

import fr.balls.*;
import fr.glob.event.*;

/**
 * TestText.
 * Is used to test the text output.
 * */
public class TestText {

    /** To test the balls. */
    private static void testBalls(){
        Balls b = new Balls(10, 100, 100);
        System.out.println("Voici mes balls -> " + b);

        System.out.println("Mooving balls ...");
        b.update();
        System.out.println("Mooved balls là -> " + b);
        System.out.println("Copyed balls là -> " + b.toStringCP());

        System.out.println("Reinit ...");
        b.reInit();
        System.out.println("Voici mes balls -> " + b);
    }

    /** To test the events. */
    private static void testEvents(){
        System.out.println("===== Testing Events =====");
        EventManager em = new EventManager();
        em.addEvent(new EventMessage(5, em, 55));
        em.addEvent(new EventMessage(3, em, 33));
        em.addEvent(new EventMessage(4, em, 44));
        em.addEvent(new EventMessage(4, em, 444));
        em.setInitialStatus();
        while (em.isMoreEvent()) {
            em.next();
        }
        System.out.println("===== No more event | Restarting =====");
        em.restart();
        while (em.isMoreEvent()) {
            em.next();
        }
        System.out.println("===== Done testing ====");
    }

    /** The main. */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("ERROR : Missing args!");
            return;
        }

        if (args[0].equals("balls")) {
            testBalls();
        }

        if(args[0].equals("events")) {
            testEvents();
        }

    }
}
