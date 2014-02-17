package sprint1;

public class CourseItem {
	private String courseID;
	private int GUID;
	
	public CourseItem(String courseID, int GUID) {
		this.courseID = courseID;
		this.GUID = GUID;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public int getGUID() {
		return GUID;
	}

	public void setGUID(int gUID) {
		GUID = gUID;
	}
	
	
}
