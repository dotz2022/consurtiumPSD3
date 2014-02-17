package sprint1;
import java.util.*;

public class Main {
	// hardcoded data to test, change to SQL queries when using DBMS
	static ArrayList<User> user = new ArrayList<>();
	static ArrayList<Course> course = new ArrayList<>();
	static ArrayList<CourseItem> courseItem = new ArrayList<>();
	static ArrayList<Session> session = new ArrayList<>();

	public static void main(String[] args) {
		//hardcoded data
		User a1 = new User(1001, "admin", "Admin Name");
		User l1 = new User(2001, "lecturer", "Dr Lect A");
		User t1 = new User(3001, "tutor", "Tutor A");
		User s1 = new User(4001, "student", "Student A");
		User s2 = new User(4002, "student", "Student B");
		User s3 = new User(4003, "student", "Student C");
		user.add(a1);
		user.add(l1);
		user.add(t1);
		user.add(s1);
		user.add(s2);
		user.add(s3);
		Course c1 = new Course("PSD", "Professional Software Development");
		Course c2 = new Course("DIM", "Distributed Information Management");
		course.add(c1);
		course.add(c2);
		CourseItem ci1 = new CourseItem("PSD", 4001);
		CourseItem ci2 = new CourseItem("PSD", 4002);
		CourseItem ci3 = new CourseItem("PSD", 4003);
		CourseItem ci4 = new CourseItem("DIM", 4001);
		courseItem.add(ci1);
		courseItem.add(ci2);
		courseItem.add(ci3);
		courseItem.add(ci4);
		
		loginMenu();

	}
	
	public static void loginMenu() {
		// need to authenticate user
		// to be done when checked with DBMS
		Scanner sc = new Scanner(System.in);
		System.out.println("Please key in your GUID"); 
		int loginGUID = sc.nextInt();
		System.out.println("Please key in your password");
		String loginPW = sc.next();
		
		for (User u : user) {
			if (u.getGUID() == loginGUID && u.getPassword().equals(loginPW)) {
				if (u.getRole().equals("admin")) {
					adminMenu();
				} else if (u.getRole().equals("lecturer")) {
					lectMenu();
				} else if (u.getRole().equals("student")) {
					studMenu();
				} else if (u.getRole().equals("tutor")) {
					tutorMenu();
				} else {
					System.out.println("Wrong GUID or password");
					loginMenu();
				}
			}
		}
		sc.close();
	}
	
	public static void adminMenu() {
		System.out.println("Logged in as admin.");
		System.out.println("");
		System.out.println("Select your choice by keying in with the number that represents it.");
		System.out.println("1. \tCreate timetable slot for session.");
		System.out.println("2. \tAdd Session to Course");
		System.out.println("3. \tQuit");
		
		Scanner sc = new Scanner(System.in);
		int select2 = sc.nextInt();
		switch (select2) {
		case 1: // USER STORY GIVEN IN EMAIL
			int countSes = 0;
			System.out.println("Viewing all sessions without timetable slot.");
			System.out.println("");
			System.out.println("WEEKLY");
			System.out.println("Session ID \tSession Name \tDuration");
			for (Session s : session) {
				if (s.getStartTime().equals("") && s.getEndTime().equals("") && s.getFrequency().equals("Weekly")) {
					System.out.println(s.getSessionID() + "\t\t" + s.getName() + "\t" + s.getDuration() + "h");
					countSes++;
				}
			}
			
			System.out.println("");
			System.out.println("FORTNIGHTLY");
			System.out.println("Session ID \tSession Name \tDuration");
			for (Session s : session) {
				if (s.getStartTime().equals("") && s.getEndTime().equals("") && s.getFrequency().equals("Fortnightly")) {
					System.out.println(s.getSessionID() + "\t\t" + s.getName() + "\t" + s.getDuration() + "h");
					countSes++;
				}
			}
			
			System.out.println("");
			System.out.println("ONCE");
			System.out.println("Session ID \tSession Name \tDuration");
			for (Session s : session) {
				if (s.getStartTime().equals("") && s.getEndTime().equals("") && s.getFrequency().equals("Once")) {
					System.out.println(s.getSessionID() + "\t\t" + s.getName() + "\t" + s.getDuration() + "h");
					countSes++;
				}
			}
			
			System.out.println("Total number of sessions that need creation of timetable slot: " + countSes);
			
			// Choose session to create timetable slot
			System.out.println("");
			System.out.println("Key in the session ID for the session you are creating timetable slot for.");
			int sesID = sc.nextInt();
			System.out.println("Which semester year?");
			// for DBMS, query to get distinct year in calendar & output for user to select
			// in calendar, have to use populateCalendarYear() to populate entire year thingy
			// includes 10 weeks and their days
			int year = sc.nextInt();
			System.out.println("Which semester? 1 or 2?");
			int sem = sc.nextInt();
			sc.close();
			// To view all slots available
			// Query will be easier
			// Weekly 	- loop through all weeks where year, sem, day, timeslot is same
			//			- check all 10 weeks with those same and ensure not added in timetable
			// Fortnightly
			//			- same as weekly just that between odd and even week
			// Once		- ask user for week and day before displaying timeslot
			// format of timetable ID is yyyy-s-ww-d
			// yyyy is 1314 where year academic year is 2013/2014
			
			
			/*for (int w=1; w<10; w++) {
				String strWk = "";
				if (w<10) {
					strWk = "0" + Integer.toString(w);
				} else {
					strWk = Integer.toString(w);
				}
				
				for (int d=1; d<5; d++) {
					for (int s=8,e=9; e<20; s++,e++) { 
						String ttID = "";
						String calID = year + "-" + sem + "-" + strWk + "-" + d;
						if (e < 10) {
							ttID = "0" + Integer.toString(s) + "0" + Integer.toString(e);
						} else if (s < 10) { //s=9, e=10
							ttID = "0" + Integer.toString(s) + Integer.toString(e);
						} else { // s and e 10 and above
							ttID = Integer.toString(s) + Integer.toString(e);
						}
					}
				}
			}*/
			
			break;
		case 2: // USER STORY 8
			// yet to hard code
			Scanner sc1 = new Scanner(System.in);
			int countSes2 = 0;
			System.out.println("Viewing all sessions without rooms.");
			System.out.println("");
			for (Session s : session) {
				if (s.getRoomID().equals("")) {
					System.out.println(s.getSessionID() + "\t\t" + s.getName());
					countSes2++;
				}
			}
			System.out.println("Total number of sessions that need creation of timetable slot: " + countSes2);
			System.out.println("");
			System.out.println("Key in the session ID for the session you are allocating room for.");
			int sesID1 = sc.nextInt();
			System.out.println("");
			System.out.println("Viewing all rooms");
			// Query  to show all room
			// Ask user to choose room
			// set sessionID's room to chosen room
			break;
		case 3:
			sc.close();
			System.out.println("You are now exiting the programme.");
			break;
		}
	}
	
