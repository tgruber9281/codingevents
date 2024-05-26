package com.launchcode.codingevents.data;

import com.launchcode.codingevents.models.EventCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Trevor Gruber
 */
@Repository
public interface EventCategoryRepository extends CrudRepository<EventCategory,Integer> {
}
