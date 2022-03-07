package com.GymApp.GymApp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
	@Query("SELECT e FROM Exercise e WHERE e.complexity = ?1 AND e.type = ?2 AND e.chest = 1")
	List<Exercise> findByComplexityAndTypeAndChest(String complexity, String type);
	
	@Query("SELECT e FROM Exercise e WHERE e.complexity = ?1 AND e.type = ?2 AND e.bycepts = 1")
	List<Exercise> findByComplexityAndTypeAndBycepts(String complexity, String type);
}
