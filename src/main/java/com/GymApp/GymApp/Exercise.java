package com.GymApp.GymApp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table (name = "exercise")
public class Exercise {
	
	@Id
	@Column(name = "exercise_id")
	private int exerciseId;
	
	@Column(name = "exercise_name")
	private String exerciseName;
	
	@Column(name = "complexity")
	private String complexity;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "bycepts")
	@Type(type = "true_false")
	private Boolean bycepts;
	
	@Column(name = "chest")
	@Type(type = "true_false")
	private Boolean chest;
	
	public Exercise() {}

	public int getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public String getComplexity() {
		return complexity;
	}

	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isBycepts() {
		return bycepts;
	}

	public void setBycepts(boolean bycepts) {
		this.bycepts = bycepts;
	}

	public boolean isChest() {
		return chest;
	}

	public void setChest(boolean chest) {
		this.chest = chest;
	}

	@Override
	public String toString() {
		return "Exercise [exerciseId=" + exerciseId + ", exerciseName=" + exerciseName + ", complexity=" + complexity
				+ ", type=" + type + ", bycepts=" + bycepts + ", chest=" + chest + "]";
	}
}
