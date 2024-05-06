package com.launchcode.codingevents.data;

import com.launchcode.codingevents.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Trevor Gruber
 */
public class EventData {

/** fields */

    private static final Map<Integer, Event> events = new HashMap<>();

/** Custom methods */

    public static Collection<Event> getAll(){
        return events.values();
    }
    
    public static Event getById(int id){
        return events.get(id);
    }
    
    public static void add(Event event){
        events.put(event.getId(), event);
    }
    
    public static void remove(int id){
        events.remove(id);
    }
    
}
