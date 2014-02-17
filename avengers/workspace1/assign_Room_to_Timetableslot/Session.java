package assign_Room_to_Timetableslot;

public class Session {
	private int sessionID;
	private int courseID;
	private String name;
	private String starttime;
	private String endtime;
	private boolean compulsory;
	private int roomID;
	private int timetableslotID;
	private int GUID;
	
	public Session(int sessionID, int courseID, String name, String endtime, 
			String starttime, boolean compulsory, int roomID, int timetableslotID, 
			int GUID) {
		setSessionID(sessionID);
		setCourseID(courseID);
		setName(name);
		setStarttime(endtime);
		setEndtime(starttime);
		setCompulsory(compulsory);
		setRoomID(roomID);
		setTimetableslotID(timetableslotID);
		setGUID(GUID);
	}
	
	public String getSessionID() {
		return sessionID == -1 ? "Empty Session Slot" : Integer.toString(sessionID);
	}
	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String isCompulsory() {
		return compulsory == true ? "Compulsory" : "Not Compulsory";
	}
	public void setCompulsory(boolean compulsory) {
		this.compulsory = compulsory;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public int getTimetableslotID() {
		return timetableslotID;
	}
	public void setTimetableslotID(int timetableslotID) {
		this.timetableslotID = timetableslotID;
	}
	public int getGUID() {
		return GUID;
	}
	public void setGUID(int gUID) {
		GUID = gUID;
	}
}
