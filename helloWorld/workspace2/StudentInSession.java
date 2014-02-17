package sprint1;

public class StudentInSession {
	private int sessionID;
	private int GUID;
	
	public StudentInSession(int sessionID, int GUID) {
		this.sessionID = sessionID;
		this.GUID = GUID;
	}

	public int getSessionID() {
		return sessionID;
	}

	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}

	public int getGUID() {
		return GUID;
	}

	public void setGUID(int gUID) {
		GUID = gUID;
	}
	
	
}
