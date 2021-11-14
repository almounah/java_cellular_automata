/**This Module is used to make Events.
 * It contains many classes :
 *  <ul>
 *      <li> Event an abstract </li>
 *      <li> Event manager that has many events </li>
 *      <li> Event Message to test events later with strings. </li>
 *      <li> Many other classes that extends The abstract Event
 *           like eventBalls, eventgrid ... </li>
 *  </ul>
 * */
package fr.glob.event;

/** EventMessage. */
public class EventMessage extends Event {

    /**The value of the event. */
    private int value;

    /** The constructor.
     * @param dateToPlay : the date of execution.
     * @param eventManager
     * @param value
     * */
    public EventMessage(long dateToPlay,
                        EventManager eventManager,
                        int value) {
        super(dateToPlay, eventManager);
        this.value = value;
    }

    /** The execute method. */
    public void execute() {
        System.out.println("Evenement executé, date="
                            + getDateToPlay() + ", value=" + value);
    }

    /** The toString method.
     * @return String.
     * */
    @Override
    public String toString() {
        return super.toString() + " ,value=" + value;
    }
}
