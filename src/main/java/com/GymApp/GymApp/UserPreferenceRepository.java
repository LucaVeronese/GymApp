package com.GymApp.GymApp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Integer> {
	@Query("SELECT up FROM UserPreference up WHERE up.fitnessLevel = ?1 AND up.daysPerWeek = ?2 AND up.focus = ?3 AND up.goal = ?4")
	List<UserPreference> findByFitnessLevelAndDayPerWeekAndFocusAndGoal(String fitnessLevel, int daysPerWeek, String focus, String goal);
	
	@Query("SELECT up FROM UserPreference up WHERE up.gym= ?1")
	List<UserPreference> findByGym(Gym gym);
}
