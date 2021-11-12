package fr.glob.event;

import java.util.Iterator;
import java.util.PriorityQueue;

public class EventManager {
    private long currentDate;
    private PriorityQueue<Event> eventQueue;
    private PriorityQueue<Event> initialQueue;


    public EventManager() {
        this.currentDate = 0;
        this.eventQueue = new PriorityQueue<Event>();
        this.initialQueue = null;
    }

    public void addEvent(Event e){
        boolean b = eventQueue.add(e);
        if(!b) System.err.println("EVENT ERROR: L'evenement ne s'est pas ajouté comme il faut!");
    }

    //Reste t'il des Event?
    public boolean isMoreEvent(){
        return !eventQueue.isEmpty();
    }

    //Reste t'il des Event à executer pour la date courante?
    public boolean isFinished(){
        Event e = eventQueue.peek(); //Ne retire pas l'élément de la queue
        if(e==null) return true; //queue vide
        if(e.getDateToPlay()<=currentDate) return false;
        return true;
    }

    public void next(){
        currentDate++;
        System.out.println("New date! -> "+currentDate);
        while(!isFinished()){
            Event e = eventQueue.poll();
            e.execute();
        }
    }

    //Sauvegarde l'êtat actuel comme état initiale de la queue (necessaire pour restart)
    public void setInitialStatus(){
        if(initialQueue!=null) {
            System.err.println("ERROR : initialQueue est déjà définie!!!");
            return;
        }
        initialQueue = new PriorityQueue<Event>();
        Iterator<Event> it = eventQueue.iterator();
        while(it.hasNext()){
            Event e = it.next();
            initialQueue.add(e);
        }
    }

    public void restart(){
        if(initialQueue==null) {
            System.err.println("ERROR : l'état initiale de la queue d'Event "+
                "n'a jamais été défini! Il faut utiliser 'setInitialStatus()'");
            return;
        }
        currentDate = 0;
        eventQueue.clear();
        Iterator<Event> it = initialQueue.iterator();
        while(it.hasNext()){
            Event e = it.next();
            eventQueue.add(e);
        }
        
    }

}