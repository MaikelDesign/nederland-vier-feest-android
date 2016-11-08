package nl.fressh.nederlandviertfeest.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruben on 08-11-16.
 */

public class Response {
    private List<EventDates> events = new ArrayList<EventDates>();

    public List<EventDates> getEvents() {
        return events;
    }
}
