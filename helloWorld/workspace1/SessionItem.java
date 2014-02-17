package us14;

public class SessionItem {
	private int sessionID;
	private String sessionCode;
	private String type;
	private String time;
	private String location;
	
	public SessionItem(int sessionID, String sessionCode, String type, String time, String location){
		this.sessionID = sessionID;
		this.sessionCode = sessionCode;
		this.type = type;
		this.time = time;
		this.location = location;
	}
	public SessionItem(SessionItem s) {
		sessionID = s.getSessionID();
		sessionCode = s.getSessionCode();
		type = s.getType();
		time = s.getTime();
		location = s.getLocation();
	}
	public int getSessionID() {
		return sessionID;
	}
	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}
	public String getSessionCode() {
		return sessionCode;
	}
	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
