package SelectSlot;

import java.util.ArrayList;

public class Session {
	private String studentId;
	private ArrayList <Integer> selectedSessionId;
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public ArrayList<Integer> getSelectedSessionId() {
		return selectedSessionId;
	}
	public void setSelectedSessionId(ArrayList<Integer> selectedSessionId) {
		this.selectedSessionId = selectedSessionId;
	}
	
	

}