	public static void studMenu() {
		System.out.println("Logged in as student.");
		System.out.println("");
		System.out.println("Select your choice by keying in with the number that represents it.");
		System.out.println("1. \tBook timetable slot");
		System.out.println("2. \tCheck if signed up for compulsory session");
		System.out.println("3. \tQuit");
		
		Scanner sc = new Scanner(System.in);
		int select = sc.nextInt();
		switch (select) {
		case 1: // USER STORY 11
			// show all items from Timetable
			// query to get more of session information
			// user key in sessionID that he/she wants to take
			// update into CourseItem
			break;
		case 2: // USER STORY 12
			// check from CourseItem with GUID
			// get all courseID and query out to see all compulsory and if taken
			break;
		case 3:
			sc.close();
			System.out.println("You are now exiting the programme.");
			break;
		}
	}
	
	public static void lectMenu() {
		System.out.println("Logged in as lecturer.");
		System.out.println("");
		System.out.println("Select your choice by keying in with the number that represents it.");
		System.out.println("1. \tImport Course");
		System.out.println("2. \tAdd Session to Course");
		System.out.println("3. \tView Session Information"); // to be changed, us14
		System.out.println("4. \tQuit");
		
		Scanner sc = new Scanner(System.in);
		int select2 = sc.nextInt();
		switch (select2) {
		case 1: // USER STORY 1
			// Import courses, to be implemented with DBMS
			// Courses and CourseItem are hardcoded to test the programme
			System.out.println("Import courses, exiting the programme.");
			System.exit(0);
			break;
		case 2: // USER STORIES 2 AND 4
			System.out.println("Key in the course ID you are adding the session to");
			String courseID2 = sc.next();
			System.out.println("What type of session are you adding? Lecture, Lab or Tutorial?");
			String sesType2 = sc.next();
			System.out.println("What is the duration of the session in hour(s)?");
			int durSes2 = sc.nextInt();
			System.out.println("Is this a compulsory session? Y or N.");
			String comp2 = sc.next();
			System.out.println("How frequent is this session? Once, Weekly, Fortnightly");
			String freq2 = sc.next();
			sc.close();
			
			// Not much validation is done here, to be done with DBMS
			// courseID check in Course database (not checked here)
			// session check that is either lect, lab or tutorial (checked)
			// check if is compulsory (checked)
			if ((sesType2.equals("Lecture") || sesType2.equals("Lab") || sesType2.equals("Tutorial"))
					&& (comp2.equals("Y") || comp2.equals("N"))
					&& (freq2.equals("Once") || freq2.equals("Weekly") || freq2.equals("Fortnightly"))) {
				Session s = new Session(courseID2, courseID2 + " " + sesType2, durSes2, comp2, freq2);
				session.add(s);
				System.out.println("Session added.");
			} else {
				System.out.println("Wrong information has been keyed.");
			}
			
			break;
		case 3: // USER STORY 14, confused with what to show already
			
			break;
		case 4:
			sc.close();
			System.out.println("You are now exiting the programme.");
			break;
		}
		
		
	}
	
	public static void tutorMenu() {
		System.out.println("Logged in as tutor.");
	}

}
