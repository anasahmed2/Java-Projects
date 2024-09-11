package model;

import java.util.Calendar;
import java.util.Date;


/**
 * Represents a Chip Counter event.
 */
// This Event class create an event with a description with the current
// date and time logged

public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    /**
     * Creates an event with the given description
     * and the current date/time stamp.
     * @param description  a description of the event
     */
    // MODIFIES: data logged
    // EFFECTS: initializes date logeed field to the current date and time
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    /**
     * Gets the date of this event (includes time).
     * @return  the date of the event
     */

    // REQUIRES: dateLogged != null
    // EFFECTS: returns dateLogged
    public Date getDate() {
        return dateLogged;
    }

    /**
     * Gets the description of this event.
     * @return  the description of the event
     */

    // REQUIRES: description != null
    // EFFECTS: returns description
    public String getDescription() {
        return description;
    }


    // EFFECTS: returns the true or false
    // based on whether the current date and time
    // equals the date and time of currentEvent
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;

        }

        if (other.getClass() != this.getClass()) {
            return false;

        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                && this.description.equals(otherEvent.description));
    }


    // EFFECTS: returns the hashCode dateLogged and description
    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    // EFFECTS: returns the date and time plus the description of logged event
    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
