package fr.glob.event;

public class EventTest extends Event {
    private int value;

    public EventTest(long dateToPlay, EventManager eventManager, int value) {
        super(dateToPlay, eventManager);
        this.value = value;
    }

    public void execute(){
        System.out.println("Evenement executé, date="+getDateToPlay()+", value="+value);
    }

    @Override
    public String toString() {
        return super.toString() + " ,value="+value;
    }
}
