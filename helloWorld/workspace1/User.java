package us14;
import java.util.*;

public class User {
	private int ID;
	private String name;
	private String type;	// student, lecturer
	private ArrayList<Integer> lesson;
	private ArrayList<Integer> tutorLesson;	// for student tutor only
	
	public User(int ID, String name, String type) {
		this.ID = ID;
		this.name = name;
		this.type = type;
		lesson = new ArrayList<>();
		tutorLesson = new ArrayList<>();
	}
	
	public User(User user){
		ID = user.getID();
		name = user.getName();
		type = user.getType();
		lesson = user.getLesson();
		tutorLesson = user.getTutorLesson();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public ArrayList<Integer> getLesson() {
		return lesson;
	}
	
	public ArrayList<Integer> getTutorLesson() {
		return tutorLesson;
	}

}
