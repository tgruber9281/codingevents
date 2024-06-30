package com.launchcode.codingevents.data;

import com.launchcode.codingevents.models.User;
import com.launchcode.codingevents.models.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Trevor Gruber
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
