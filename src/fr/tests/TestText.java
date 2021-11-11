package fr.tests;

import fr.balls.*;
import fr.glob.event.*;


public class TestText {

    private static void testBalls(){
        Balls b = new Balls(10, 100, 100);
        System.out.println("Voici mes balls -> " + b);

        System.out.println("Mooving balls ...");
        b.translate(2, 10);
        System.out.println("Mooved balls là -> " + b);
        System.out.println("Copyed balls là -> " + b.toStringCP());

        System.out.println("Reinit ...");
        b.reInit();
        System.out.println("Voici mes balls -> " + b);
    }

    private static void testEvents(){
        System.out.println("Testing Events...");
        EventManager em = new EventManager();
        em.addEvent(new EventTest(5, 55));
        em.addEvent(new EventTest(3, 33));
        em.addEvent(new EventTest(4, 44));
        em.addEvent(new EventTest(4, 444));
        while(em.isEvent()){
            em.next();
        }
        System.out.println("No more event\nDone testing Events!");
    }
    public static void main(String[] args) {
        if(args.length == 0){ 
            System.out.println("ERROR : Missing args!");
            return;
        }
        if(args[0].equals("balls")) testBalls();
        if(args[0].equals("events")) testEvents();
    }
}
