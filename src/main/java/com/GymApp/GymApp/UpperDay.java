package com.GymApp.GymApp;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpperDay extends Day{
	private List<Exercise> chestEx;
	private List<Exercise> bicepEx;
	//private String complexity;
	//private String type;
	
	/*@Autowired
	ExerciseRepository repo;
	
	@Autowired
	ProgramService programService;
	
	Random rand = new Random();
	*/
	
	/*public UpperDay(String type) {
		this.type = type;
	}*/
	
	public UpperDay() {}

	/*public List<Exercise> getChestEx(String complexity, String type) {
		return repo.findByComplexityAndTypeAndChest(complexity, type);
	}*/

	/*public Exercise getChestEx(String complexity, String type) {
		List<Exercise> list = repo.findByComplexityAndTypeAndChest(complexity, type);
		return list.get(rand.nextInt(list.size()));
	}*/
	
	/*public Exercise getChestEx(String complexity, String type) {
		return programService.chestExercise(complexity, type);
	}*/
	
	/*public void setChestEx(List<Exercise> chestEx) {
		this.chestEx = chestEx;
	}*/

	/*public List<Exercise> getByceptsEx(String complexity, String type) {
		//return byceptsEx;
		return repo.findByComplexityAndTypeAndBycepts(complexity, type);
	}*/
	
	/*public Exercise getByceptsEx(String complexity, String type) {
		List<Exercise> list = repo.findByComplexityAndTypeAndBycepts(complexity, type);
		return list.get(rand.nextInt(list.size()));
	}

	public void setByceptsEx(List<Exercise> byceptsEx) {
		this.byceptsEx = byceptsEx;
	}
*/
	@Override
	public String toString() {
		return "UpperDay [chestEx=" + chestEx + ", byceptsEx=" + bicepEx + "]";
	}
	
	
}
