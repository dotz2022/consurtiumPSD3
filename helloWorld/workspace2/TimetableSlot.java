package sprint1;

public class TimetableSlot { // slots are from 8am to 8pm (0800 - 2000)
	private String timetableSlotID; // if slot is 9-10am ID is 0910
	private String startTime; // e.g. 0900
	private String endTime; // e.g. 1000
	//private int sessionID;
	
	public String generateTimetableSlotID (String start, String end) {
		return (start + end);
	}
	
	public void populateTimetableSlot() {
		//set start time as 8am and end time as 9am
		//for end < 8pm
		for (int s=8,e=9; e<20; s++,e++) { 
			if (e < 10) {
				timetableSlotID = generateTimetableSlotID("0" + Integer.toString(s), "0" + Integer.toString(e));
				startTime = "0" + Integer.toString(s) + "00";
				endTime = "0" + Integer.toString(e) + "00";
			} else if (s < 10) { //s=9, e=10
				timetableSlotID = generateTimetableSlotID("0" + Integer.toString(s), Integer.toString(e));
				startTime = "0" + Integer.toString(s) + "00";
				endTime = Integer.toString(e) + "00";
			} else { // s and e 10 and above
				timetableSlotID = generateTimetableSlotID(Integer.toString(s), Integer.toString(e));
				startTime = Integer.toString(s) + "00";
				endTime = Integer.toString(e) + "00";
			}
		}
	}
	
	public String getTimetableSlotID() {
		return timetableSlotID;
	}
	public void setTimetableSlotID(String timetableSlotID) {
		this.timetableSlotID = timetableSlotID;
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
	
	
}
