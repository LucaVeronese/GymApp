package com.GymApp.GymApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {
	@Query("SELECT g FROM Gym g WHERE g.gymUsername = ?1")
	Gym findGymByUsername(String gymUsername);
}
