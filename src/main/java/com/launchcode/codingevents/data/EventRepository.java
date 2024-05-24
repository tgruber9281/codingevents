package com.launchcode.codingevents.data;

import com.launchcode.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Trevor Gruber
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
