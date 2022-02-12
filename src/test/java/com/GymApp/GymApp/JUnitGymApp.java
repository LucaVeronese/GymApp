package com.GymApp.GymApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class JUnitGymApp {

	@Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private GymRepository repo;
    
    @Test
    public void testCreateUser() {
        Gym gym = new Gym();
        gym.setGymName("Fitness");
        gym.setGymPassword("Password");
        gym.setGymEmail("gym@email.com");
        gym.setGymUsername("Username");
         
        Gym savedGym = repo.save(gym);
         
        Gym existGym = entityManager.find(Gym.class, savedGym.getGymId());
         
        assert(gym.getGymEmail()).equals(existGym.getGymEmail());
        
    }
    
    @Test
    public void testFindGymByUsername() {
    	String username = "Username";
    	
    	Gym gym = repo.findGymByUsername(username);
    	
    	assertNotNull(gym);
    }
}
