package sprint1;

public class Calendar {
	private String calendarID; // yyyy-s-ww-d
	private int year;// format will be for example year of 2013/2014 so ID will be 1314
	private int semester; // semester 1 or 2
	private int week; // set as 10 weeks
	private int day; // Mon - Fri
	
	public Calendar () {
	}
	
	public String generateCalendarID(int year, int semester, int week, int day) {
		String wk = Integer.toString(week);
		String id = "";
		
		if (week < 10) { // can also be != just that in case want to change no of weeks to 12 etc
			wk = "0" + wk; // so that 1 becomes 01
		}
		id = year + "-" + semester + "-" + wk + "-" + day;
		return id;
	}
	
	// populate all calendar items for the year
	public void populateCalendarYear(int year) {
		for (int s=1; s<2; s++) {
			for (int w=1; w<10; w++) {
				for (int d=1; d<5; d++) {
					Calendar c = new Calendar();
					c.setYear(year);
					c.setSemester(s);
					c.setWeek(w);
					c.setDay(d);
					c.setCalendarID(generateCalendarID(year,s,w,d));
				}
			}
		}
	}
	
	public String getCalendarID() {
		return calendarID;
	}
	public void setCalendarID(String calendarID) {
		this.calendarID = calendarID;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	
	
	
}
