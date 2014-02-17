package sprint1;

public class Session {
	private int sessionID;
	private String courseID;
	private String name;
	public int duration; // added so that admin knows how long the session is for
	private String startTime;
	private String endTime;
	private String compulsory;
	private String roomID;
	private int timetableSlotID;
	private String frequency;  // Once, Weekly, Fortnightly
	// need freq, when creating lecturer specify then admin can check before allocating timetable slot
	private static int idCount = 0;
	
	public Session(String courseID, String name, int duration, String compulsory, String frequency) {
		sessionID = getNextID();
		this.courseID = courseID;
		this.name = name;
		this.duration = duration;
		startTime = "";
		endTime = "";
		this.compulsory = compulsory;
		this.roomID = "";
		this.timetableSlotID = -1;
		this.frequency = frequency;
	}
	
	public static int getNextID() {
		return ++idCount;
	}

	public int getSessionID() {
		return sessionID;
	}

	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCompulsory() {
		return compulsory;
	}

	public void setCompulsory(String compulsory) {
		this.compulsory = compulsory;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public int getTimetableSlotID() {
		return timetableSlotID;
	}

	public void setTimetableSlotID(int timetableSlotID) {
		this.timetableSlotID = timetableSlotID;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
}
