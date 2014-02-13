package us14;
import java.util.*;

public class Main {
	static ArrayList<User> user = new ArrayList<>();
	static Session ses = new Session();
	static ArrayList<String> compulsory = new ArrayList<>();

	public static void main(String[] args) {
		//create session
		SessionItem psdlect1 = new SessionItem(1, "PSD", "Lecture", "0900", "LR01");
		ses.getSession().add(psdlect1);
		SessionItem psdlect2 = new SessionItem(2, "PSD", "Lecture", "0900", "LR02");
		ses.getSession().add(psdlect2);
		SessionItem psdlab1 = new SessionItem(3, "PSD", "Lab", "1300", "LB01");
		ses.getSession().add(psdlab1);
		SessionItem psdlab2 = new SessionItem(4, "PSD", "Lab", "1300", "LB02");
		ses.getSession().add(psdlab2);

		SessionItem dimlect1 = new SessionItem(5, "DIM", "Lecture", "0900", "LR01");
		ses.getSession().add(dimlect1);
		SessionItem dimlab1 = new SessionItem(6, "DIM", "Lab", "1300", "LB01");
		ses.getSession().add(dimlab1);

		//Student attending PSD
		User s1 = new User(1001, "studA", "student");
		User s2 = new User(1002, "studB", "student");
		User s3 = new User(1003, "studC", "student");
		User s4 = new User(1004, "studD", "student");
		User s5 = new User(1005, "studE", "student");
		user.add(s1);
		user.add(s2);
		user.add(s3);
		user.add(s4);
		user.add(s5);
		s1.getLesson().add(1);
		s1.getLesson().add(3);
		s2.getLesson().add(1);
		s2.getLesson().add(3);
		s3.getLesson().add(1);
		s3.getLesson().add(3);
		s4.getLesson().add(1);
		s4.getLesson().add(3);
		s5.getLesson().add(1);
		s5.getLesson().add(3);

		User s6 = new User(1006, "studF", "student");
		User s7 = new User(1007, "studG", "student");
		User s8 = new User(1008, "studH", "student");
		User s9 = new User(1009, "studI", "student");
		User s10 = new User(1010, "studJ", "student");
		user.add(s6);
		user.add(s7);
		user.add(s8);
		user.add(s9);
		user.add(s10);
		s6.getLesson().add(2);
		s6.getLesson().add(4);
		s7.getLesson().add(2);
		s7.getLesson().add(4);
		s8.getLesson().add(2);
		s8.getLesson().add(4);
		s9.getLesson().add(2);
		s9.getLesson().add(4);
		s10.getLesson().add(2);
		s10.getLesson().add(4);

		//Student tutoring PSD and attending DIM
		User t1 = new User(1011, "studK", "student");
		User t2 = new User(1012, "studL", "student");
		User t3 = new User(1013, "studM", "student");
		user.add(t1);
		user.add(t2);
		user.add(t3);
		t1.getLesson().add(5);
		t1.getLesson().add(6);
		t1.getTutorLesson().add(3);
		t2.getLesson().add(5);
		t2.getLesson().add(6);
		t2.getTutorLesson().add(3);
		t3.getLesson().add(5);
		t3.getLesson().add(6);
		t3.getTutorLesson().add(4);

		//Lecturer
		User l1 = new User(2001, "lectA", "lecturer");
		User l2 = new User(2002, "lectB", "lecturer");
		user.add(l1);
		user.add(l2);
		l1.getLesson().add(1);
		l1.getLesson().add(3);
		l2.getLesson().add(2);
		l2.getLesson().add(4);

		compulsory.add("PSD Lecture");
		compulsory.add("PSD Lab");
		compulsory.add("NS3 Lecture");
		compulsory.add("NS3 Lab");
		compulsory.add("OS3 Lecture");
		compulsory.add("OS3 Lab");

		mainMenu();

	}

	public static void mainMenu(){
		Scanner sc = new Scanner(System.in);
		System.out.println("MAIN MENU");
		System.out.println("Signed in as?");
		boolean foundLect = false;
		boolean foundStud = false;
		String login = sc.next();
		int userIndex = -1;
		for (User u : user) {
			if (u.getName().equals(login)){
				if (u.getType().equals("lecturer")){
					foundLect = true;
					for (int i =0; i<user.size(); i++) {
						if (user.get(i).getName().equals(login)) {
							userIndex = i;
						}
					}
					break;
				} else if (u.getType().equals("student")) {
					foundStud = true;
					for (int i =0; i<user.size(); i++) {
						if (user.get(i).getName().equals(login)) {
							userIndex = i;
						}
					}
					break;
				}
				break;
			} 
		}
		if (foundLect == true){
			//System.out.println("LECTURER FOUND");
			System.out.println("Logged in as: " + login);
			System.out.println("");
			System.out.println("All Sessions");

			for (SessionItem s : ses.getSession()) {
				for (int i=0; i<user.get(userIndex).getLesson().size();i++){


					if (s.getSessionID() == user.get(userIndex).getLesson().get(i)) {
						System.out.println("->\t" + s.getSessionCode() + " " + s.getType());
						System.out.println("\t" + s.getTime() + " at " + s.getLocation());
						System.out.print("\t" + "Student(s): ");
						for (User st : user) {
							for (int a=0; a<st.getLesson().size();a++)
								if (s.getSessionID() == st.getLesson().get(a) && st.getType().equals("student")) {
									System.out.print(st.getName() + ", ");
								}
						}
						System.out.println("");
						System.out.print("\t" + "Tutor(s): ");
						for (User tut : user) {
							for (int a=0; a<tut.getTutorLesson().size();a++)
								if (s.getSessionID() == tut.getTutorLesson().get(a)) {
									System.out.print(tut.getName() + ", ");
								}
						}
						System.out.println("");

					}
				}
			}
		} else if (foundStud == true) {	
			//System.out.println("STUDENT FOUND");
			boolean foundPSDLab = false;
			boolean foundPSDLect = false;
			boolean foundNSLab = false;
			boolean foundNSLect = false;
			
			//System.out.println(user.get(userIndex));
			for (SessionItem s : ses.getSession()) {
				for (int stud=0; stud<user.get(userIndex).getLesson().size(); stud++){
					if (s.getSessionID() == user.get(userIndex).getLesson().get(stud)) {
						String merge = s.getSessionCode() + " " + s.getType();
						if (merge.equals("PSD Lecture")) {
							foundPSDLect = true;
						} else if (merge.equals("PSD Lab")) {
							foundPSDLab = true;
						} else if (merge.equals("NS Lab")) {
							foundNSLab = true;
						} else if (merge.equals("NS Lecture")) {
							foundNSLect = true;
						}

					}
				}
			}

			if (foundPSDLect == true){
				System.out.println("PSD Lect: \tSigned up");
			} else {
				System.out.println("PSD Lect: \tNot yet signed up");
			}
			if (foundPSDLab == true){
				System.out.println("PSD Lab: \tSigned up");
			} else {
				System.out.println("PSD Lab: \tNot yet signed up");
			}
			if (foundNSLect == true){
				System.out.println("NS Lect: \tSigned up");
			} else {
				System.out.println("NS Lect: \tNot yet signed up");
			}
			if (foundNSLab == true){
				System.out.println("NS Lab: \tSigned up");
			} else {
				System.out.println("NS Lab: \tNot yet signed up");
			}
		} else {
			System.exit(0);
		}

	}
}