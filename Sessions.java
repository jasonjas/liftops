package liftops;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sessions {
	private int id;
	private int value;
	private String startTime;
	private String endTime;
	private String date;
	private String description;
	private int userId;
	private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private static final DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
	private LocalDateTime now = LocalDateTime.now();
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setStartDate() {
		this.startTime = tf.format(now);
	}
	
	public String getStartDate() {
		return startTime;
	}
	
	public void setEndDate() {
		this.endTime = tf.format(now);
	}
	
	public String getEndDate() {
		return endTime;
	}
	
	public void setDate(String date) {
		this.date = df.format(now);
	}
	
	public String getDate() {
		return date;
	}
}
