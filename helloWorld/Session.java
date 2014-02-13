package us14;
import java.util.*;

public class Session {
	private ArrayList<SessionItem> session;
	
	public Session () {
		session = new ArrayList<>();
	}
	public ArrayList<SessionItem> getSession() {
		return session;
	}
	public int sessionSize() {
		return session.size();
	}

}
