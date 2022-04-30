package com.GymApp.GymApp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.util.UriComponentsBuilder;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)

class JUnitGymApp {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private GymRepository repo;

	@Autowired
	private ExerciseRepository exerciseRepo;

	@Autowired
	private UserPreferenceRepository userRepo;

	@MockBean
	private ProgramService service;

	@Test
	public void testCreateUser() {
		Gym gym = new Gym();
		gym.setGymName("Fitness");
		gym.setGymPassword("GGGggg!!1");
		gym.setGymPasswordConfirmed("GGGggg!!1");
		gym.setGymEmail("germana@g.com");
		gym.setGymUsername("Username");
		gym.setEnabled(true);
		gym.setRole("ROLE_USER");

		Gym savedGym = repo.save(gym);

		Gym existGym = entityManager.find(Gym.class, savedGym.getGymEmail());

		assert (gym.getGymEmail()).equals(existGym.getGymEmail());
	}

	@Test
	public void testFindGymByEmail() {
		String email = "gym@email.com";

		Optional<Gym> gym = repo.findByUsername(email);

		assertNotNull(gym);
	}

	@Test
	public void testFindByComplexityAndTypeAndFocus() {
		String complexity = "Beginner";
		String type = "Compound";
		String focus = "Chest";

		Exercise exercise = exerciseRepo.findByComplexityAndTypeAndFocus(complexity, type, focus);

		assertNotNull(exercise);
	}

	@Test
	public void testFindByFitnessLevelAndDayPerWeekAndFocusAndGoal() {
		String fitnessLevel = "Beginner";
		int daysPerWeek = 3;
		String focus = "Upper";
		String goal = "Gain";

		List<UserPreference> list = userRepo.findByFitnessLevelAndDayPerWeekAndFocusAndGoal(fitnessLevel, daysPerWeek,
				focus, goal);

		assertNotNull(list);
	}
}
