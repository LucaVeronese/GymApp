package com.GymApp.GymApp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {
	@Query("SELECT p FROM Program p WHERE p.gym= ?1")
	List<Program> findByGym(Gym gym);
}
