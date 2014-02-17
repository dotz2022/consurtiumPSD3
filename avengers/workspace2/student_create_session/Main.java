package ui;

import java.util.*;

import SelectSlot.SelectSlot;
import SelectSlot.Session;

public class Main {
	public static void main(String[]args)
	{
		Session student = new Session();
		ArrayList <Integer> sessionID = new ArrayList <Integer>();
		String selectedSessionID;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Type in student ID");
		student.setStudentId(sc.nextLine());
		
		System.out.println("Please type in the session ID eg: 11, 12, 13, 14 ");
		System.out.println();
		System.out.println("Course: PSD 3");
		System.out.println("------------------------------------");
		System.out.println("| Session |    |    |    |    |    |");
		System.out.println("|---------| 1  | 2  | 3  | 4  | 5  |");
		System.out.println("| Choice  |    |    |    |    |    |");
		System.out.println("|---------|------------------------|");
		System.out.println("|    1    | 11 | 12 | 13 | 14 | 15 |");
		System.out.println("|---------|------------------------|");
		System.out.println("|    2    | 21 | 22 | 23 | 24 | 25 |");
		System.out.println("|---------|------------------------|");
		System.out.println("|    3    | 31 | 32 | 33 | 34 | 35 |");
		System.out.println("|---------|-------------------------");
		
		selectedSessionID = sc.nextLine();
		sc.close();
		String[] sessionList = selectedSessionID.split("\\,");

		for(int i = 0; i < sessionList.length ; i++)
		{
			sessionID.add(i, Integer.parseInt(sessionList[i]));
		}
		
		student.setSelectedSessionId(sessionID);
		
		ArrayList <Boolean> result = new SelectSlot().approval(student);
		
		String[] success = {""," are booked successfully."};
		String[] failure = {""," has book up, please rebook the session again"};
		
		for(int i = sessionList.length ; i > 0 ; i--)
		{
			if(result.get(i-1) == true)
			{
				if(success.equals(""))
				{
					success[0] = sessionList[i-1];
				}
				else
				{
					success[0] = sessionList[i-1] + ", " + success[0];
				}
			}
			else
			{
				if(success.equals(""))
				{
					failure[0] = sessionList[i-1];
				}
				else
				{
					failure[0] = sessionList[i-1] + ", " + failure[0];
				}
			}
		}
		
		System.out.println("Congurations, session " + success[0] + success[1]);
		System.out.println("However, session " + failure[0] + failure[1]);
		
	}
}
