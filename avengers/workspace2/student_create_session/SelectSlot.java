package SelectSlot;

import java.util.*;

import DBMS.DBMS;

public class SelectSlot {

	public ArrayList <Boolean> approval (Session a)
	{
		ArrayList <Boolean> message = new ArrayList<Boolean>();
		boolean results ;
		
		int noOfSessionAttend = a.getSelectedSessionId().size();
		
		for(int i = 0; i < noOfSessionAttend; i++)
		{
			results = attemptToUpdateSession(a.getSelectedSessionId().get(i), a.getStudentId());
			message.add(results);
		}
		
		return message;
	}

	private boolean attemptToUpdateSession(int session, String studentId) {
		DBMS attemptApproval = new DBMS();
		ArrayList<Object> results = attemptApproval.retrieveResult("select sis.seatsTaken from (Select sessionID sessionID, count(GUID) seatsTaken from studentInSession where sessionID = " + session + " group by " + session + ") sis, session s, room r where (r.capacity - sis.seatsTaken) > 0 and sis.sessionID = s.sessionID and s.roomID = r.roomID;");
		
		if(results.size() == 0)
		{
			return false;
		}
		
		else
		{
			attemptApproval.updateResult("INSERT INTO StudentInSession (sessionID, GUID) VALUES ('"+ session +" , " + studentId + ");");
			return true;
		}
		
	}

}
