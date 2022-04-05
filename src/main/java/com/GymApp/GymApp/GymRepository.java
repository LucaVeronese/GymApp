package com.GymApp.GymApp;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {
	@Query("SELECT g FROM Gym g WHERE g.gymEmail = ?1")
	Optional<Gym> findByUsername(String email);
	
	@Query("SELECT g FROM Gym g WHERE g.gymEmail = ?1")
	Gym findByEmail(@Param("email") String email);
	
	@Query("SELECT g FROM Gym g WHERE g.gymEmail = ?1 AND g.gymPassword = ?2")
	Gym findByEmailAndPassword(String email, String password);
	
}
