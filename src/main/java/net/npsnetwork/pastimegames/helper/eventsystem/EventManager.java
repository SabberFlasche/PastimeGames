package net.npsnetwork.pastimegames.helper.eventsystem;

import net.npsnetwork.pastimegames.exceptions.EventListenerAlreadyBoundException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class EventManager<T> {
    private ArrayList<EventListener<T>> events = new ArrayList<>();

    /**
     * Subscribes a Class to an event. Your class must implement {@link EventListener}.
     * @param listener Class to subscribe this event to
     * @throws EventListenerAlreadyBoundException thrown when the class is already subscribed.
     */
    public void subscribe(@NotNull EventListener<T> listener) throws EventListenerAlreadyBoundException {
        if (events.contains(listener)) {
            throw new EventListenerAlreadyBoundException();
        }
        events.add(listener);
    }

    /**
     *Unsubscribes an Event
     * @param listener listener to remove
     * @return true if the listener got removed
     * @see {@link ArrayList}
     */
    public boolean unSubscribe(@NotNull EventListener<T> listener) {
        return events.remove(listener);
    }

    /**
     *Calls all listeners of this event
     * @param arg data to parse
     */
    public void call(@NotNull T arg) {
        for (EventListener<T> event : events) {
            System.out.println("call");
            event.onCall(arg);
        }
    }
}
