package fr.glob.event;

import java.util.PriorityQueue;

public class EventManager {
    private long currentDate;
    private PriorityQueue<Event> elemQueue;


    public EventManager() {
        this.currentDate = 0;
        this.elemQueue = new PriorityQueue<Event>();
    }

    public void addEvent(Event e){
        boolean b = elemQueue.add(e);
        if(!b) System.err.println("EVENT ERROR: L'evenement ne s'est pas ajouté comme il faut!");
    }

    //Reste t'il des Event?
    public boolean isEvent(){
        return !elemQueue.isEmpty();
    }

    //Reste t'il des Event à executer pour la date courante?
    public boolean isFinished(){
        Event e = elemQueue.peek(); //Ne retire pas l'élément de la queue
        if(e==null) return true; //queue vide
        if(e.getDateToPlay()<=currentDate) return false;
        return true;
    }

    public void next(){
        currentDate++;
        System.out.println("New date! -> "+currentDate);
        while(!isFinished()){
            Event e = elemQueue.poll();
            e.execute();
        }
    }

    public void restart(){
    }

}