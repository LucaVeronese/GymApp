package com.GymApp.GymApp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "program")
public class Program {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int programId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
	private Gym gym;

	public Program (Gym gym) {
		this.gym = gym;
	}
	
	public Program() {
	}
	
	public Gym getGym() {
		return gym;
	}
	
	public void setGym(Gym gym) {
		this.gym = gym;
	}
}
