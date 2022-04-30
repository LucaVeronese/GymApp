package com.GymApp.GymApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
	@Query("SELECT e FROM Exercise e WHERE e.complexity = ?1 AND e.type = ?2 AND e.focus = ?3")
	Exercise findByComplexityAndTypeAndFocus(String complexity, String type, String focus);
}
