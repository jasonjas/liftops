package liftops;


public class Workouts {
	private String workout;
	private String category;
	private int id;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setWorkout(String workout) {
		this.workout = workout;
	}
	
	public String getWorkout() {
		return workout;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}
}
