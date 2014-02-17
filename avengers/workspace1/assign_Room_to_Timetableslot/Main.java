package assign_Room_to_Timetableslot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	private static final String MENU_NAME = "Room Assignment Menu";
	private static ArrayList<Room> listOfRooms = new ArrayList<Room>();
	private static ArrayList<Session> listOfSessions = new ArrayList<Session>();;
	private static Room room1 = new Room(0, "Block E1 Lab", 50);
	private static Room room2 = new Room(1, "Block W1 Lecture Theatre", 50);
	
	private static Session session = new Session(0, -1, "WhatIsThisFor?", "01/01/2014 09:00:00", 
			"01/01/2014 10:00:00", true, -1, -1, -1);
	
	public static void main(String[] args) throws IOException { 

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String getInput;
		
		listOfRooms.add(room1);
		listOfRooms.add(room2);
		listOfSessions.add(session);
		
		try {
			do {
				System.out.println(MENU_NAME);
				System.out.println("1. Assign a room to a timetable slot.");
				System.out.println("B. Back");
				System.out.print(">> ");
				getInput = br.readLine();

				if (getInput.equals("1")) {
					int room_num;
					System.out.println("\n\n" + MENU_NAME);
					
					for (int i = 0; i < listOfRooms.size(); i++) {
						System.out.println("Room number : "+(i+1)+ ". " + listOfRooms.get(i).getVenue());
					}
					System.out.println("Enter room number which will be assign to a timetable.");
					System.out.print(">> ");
					room_num = Integer.parseInt(br.readLine());
					
					System.out.println("Enter start date and time of the session.");
					System.out.println("Format : dd/mm/yyyy hh:mm:ss");
					System.out.print(">> ");
					getInput = br.readLine();
					
					for (int i = 0; i < listOfSessions.size(); i++) {
						if (listOfSessions.get(i).getStarttime().equals(getInput)) {
							listOfSessions.get(i).setRoomID(room_num);
							
							System.out.println("\nSession ID : " + listOfSessions.get(i).getSessionID());
							System.out.println("Name : " + listOfSessions.get(i).getName());
							System.out.println("Session Start Time : " + listOfSessions.get(i).getStarttime());
							System.out.println("Session End Time : " + listOfSessions.get(i).getEndtime());
							System.out.println("Room ID : " + listOfSessions.get(i).getRoomID());
							System.out.println("Successfully added!");
						}
					}
				}
			} while (!getInput.toUpperCase().equals("B"));
		} catch(Exception exception) {
			System.out.println("Invalid input.");
		}
		finally {
			br.close();
		}
	}
}