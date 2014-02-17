package create_Timetable_Slot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import create_Timetable_Slot.Session;

public class Main {
	/*
		int sessionID, int courseID, String name, Date starttime, 
		Date endtime, boolean compulsory, int roomID, int timetableslotID, 
		int GUID
	 */
	private static Session session = new Session(0, -1, "WhatIsThisFor?", "01/01/2014 09:00:00", 
												"01/01/2014 10:00:00", true, -1, -1, -1);
	private static Session session2 = new Session(0, -1, "WhatIsThisFor2?", "01/01/2014 10:00:00", 
			"01/01/2014 11:00:00", false, -1, -1, -1);
	
	private static Timetable_Slot tbSlot = new Timetable_Slot(0, "01/01/2014 09:00:00", "01/01/2014 10:00:00", -1);
	private static Timetable_Slot tbSlot2 = new Timetable_Slot(1, "01/01/2014 10:00:00", "01/01/2014 11:00:00", 0);
	private static Timetable_Slot tbSlot3 = new Timetable_Slot(2, "02/01/2014 09:00:00", "02/01/2014 10:00:00", 0);
	
	private static ArrayList<Session> listOfSessions;
	private static ArrayList<Timetable_Slot> listOfTbSlots;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String getInput;
		
		try {
			do {
				System.out.println("\nTimetable Slot Menu");
				System.out.println("1. Create timetable slot for a session");
				System.out.println("B. Back"); // Quit this menu
				System.out.print(">> ");
				getInput = br.readLine();
				
				if (getInput.equals("1")) {
					// Retrieve from database for any unassigned session
					boolean IsUnAssignAvailable = RetrieveSession();
					if (IsUnAssignAvailable) { 
						getInput = br.readLine();
						
						if (Integer.parseInt(getInput) > 0 || 
							Integer.parseInt(getInput) < (listOfSessions.size() - 1)) {
							// Retrieve timetable slot for display
							RetrieveTimetableSlot(br, getInput);
							
						}
					}
				}
				
			} while (!getInput.toUpperCase().equals("B"));
			
		} catch (Exception e) {
			System.out.println("Invalid input.");
		}
		finally {
			br.close();
		}
	}
	
	private static boolean RetrieveSession() {
		listOfSessions = new ArrayList<Session>();
		
		if(session.getTimetableslotID() == -1) {
			listOfSessions.add(session);
			listOfSessions.add(session2);
		}
		// Database retrieve code here..
		//listOfSessions = SearchFromDB(QueryString);
		
		// Check if there are any records return from the Query
		if (listOfSessions.size() > 0) {
			System.out.println("****** START: List of Unassigned Session(s) ******");
			
			for (int i = 0; i < listOfSessions.size(); i++) {
				System.out.println("\nSession Slot : " + (i + 1));
				System.out.println("Course ID : " + listOfSessions.get(i).getCourseID());
				System.out.println("Start Time : " + listOfSessions.get(i).getStarttime());
				System.out.println("End Time : " + listOfSessions.get(i).getEndtime());
				System.out.println("Attendance : " + listOfSessions.get(i).isCompulsory());
			}
			
			System.out.println("\n****** END: List of Unassigned Session(s) ******");
			System.out.println("Which session slot to add into timetable?");
			System.out.print(">> ");
			
			return true;
		}
		else {
			System.out.println("Sorry. There are no unassigned session at this time.\n");
			
			return false;
		}
	}
	
	private static void RetrieveTimetableSlot(BufferedReader br, String sessionNum) throws IOException {
		// Check whether if a timetable is already created
		// Database retrieve code here..
		//listOfTbSlots = SearchFromDB(QueryString); // IF SEARCH BY DATE & TIME, WILL RETURN ONLY ONE RESULT
		
		String getInput;
		listOfTbSlots = new ArrayList<Timetable_Slot>();
		listOfTbSlots.add(tbSlot);
		listOfTbSlots.add(tbSlot2);
		listOfTbSlots.add(tbSlot3);
		
		// If the timetableslot is already created on that date
		if (listOfTbSlots.size() > 0) {
			
			if (listOfTbSlots.get(0).getSessionID() >= 0) {
				System.out.println("This session slot has already been taken!");
			}
			else {
				timetableSlot_Prompt(sessionNum);
				System.out.println("Enter 'Y' to add session.");
				System.out.println("Enter 'N' to go back.");
				System.out.print("(Y/N)>> ");
				getInput = br.readLine();
				
				if (getInput.toUpperCase().equals("Y")) {
					// Update Query - Update the session with the timetableID
					// Update Query - Update the timetableslot with sessionID
					listOfSessions.get(Integer.parseInt(sessionNum)- 1).setTimetableslotID(listOfTbSlots.get(0).getTimetableslotID());
					listOfTbSlots.get(0).setSessionID(Integer.parseInt(sessionNum) - 1);
				}
			}
		}
		else {
			// Insert a new timetableslot into database 
			System.out.println("No timetable slot was created during the provided date/time!");
			timetableSlot_Prompt(sessionNum);
			System.out.println("Enter 'Y' to add session. (This will a create timetable slot!)");
			System.out.println("Enter 'N' to go back.");
			System.out.print("(Y/N)>> ");
			
			// Update Query - Update the session with the timetableID
			// Update Query - Update the timetableslot with sessionID
			listOfSessions.get(Integer.parseInt(sessionNum)- 1).setTimetableslotID(listOfTbSlots.get(0).getTimetableslotID());
			listOfTbSlots.get(0).setSessionID(Integer.parseInt(sessionNum) - 1);
			listOfTbSlots.get(0).setStarttime(listOfSessions.get(Integer.parseInt(sessionNum)- 1).getStarttime());
			listOfTbSlots.get(0).setEndtime(listOfSessions.get(Integer.parseInt(sessionNum)- 1).getEndtime());
		}
	}

	private static void timetableSlot_Prompt(String getInput) {
		System.out.println("Your session slot...");
		System.out.println("Session ID : " + listOfSessions.get(Integer.parseInt(getInput) - 1).getSessionID());
		System.out.println("Start Time : " + listOfSessions.get(Integer.parseInt(getInput) - 1).getStarttime());
		System.out.println("End Time : " + listOfSessions.get(Integer.parseInt(getInput) - 1).getEndtime());
	}
}
