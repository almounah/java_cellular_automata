package fr.glob.event;

public class EventMessage extends Event {
    private int value;

    public EventMessage(long dateToPlay, EventManager eventManager, int value) {
        super(dateToPlay, eventManager);
        this.value = value;
    }

    public void execute() {
        System.out.println("Evenement executé, date="+getDateToPlay()+", value="+value);
    }

    @Override
    public String toString() {
        return super.toString() + " ,value="+value;
    }
}
