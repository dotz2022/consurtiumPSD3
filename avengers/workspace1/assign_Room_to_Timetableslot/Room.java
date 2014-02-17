package assign_Room_to_Timetableslot;

public class Room {
	private int roomID;
	private String venue;
	private int capacity;
	
	public Room(int roomID, String venue, int capacity) {
		setRoomID(roomID);
		setVenue(venue);
		setCapacity(capacity);
	}
	
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
