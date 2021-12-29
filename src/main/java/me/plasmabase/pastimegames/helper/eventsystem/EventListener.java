package me.plasmabase.pastimegames.helper.eventsystem;

import org.jetbrains.annotations.NotNull;

public interface EventListener<T>{
    /**
     * Gets called when subscribed event is called
     * @param arg data
     */
    void onCall(@NotNull T arg);
}
